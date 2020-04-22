package eu.mrndesign.matned.cashMachine.pin;

public class PinView implements PinContract.View {

    private PinScreen pinScreen;

    PinView(PinScreen pinScreen) {
        this.pinScreen = pinScreen;
    }

    @Override
    public void disableConfirmButton() {
         pinScreen.getConfirm().setVisible(false);
    }

    @Override
    public void enableConfirmButton() {
        pinScreen.getConfirm().setVisible(true);
    }

    @Override
    public void showTooShortPinError() {
        pinScreen.getMessage().setText("Too short PIN");
    }

    @Override
    public void showTooLongPinError() {
        pinScreen.getMessage().setText("Too long PIN");
    }

    @Override
    public void showOnlyDigitsError() {
        pinScreen.getMessage().setText("Only digits error, try again");
        disableConfirmButton();
    }

    @Override
    public void hideError() {
        pinScreen.getMessage().setText("");
    }

    @Override
    public void correctPin() {
        pinScreen.correctPin();
    }

    @Override
    public void wrongPin() {
        pinScreen.wrongPin();
    }

}
