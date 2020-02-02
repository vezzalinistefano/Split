package logic;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

/**
 * Classe che implementa un task di divisione o ricomposizione dei file
 * da mettere in coda
 */
public abstract class Task {
    private JProgressBar progress;

    public void encryptFile(File f, int mode, String keyword) {
        try {
            String cryptFileName = new String();
            if(mode == Cipher.ENCRYPT_MODE)
                cryptFileName = f.getPath() + ".crypt";
            else if(mode == Cipher.DECRYPT_MODE)
                cryptFileName.replace(".crypt", "");

            PBEKeySpec pbeKeySpec = new PBEKeySpec(keyword.toCharArray());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory
                    .getInstance("PBEWithMD5AndTripleDES");
            SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);

            byte[] salt = new byte[8];
            Random random = new Random();
            random.nextBytes(salt);

            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
            cipher.init(mode, secretKey, pbeParameterSpec);

            FileInputStream inputStream = new FileInputStream(f);
            byte[] inputBytes = new byte[(int) f.length()];
            inputStream.read(inputBytes);

            byte[] cryptBuffer = cipher.doFinal(inputBytes);

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
