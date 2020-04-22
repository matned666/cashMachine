package eu.mrndesign.matned.cashMachine.deposit;

import eu.mrndesign.matned.cashMachine.StaticData;
import eu.mrndesign.matned.cashMachine.card.Card;
import eu.mrndesign.matned.cashMachine.model.Cash;
import eu.mrndesign.matned.cashMachine.model.CashMachineStorage;
import eu.mrndesign.matned.cashMachine.securityLoad.Cryptology;
import eu.mrndesign.matned.cashMachine.securityLoad.FileOperations;

import java.util.LinkedList;
import java.util.List;

public class DepositPresenter implements DepositContract.Presenter {


    private final DepositContract.View view;
    private final CashMachineStorage machineStorage;

    private Card card;

    private final String PIN;

    private List<Cash> notesToDeposit;
    private FileOperations fO;
    private final Cryptology code = new Cryptology();
    private LinkedList<Cash> givenMoneyNotes;

    DepositPresenter(DepositContract.View view, CashMachineStorage machineStorage,Card card) {
        this.view = view;
        this.machineStorage = machineStorage;
        this.card = card;
        this.PIN = card.getPin();
        fO = new FileOperations(card.getCardNumber());
        notesToDeposit = new LinkedList<>();
    }

    @Override
    public void deposit() throws Exception {
        machineStorage.availableMoney().addAll(notesToDeposit);
        card.setBalance(String.valueOf(Integer.parseInt(card.getBalance()) + getTotalAmount()));
        fO.writeDataToFile("1"+code.encrypt(card.cardData_forSave(),StaticData.BANK_ENCRYPT_CODE+PIN));
        view.onDepositConfirm();
    }

    @Override
    public void onCashAdd(int value) {
        notesToDeposit.add(getNote(value));
        view.onCashAdd(getTotalAmount(), givenNotes());
    }

    private int getTotalAmount() {
        int temp = 0;
        for (Cash el : notesToDeposit) {
            temp += el.getWorth();
        }
        return temp;
    }

    private Cash getNote(int value) {
        Cash[] cashValues = Cash.values();
        for (Cash el : cashValues) {
            if(value == el.getWorth()) return el;
        }
        return null;
    }

    private String givenNotes() {
        StringBuilder temp = new StringBuilder();
        givenMoneyNotes();
        for (Cash el :
                givenMoneyNotes) {
            temp.append("[").append(el.getWorth()).append("$] x ").append(getAmountOfEachNote(el)).append(" ");
        }
        return String.valueOf(temp);
    }

    private void givenMoneyNotes() {
        givenMoneyNotes = new LinkedList<>();
        for (Cash el :
                notesToDeposit) {
            if(!givenMoneyNotes.contains(el)){
                givenMoneyNotes.add(el);
            }
        }
    }

    private int getAmountOfEachNote(Cash cashNote){
        int amount = 0;
        for (Cash el : notesToDeposit) {
            if (cashNote == el) amount++;
        }
        return amount;
    }
}
