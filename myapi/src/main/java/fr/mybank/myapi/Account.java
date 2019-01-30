package fr.mybank.myapi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import fr.mybank.myapi.Operation.Types;

public class Account {

	private Long number;
	private String owner;
	private ArrayList<Operation> operations;

	protected Account(String owner, Long number) {
		setNumber(number);
		setOwner(owner);
		setOperations(new ArrayList());
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public ArrayList<Operation> getOperations() {
		return operations;
	}

	public void setOperations(ArrayList<Operation> operations) {
		this.operations = operations;
	}

	protected void printHistory(){

		BigDecimal balance = BigDecimal.ZERO;

		System.out.println("\n\n**** ACCOUNT HISTORY (" + owner + " - account " + number + ") ****");

		for(Operation operation : operations)
		{
			BigDecimal amount = operation.getVariation();
			Date date = operation.getDate();
			Types type = operation.getType();
			balance = balance.add(amount);
			System.out.println("----------------------------");
			System.out.println("Date of operation: " + date);
			System.out.println("Operation type: " + type.toString());
			System.out.println("Amount: " + amount);
			System.out.println("New balance: " + balance);
		}

		System.out.println("----------------------------");
		System.out.println("Final balance: " + balance);

	}


	protected BigDecimal getFinalBalance() throws UnsufficientFundsException{

		BigDecimal balance = BigDecimal.ZERO;

		for(Operation operation : operations)
		{
			BigDecimal amount = operation.getVariation();
			balance = balance.add(amount);
		}

		if(balance.signum() == -1)
			throw new UnsufficientFundsException();

		return balance;

	}

	public void deposit(BigDecimal amount) throws NullAmountException{
		Operation deposit = new Operation(amount);
		operations.add(deposit);
	}

	public void withdraw(BigDecimal amount) throws NullAmountException{
		Operation withdrawal = new Operation(amount.negate());
		operations.add(withdrawal);
	}


}
