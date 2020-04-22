package eu.mrndesign.matned.cashMachine.pin;

import eu.mrndesign.matned.cashMachine.card.Card;

public class PinContract {

    public interface View {
        void disableConfirmButton();
        void enableConfirmButton();

        //errors
        void showTooShortPinError();
        void showTooLongPinError();
        void showOnlyDigitsError();
        void hideError();

        //navigation
        void correctPin();
        void wrongPin();
    }

    public interface Presenter {
        void onPinTyping(String pin, char typedChar) throws Exception;
        void onPinConfirmed(String pin) throws Exception;
        Card getCard();
    }
}
