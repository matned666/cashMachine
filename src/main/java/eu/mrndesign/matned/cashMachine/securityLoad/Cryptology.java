package eu.mrndesign.matned.cashMachine.securityLoad;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.KeySpec;

public class Cryptology {

    private static final int KEY_LENGTH = 24;
    private static final String UNICODE_FORMAT = "UTF8";
    private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

    private Cipher cipher;
    private SecretKey key;

    public boolean checkKey(String text, String key) throws Exception {
        String generatedKey = keyGenerator(key);
        String tryMe = decrypt(text, generatedKey);
        return tryMe != null;
    }

    public String encrypt(String unencryptedString, String encryptKey) throws Exception {
        String encryptedString;
        setEncryptionKey(keyGenerator(encryptKey));
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            return null;
        }
        return encryptedString;
    }

    public String decrypt(String encryptedString, String decryptKey) throws Exception {
        String decryptedText;
        setEncryptionKey(keyGenerator(decryptKey));
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = new String(plainText);
        } catch (Exception e) {
            return null;
        }
        return decryptedText;
    }

    private void setEncryptionKey(String secretKey) throws Exception {
        byte[] arrayBytes = secretKey.getBytes(UNICODE_FORMAT);
        KeySpec ks = new DESedeKeySpec(arrayBytes);
        String myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        SecretKeyFactory skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }

    private String keyGenerator(String keyBase) {
        StringBuilder keyBuilder = new StringBuilder(keyBase);
        StringBuilder keyBaseBuilder = new StringBuilder(keyBase);
        for (int i = keyBase.length() - 1; i >= 0; i--) {
            keyBaseBuilder.append(keyBase.charAt(i));
        }
        int counter = 0;
        for (int i = 1; i <= KEY_LENGTH; i++) {
            keyBuilder.append(keyBaseBuilder.charAt(counter));
            counter++;
            if (counter >= keyBaseBuilder.length()) counter = 0;
        }
        return String.valueOf(keyBuilder);
    }

}


