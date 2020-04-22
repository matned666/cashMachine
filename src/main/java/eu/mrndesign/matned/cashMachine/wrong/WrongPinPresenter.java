package eu.mrndesign.matned.cashMachine.wrong;

public class WrongPinPresenter implements WrongPinContract.Presenter {
    private final WrongPinContract.View view;

    WrongPinPresenter(WrongPinContract.View view) {
        this.view = view;
    }

    @Override
    public void okClicked() {
        view.confirmMessage();
    }
}
