package logic;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

public class MergeTask extends Task {
    private File[] files;

    private String keyword;

    public MergeTask(File[] files, String keyword) {
        super();

        this.files = files;
        this.keyword = keyword;
    }

    public void performTask() {
        for (File f : this.files) {
            if (isCrypted(f)) {
                decryptFile(f);
            }
        }
    }

    private boolean isCompressed(File f) {
        return (f.getName().contains(".zip"));
    }

    private boolean isCrypted(File f) {
        return (f.getName().contains(".crypt"));
    }

    private void decryptFile(File file) {
        try {
            String cryptFileName;
            cryptFileName = file.getPath();
            cryptFileName = cryptFileName.replace(".crypt", "");


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

            FileOutputStream fos = new FileOutputStream(cryptFileName);
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
}
