package matned.cashMachine.pin.changePin;

import eu.mrndesign.matned.cashMachine.pin.changePin.ChangePinContract;
import eu.mrndesign.matned.cashMachine.pin.changePin.ChangePinPresenter;
import eu.mrndesign.matned.cashMachine.pin.changePin.ChangePinScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import eu.mrndesign.matned.cashMachine.Check;
import eu.mrndesign.matned.cashMachine.StaticData;
import eu.mrndesign.matned.cashMachine.card.Card;
import eu.mrndesign.matned.cashMachine.securityLoad.Cryptology;
import eu.mrndesign.matned.cashMachine.securityLoad.FileOperations;

import javax.swing.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ChangePinPresenterTest {

    private final String PATH = "src\\main\\resources\\0000000000000000.card";
    private final String PIN = "1234";

    private ChangePinScreen pinscr;
    private ChangePinContract.View view;
    private ChangePinContract.Presenter presenter;
    private JPasswordField newP;

    @BeforeEach
    void setup() throws Exception {

        FileOperations fo = new FileOperations(PATH);
        Cryptology code = new Cryptology();
        Card card = new Card.Builder(PATH)
                .ownerName("Roman")
                .ownerSurname("Pawlak")
                .balance("2000")
                .build();
        fo.writeDataToFile(code.encrypt(card.cardData_forSave(), StaticData.BANK_ENCRYPT_CODE+PIN));
        pinscr = new ChangePinScreen(card);

        view = mock(ChangePinContract.View.class);
        presenter = new ChangePinPresenter(view, pinscr, card);
    }

// TODO ALL TESTS

    @Test
    void changePinCorrectly() throws Exception {
        pinscr.setNewPassword("1234");
        pinscr.setNewPassword("4444");
        pinscr.setNewPasswordConfirm("4444");

        presenter.onPinTyping((char) 10);

        verify(view).enableConfirmButton();
        verify(view).hideError();
    }

    //TODO             @SQL  @JAVA  @GIT  @MAVEN  @


     @Test
    void notCorrectOldPin(){

    }

     @Test
    void notEqualNewPinAndConfirmation(){

    }

     @Test
    void onTypeNonNumericTypeOnOldPinField(){

    }

     @Test
    void onTypeNonNumericTypeOnNewPinField() throws Exception {
         pinscr.setNewPasswordConfirm("44oi");

         presenter.onPinTyping((char) 10);

         verify(view).showOnlyDigitsError();
         verify(view).disableConfirmButton();
    }

     @Test
    void onTypeNonNumericTypeOnConfirmPinField(){

    }

     @Test
    void onTypeDifferentAmountOfTypesThanCorrectLengthOfPin() throws Exception {
         pinscr.setNewPassword("1234");
         pinscr.setNewPassword("4444");
         pinscr.setNewPasswordConfirm("4434");

         System.out.println("new pin: "+Check.getPassword(pinscr.getNewPin()));

         presenter.onPinTyping((char) '1');

         verify(view).onNotEqualPinConfirm();
    }

}