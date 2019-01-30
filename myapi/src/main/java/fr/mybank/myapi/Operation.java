package fr.mybank.myapi;

import java.math.BigDecimal;
import java.util.Date;

import com.sun.media.jfxmedia.logging.Logger;

public class Operation {

	private BigDecimal variation;
	private Date date;
	private Types type;

	public enum Types {DEPOSIT, WITHDRAWAL}


	public Operation(BigDecimal variation) throws NullAmountException{

		this.variation = variation;
		this.date = new Date();

		if(variation.signum() == 1)
		{
			type = Types.DEPOSIT;
		}
		else if(variation.signum() == -1)
		{
			type = Types.WITHDRAWAL;
		}
		else
		{
			throw new NullAmountException();
		}

		Logger.logMsg(0,"----------------------------");
		Logger.logMsg(0,"Date of operation: " + date);
		Logger.logMsg(0,"Operation type: " + type.toString());
		Logger.logMsg(0,"Variation: " + variation);

	}


	public Date getDate() {
		return date;
	}

	public BigDecimal getVariation() {
		return variation;
	}


	public Types getType() {
		return type;
	}



}


