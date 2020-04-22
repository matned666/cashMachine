package eu.mrndesign.matned.cashMachine.thanks;

import eu.mrndesign.matned.cashMachine.model.Cash;

import java.util.List;

public class ThanksContract {

    public interface View {
        void showMeTheMoney(String withdrawal);

        void confirmMessage();
    }

    public interface Presenter {
        void init();
        void okClicked();
        List<Cash> getMoney();
        int getGivenAmount();
    }
}
