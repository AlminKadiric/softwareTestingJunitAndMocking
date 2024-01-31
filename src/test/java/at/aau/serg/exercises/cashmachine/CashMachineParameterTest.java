package at.aau.serg.exercises.cashmachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CashMachineParameterTest {
    CashMachine cashMachine = new CashMachine();

    @ParameterizedTest
    @ValueSource(strings = {"12300", "13400", "10000"})
    public void insertCard(String cardNr) {
        cashMachine.insertCard(cardNr);
        Assertions.assertEquals(CashMachineState.CARD_INSERTED, cashMachine.getCurrentState());
    }

    @ParameterizedTest
    @ValueSource(strings = {"12313", "13413", "10013"})
    public void insertCard_returnsFalse(String cardNr) {
        cashMachine.insertCard(cardNr);
        Assertions.assertNotEquals(CashMachineState.CARD_INSERTED, cashMachine.getCurrentState());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0000", "1337", "1234"}) // six numbers
    public void inputPINTest(String pin) {
        cashMachine.insertCard("12300");
        cashMachine.inputPIN(pin);
        if (cashMachine.getCurrentState() == CashMachineState.PIN_NOT_OK)
            Assertions.assertEquals(CashMachineState.PIN_NOT_OK, cashMachine.getCurrentState());
        else
            Assertions.assertTrue(cashMachine.getCurrentState() == CashMachineState.PIN_OK, "PIN ok");

    }

    @ParameterizedTest
    @ValueSource(ints = {100, 1200, 152}) // six numbers
    public void amountTest(Integer amount) {
        cashMachine.insertCard("validCardNumber");
        cashMachine.inputPIN("1337");
        cashMachine.selectAmount(amount);

        if (cashMachine.getCurrentState() == CashMachineState.AMOUNT_VALID)
            Assertions.assertEquals(CashMachineState.AMOUNT_VALID, cashMachine.getCurrentState());
        else
            Assertions.assertTrue(cashMachine.getCurrentState() == CashMachineState.AMOUNT_NOT_VALID, "AMOUNT_NOT_VALID");

    }

    @ParameterizedTest
    @ValueSource(ints = {100, 1200, 50}) // six numbers
    public void testTakeCash(Integer amount) {
        cashMachine.insertCard("validCardNumber");
        cashMachine.inputPIN("1337");
        cashMachine.selectAmount(amount);
        cashMachine.takeCash();
        Assertions.assertEquals(CashMachineState.CASH_GIVEN, cashMachine.getCurrentState());
    }


    @Test
    public void removeCardTest() {
        cashMachine.insertCard("validCardNumber");
        cashMachine.inputPIN("1337");
        cashMachine.selectAmount(100);
        cashMachine.takeCash();
        cashMachine.removeCard();
        Assertions.assertEquals(CashMachineState.CARD_TAKEN,cashMachine.getCurrentState());
    }


}
