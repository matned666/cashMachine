package eu.mrndesign.matned.cashMachine.thanks;

import eu.mrndesign.matned.cashMachine.model.Cash;

import java.util.LinkedList;
import java.util.List;

public class ThanksPresenter implements ThanksContract.Presenter {
    private final ThanksContract.View view;
    private final List<Cash> money;
    private List<Cash> givenMoneyNotes;

    ThanksPresenter(ThanksContract.View view, List<Cash> money) {
        this.view = view;
        this.money = money;
        givenMoneyNotes();
    }

    @Override
    public void init() {
        view.showMeTheMoney(listOfCash());
    }

    @Override
    public void okClicked() {
        view.confirmMessage();
    }

    private String listOfCash(){
        StringBuilder list = new StringBuilder("List of withdrawn notes: ");
        givenMoneyNotes();
        if(givenMoneyNotes.size()>0) {
            for (int i = 0; i < givenMoneyNotes.size(); i++) {
                list.append("[").append(givenMoneyNotes.get(i).getWorth()).append("$] x ").append(getAmountOfEachNote(givenMoneyNotes.get(i)));
                if(i < givenMoneyNotes.size()-1) list.append(",");
            }
        }
        return String.valueOf(list);
    }

    private void givenMoneyNotes() {
        givenMoneyNotes = new LinkedList<>();
        for (Cash el :
                money) {
            if(!givenMoneyNotes.contains(el)){
                givenMoneyNotes.add(el);
            }
        }
    }

    private int getAmountOfEachNote(Cash cashNote){
        int amount = 0;
        for (Cash el : money) {
            if (cashNote == el) amount++;
        }
        return amount;
    }

    public int getGivenAmount(){
        int temp =0;
        for (Cash el : money) {
            temp += el.getWorth();
        }
        return temp;
    }

    public List<Cash> getMoney() {
        return money;
    }
}
