package eu.mrndesign.matned.cashMachine.pin;

import eu.mrndesign.matned.cashMachine.card.Card;

import javax.swing.*;

public interface PinScreenInterface {
    void correctPin();

    void wrongPin();

    JLabel getMessage();

    JButton getConfirm();

    JPasswordField getPasswordField();

    String getCardNumber();

    interface ScreenListener {
        //info
        void onCorrectPin(Card card);

        void onWrongPin();
    }
}
