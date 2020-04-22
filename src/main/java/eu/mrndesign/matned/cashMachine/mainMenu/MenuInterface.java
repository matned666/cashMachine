package eu.mrndesign.matned.cashMachine.mainMenu;

public interface MenuInterface {


    void infoButtonPress();
    void withdrawButtonPress();
    void depositButtonPress();
    void balanceButtonPress();
    void exitButtonPress();
    void changePinButtonPress();
    void dialog(String message);


    interface ScreenListener {
        void onWithdraw();
        void onExit();
        void onChangePin();
        void onDeposit();
        void showMenu();
    }
}
