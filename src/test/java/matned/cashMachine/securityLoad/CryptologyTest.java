package matned.cashMachine.securityLoad;

import eu.mrndesign.matned.cashMachine.securityLoad.Cryptology;
import org.junit.jupiter.api.Test;
import eu.mrndesign.matned.cashMachine.StaticData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CryptologyTest {

    @Test
    void encryptionTest() throws Exception {
        Cryptology crypt = new Cryptology();
        String testText = "Testing Text jgfkhgfjhgfjhgfdjgf76587658";
        String encryptedTxt = crypt.encrypt(testText, "1234");
        String decryptedTxt = crypt.decrypt(encryptedTxt, "1234");

        assertNotEquals(encryptedTxt, testText);
        assertEquals(decryptedTxt, testText);

    }

    @Test
    void encryptingTest1_NoResultTest___CREATES_CARD_DATA() throws Exception {
        final String PIN = "1234";
        final String CARD_NUMBER = "0000000000000000";
        final String TEXT_TO_ENCRYPT = "Jan" + StaticData.SEPARATOR + "Kowalski" + StaticData.SEPARATOR + "2300";
        Cryptology code = new Cryptology();
        String key2 = StaticData.BANK_ENCRYPT_CODE + PIN;
        String encryptedTextLVL1 = code.encrypt(TEXT_TO_ENCRYPT, key2);
        System.out.println("Card num: " + CARD_NUMBER);
        System.out.println("Text to encrypt: " + TEXT_TO_ENCRYPT);
        System.out.println("Encrypted data: " + encryptedTextLVL1);
    }

}