package eu.mrndesign.matned.cashMachine.deposit;

import eu.mrndesign.matned.cashMachine.card.Card;
import eu.mrndesign.matned.cashMachine.model.Cash;

import javax.swing.*;
import java.util.List;

public interface DepositScreenInterface {

    void onDepositConfirm();

    JLabel getMessage();
    JLabel getMessage2();

    List<Cash> getNotesToDeposit();

    JButton getConfirm();

    Card getCard();

    void setCard(Card card);

    interface ScreenListener {
        void onCashDeposit();
        void onBackFromDeposit();
    }

}
