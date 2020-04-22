package eu.mrndesign.matned.cashMachine.dashboard;

import eu.mrndesign.matned.cashMachine.BaseSwingScreen;
import eu.mrndesign.matned.cashMachine.card.Card;
import eu.mrndesign.matned.cashMachine.model.Cash;
import eu.mrndesign.matned.cashMachine.model.CashMachineStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;


public class DashboardScreen extends BaseSwingScreen implements DashboardScreenInterface {
    private final JTextField moneyAmount;
    private final JLabel message;
    private final JButton confirm;
    private Card card;

    private final DashboardContract.Presenter presenter;
    private final ScreenListener listener;

    public DashboardScreen(ScreenListener listener, Card card, CashMachineStorage storage) {
        this.listener = listener;
        DashboardContract.View view = new DashboardView(this);
        presenter = new DashboardPresenter(view, storage ,card);
        frame = new JFrame("Insert amount of cash");
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(0, 1));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(new Label("Cash:"));
        moneyAmount = new JPasswordField();
        moneyAmount.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                presenter.onTyping(moneyAmount.getText());
            }
        });
        frame.add(moneyAmount);

        message = new JLabel();
        frame.add(message);

        confirm = new JButton("Confirm");
        confirm.addActionListener(e -> {
            try {
                presenter.getCash(moneyAmount.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        frame.add(confirm);
        confirm.setVisible(true);
    }

    @Override
    public void onWithdrawalConfirm(List<Cash> money) {
        listener.onCashWithdrawal(money);
    }

    @Override
    public JLabel getMessage() {
        return message;
    }

    @Override
    public List<Cash> getNotesToWithdraw() {
        return presenter.getNotesToWithdraw();
    }

    @Override
    public JButton getConfirm() {
        return confirm;
    }

    @Override
    public Card getCard() {
        return card;
    }

    @Override
    public void setCard(Card card) {
        this.card = card;
    }
}
