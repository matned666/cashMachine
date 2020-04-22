package eu.mrndesign.matned.cashMachine.pin.changePin;

import eu.mrndesign.matned.cashMachine.Check;
import eu.mrndesign.matned.cashMachine.StaticData;
import eu.mrndesign.matned.cashMachine.card.Card;
import eu.mrndesign.matned.cashMachine.securityLoad.Cryptology;
import eu.mrndesign.matned.cashMachine.securityLoad.FileOperations;

import javax.swing.*;

public class ChangePinPresenter implements ChangePinContract.Presenter {

    private ChangePinContract.View view;
    private ChangePinScreen changePinScreen;
    private final Card card;
    private static final int PIN_LENGTH = StaticData.PIN_LENGTH;
    private FileOperations FO;
    private final Cryptology code;

    private JPasswordField oldPin;
    private JPasswordField newPin;
    private JPasswordField newPin2;

    public ChangePinPresenter(ChangePinContract.View view, ChangePinScreen changePinScreen, Card card) {
        this.view = view;
        this.changePinScreen = changePinScreen;
        this.card = card;
        code = new Cryptology();
    }

    @Override
    public void onPinTyping(char typedChar) throws Exception {
        refreshVariables();
        if (onIncorrectPasswordFieldsFill()) {
            if (onAnyIsNOTNumeric()){
                view.showOnlyDigitsError();
                view.disableConfirmButton();
            }else {
                if (areNewPinAndConfirmationNOTEqual()) {
                    view.onNotEqualPinConfirm();
                    view.disableConfirmButton();
                }
                if (onAnyFieldTooShort()) {
                    view.showTooShortPinError();
                    view.disableConfirmButton();
                } else if (onAnyFileldTooLong()) {
                    view.showTooLongPinError();
                    view.disableConfirmButton();
                } else view.hideError();
            }
        } else {
            view.hideDifferentPinsError();
            view.enableConfirmButton();
            view.hideError();
            if (typedChar == 10) onConfirm();
        }
    }


    @Override
    public void onConfirm() throws Exception {
        FO = new FileOperations((card.getCardNumber()));
        if (pinIsCorrect()) {
            FO.writeDataToFile("1" + code.encrypt(card.cardData_forSave(), StaticData.BANK_ENCRYPT_CODE + Check.getPassword(changePinScreen.getNewPin())));
            view.onCorrectPin();
        } else {
            view.onWrongOldPin();
        }
    }

    private boolean onAnyFileldTooLong() {
        return isTooLong(oldPin) || isTooLong(newPin) || isTooLong(newPin2);
    }

    private boolean onAnyFieldTooShort() {
        return isTooShort(oldPin) || isTooShort(newPin) || isTooShort(newPin2);
    }

    private boolean onAnyIsNOTNumeric() { return isNOTNumeric(oldPin) || isNOTNumeric(newPin) || isNOTNumeric(newPin2);}

    private boolean onIncorrectPasswordFieldsFill() {
        return isNOTCorrect(oldPin) || isNOTCorrect(newPin) || isNOTCorrect(newPin2) || areNewPinAndConfirmationNOTEqual()
                || isNOTNumeric(oldPin) || isNOTNumeric(newPin) || isNOTNumeric(newPin2);
    }


    private boolean isTooShort(JPasswordField pas) {
        return Check.getPassword(pas).trim().length() < PIN_LENGTH;
    }

    private boolean isTooLong(JPasswordField pas) {
        return Check.getPassword(pas).trim().length() > PIN_LENGTH;
    }

    private boolean isNOTCorrect(JPasswordField pas) {
        return (pas.getPassword().length != StaticData.PIN_LENGTH);
    }

    private boolean isNOTNumeric(JPasswordField pas) {
        return (!Check.isNumeric(Check.getPassword(pas)));
    }

    private boolean areNewPinAndConfirmationNOTEqual() {
        return !Check.getPassword(newPin).equals(Check.getPassword(newPin2));
    }

    private void refreshVariables() {
        oldPin = changePinScreen.getOldPin();
        newPin = changePinScreen.getNewPin();
        newPin2 = changePinScreen.getNewPinConfirmation();
    }

    private boolean pinIsCorrect() throws Exception {
        return code.checkKey(FO.readDataFromFile().substring(1), StaticData.BANK_ENCRYPT_CODE + Check.getPassword(changePinScreen.getOldPin()));
    }
}
