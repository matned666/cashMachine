package eu.mrndesign.matned.cashMachine;//package pl.sda.rafal.zientara.cashMachine;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import pl.sda.rafal.zientara.cashMachine.card.Card;
//import pl.sda.rafal.zientara.cashMachine.pin.changePin.ChangePinContract;
//import pl.sda.rafal.zientara.cashMachine.pin.changePin.ChangePinPresenter;
//import pl.sda.rafal.zientara.cashMachine.pin.changePin.ChangePinScreen;
//import pl.sda.rafal.zientara.cashMachine.securityLoad.Cryptology;
//import pl.sda.rafal.zientara.cashMachine.securityLoad.FileOperations;
//
//import javax.swing.*;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//
//public class as {
//
//    // ZMIANA PINU
//    //  PROBLEM W TESTACH
//
//    /// Tu testy które maj¹ problem
//
//    @BeforeEach
//    void setup() throws Exception {
//
//        FileOperations fo = new FileOperations(PATH);
//        Cryptology code = new Cryptology();
//        Card card = new Card.Builder(PATH)
//                .ownerName("Roman")
//                .ownerSurname("Pawlak")
//                .balance("2000")
//                .build();
//        fo.writeDataToFile(code.encrypt(card.cardData_forSave(), StaticData.BANK_ENCRYPT_CODE+PIN));
//        pinscr = new ChangePinScreen(card);
//
//        view = mock(ChangePinContract.View.class);
//        presenter = new ChangePinPresenter(view, pinscr, card);
//    }
//
//
//    @Test
//    void onTypeDifferentAmountOfTypesThanCorrectLengthOfPin() throws Exception {
//        pinscr.setNewPassword("1234");
//        pinscr.setNewPassword("4444");
//        pinscr.setNewPasswordConfirm("4434");
//
//        System.out.println("new pin: "+Check.getPassword(pinscr.getNewPin()));
//
//        presenter.onPinTyping((char) '1');
//
//        verify(view).onNotEqualPinConfirm();
//    }
//
//
//    //PRESENTER
//
//    private JPasswordField oldPin;
//    private JPasswordField newPin;
//    private JPasswordField newPin2;
//
//    @Override
//    public void onPinTyping(char typedChar) throws Exception {
//        refreshVariables(); // inicjuje passwordFields - trzeba za ka¿dym wciœniêciem pobraæ dane z 3 pól na SCREEN
//        if (onIncorrectPasswordFieldsFill()) {
//            if (onAnyIsNOTNumeric()){
//                view.showOnlyDigitsError();
//                view.disableConfirmButton();
//            }else {
//                if (areNewPinAndConfirmationNOTEqual()) {
//                    view.onNotEqualPinConfirm();
//                    view.disableConfirmButton();
//                }
//                if (onAnyFieldTooShort()) {
//                    view.showTooShortPinError();
//                    view.disableConfirmButton();
//                } else if (onAnyFileldTooLong()) {
//                    view.showTooLongPinError();
//                    view.disableConfirmButton();
//                } else view.hideError();
//            }
//        } else {
//            view.hideDifferentPinsError();
//            view.enableConfirmButton();
//            view.hideError();
//            if (typedChar == 10) onConfirm();
//        }
//    }
//    // tu programik dzia³a super , na ¿ywo
//
//    // SCREEN
//    //KeyListener przypisany do 3 pól w pinem
//    private KeyListener keyListener() {
//        return new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                try {
//                    presenter.onPinTyping(e.getKeyChar());
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        };
//    }
//
//
//
//
//
//
//
//
//    Siema, mam problem  - trzy JPaswordField, do ka¿dego przypiêty wspólny keyListener ,  odnosz¹cy siê do jednej metody onType w prezenterze. A teraz testy - wpisuje statycznie passwordField_1.setText(pin)  ... 2,3 , w wywo³uje presenter.onType(char) i wychodzi mi, ¿e pin nie jest numeryczny chocia¿ wszystkie trzy pola korzystaj¹ce z keyListenera s¹ wype³nione numerycznie... pytanie WHY?
//
//
//
//
//
//
//
//
//
//
//
//}
