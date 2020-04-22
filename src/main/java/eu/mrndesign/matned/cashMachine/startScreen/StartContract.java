package eu.mrndesign.matned.cashMachine.startScreen;

public class StartContract {
    public interface View {

        void onCardNumberConfirmation();

        void wrongCardNumberError();
        void numberFormatError();
        void cardDataError();
        void correctCardNumberLength();
        void onTooLongCardNumberError();
        void onTooShortCardNumberError();
        void hideError();

        void disableConfirmButton();
        void enableConfirmButton();


    }

    public interface Presenter {
        void onConfirm(String cardNumber) throws Exception;
    }
}
