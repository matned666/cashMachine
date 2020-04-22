package eu.mrndesign.matned.cashMachine.mainMenu;

import eu.mrndesign.matned.cashMachine.BaseSwingScreen;
import eu.mrndesign.matned.cashMachine.card.Card;

import javax.swing.*;
import java.awt.*;

public class MenuScreen extends BaseSwingScreen implements MenuInterface{


    private final Card card;

    private final MenuInterface.ScreenListener listener;

    public MenuScreen(MenuInterface.ScreenListener listener, Card card) {
        this.card = card;
        this.listener = listener;
        JLabel message = new JLabel(new ImageIcon("src\\main\\resources\\D6uioQEX4AIRiu3.jpg"));
        JButton info = new JButton("Account informations");
        JButton withdraw = new JButton("Withdraw money");
        JButton deposit = new JButton("Deposit money");
        JButton balance = new JButton("Check your ballance");
        JButton exit = new JButton("EXIT");
        JButton changePin = new JButton("Change PIN");

        frame= new JFrame("MAIN MENU");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(7, 1));

        info.addActionListener(e -> infoButtonPress() );
        withdraw.addActionListener(e -> withdrawButtonPress() );
        deposit.addActionListener(e -> depositButtonPress() );
        balance.addActionListener(e -> balanceButtonPress() );
        changePin.addActionListener(e -> changePinButtonPress() );
        exit.addActionListener(e -> exitButtonPress() );

        frame.add(message);
        frame.add(info);
        frame.add(withdraw);
        frame.add(deposit);
        frame.add(balance);
        frame.add(changePin);
        frame.add(exit);
        frame.setVisible(true);

    }

    @Override
    public void depositButtonPress() {
        listener.onDeposit();
    }

    @Override
    public void infoButtonPress() {
        dialog("Your data is: " + card.getOwnerName()+" "+ card.getOwnerSurname());
    }

    @Override
    public void withdrawButtonPress() {
        listener.onWithdraw();
    }

    @Override
    public void balanceButtonPress() {
        dialog("Your actual balance is: " + card.getBalance() + "$");
    }

    @Override
    public void exitButtonPress() {
        listener.onExit();
    }

    @Override
    public void changePinButtonPress() {
        listener.onChangePin();
    }

    @Override
    public void dialog(String message) {
        JDialog dialog = new JDialog(frame);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setLayout(new GridLayout(0,1));
        dialog.setSize(300,300);
        JButton confirm = new JButton("OK");
        confirm.addActionListener(e -> dialog.dispose());
        dialog.add(new JLabel(new ImageIcon("src\\main\\resources\\D6uioQEX4AIRiu3.jpg")));
        dialog.add(new JLabel());
        dialog.add(new JLabel(message));
        dialog.add(new JLabel());
        dialog.add(confirm);
        dialog.setVisible(true);
    }


}


