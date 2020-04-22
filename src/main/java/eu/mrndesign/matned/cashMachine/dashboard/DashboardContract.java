package eu.mrndesign.matned.cashMachine.dashboard;

import eu.mrndesign.matned.cashMachine.model.Cash;

import java.util.List;

public class DashboardContract {

    public interface View {
        void onWithdrawalConfirm(List<Cash> money);

        void onPossibleLowestWithdraw();
        void onNotEnoughBalance();
        DashboardScreen getDashboardScreen();

        void notDivisibleByNotesError();
        void notEnoughNotesError();
        void notNumericError();
        void hideError();

        void enableConfirmButton();
        void disableConfirmButton();
    }

    public interface Presenter {
        void getCash(String value) throws Exception;
        void onTyping(String value);
        List<Cash> getNotesToWithdraw();
    }
}
