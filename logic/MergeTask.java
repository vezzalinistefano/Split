package logic;

import gui.error.ErrorPopupMessage;
import gui.panels.QueueTablePanel;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipInputStream;

/**
 * Il {@link Task} che permette l'unione di più file in un unico file
 */
public class MergeTask extends Task {

    /**
     * Contiene tutte le parti del file
     */
    private ArrayList<File> files;

    /**
     * Il nome che avrà il file finale
     */
    private String mergedFileName;

    /**
     * Il percorso del file finale
     */
    private String mergedFilePath;

    /**
     * Inizializza un MergeTask andando anche a pescare a partire dal primo dato in input
     * tutti i file che comporranno quello finale.
     *
     * @param f          Il primo file di tutti quelli che comporranno il file finale
     * @param keyword    La password utilizzata per decriptare i file
     * @param tablePanel Riferimento ad una {@link QueueTablePanel} utilizzato per l'aggiornamento della vista
     */
    public MergeTask(File f, String keyword, QueueTablePanel tablePanel) {
        super(tablePanel, f);

        this.files = new ArrayList<>();
        this.file = f;
        this.keyword = keyword;

        String fileName = file.getName();

        this.files.add(f);

        boolean zipped = false;
        if (isZipped(f)) {
            fileName = fileName.replace(".zip", "");
            zipped = true;
        }
        boolean encrypted = false;
        if (isEncrypted(f)) {
            fileName = fileName.replace(".crypt", "");
            encrypted = true;
        }

        Pattern pattern = Pattern.compile("(.*)\\.(.*)$");
        Matcher matcher = pattern.matcher(fileName);
        int partIdx = 0;
        if (matcher.find()) {
            // Costruzione del nome e del percorso del nuovo file
            this.mergedFilePath = f.getParent() + File.separator + matcher.group(1);
            this.mergedFileName = matcher.group(1);
            partIdx = Integer.parseInt(matcher.group(2)) + 1;
        }

        // Ricerca di tutte le parti del file
        // I nomi delle altre parti di file vengono costruiti supponendo che se la prima parte è zippata lo siano anche
        // tutte le altre etc.
        boolean keepSearching = true;

        while (keepSearching) {
            String filesPath = "" + this.mergedFilePath + "." + partIdx++;

            if (zipped) filesPath += ".zip";
            if (encrypted) filesPath += ".crypt";

            File tmp = new File(filesPath);
            keepSearching = tmp.exists();

            if (keepSearching) this.files.add(tmp);
        }

    }

    /**
     * Ritorna il nome finale del file
     *
     * @return Il nome finale del file
     */
    public String getFileName() {
        return this.mergedFileName;
    }

    /**
     * Ricompone il file
     */
    @Override
    public void run() {
        int progress = 100 / this.files.size();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer;
        try {
            for (File f : this.files) {
                if (isEncrypted(f)) {
                    try {
                        f = decryptFile(f);
                    }catch (IOException ex) {
                        ErrorPopupMessage.show("" + ex.getMessage(), "File non trovato");
                        continue;
                    }
                    if (f == null)
                        break;
                }
                if (isZipped(f)) {
                    f = unzipFile(f);
                    if (f == null)
                        continue;
                }

                try {
                    FileInputStream fis = new FileInputStream(f);

                    buffer = new byte[(int) f.length()];

                    fis.read(buffer);

                    baos.write(buffer);
                    fis.close();

                    f.delete();
                } catch (FileNotFoundException ex) {
                    ErrorPopupMessage.show("Il file " + f.getName() + " non è stato trovato", "");
                }

                super.setProgressValue(progress);

            }

            if (super.getProgress() < 100) {
                super.setProgressValue(100 - super.getProgress());
            }

            FileOutputStream fos = new FileOutputStream(this.mergedFilePath);
            byte[] totBuffer = baos.toByteArray();
            baos.close();
            fos.write(totBuffer, 0, totBuffer.length);
            fos.close();
        } catch (IOException ex) {
            ErrorPopupMessage.show("Il file " + file.getName() + " non è stato trovato", "");
        }

    }

    /**
     * Controlla se il file è zippato o no.
     *
     * @param f Il file da controllare
     * @return true se il file è zippato, false altrimenti
     */
    private boolean isZipped(File f) {
        return (f.getName().contains(".zip"));
    }

    /**
     * Controlla se il file è criptato o no.
     *
     * @param f Il file da controllare
     * @return true se il file è criptato, false altrimenti
     */
    private boolean isEncrypted(File f) {
        return (f.getName().contains(".crypt"));
    }

    /**
     * Decripta il file e ritorna il file decriptato.
     * <p>
     * Viene letto il salt dal file e viene inizializzato un istanza di {@link Cipher} in modalità DECRYPT, dopodichè se
     * la password è corretta il file viene decriptato a porzioni di 64 bit e viene creato un nuovo file copia di
     * quello dato in input ma decriptato.
     * Il file criptato viene eliminato.
     *
     * @param f Il file da decriptare
     * @return
     */
    private File decryptFile(File f) throws IOException{
        String newFileName;
        newFileName = f.getPath();
        newFileName = newFileName.replace(".crypt", "");

        try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(this.keyword.toCharArray());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory
                    .getInstance("PBEWithMD5AndTripleDES");
            SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);

            FileInputStream fis = new FileInputStream(f);
            byte[] salt = new byte[8];
            fis.read(salt);

            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);

            Cipher cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpec);
            FileOutputStream fos = new FileOutputStream(newFileName);
            byte[] in = new byte[64];
            int read;
            while ((read = fis.read(in)) != -1) {
                byte[] output = cipher.update(in, 0, read);
                if (output != null)
                    fos.write(output);
            }

            byte[] output;
            try {
                output = cipher.doFinal();
            } catch (BadPaddingException ex) {
                fos.close();
                File newFile = new File(newFileName);
                newFile.delete();
                ErrorPopupMessage.show("La password non è valida", "Password errata");
                return null;
            }

            if (output != null)
                fos.write(output);

            fis.close();
            fos.flush();
            fos.close();
        } catch (InvalidKeyException | IllegalBlockSizeException
                | InvalidAlgorithmParameterException | NoSuchAlgorithmException | InvalidKeySpecException
                | NoSuchPaddingException ex) {
            ex.printStackTrace();
        }

        File newFile = new File(newFileName);
        f.delete();
        return newFile;
    }

    /**
     * Decomprime il file.
     * <p>
     * Il buffer del file scelto viene dato in basto ad un {@link ZipInputStream} che va a scrivere su
     * disco il file decompresso.
     * Dopodichè elimina il vecchio file ancora compresso
     *
     * @param f Il file da decomprimere
     * @return Il file decompresso
     */
    private File unzipFile(File f) {
        String newFileName = f.getPath().replace(".zip", "");
        FileInputStream fis;
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(f);
            ZipInputStream zis = new ZipInputStream(fis);
            zis.getNextEntry();
            FileOutputStream fos = new FileOutputStream(newFileName);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException ex) {
            ErrorPopupMessage.show("Il file " + f.getName() + " non è stato trovato", "");
            return null;
        }

        File newFile = new File(newFileName);
        f.delete();
        return newFile;
    }

    /**
     * Ritorna la lunghezza del file.
     *
     * @return La lunghezza del file.
     */
    public int getFilesLength() {
        return this.files.size();
    }
}
