package eu.mrndesign.matned.cashMachine.startScreen;

public class StartView implements StartContract.View{

    private StartScreen startScreen;

    StartView(StartScreen startScreen) {
        this.startScreen = startScreen;
    }

    @Override
    public void onCardNumberConfirmation() {
        startScreen.correctCardNum();
    }

    @Override
    public void wrongCardNumberError() {
        startScreen.getMessage().setText("Wrong card number");
    }

    @Override
    public void numberFormatError() {
        startScreen.getMessage().setText("Write only digits");
    }

    @Override
    public void cardDataError() {
        startScreen.getMessage().setText("Card data was incorrect");
    }

    @Override
    public void correctCardNumberLength() {
        startScreen.getMessage().setText("OK");
    }

    @Override
    public void onTooLongCardNumberError() {
        startScreen.getMessage().setText("Too long card number");
    }

    @Override
    public void onTooShortCardNumberError() {
        startScreen.getMessage().setText("Too short card number");
    }

    @Override
    public void hideError() {
        startScreen.getMessage().setText("OK");
    }

    @Override
    public void disableConfirmButton() {
        startScreen.getConfirm().setVisible(false);
    }

    @Override
    public void enableConfirmButton() {
        startScreen.getConfirm().setVisible(true);
    }
}
