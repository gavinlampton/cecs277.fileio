/*Gavin Lampton, Alfonso Villalobos
 * March 17, 2020
 * Purpose: This program will write transaction information to a file.
 * Inputs: Data about business transactions.
 * Outputs: The given data.
 */

import java.util.NoSuchElementException;

/**
 * This exception describes when the specified cost is invalid.
 * It allows feedback on the minimum or maximum costs required to be passed to the calling function.
 * @author gavin
 */

/*
 * @fonso: 
 * I just rewrote this entire class to use as an example of what a good exception class would look like, to the best of my knowledge.
 * Note that it's basically just constructors and a toString().
*/ 
public class InvalidCost extends InvalidTransaction
{
    
	private double minCost;
	private boolean hasMin;
	
	private double maxCost;
	private boolean hasMax;
	
	private StringBuilder errorMsgBuilder;
	private boolean isMsgBuilt;
	
    //This needs to run regardless of which constructor was called.
	private void universalInitialization(double minCost, double maxCost)
	{
    	this.minCost = minCost;
    	this.maxCost = maxCost;
    	
    	//These will be overwritten by the other constructors if false.
    	hasMin = true;
    	hasMax = true;
    	
    	errorMsgBuilder = new StringBuilder();
    	isMsgBuilt = false;
	}
	
	
	/*-------Constructors without a message---------*/
	
    /**
     * Constructs a new Invalid Cost exception with no specified values.
     */
	public InvalidCost()
	{
		this(0.0, 0.0);
		hasMin=false;
		hasMax=false;
	}

    /**
     * Constructs a new Invalid Cost exception with only a specified minimum cost.
     * @param minCost - the minimum cost used in the toString() method.
     */
    public InvalidCost(double minCost)
    {
    	this(minCost, 0.0);
    	hasMax=false;
    }
    
    /**
     * Constructs a new Invalid Cost exception with specified minimum and maximum costs.
     * @param minCost - the minimum cost used in the toString() method.
     * @param maxCost - the maximum cost used in the toString() method.
     */
    public InvalidCost(double minCost, double maxCost)
    {
    	universalInitialization(minCost, maxCost);
    }
	
    
    /*------Constructors with a given message------*/

    /**
     * Constructs a new Invalid Cost exception with only a specified error message.
     * @param message - the detail message.
     */
	public InvalidCost(String message)
	{
		this(0.0, 0.0, message);
		hasMin=false;
		hasMax=false;
	}
    
	/**
	 * Constructs a new Invalid Cost exception with a specified minimum cost and message.
	 * @param minCost - the minimum cost used in the toString() method.
	 * @param message - the detail message.
	 */
    public InvalidCost(double minCost, String message)
    {
    	this(minCost, 0.0, message);
    	hasMax=false;
    }
    
    /**
     * Constructs a new Invalid Cost exception with specified minimum and maximum costs and an error message.
     * @param minCost - the minimum cost used in the toString() method.
     * @param maxCost - the maximum cost used in the toString() method.
     * @param message - the detail message.
     */
    public InvalidCost(double minCost, double maxCost, String message)
    {
    	super(message);
    	universalInitialization(minCost, maxCost);
    }
    
    /**
     * Gets the minimum cost, if it was specified.
     * @return The minimum cost which caused the exception to be thrown.
     * @throws NoSuchElementException - is thrown if no minimum cost was set.
     */
    public double getMinCost() throws NoSuchElementException
    {
    	if(hasMin)
    	{
    		return minCost;
    	}
    	
    	throw new NoSuchElementException();
    }
    
    /**
     * Gets the maximum cost, if it was specified.
     * @return The maximum cost which caused the exception to be thrown.
     * @throws NoSuchElementException - is thrown if no maximum cost was set.
     */
    public double getMaxCost() throws NoSuchElementException
    {
    	if(hasMax)
    	{
    		return maxCost;
    	}
    	
    	throw new NoSuchElementException();
    	
    }
    
    
    /**
     * Returns a description of the throwable as described in the throwable class
     * Will also give the minimum and maximum prices, if specified.
     * @return A string representation of this Invalid Cost.
     */
    @Override
    public String toString()
    {
    	if(!isMsgBuilt)
    	{
        	buildErrorMsg();
    	}
    	
    	return errorMsgBuilder.toString();
    }
    
    private void buildErrorMsg()
    {
    	errorMsgBuilder.delete(0, errorMsgBuilder.length());
    	
    	
    	/*
    	 * This will add "{Name}: {message arg in constructors}"
    	 * 
    	 * Read this if you have questions:
    	 * https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html#toString()
    	 */
    	errorMsgBuilder.append(super.toString());
    	
    	
    	
    	if(hasMin || hasMax)
    	{
        	errorMsgBuilder.append("\n");
        	
    		if(hasMin)
    		{
    			errorMsgBuilder.append(String.format("Must be more than %f\n", minCost));
    		}
    		if(hasMax)
    		{
    			errorMsgBuilder.append(String.format("Must be less than %f\n", maxCost));
    		}
    	}
    	
    	isMsgBuilt = true;
    }
    
}