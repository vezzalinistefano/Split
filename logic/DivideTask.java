package logic;

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
 * Classe che estende {@link Task} implementando un task di divisione di un file
 */
public class DivideTask extends Task {
    private File file;

    private double parts;

    private int sizeOfFiles;

    private boolean crypt;

    private boolean compress;

    private String keyword;

    public DivideTask(File file, int parts, int sizeOfFiles, boolean crypt, boolean compress, String keyword) {
        this.file = file;
        this.parts = parts;
        this.crypt = crypt;
        this.compress = compress;
        this.keyword = keyword;

        if (this.parts != -1) {
            this.sizeOfFiles = (int) Math.ceil((double) file.length() / parts);
        } else {
            this.sizeOfFiles = sizeOfFiles * 1024 * 1024;
        }
    }

    public String getFileName() {
        return file.getName();
    }

    public String isCrypt() {
        return (this.crypt ? "Si" : "No");
    }

    public String isCompress() {
        return (this.compress ? "Si" : "No");
    }

    public String getKeyword() {
        return keyword;
    }

    public void performTask() throws IOException {
        String fileName = file.getName();
        byte[] buffer = new byte[sizeOfFiles];

        int partIdx = 1;

        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {
                String filePartName = String.format("%s.%d", fileName, partIdx++);

                File newFile = new File(file.getParent(), filePartName);
                try (FileOutputStream out = new FileOutputStream(newFile)) {
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
                }
            }
        }
    }

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

        } catch (FileNotFoundException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException ex) {

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
