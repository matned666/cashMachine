package eu.mrndesign.matned.cashMachine.pin.changePin;

public class ChangePinContract {

    public interface View{
        void onCorrectPin();
        void onWrongOldPin();

        void onNotEqualPinConfirm();
        void emptyOldPinError();
        void emptyNewPinError();

        void disableConfirmButton();
        void enableConfirmButton();

        //errors
        void showTooShortPinError();
        void showTooLongPinError();
        void showOnlyDigitsError();
        void hideError();
        void hideDifferentPinsError();
    }

    public  interface Presenter{
        void onPinTyping(char typedChar) throws Exception;

        void onConfirm() throws Exception;

    }
}
