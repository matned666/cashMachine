package eu.mrndesign.matned.cashMachine;

import eu.mrndesign.matned.cashMachine.card.Card;
import eu.mrndesign.matned.cashMachine.dashboard.DashboardScreen;
import eu.mrndesign.matned.cashMachine.deposit.DepositScreen;
import eu.mrndesign.matned.cashMachine.mainMenu.MenuScreen;
import eu.mrndesign.matned.cashMachine.model.Cash;
import eu.mrndesign.matned.cashMachine.model.CashMachineStorage;
import eu.mrndesign.matned.cashMachine.model.RandomMachineStorage;
import eu.mrndesign.matned.cashMachine.pin.PinScreen;
import eu.mrndesign.matned.cashMachine.pin.changePin.ChangePinScreen;
import eu.mrndesign.matned.cashMachine.startScreen.StartScreen;
import eu.mrndesign.matned.cashMachine.thanks.ThanksScreen;
import eu.mrndesign.matned.cashMachine.wrong.WrongPinScreen;

import java.util.List;

public class ScreensManager implements
        PinScreen.ScreenListener,
        WrongPinScreen.ScreenListener,
        DashboardScreen.ScreenListener,
        ThanksScreen.ScreenListener,
        MenuScreen.ScreenListener,
        ChangePinScreen.ScreenListener,
        StartScreen.ScreenListener,
        DepositScreen.ScreenListener {

    private PinScreen pinScreen;
    private StartScreen startScreen;
    private WrongPinScreen wrongPinScreen;
    private DashboardScreen dashboardScreen;
    private DepositScreen depositScreen;
    private ThanksScreen thanksScreen;
    private MenuScreen menuScreen;
    private ChangePinScreen changePinScreen;

    private CashMachineStorage storage;

    private Card card;

    public void start() {
        startScreen = new StartScreen(this);
        startScreen.show();
        storage = new RandomMachineStorage();
    }

    @Override
    public void onCorrectPin(Card card) {
        this.card = pinScreen.getCard();
        pinScreen.hide();
        showMenu();
    }

    @Override
    public void showMenu() {
        menuScreen = new MenuScreen(this, card);
        menuScreen.show();
    }


    @Override
    public void onWrongPin() {
        pinScreen.hide();
        wrongPinScreen = new WrongPinScreen(this);
        wrongPinScreen.show();
    }

    @Override
    public void onWrongPinConfirm() {
        pinScreen.hide();
        wrongPinScreen.hide();
        System.exit(0);
    }

    @Override
    public void onCashWithdrawal(List<Cash> money) {
        dashboardScreen.hide();
        thanksScreen = new ThanksScreen(this, money);
        thanksScreen.show();
    }

    @Override
    public void onWithdrawalConfirm() {
        thanksScreen.hide();
        menuScreen.show();
    }

    @Override
    public void onWithdraw() {
        menuScreen.hide();
        showDashboard();
    }

    private void showDashboard() {
        dashboardScreen = new DashboardScreen(this, card, storage);
        dashboardScreen.show();
    }

    @Override
    public void onExit() {
        menuScreen.hide();
        System.exit(0);
    }

    @Override
    public void onChangePin() {
        menuScreen.hide();
        changePinScreen = new ChangePinScreen(this, card);
        changePinScreen.show();
    }

    @Override
    public void onWrongChangePin() {
        onWrongPinConfirm();
    }

    @Override
    public void onCorrectChangePin() {
        changePinScreen.hide();
        menuScreen.show();
    }

    @Override
    public void onBackButtonPress() {
        changePinScreen.hide();
        menuScreen.show();
    }

    @Override
    public void onCorrectCardNum() {
        String cardNumber = startScreen.getCardNumber();
        startScreen.hide();
        pinScreen = new PinScreen(this, cardNumber);
        pinScreen.show();

    }

    @Override
    public void onCashDeposit() {
        depositScreen.hide();
        menuScreen.show();
    }

    @Override
    public void onBackFromDeposit() {
        depositScreen.hide();
        menuScreen.show();
    }

    @Override
    public void onDeposit() {
        menuScreen.hide();
        depositScreen = new DepositScreen(this, card, storage);
        depositScreen.show();
    }
}
