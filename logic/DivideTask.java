package logic;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Classe che estende {@link Task} implementando un task di divisione di un file
 */
public class DivideTask extends Task {
    private File file;

    private int parts;

    private int sizeOfFiles;

    private boolean crypt;

    private boolean compress;

    private String keyword;

    public DivideTask(File file, int parts, int sizeOfFiles, boolean crypt, boolean compress, String keyword) {
        this.file = file;
        this.parts = parts;
        this.sizeOfFiles = sizeOfFiles;
        this.crypt = crypt;
        this.compress = compress;
        this.keyword = keyword;
    }

    public String getFileName() {
        return file.getName();
    }

    public int getParts() {
        return parts;
    }

    public int getSizeOfFiles() {
        return sizeOfFiles;
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

        byte[] buffer = new byte[sizeOfFiles * 1024 * 1024];

        int partIdx = 1;

        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {
                String filePartName = String.format("%03d-%s", partIdx++, fileName);

                File newFile = new File(file.getParent(), filePartName);
                try (FileOutputStream out = new FileOutputStream(newFile)) {
                    out.write(buffer, 0, bytesAmount);
                    out.close();
                    if (this.compress) {
                        compressFile(newFile.getPath());
                        newFile.delete();
                    }
                    if(this.crypt) {
                        super.encryptFile(newFile, Cipher.ENCRYPT_MODE, this.keyword);
                        newFile.delete();
                    }
                }
            }
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
