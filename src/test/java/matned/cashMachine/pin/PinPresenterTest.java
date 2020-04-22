package matned.cashMachine.pin;

import eu.mrndesign.matned.cashMachine.pin.PinContract;
import eu.mrndesign.matned.cashMachine.pin.PinPresenter;
import eu.mrndesign.matned.cashMachine.pin.PinScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PinPresenterTest {

    private PinContract.View view;
    private PinContract.Presenter presenter;

    @BeforeEach
    void setup() {
        PinScreen pinscr = new PinScreen("src\\main\\resources\\0000000000000000.card");
        view = mock(PinContract.View.class);
        presenter = new PinPresenter(pinscr, view);
    }

    @Test
    void tooShortPin() throws Exception {
        // given when
        presenter.onPinTyping("1", '1');

        // then
        verify(view).disableConfirmButton();
        verify(view).showTooShortPinError();
    }

    @Test
    void tooLongPin() throws Exception {
        // given when
        presenter.onPinTyping("12345",'5');

        // then
        verify(view).disableConfirmButton();
        verify(view).showTooLongPinError();
    }

    @Test
    void onlyDigitsPin() throws Exception {
        // given when
        presenter.onPinTyping("1abc", 'c');

        // then
        verify(view).disableConfirmButton();
        verify(view).showOnlyDigitsError();
    }

    @Test
    void correctPin_buttonShouldBeEnabled() throws Exception {
        // given when
        presenter.onPinTyping("1234", '4');

        // then
        verify(view).enableConfirmButton();
        verify(view).hideError();
    }

    @Test
    void correctPinWithSpaces_buttonShouldBeEnabled() throws Exception {
        // given when
        presenter.onPinTyping("  1234     ",' ');//hint use .trim()

        // then
        verify(view).enableConfirmButton();
        verify(view).hideError();
    }

    @Test
    void correctPinConfirmed() throws Exception {
        // given when
        presenter.onPinConfirmed("1234");

        // then
        verify(view).correctPin();
    }
    @Test
    void wrongPinConfirmed() throws Exception {
        // given when
        presenter.onPinConfirmed("0000");

        // then
        verify(view).wrongPin();
    }

}