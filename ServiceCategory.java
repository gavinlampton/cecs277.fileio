/*Gavin Lampton, Alfonso Villalobos
 * March 17, 2020
 * Purpose: This program will write transaction information to a file.
 * Inputs: Data about business transactions.
 * Outputs: The given data.
 */

import java.util.EnumSet;
/**
 * This enum gives a specific list of services which are offered and the minimum cost of each.
 * @author gavin, fonso
 *
 */
public enum ServiceCategory{
	
    CONFERENCE("Conference"),
    DINNER("Dinner"),
    ENTERTAINMENT("Entertainment"),
    LODGING("Lodging"),
    LUNCH("Lunch"),
	MISC("Misc"),
    ROOMSERVICE("Room Service"),
    TRANSPORTATION("Transportation");
    
    private String name;
    private double minimumCost;
    
    /**
     * Initializes the service with an empty string and a minimum cost of zero.
     */
    ServiceCategory()
    {
    	this("",0.00);
    }
    
    /**
     * Initializes the service with a minimum cost of 0.
     * @param serviceName
     */
    ServiceCategory(String serviceName)
    {
    	this(serviceName,0.00);
    }
    
    /**
     * Initializes the service with the given minimum cost.
     * @param serviceName
     * @param cost
     */
    ServiceCategory(String serviceName, double cost)
    {
        this.name = serviceName;
        this.minimumCost = cost;
    }
    
    /**
     * Gets the human readable name of the service.
     * @return The human readable name of the service.
     */
    
    public String getName()
    {
        return name;
    }
    
    /**
     * Gets the minimum cost of the service
     * @return minimum cost of the service.
     */
    public double getMinCost()
    {
        return minimumCost;
    }
 
    /**
     * Gives a string containing the service name and cost, separated by a semicolon.
     * @return The name and the minimum cost.
     */
    @Override
    public String toString()
    {
        return String.format("%s%s%f", name, PointOfSalesSystem.delimiter, minimumCost);
    }
    
    
    /**
     * Determines if a service name corresponds with a valid service.
     * @param serviceName
     * @return true or false
     */
    public static boolean isValidService(String serviceName)
    {
    	return (getCategory(serviceName) == null);
    }
    
    /**
     * Gets the category which corresponds to the given name.
     * @param s - the name of the desired category.
     * @return The category or null, if invalid.
     */
    public static ServiceCategory getCategory(String s)
    {
    	for(ServiceCategory sType: ServiceCategory.values())
    	{
    		if(s.equalsIgnoreCase(sType.getName()))
    		{
    			return sType;
    		}
    	}
    	
    	return null;
    }
}