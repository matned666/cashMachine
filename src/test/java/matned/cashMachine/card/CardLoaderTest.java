package matned.cashMachine.card;

import eu.mrndesign.matned.cashMachine.card.Card;
import eu.mrndesign.matned.cashMachine.card.CardLoader;
import eu.mrndesign.matned.cashMachine.securityLoad.FileOperations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import eu.mrndesign.matned.cashMachine.securityLoad.FileOperations;

import static org.junit.jupiter.api.Assertions.*;

class CardLoaderTest {

    private static final String TEST_CARD_NUMBER = "4589619849657531";
    private static final String PATH = "src\\main\\resources\\4589619849657531.card";

    private FileOperations fileOperations;
    private CardLoader loader;
    private String CORRECT_PIN = "1234";


    //Pin is '1234'
    @BeforeEach
    void setup() throws Exception {
        fileOperations = new FileOperations(PATH);
        fileOperations.writeDataToFile("1jhs9NziaDF7hpVDYvDV0W3WveL0pSUC5whcz5rQl/RA=");
        loader = new CardLoader(TEST_CARD_NUMBER);
    }

    @Test
    void correctPinEnteringLoadsCorrectData() throws Exception {
        loader.ENTER_PIN(CORRECT_PIN);

        Card card = loader.getCard();

        assertEquals(card.getBalance(),"2300");
        assertEquals(card.getOwnerName(),"Mateusz");
        assertEquals(card.getOwnerSurname(),"Niedbal");
    }



    @Test
    void incorrectPinEnteringSavesDataWithNextPinAttempt() throws Exception {

        loader.ENTER_PIN("2356");
        loader = new CardLoader(TEST_CARD_NUMBER);
        loader.ENTER_PIN("2056");
        assertEquals(loader.getPinCondition(),3);
        loader = new CardLoader(TEST_CARD_NUMBER);
        loader.ENTER_PIN(CORRECT_PIN);
        Card card = loader.getCard();

        assertEquals(card.getOwnerName(),"Mateusz");
        assertEquals(card.getOwnerSurname(),"Niedbal");
        assertEquals(card.getBalance(),"2300");

    }

    @Test
    void onSecondAttemptCanStillLoadData() throws Exception {

        loader.ENTER_PIN("2356");
        loader = new CardLoader(TEST_CARD_NUMBER);

        loader.ENTER_PIN(CORRECT_PIN);
        assertEquals(loader.getPinCondition(),2);
        Card card = loader.getCard();


        assertEquals(card.getOwnerName(),"Mateusz");
        assertEquals(card.getOwnerSurname(),"Niedbal");
        assertEquals(card.getBalance(),"2300");

    }

   @Test
    void onThirdAttemptCanStillLoadData() throws Exception {

       loader.ENTER_PIN("2356");
       loader = new CardLoader(TEST_CARD_NUMBER);
       loader.ENTER_PIN("0396");
       assertEquals(loader.getPinCondition(),3);
       loader = new CardLoader(TEST_CARD_NUMBER);

       loader.ENTER_PIN(CORRECT_PIN);
       Card card = loader.getCard();

       assertEquals(card.getOwnerName(),"Mateusz");
       assertEquals(card.getOwnerSurname(),"Niedbal");
       assertEquals(card.getBalance(),"2300");
    }

    @Test
    void thirdIncorrectPinEnteringAttemptBlocksCardPermanently() throws Exception {
        loader = new CardLoader(TEST_CARD_NUMBER);
        loader.ENTER_PIN("5634");
        loader = new CardLoader(TEST_CARD_NUMBER);
        loader.ENTER_PIN("1344");
        loader = new CardLoader(TEST_CARD_NUMBER);
        loader.ENTER_PIN("4444");

        CardLoader loader34 = new CardLoader(TEST_CARD_NUMBER);

        Card card12 = loader34.getCard();
        assertThrows(NullPointerException.class, () -> card12.getOwnerName());
        assertNull(card12);


        loader34.ENTER_PIN(CORRECT_PIN);

        Card card = loader34.getCard();

        assertNull(card);

    }


}