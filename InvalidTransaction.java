/*Gavin Lampton, Alfonso Villalobos
 * March 17, 2020
 * Purpose: This program will write transaction information to a file.
 * Inputs: Data about business transactions.
 * Outputs: The given data.
 */

/**
 * This class allows polymorphism between InvalidTransaction and InvalidCost without including all exceptions.
 * @author gavin
 *
 */
public class InvalidTransaction extends Exception
{
	 /**
     * Constructs a new Invalid Transaction exception with no specified values.
     */
	InvalidTransaction()
	{
		super();
	}
	
	/**
	 * Constructs a new Invalid Transaction exception with a specified error message.
	 * @param message
	 */
	InvalidTransaction(String message)
	{
		super(message);
	}
	
	/**
	 * Constructs a String representation of this Invalid transaction
	 * See Throwable's toString method.
	 */
	@Override
	public String toString()
	{
		return super.toString();
	}
}
