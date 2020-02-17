package logic;

import gui.panels.QueueTablePanel;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Il {@link Task} che permette la divisione di un file
 */
public class DivideTask extends Task {

    /**
     * Parti in cui verrà diviso il file
     */
    private double parts;

    /**
     * La dimensione massima di ogni file scaturito dalla
     * divisione
     */
    private int sizeOfFiles;

    /**
     * Indica se i file verranno criptati oppure no
     */
    private boolean crypt;

    /**
     * Indica se i file verranno compressi oppure no
     */
    private boolean compress;


    /**
     * Ritorna il numero di parti in cui il file verrà diviso
     *
     * @return il numero di parti in cui il file verrà diviso
     */
    public int getParts() {
        return parts > 0 ? (int) this.parts : 1;
    }

    /**
     * Ritorna la dimensione massima dei file scaturiti dalla divisione
     *
     * @return dimensione massima dei file
     */
    public int getSizeOfFiles() {
        return sizeOfFiles;
    }

    /**
     * Inizializza un DivideTask.
     * <p>
     * Nel caso in cui l'utente inserisca il numero di parti desiderate il metodo va a calcolare la dimensione massima
     * che assumerà ogni parte (l'ultima parte potrebbe essere più piccola).
     * <p>
     * Nel caso in cui l'utente inserisca la dimensione dei file desiderata il metodo va a calcolare in quante parti
     * verrà diviso il file.
     *
     * @param file        Il file scelto per essere diviso
     * @param parts       In quante parti si desidera suddividere il file
     * @param sizeOfFiles La dimensione dei file scaturiti dalla divisione desiderata
     * @param crypt       I file andranno criptati o no
     * @param compress    I file andranno compressi o no
     * @param keyword     La password per la criptazione, nel caso non si voglia criptare il file questo campo risulterà
     *                    vuoto
     * @param tablePanel  Riferimento alla tabella che mostra i task in coda
     */
    public DivideTask(File file, int parts, int sizeOfFiles, boolean crypt, boolean compress, String keyword,
                      QueueTablePanel tablePanel) {
        super(tablePanel, file);
        this.parts = parts;
        this.crypt = crypt;
        this.compress = compress;
        this.keyword = keyword;

        if (this.parts != -1) {
            this.sizeOfFiles = (int) Math.ceil((double) file.length() / parts);
        } else {
            this.sizeOfFiles = sizeOfFiles * 1024 * 1024;
            this.parts = Math.ceil(file.length() / (double) this.sizeOfFiles);
        }
    }

    /**
     * Ritorna una stringa che rappresenta la scelta dell'utente nella criptazione
     * del file
     *
     * @return "Si" o "No" in base al valore di {@link #crypt}
     */
    public String isCrypt() {
        return (this.crypt ? "Si" : "No");
    }

    /**
     * Ritorna una stringa che rappresenta la scelta dell'utente nella compressione
     * del file
     *
     * @return "Si" o "No" in base al valore di {@link #compress}
     */
    public String isCompress() {
        return (this.compress ? "Si" : "No");
    }

    /**
     * Esegue il task di divisione.
     * <p>
     * Per prima cosa si estrae una parte di file di dimensione {@link #sizeOfFiles} e la si mette in un nuovo file,
     * dopodichè in base al valore di {@link #crypt} e {@link #compress} questo nuovo file viene compresso nel metodo
     * {@link #compressFile(String)} o criptato nel metodo {@link #encryptFile(File)}.
     * <p>
     * Per ogni file creato viene vatto avanzare il valore rappresentante il progresso del task.
     */
    public void run() {
        String fileName = file.getName();
        byte[] buffer = new byte[sizeOfFiles];

        int partIdx = 1;

        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);

            float progress;

            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {
                String filePartName = String.format("%s.%d", fileName, partIdx++);

                File newFile = new File(file.getParent(), filePartName);

                FileOutputStream out = new FileOutputStream(newFile);
                out.write(buffer, 0, bytesAmount);
                out.close();
                if (this.compress) {
                    compressFile(newFile.getPath());
                    newFile.delete();
                }
                if (this.crypt) {
                    encryptFile(newFile);
                    newFile.delete();
                }

                progress = 100 / ((float) file.length() / bytesAmount);
                super.setProgressValue(progress);
            }

            if (super.getProgress() < 100) {
                super.setProgressValue(100 - super.getProgress());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Cripta il file.
     * <p>
     * Per prima cosa viene creata un istanza di {@link Cipher} settata per utilizzare l'algoritmo
     * "PBEWithMD5AndTripleDES", dopodichè viene inizializzata per essere usata in modalità ENCRYPT e
     * per usare la password scelta dotata di salt(per proteggersi dagli attacchi a dizionario).
     * Il salt viene poi scritto sul file.
     * Il cipher cripta man mano porzioni di 64 byte del file fino a quando non l'ha criptato tutto.
     *
     * @param f File da criptare
     */
    private void encryptFile(File f) {
        String cryptFileName = f.getPath() + ".crypt";
        try {
            FileInputStream inFile = new FileInputStream(f);
            FileOutputStream outFile = new FileOutputStream(cryptFileName);

            PBEKeySpec pbeKeySpec = new PBEKeySpec(this.keyword.toCharArray());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndTripleDES");
            SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);

            byte[] salt = new byte[8];
            Random random = new Random();
            random.nextBytes(salt);

            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpec);
            outFile.write(salt);

            byte[] input = new byte[64];
            int bytesRead;
            while ((bytesRead = inFile.read(input)) != -1) {
                byte[] output = cipher.update(input, 0, bytesRead);
                if (output != null)
                    outFile.write(output);
            }

            byte[] output = cipher.doFinal();
            if (output != null)
                outFile.write(output);

            inFile.close();
            outFile.flush();
            outFile.close();

        } catch (FileNotFoundException | NoSuchAlgorithmException
                | InvalidKeySpecException | NoSuchPaddingException ex) {

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Comprime il file.
     * <p>
     * Il buffer del file scelto viene dato in basto ad un {@link ZipOutputStream} che va a scrivere su
     * disco il file compresso.
     *
     * @param path Il percorso del file da comprimere
     */
    private void compressFile(String path) {
        File f = new File(path);
        String zipFileName = String.format(path + ".zip");

        try {
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);

            zos.putNextEntry(new ZipEntry(f.getName()));

            byte[] zBuffer = new byte[(int) f.length()];
            FileInputStream fis = new FileInputStream(f);
            fis.read(zBuffer);
            fis.close();

            zos.write(zBuffer, 0, zBuffer.length);
            zos.closeEntry();
            zos.close();
        } catch (FileNotFoundException ex) {
            //TODO future log
        } catch (IOException ex) {
            //TODO future log
        }
    }
}
