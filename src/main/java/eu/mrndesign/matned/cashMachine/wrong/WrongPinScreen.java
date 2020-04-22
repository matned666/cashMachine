package eu.mrndesign.matned.cashMachine.wrong;

import eu.mrndesign.matned.cashMachine.BaseSwingScreen;

import javax.swing.*;
import java.awt.*;

public class WrongPinScreen extends BaseSwingScreen implements WrongPinContract.View {

    private final WrongPinContract.Presenter presenter = new WrongPinPresenter(this);
    private final ScreenListener listener;

    public WrongPinScreen(ScreenListener listener) {
        this.listener = listener;
        frame = new JFrame("ERROR!");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1));
        frame.add(new JLabel("Wrong PIN!" + System.lineSeparator() +
                "Is is RLY your card?!" + System.lineSeparator() +
                "=_=" + System.lineSeparator() +
                "Take it and run away! Bzzzt!"));

        JButton confirm = new JButton("Ok, Ok! Sry ;(");
        confirm.addActionListener(e -> presenter.okClicked());
        frame.add(confirm);
    }

    @Override
    public void confirmMessage() {
        listener.onWrongPinConfirm();
    }

    public interface ScreenListener {
        void onWrongPinConfirm();
    }

}
