package eu.mrndesign.matned.cashMachine.pin;

import eu.mrndesign.matned.cashMachine.BaseSwingScreen;
import eu.mrndesign.matned.cashMachine.Check;
import eu.mrndesign.matned.cashMachine.ScreensManager;
import eu.mrndesign.matned.cashMachine.card.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PinScreen extends BaseSwingScreen implements PinScreenInterface {
    private final JPasswordField passwordField;
    private final JLabel message;
    private final JButton confirm;

    private final String cardNumber;

    private final PinContract.View view = new PinView(this);
    private final PinContract.Presenter presenter = new PinPresenter(this, view);

    private ScreenListener listener;//

    private Card card;

//    PinScreen() {
//        cardNumber = null;
//        this.listener = new ScreenListener() {
//            @Override
//            public void onCorrectPin(Card card) {
//
//            }
//
//            @Override
//            public void onWrongPin() {
//
//            }
//        };
//        passwordField = new JPasswordField();
//        message = new JLabel();
//        confirm = new JButton();
//        initialize();
//    }


    public PinScreen(String cardNumber) {
        this.cardNumber = cardNumber;
        passwordField = new JPasswordField();
        message = new JLabel();
        confirm = new JButton("Accept");
        initialize();
    }

    public PinScreen(ScreensManager listener, String cardNumber) {
        this.cardNumber = cardNumber;
        this.listener = listener;
        passwordField = new JPasswordField();
        message = new JLabel();
        confirm = new JButton("Accept");
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Insert PIN");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));
        frame.add(new Label("Pin:"));
        frame.add(passwordField);
        frame.add(message);
        confirm.addActionListener(e -> {
            try {
                presenter.onPinConfirmed(Check.getPassword(passwordField));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        confirm.setVisible(false);
        frame.add(confirm);
        passwordField.addKeyListener(new KeyListener() {
                                         @Override
                                         public void keyTyped(KeyEvent e) {
                                         }

                                         @Override
                                         public void keyPressed(KeyEvent e) {
                                         }

                                         @Override
                                         public void keyReleased(KeyEvent e) {
                                             System.out.println(passwordField.getPassword());
                                             try {
                                                 presenter.onPinTyping(Check.getPassword(passwordField), e.getKeyChar());
                                             } catch (Exception ex) {
                                                 ex.printStackTrace();
                                             }
                                         }
                                     }
        );
    }

        @Override
    public void correctPin() {
        listener.onCorrectPin(card);
    }

        @Override
    public void wrongPin() {
        listener.onWrongPin();
    }

    @Override
    public JLabel getMessage() {
        return message;
    }

    @Override
    public JButton getConfirm() {
        return confirm;
    }

    @Override
    public JPasswordField getPasswordField() {
        return passwordField;
    }

    @Override
    public String getCardNumber() {
        return this.cardNumber;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
