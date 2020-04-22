package eu.mrndesign.matned.cashMachine.model;


import java.util.List;

public interface CashMachineStorage {

    List<Cash> availableMoney();
    void remove(Cash cash);
    void add(Cash cash);

}
