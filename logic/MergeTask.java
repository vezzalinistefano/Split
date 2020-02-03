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
        if (isEncrypted(f)) {
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
        }

        FileOutputStream fos = new FileOutputStream(this.mergedFilePath);
        byte[] totBuffer = baos.toByteArray();
        baos.close();
        fos.write(totBuffer, 0, totBuffer.length);
        fos.close();
    }

    private boolean isZipped(File f) {
        return (f.getName().contains(".zip"));
    }

    private boolean isEncrypted(File f) {
        return (f.getName().contains(".crypt"));
    }

    private File decryptFile(File file) {
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
            cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpec);

            FileOutputStream fos = new FileOutputStream(newFileName);

            byte[] output = cipher.doFinal();
            fos.write(output, 0, output.length);

            inputStream.close();
            fos.close();

            file = new File(newFileName);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | IOException | InvalidKeySpecException
                | InvalidAlgorithmParameterException e) {
            //TODO future log
            System.err.println(e.getMessage());
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return file;
    }

    private File unzipFile(File f) {
        String newFileName = f.getPath().replace(".zip", "");
        FileInputStream fis;
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(f);
            ZipInputStream zis = new ZipInputStream(fis);

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

        f = new File(newFileName);
        return f;
    }
}
