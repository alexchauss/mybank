package fr.mybank.myapi;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;


public class AccountTest {

	private static Account accountTest;
	private static Account accountTest2;

	// Given un premier compte sur lequel :
	// je dépose 200, puis 48
	// je retire 220

	// et un second compte sur lequel :
	// je dépose 47, puis 201
	// je retire 220, puis 50
	// je dépose 3400.90

    @BeforeClass
    public static void setUp() throws NullAmountException {
    	accountTest = new Account("Alex",123456789L);
    	accountTest.deposit(new BigDecimal(200));
	    accountTest.deposit(new BigDecimal(48));
	    accountTest.withdraw(new BigDecimal(220));

	    accountTest2 = new Account("Marcel",987654321L);
	    accountTest2.deposit(new BigDecimal(47));
	    accountTest2.deposit(new BigDecimal(201));
	    accountTest2.withdraw(new BigDecimal(220));
	    accountTest2.withdraw(new BigDecimal(50));
	    accountTest2.deposit(new BigDecimal(3400.90));
    }

    // Then l'historique d'opérations du premier compte n'est pas vide
	@Test
	public void accountShouldGetOperations() throws NullAmountException {
		assertTrue(!accountTest.getOperations().isEmpty());
	}

	// Then le balance final du premier compte vaut 28
	@Test
    public void operationsShouldReturn28() throws NullAmountException, UnsufficientFundsException {
        assertEquals(new BigDecimal(28), accountTest.getFinalBalance());
    }

	// When je dépose 0 sur le premier compte
	// Then une exception NullAmountException est levée
	@Test(expected = NullAmountException.class)
    public void depositShouldReturnException() throws NullAmountException {
		accountTest.deposit(new BigDecimal(0));
    }

	// When je retire 400 sur le second compte
	// Then une exception UnsufficientFundsException est levée
	@Test(expected = UnsufficientFundsException.class)
    public void withdrawalShouldReturnException() throws UnsufficientFundsException, NullAmountException {
		accountTest.withdraw(new BigDecimal(400));
		accountTest.getFinalBalance();
		accountTest.printHistory();
    }

	// Then le balance final du second compte vaut 3378.90
	@Test
    public void operationsShouldReturn3378point90() throws UnsufficientFundsException {
		assertEquals(new BigDecimal(3378.90), accountTest2.getFinalBalance());
		accountTest2.printHistory();
    }

}
