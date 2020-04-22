package eu.mrndesign.matned.cashMachine.dashboard;

import eu.mrndesign.matned.cashMachine.model.Cash;

import java.util.List;

public class DashboardView implements DashboardContract.View {

    private DashboardScreen dashboardScreen;

    DashboardView(DashboardScreen dashboardScreen) {
        this.dashboardScreen = dashboardScreen;

    }

    @Override
    public void onWithdrawalConfirm(List<Cash> money) {
        dashboardScreen.onWithdrawalConfirm(money);
    }

    @Override
    public void onPossibleLowestWithdraw() {
        dashboardScreen.getMessage().setText("Still too small");
    }

    @Override
    public void onNotEnoughBalance() {
        dashboardScreen.getMessage().setText("Not enough cash on your Account, earn more.");
    }

    @Override
    public void notDivisibleByNotesError() {
        dashboardScreen.getMessage().setText("Not divisible by available notes, try in Afghanistan.");
    }

    @Override
    public void notEnoughNotesError() {
        dashboardScreen.getMessage().setText("Sorry, but there are no available notes, Try in another cash machine");
    }

    @Override
    public void notNumericError() {
        dashboardScreen.getMessage().setText("Not numeric");
        disableConfirmButton();
    }


    @Override
    public void hideError() {
        dashboardScreen.getMessage().setText("");
        enableConfirmButton();
    }

    @Override
    public void enableConfirmButton() {
        dashboardScreen.getConfirm().setVisible(true);
    }

    @Override
    public void disableConfirmButton() {
        dashboardScreen.getConfirm().setVisible(false);
    }

    @Override
    public DashboardScreen getDashboardScreen() {
        return dashboardScreen;
    }
}
