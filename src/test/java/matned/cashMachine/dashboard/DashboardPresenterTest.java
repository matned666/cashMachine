package matned.cashMachine.dashboard;

import eu.mrndesign.matned.cashMachine.dashboard.DashboardContract;
import eu.mrndesign.matned.cashMachine.dashboard.DashboardPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import eu.mrndesign.matned.cashMachine.card.Card;
import eu.mrndesign.matned.cashMachine.model.Cash;
import eu.mrndesign.matned.cashMachine.model.CashMachineStorage;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

class DashboardPresenterTest {

    private DashboardContract.View view;
    private DashboardContract.Presenter presenter;
    private CashMachineStorage machineStorage;

    @BeforeEach
    void setup() {
        view = mock(DashboardContract.View.class);
        machineStorage = mock(CashMachineStorage.class);
        Card card = new Card.Builder("src\\main\\resources\\testcard.card")
                .ownerName("Roman")
                .ownerSurname("Pawlak")
                .balance("2000")
                .build();
        presenter = new DashboardPresenter(view, machineStorage, card);
    }

    @Test
    void withdrawCash() throws Exception{
        when(machineStorage.availableMoney()).thenReturn(
                Arrays.asList(
                        Cash.BANK_NOTE_50,
                        Cash.BANK_NOTE_20,
                        Cash.BANK_NOTE_100,
                        Cash.BANK_NOTE_20,
                        Cash.BANK_NOTE_50,
                        Cash.BANK_NOTE_20,
                        Cash.BANK_NOTE_100,
                        Cash.BANK_NOTE_20));

        presenter.getCash("170");
        verify(view).onWithdrawalConfirm(
                Arrays.asList(
                        Cash.BANK_NOTE_100,
                        Cash.BANK_NOTE_50,
                        Cash.BANK_NOTE_20
                        ));
    }

    @Test
    void withdrawButNotEnoughInTheStorage() throws Exception{
        when(machineStorage.availableMoney()).thenReturn(
                Arrays.asList(
                        Cash.BANK_NOTE_100,
                        Cash.BANK_NOTE_20));

        presenter.getCash("250");
        verify(view).notEnoughNotesError();
    }

    @Test
    void addZeroToWithdraw() throws Exception{
        when(machineStorage.availableMoney()).thenReturn(
                Collections.singletonList(
                        Cash.BANK_NOTE_20));

        presenter.getCash("50");
        verify(view).notEnoughNotesError();
    }

    @Test
    void addNumberNotDivisibleByNotes() throws Exception{
        when(machineStorage.availableMoney()).thenReturn(
                Arrays.asList(
                        Cash.BANK_NOTE_100,
                        Cash.BANK_NOTE_20));

        presenter.getCash("5");
        verify(view).notDivisibleByNotesError();
    }

    @Test
    void add____NumberWithSpaces___() throws Exception{
        when(machineStorage.availableMoney()).thenReturn(
                Collections.singletonList(
                        Cash.BANK_NOTE_20));

        presenter.getCash(" 20   ");

        verify(view).onWithdrawalConfirm(
                Collections.singletonList(
                        Cash.BANK_NOTE_20));
    }

    @Test
    void addIllegalNumberFormat() {
        presenter.onTyping("23r");
        verify(view).notNumericError();
    }

    @Test
    void amountBelowZero() throws Exception{
        presenter.getCash("-300");
        verify(view).notDivisibleByNotesError();
    }
}
