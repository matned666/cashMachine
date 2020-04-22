package eu.mrndesign.matned.cashMachine.pin.changePin;

import eu.mrndesign.matned.cashMachine.BaseSwingScreen;
import eu.mrndesign.matned.cashMachine.card.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChangePinScreen extends BaseSwingScreen implements ChangePinScreenInterface {

    private JButton confirmButton;
    private JPasswordField oldPassword;
    private JPasswordField newPassword;
    private JPasswordField newPasswordConfirm;
    private JLabel message;
    private JLabel message2;
    private ChangePinScreenInterface.ScreenListener listener;
    private Card card;
    private ChangePinContract.Presenter presenter;

    //Constructor for testing
    public ChangePinScreen(Card card) {
        this.card = card;
        initialize();
    }

    public ChangePinScreen(ChangePinScreenInterface.ScreenListener listener, Card card) {
        this.listener = listener;
        this.card = card;
        initialize();
    }

    private void initialize() {
        ChangePinContract.View view = new ChangePinView(this);
        presenter = new ChangePinPresenter(view, this, card);
        confirmButton = new JButton("Confirm");
        JButton backButton = new JButton("BACK");
        oldPassword = new JPasswordField();
        newPassword = new JPasswordField();
        newPasswordConfirm = new JPasswordField();
        message = new JLabel("Change your PIN");
        message2 = new JLabel("Fill all 3 text fields correctly to change your PIN");
        frame = new JFrame("Change Pin");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(11, 1));
        frame.add(new JLabel("Your old password:"));
        frame.add(oldPassword);
        frame.add(new JLabel("Your new password: "));
        frame.add(newPassword);
        frame.add(new JLabel("Confirm your new password: "));
        frame.add(newPasswordConfirm);
        frame.add(new JLabel(""));
        frame.add(confirmButton);
        frame.add(backButton);
        frame.add(message);
        frame.add(message2);
        confirmButton.addActionListener(e -> {
            try {
                presenter.onConfirm();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        backButton.addActionListener(e -> backButtonPress());
        view.disableConfirmButton();
        oldPassword.addKeyListener(keyListener());
        newPassword.addKeyListener(keyListener());
        newPasswordConfirm.addKeyListener(keyListener());
    }

    @Override
    public void confirmButtonPress() {
        JDialog confirmDialog = new JDialog(frame);
        confirmDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        confirmDialog.setSize(400, 200);
        confirmDialog.setLayout(new GridLayout(5, 1));
        confirmDialog.add(new JLabel());
        confirmDialog.add(new JLabel("LOL, The PIN has been changed"));
        confirmDialog.add(new JLabel("Don't forget it !!"));
        confirmDialog.add(new JLabel());
        JButton okButton = new JButton("OK");
        confirmDialog.add(okButton);
        okButton.addActionListener(e -> listener.onCorrectChangePin());
        confirmDialog.setVisible(true);
    }

    @Override
    public void wrongPasswordPress() {
        listener.onWrongChangePin();
    }

    @Override
    public void backButtonPress() {
        listener.onBackButtonPress();
    }

    @Override
    public Card getCard() {
        return this.card;
    }

    @Override
    public JPasswordField getOldPin() {
        return oldPassword;
    }

    @Override
    public JPasswordField getNewPin() {
        return newPassword;
    }

    @Override
    public JPasswordField getNewPinConfirmation() {
        return newPasswordConfirm;
    }

    @Override
    public JButton getAcceptButton() {
        return confirmButton;
    }

    @Override
    public JLabel getMessage1() {
        return message;
    }

    @Override
    public JLabel getMessage2() {
        return message2;
    }

    private KeyListener keyListener() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    presenter.onPinTyping(e.getKeyChar());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }


    // setters for Testing

    public void setOldPassword(String oldPassword) {
        this.oldPassword.setText(oldPassword);
    }

    public void setNewPassword(String newPassword) {
        this.newPassword.setText(newPassword);
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm.setText(newPasswordConfirm);
    }
}
