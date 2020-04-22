package eu.mrndesign.matned.cashMachine.dashboard;

import eu.mrndesign.matned.cashMachine.Check;
import eu.mrndesign.matned.cashMachine.StaticData;
import eu.mrndesign.matned.cashMachine.card.Card;
import eu.mrndesign.matned.cashMachine.model.Cash;
import eu.mrndesign.matned.cashMachine.model.CashMachineStorage;
import eu.mrndesign.matned.cashMachine.securityLoad.Cryptology;
import eu.mrndesign.matned.cashMachine.securityLoad.FileOperations;

import java.util.*;
import java.util.stream.Collectors;

public class DashboardPresenter implements DashboardContract.Presenter {



    private final DashboardContract.View view;
    private final CashMachineStorage machineStorage;

    private Card card;

    private List<Cash> notesToWithdraw;
    private List<Cash> tempNotesInCashMachine;
    private  List<Cash> availableMoney;
    private final FileOperations fO;
    private final Cryptology code = new Cryptology();

    public DashboardPresenter(DashboardContract.View view, CashMachineStorage machineStorage, Card card) {
        this.view = view;
        this.machineStorage = machineStorage;
        this.card = card;
        fO = new FileOperations(card.getCardNumber());
    }


    @Override
    public void getCash(String value) throws Exception {
        tempNotesInCashMachine();
        availableMoney_Sort();
        int actualBalance = Integer.parseInt(card.getBalance().trim());
        if(Check.isNumeric(value)) {
            int amount = Integer.parseInt(value.trim());
            if (isPossibleWithdraw(amount)  && amount <= getMoneyInCashMachine()) {
                notesToWithdraw = new LinkedList<>();
                actualBalance -= amount;
                if(actualBalance >= 0) {
                    cashWithdraw(amount);
                    System.out.println(card.getBalance());
                    card.setBalance(String.valueOf(actualBalance));
                    fO.writeDataToFile("1"+code.encrypt(card.cardData_forSave(), StaticData.BANK_ENCRYPT_CODE+card.getPin()));
                    view.onWithdrawalConfirm(notesToWithdraw);
                }else{
                    view.onNotEnoughBalance();
                }
            } else if (!isPossibleWithdraw(amount)  && amount <= getMoneyInCashMachine()) {
                view.notDivisibleByNotesError();
            } else {
                view.notEnoughNotesError();
            }
        }else view.notNumericError();
    }

    private void availableMoney_Sort() {
        availableMoney = machineStorage.availableMoney()
                .stream()
                .sorted(Comparator.comparing(Cash::getWorth).reversed())
                .collect(Collectors.toList());
    }

    private void tempNotesInCashMachine() {
        tempNotesInCashMachine = new LinkedList<>();
        tempNotesInCashMachine.addAll(machineStorage.availableMoney());
    }

    @Override
    public void onTyping(String value) {
        if(!Check.isNumeric(value)){
            view.notNumericError();
        }else{
            view.hideError();
            int amount = Integer.parseInt(value);
            if (amount < getLowestPossibleValue()) {
                view.onPossibleLowestWithdraw();
            }
        }
    }

    private int getLowestPossibleValue() {
        return Arrays.stream(Cash.values()).mapToInt(Cash::getWorth).min().orElseThrow(NoSuchElementException::new);
    }

    private boolean isPossibleWithdraw(int totalValue){

        for (Cash value : tempNotesInCashMachine) {
            if (totalValue >= value.getWorth() && machineStorage.availableMoney().contains(value)) {
                totalValue -= value.getWorth();
                tempNotesInCashMachine.remove(value);
                return isPossibleWithdraw(totalValue);
            }
        }
        return totalValue == 0;
    }

    private int getMoneyInCashMachine(){
        int tempValue=0;
        for (Cash value : machineStorage.availableMoney()) {
                tempValue += value.getWorth();
        }
        return tempValue;
    }

    private boolean cashWithdraw(int totalValue) {
        Cash[] cash = Cash.values();
        for (int i = cash.length-1; i >= 0; i--) {
            if (totalValue >= cash[i].getWorth() && availableMoney.contains(cash[i])) {
                if (totalValue == 0) break;
                totalValue -= cash[i].getWorth();
                notesToWithdraw.add(cash[i]);
                availableMoney.remove(cash[i]);
                machineStorage.remove(cash[i]);
                return cashWithdraw(totalValue);
            }

        }
        return false;
    }

    public List<Cash> getNotesToWithdraw() {
        return notesToWithdraw;
    }

    private int getTotalGivenMoney(){
        int temp = 0;
        for (Cash el :  notesToWithdraw) {
            temp += el.getWorth();
        }
         return  temp;
    }



}
