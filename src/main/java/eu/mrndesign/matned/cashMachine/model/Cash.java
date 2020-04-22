package eu.mrndesign.matned.cashMachine.model;

public enum Cash {
    BANK_NOTE_10(10),
    BANK_NOTE_20(20),
    BANK_NOTE_50(50),
    BANK_NOTE_100(100),
    BANK_NOTE_200(200),
    BANK_NOTE_500(500);
    private final int worth;

    Cash(int worth) {

        this.worth = worth;
    }

    public int getWorth() {
        return worth;
    }

}
