package eu.mrndesign.matned.cashMachine.deposit;

import eu.mrndesign.matned.cashMachine.BaseSwingScreen;
import eu.mrndesign.matned.cashMachine.card.Card;
import eu.mrndesign.matned.cashMachine.model.Cash;
import eu.mrndesign.matned.cashMachine.model.CashMachineStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class DepositScreen extends BaseSwingScreen implements DepositScreenInterface {

    private final JLabel message;
    private final JLabel message2;

    private Card card;

    private final DepositContract.Presenter presenter;
    private final ScreenListener listener;

    // this is a massacre constructor and it will be like that :D    ---> I know , it's too long, but I will to do android
    public DepositScreen(ScreenListener listener, Card card, CashMachineStorage storage) {
        this.listener = listener;
        DepositContract.View view = new DepositView(this);
        presenter = new DepositPresenter(view, storage,card);
        frame = new JFrame("DEPOSIT");
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(0, 1));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton note500 = new JButton("Add bank note: [500$]");
        JButton note200 = new JButton("Add bank note: [200$]");
        JButton note100 = new JButton("Add bank note: [100$]");
        JButton note50 = new JButton("Add bank note: [50$]");
        JButton note20 = new JButton("Add bank note: [20$]");
        JButton note10 = new JButton("Add bank note: [10$]");

        frame.add(new Label("Cash:"));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(x -> listener.onBackFromDeposit());

        message = new JLabel("Give me some cash");
        message2 = new JLabel("Given notes: ");
        frame.add(message);
        frame.add(message2);
        frame.add(note500);
        frame.add(note200);
        frame.add(note100);
        frame.add(note50);
        frame.add(note20);
        frame.add(note10);
        frame.add(new JLabel("Confirm deposit or cancel:"));

        note500.addActionListener(action(500));
        note200.addActionListener(action(200));
        note100.addActionListener(action(100));
        note50.addActionListener(action(50));
        note20.addActionListener(action(20));
        note10.addActionListener(action(10));

        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(e -> {
            try {
                presenter.deposit();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            onDepositConfirm();
        });
        frame.add(confirm);
        frame.add(backButton);
        confirm.setVisible(true);
    }

    private ActionListener action(int value){
        return e -> presenter.onCashAdd(value);
    }



    @Override
    public void onDepositConfirm() {
        listener.onCashDeposit();
    }

    @Override
    public JLabel getMessage() {
        return message;
    }

   @Override
    public JLabel getMessage2() {
        return message2;
    }

    @Override
    public List<Cash> getNotesToDeposit() {
        return null;
    }

    @Override
    public JButton getConfirm() {
        return null;
    }

    @Override
    public Card getCard() {
        return null;
    }

    @Override
    public void setCard(Card card) {

    }
}
