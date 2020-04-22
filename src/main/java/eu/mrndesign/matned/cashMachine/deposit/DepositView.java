package eu.mrndesign.matned.cashMachine.deposit;

public class DepositView implements DepositContract.View {

    private DepositScreen depositScreen;

    DepositView(DepositScreen depositScreen) {
        this.depositScreen = depositScreen;
    }

    @Override
    public void onDepositConfirm() {

        depositScreen.onDepositConfirm();
    }

    @Override
    public void onCashAdd(int amount, String givenNotesMessage) {
        depositScreen.getMessage().setText("Actually added money: "+amount);
        depositScreen.getMessage2().setText("Actually added notes: "+givenNotesMessage);
    }
}
