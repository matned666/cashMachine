package eu.mrndesign.matned.cashMachine.model;

import java.util.Comparator;

public class CashComparator implements Comparator<Cash> {

    @Override
    public int compare(Cash o1, Cash o2) {
        return Integer.compare(o1.getWorth(), o2.getWorth());
    }
}
