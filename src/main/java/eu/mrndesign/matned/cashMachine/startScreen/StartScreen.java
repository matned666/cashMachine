package eu.mrndesign.matned.cashMachine.startScreen;

import eu.mrndesign.matned.cashMachine.BaseSwingScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;

public class StartScreen extends BaseSwingScreen implements StartScreenInterface {

    private final ScreenListener listener;

    private final StartContract.View view = new StartView(this);
    private final StartContract.Presenter presenter = new StartPresenter(this, view);
    private final JButton confirm;
    private final JLabel message;
    private String cardNumber;

    public StartScreen(ScreenListener listener) {
        this.listener = listener;
        message = new JLabel("Welcome");
        confirm = new JButton("Accept");
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Cash machine");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1));
        JLabel image = new JLabel(new ImageIcon("src\\main\\resources\\D6uioQEX4AIRiu3.jpg"));
        frame.add(image);
        frame.add(new Label("Enter your card number:"));
        frame.add(message);
        JTextArea dropFileArea = new JTextArea("Drop your card file here:");
        dropFileArea.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    java.util.List<File> droppedFiles;
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    droppedFiles = (java.util.List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    if (droppedFiles.size() > 1) {
                        droppedFiles.clear();
                    }
                    cardNumber = droppedFiles.get(0).getAbsolutePath();
                    presenter.onConfirm(cardNumber);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(dropFileArea);
        confirm.setVisible(false);
        frame.add(confirm);
    }

    @Override
    public void correctCardNum() {
        listener.onCorrectCardNum();
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
    public String getCardNumber() {
        return this.cardNumber;
    }


}
