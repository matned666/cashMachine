package eu.mrndesign.matned.cashMachine.dashboard;

import eu.mrndesign.matned.cashMachine.card.Card;
import eu.mrndesign.matned.cashMachine.model.Cash;

import javax.swing.*;
import java.util.List;

public interface DashboardScreenInterface {
    void onWithdrawalConfirm(List<Cash> money);

    JLabel getMessage();

    List<Cash> getNotesToWithdraw();

    JButton getConfirm();

    Card getCard();

    void setCard(Card card);

    interface ScreenListener {
        void onCashWithdrawal(List<Cash> money);
    }
}
