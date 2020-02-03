package logic;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MergeTask extends Task {
    private File[] files;

    private String keyword;

    private String mergedFileName;

    private String mergedFilePath;

    public MergeTask(File[] files, String keyword) {
        super();

        this.files = files;
        this.keyword = keyword;

        File f = files[0];
        String fileName = f.getName();

        if (isZipped(f)) {
            fileName = fileName.replace(".zip", "");
        }
        if (isCrypted(f)) {
            fileName = fileName.replace(".crypt", "");
        }

        Pattern pattern = Pattern.compile("(?![0-9])(?!-).*$");
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            this.mergedFilePath = f.getParent() + File.separator + matcher.group(0);
            this.mergedFileName = matcher.group(0);
        }
    }

    public String getFileName() {
        return this.mergedFileName;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void performTask() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer;
        for (File f : this.files) {
            if (isCrypted(f)) {
                decryptFile(f);
            }
            if (isZipped(f)) {
                unzipFile(f);
            }
            FileInputStream fis = new FileInputStream(f);

            buffer = new byte[(int) f.length()];

            fis.read(buffer);

            baos.write(buffer);
        }

        FileOutputStream fos = new FileOutputStream(this.mergedFilePath);
        byte[] totBuffer = baos.toByteArray();
        fos.write(totBuffer, 0, totBuffer.length);
    }

    private boolean isZipped(File f) {
        return (f.getName().contains(".zip"));
    }

    private boolean isCrypted(File f) {
        return (f.getName().contains(".crypt"));
    }

    private void decryptFile(File file) {
        try {
            String newFileName;
            newFileName = file.getPath();
            newFileName = newFileName.replace(".crypt", "");


            PBEKeySpec pbeKeySpec = new PBEKeySpec(keyword.toCharArray());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory
                    .getInstance("PBEWithMD5AndTripleDES");
            SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);

            FileInputStream inputStream = new FileInputStream(file);
            byte[] salt = new byte[8];
            inputStream.read(salt);

            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);

            Cipher cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpec);

            byte[] cryptBuffer = cipher.doFinal();

            FileOutputStream fos = new FileOutputStream(newFileName);
            fos.write(cryptBuffer, 0, cryptBuffer.length);

            inputStream.close();
            fos.close();

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | IOException | BadPaddingException | InvalidKeySpecException
                | InvalidAlgorithmParameterException e) {
            //TODO future log
            System.err.println(e.getMessage());
        }
    }

    private void unzipFile(File f) {
        String newFileName = f.getPath().replace(".zip", "");
        FileInputStream fis;
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(f);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();

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
    }

    private int getTotalSize() {
        long totSize = 0;
        for (File f : this.files) {
            totSize += f.length();
        }
        return (int) totSize;
    }
}
