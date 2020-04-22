package eu.mrndesign.matned.cashMachine.card;

import eu.mrndesign.matned.cashMachine.StaticData;

public class Card {

    private String cardNumber;
    private String ownerName;
    private String ownerSurname;
    private String balance;
    private String pin;


    private Card(Builder builder) {
        this.cardNumber = builder.cardNumber;
        this.ownerName = builder.ownerName;
        this.ownerSurname = builder.ownerSurname;
        this.balance = builder.balance;

    }

    public String getCardNumber() {
        return cardNumber;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public String getOwnerSurname() {
        return ownerSurname;
    }
    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }


    public String cardData_forSave(){
        return ownerName +
                StaticData.SEPARATOR +
                ownerSurname +
                StaticData.SEPARATOR +
                balance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }





    public static class Builder {

        private String cardNumber;

        private String ownerName;
        private String ownerSurname;
        private String balance;




        public Builder(String cardNumber) {
            this.cardNumber = cardNumber;
        }



        public Builder ownerName(String ownerName) {
            this.ownerName = ownerName;
            return this;
        }

        public Builder ownerSurname(String ownerSurname) {
            this.ownerSurname = ownerSurname;
            return this;
        }

        public Builder balance(String balance) {
            this.balance = balance;
            return this;
        }

        public Card build(){
            return new Card(this);
        }


    }
}