/*Gavin Lampton, Alfonso Villalobos
 * March 17, 2020
 * Purpose: This program will write transaction information to a file.
 * Inputs: Data about business transactions.
 * Outputs: The given data.
 */


/**
 * This class represents individual sales transactions made by personnel.
 * It will keep track of the service which was performed, the cost of said service, the name of the client which bought it, and the date the transaction occurred.
 * @author gavin
 *
 */
public class Sale 
{
	public static final int numOfFields = 4;
	public static final String garbageString = "N/A";
	
	private static final String delimiter = PointOfSalesSystem.delimiter;
	

    private String clientName;
	private ServiceCategory service;
	private double cost;
    private String date;
    
    /**
     * Initializes a Sale with dummy values.
     */
    public Sale()
    {
    	service = ServiceCategory.MISC;
    	cost = 0.0;
    	clientName = garbageString;
    	date = garbageString;
    }
    
    /**
     * Initializes a Sale with a service, cost, client name, and date.
     * @param service - specifies the service given.
     * @param cost - specifies the cost of the service.
     * @param clientName - specifies the name of the client which received the service.
     * @param date - specifies the date when the transaction was made.
     */
    public Sale(String clientName, ServiceCategory service, double cost, String date) throws InvalidCost
    {
    	this.clientName = clientName;
    	this.service = service;
    	this.date = date;
    	
    	setCost(cost);
    	
    }
    
    /**
     * Returns the client's name.
     * @return The client's name.
     */
    public String getClientName()
    {
        return clientName;
    }
    
    /**
     * Sets the client's name to the specified value.
     * @param clientName - the new value for the client's name.
     */
    public void setClientName(String clientName)
    {
    	if(!clientName.isEmpty())
    	{
            this.clientName = clientName;
    	}
    }
    
    /**
     * Returns the cost of the transaction.
     * @return The cost of the transaction.
     */
    public double getCost()
    {
    	return cost;
    }
    
    /**
     * Sets the cost for this transaction if it is valid.
     * @param cost - the new cost for this transaction
     * @throws InvalidCost - if the cost is below the minimum price for the given service
     */
    public void setCost(double cost) throws InvalidCost
    {
    	if(cost >= service.getMinCost())
    	{
    		this.cost = cost;
    	}
    	else
    	{
    		throw new InvalidCost(this.cost);
    	}
    }
    
    
    /**
     * Returns the serviceCategory for this transaction.
     * @return The serviceCategory.
     */
    public ServiceCategory getService()
    {
    	return this.service;
    }
    
    /**
     * Sets the service for this transaction.
     * @param service - the new service for this transaction.
     */
    public void setService(ServiceCategory service)
    {
    	this.service = service;
    }
    
    /**
     * Returns the date for when this transaction occurred.
     * @return The date when this transaction occurred.
     */
    public String getDate()
    {
        return date;
    }
    
    /**
     * Sets the date for this transaction if the string is not empty.
     * @param date - the new date for this transaction.
     */
    public void setDate(String date)
    {
    	if(!date.isEmpty())
    	{
            this.date = date;
    	}
    }
    
    /**
     * Returns a string which gives the client's name, the service's name, the cost, and the date of this transaction seperated by semi-colons.
     * @return A string which describes the object, as specified above.
     */
    @Override
    public String toString()
    {
    	return String.format("%s%s%s%s%.2f%s%s", clientName, delimiter, service.getName(), delimiter, cost, delimiter, date);
    }
}
