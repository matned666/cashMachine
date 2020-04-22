package eu.mrndesign.matned.cashMachine.deposit;

public class DepositContract {

    public interface View {
        void onDepositConfirm();

        void onCashAdd(int amount, String givenNotesMessage);
    }

    public interface Presenter {
        void deposit() throws Exception;
        void onCashAdd(int value);
    }

}
