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

        // Adds bytes to key until 16 bytes (characters)
        if (key.length() < 16) {
            addMissingBytes();
        }
        // Creates a secret key object based on provided key
        Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
        String message = "";

        try {

            // Creates a cryptographic cipher in charge of the encryption/decryption using AES encryption algorithm
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cryptType, secretKey);

            // Encrypts/Decrypts (encrypted data is encoded using Base64 so that it can be saved onto .txt file)
            if (cryptType == Cipher.ENCRYPT_MODE) {
                byte[] byteCode = cipher.doFinal(phrase.getBytes());
                message = Base64.getEncoder().encodeToString(byteCode);
            }
            else if (cryptType == Cipher.DECRYPT_MODE) {
                byte [] byteCode = Base64.getDecoder().decode(phrase);
                message = new String(cipher.doFinal(byteCode));
            }

            return message;

        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | IllegalArgumentException e) {
            e.printStackTrace();
            return "_corrupt"; // Return value if there is error with cryptography
        }
    }
    private static void addMissingBytes() {
        int missingBytes = 16 - key.length();

        for (int i = 0; i < missingBytes; i++) {
            key = key.concat("_");
        }
    }
}
