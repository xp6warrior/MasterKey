package Core;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public abstract class Cryptography {
    private static String key;

    public static void setKey(String k) {
        key = k;
    }

    public static String doCryptography(String phrase, int cryptType) {
        if (key.length() < 16) {
            int missingBytes = 16 - key.length();

            for (int i = 0; i < missingBytes; i++) {
                key = key.concat("_");
            }
        }
        Key secretKey = new SecretKeySpec(key.getBytes(), "AES");

        String message = "corrupt";
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cryptType, secretKey);

            if (cryptType == Cipher.ENCRYPT_MODE) {
                byte[] byteCode = cipher.doFinal(phrase.getBytes());
                message = Base64.getEncoder().encodeToString(byteCode);
            }
            else if (cryptType == Cipher.DECRYPT_MODE) {
                byte [] byteCode = Base64.getDecoder().decode(phrase);
                message = new String(cipher.doFinal(byteCode));
            }

        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return message;
    }

    public static boolean keyTest(String k) {
        key = k;

        String samplePassword = MUserData.hasData().split(" ")[0];
        return !doCryptography(samplePassword, Cipher.DECRYPT_MODE).equals("corrupt");
    }
}
