package eu.mrndesign.matned.cashMachine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class
RandomMachineStorage implements CashMachineStorage {


    private List<Cash> money;

    public RandomMachineStorage() {
        money = new ArrayList<>();
        initRandomCash();
    }

    private void initRandomCash() {
        Cash[] values = Cash.values();
        Random random = new Random();
        int bankNoteCount = random.nextInt(100);
        for (int i = 0; i <bankNoteCount; i++) {
            Cash cash = values[random.nextInt(values.length)];
            money.add(cash);
        }
    }

    @Override
    public List<Cash> availableMoney() {
        return money;
    }

    @Override
    public void remove(Cash cash) {
        money.remove(cash);
    }

    @Override
    public void add(Cash cash) {
        money.add(cash);
    }
}
