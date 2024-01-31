package at.aau.serg.exercises.cashmachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class CashMachineRandomTest {
    CashMachine cashMachine = new CashMachine();

     @Test
    public void insertCard( ){

         Random random = new Random();
         String randomNumber = String.format("%04d",random.ints(0,999).findFirst().getAsInt());
         cashMachine.insertCard(randomNumber);
         Assertions.assertEquals(CashMachineState.CARD_INSERTED,cashMachine.getCurrentState());
     }
    @Test
    public void inputPINTest() {
         cashMachine.insertCard("12300");
         Random random = new Random();
     String randomNum = String.format("%04d", String.format("%04d", random.nextInt(999+1)));
     cashMachine.inputPIN(randomNum);

        if (cashMachine.getCurrentState() == CashMachineState.PIN_NOT_OK)
            Assertions.assertEquals(CashMachineState.PIN_NOT_OK, cashMachine.getCurrentState());
        else
            Assertions.assertTrue(cashMachine.getCurrentState() == CashMachineState.PIN_OK, "PIN ok");
    }

    @Test
    public void amountTest() {

        cashMachine.insertCard("validCardNumber");
        cashMachine.inputPIN("1337");
        Random r = new Random();
        Integer randomNr = r.ints(0,999).findFirst().getAsInt();
        cashMachine.selectAmount(randomNr);
        if (cashMachine.getCurrentState() == CashMachineState.AMOUNT_VALID)
            Assertions.assertEquals(CashMachineState.AMOUNT_VALID, cashMachine.getCurrentState());
        else
            Assertions.assertTrue(cashMachine.getCurrentState() == CashMachineState.AMOUNT_NOT_VALID, "AMOUNT_NOT_VALID");
    }

    @Test
    public void takeCashTest() {
        cashMachine.insertCard("validCardNumber");
        cashMachine.inputPIN("1337");
        cashMachine.selectAmount(100);
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
        Assertions.assertEquals(CashMachineState.CARD_TAKEN, cashMachine.getCurrentState());
    }

}



