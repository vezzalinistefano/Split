package logic;

import gui.queueTable.QueueTablePanel;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.swing.*;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipInputStream;

public class MergeTask extends Task {
    private File firstFile;

    private ArrayList<File> files;

    private String keyword;

    private String mergedFileName;

    private String mergedFilePath;

    private boolean zipped = false;

    private boolean encrypted = false;

    public MergeTask(File f, String keyword, QueueTablePanel tablePanel) {
        super(tablePanel);

        this.files = new ArrayList<>();
        this.firstFile = f;
        this.keyword = keyword;

        String fileName = firstFile.getName();

        this.files.add(f);

        if (isZipped(f)) {
            fileName = fileName.replace(".zip", "");
            this.zipped = true;
        }
        if (isEncrypted(f)) {
            fileName = fileName.replace(".crypt", "");
            this.encrypted = true;
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

            if (this.zipped) filesPath += ".zip";
            if (this.encrypted) filesPath += ".crypt";

            File tmp = new File(filesPath);
            keepSearching = tmp.exists();

            if (keepSearching) this.files.add(tmp);
        }

    }

    public String getFileName() {
        return this.mergedFileName;
    }

    public String getKeyword() {
        return this.keyword;
    }

    @Override
    public void run() {
        //TODO avanzamento progreso anche per merge
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer;
        try {
            for (File f : this.files) {
                if (isEncrypted(f)) {
                    f = decryptFile(f);
                }
                if (isZipped(f)) {
                    f = unzipFile(f);
                }

                FileInputStream fis = new FileInputStream(f);

                buffer = new byte[(int) f.length()];

                fis.read(buffer);

                baos.write(buffer);
                fis.close();

                f.delete();

            }

            FileOutputStream fos = new FileOutputStream(this.mergedFilePath);
            byte[] totBuffer = baos.toByteArray();
            baos.close();
            fos.write(totBuffer, 0, totBuffer.length);
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean isZipped(File f) {
        return (f.getName().contains(".zip"));
    }

    private boolean isEncrypted(File f) {
        return (f.getName().contains(".crypt"));
    }

    private File decryptFile(File f) {
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

            byte[] output = cipher.doFinal();
            if (output != null)
                fos.write(output);

            fis.close();
            fos.flush();
            fos.close();
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException ex) {

        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException
                 | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        File newFile = new File(newFileName);
        f.delete();
        return newFile;
    }

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
            ex.printStackTrace();
        }

        File newFile = new File(newFileName);
        f.delete();
        return newFile;
    }
}
