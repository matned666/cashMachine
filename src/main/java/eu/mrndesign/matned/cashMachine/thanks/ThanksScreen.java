package eu.mrndesign.matned.cashMachine.thanks;

import eu.mrndesign.matned.cashMachine.BaseSwingScreen;
import eu.mrndesign.matned.cashMachine.model.Cash;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class ThanksScreen extends BaseSwingScreen implements ThanksContract.View {
    private final JLabel message;
    private final JLabel moneyInfo;
    private final ScreenListener listener;
    private final ThanksContract.Presenter presenter;

    public ThanksScreen(ScreenListener listener, List<Cash> money) {
        presenter = new ThanksPresenter(this, money);
        this.listener = listener;
        //frame ma dostep protected
        frame = new JFrame("Money money money!");
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(0, 1));

        message = new JLabel("There is Your cash!" + System.lineSeparator() +
                " Don't spend it too fast! Bzzzt!");
        frame.add(message);

        moneyInfo = new JLabel();
        frame.add(moneyInfo);

        JButton confirm = new JButton("Ok Thanks! :D");
        confirm.addActionListener(e -> presenter.okClicked());
        frame.add(confirm);
        presenter.init();

    }
    @Override
    public void confirmMessage() {
        listener.onWithdrawalConfirm();
    }

    @Override
    public void showMeTheMoney(String message) {
        StringBuilder builder;
        if(presenter.getMoney().size() > 0) {
            builder = new StringBuilder("There is Your cash!" + System.lineSeparator() +
                    "Don't spend it too fast! Bzzzt! "+presenter.getGivenAmount());
            moneyInfo.setText(message);
        }else builder = new StringBuilder("Nothing to withdraw...");
        this.message.setText(String.valueOf(builder));
    }

    public interface ScreenListener {
        void onWithdrawalConfirm();
    }

}
