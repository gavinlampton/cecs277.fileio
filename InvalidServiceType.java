/*Gavin Lampton, Alfonso Villalobos
 * March 17, 2020
 * Purpose: This program will write transaction information to a file.
 * Inputs: Data about business transactions.
 * Outputs: The given data.
 */

/**
 *This exception describes when the specified service name is invalid.
 *It also gives a list of the services which are valid in its toString() value.
 * @author fonso, gavin
 */

/*
 * @fonso: 
 * Exception classes don't exist to specify how the problem is handled, only to store info on why it happened.
 * Think of them like a class which only exists as a return type from a function.
 * 
 * I kept this class closer to how you originally had it in order to try and make it clearer how to do that.
*/ 
public class InvalidServiceType extends InvalidTransaction{

	private StringBuilder errorMsgBuilder;
	private boolean isMsgBuilt;

    //This needs to run regardless of which constructor was called.
	private void universalInitialization()
	{
		errorMsgBuilder = new StringBuilder();
		isMsgBuilt = false;
	}
	
	
	/**
	 * Constructs an Invalid Service Type exception with a default message.
	 */
    public InvalidServiceType(){
    	//Note that we can use the Throwable class's method getMessage() to get whatever we feed into the super class's constructor as the message.
    	
    	//I think the name "InvalidServiceType" implies that the service they tried is not allowed
        super("\nThis service is not allowed.");
        
        universalInitialization();
    }
    
    /**
     * Constructs an Invalid Service Type exception with a default message that specifies which type was tried.
     * @param service - the service which was tried.
     */
    public InvalidServiceType(String service){
        super("\n" + service + " is not a valid service.");
        
        universalInitialization();
    }
    
    /**
     * Returns a description of the throwable as described in the throwable class
     * Will give a default message even if not specified.
     * @return A string representation of this Invalid Service Type.
     */
    @Override
    public String toString()
    {
    	if(!isMsgBuilt)
    	{
    		errorMsgBuilder.append(String.format("%s: %s\n%s", super.toString(), getMessage(), getValidServices()));
    		isMsgBuilt = true;
    	}
    	
    	return errorMsgBuilder.toString();
    }
    
    
    private static String getValidServices(){
    	/*
    	 * It's weird but StringBuilders are significantly more efficient than concatenation (using the +).
    	 * Only using StringBuilders is good habit to build.
    	 */
    	
    	StringBuilder validServicesBuilder =  new StringBuilder();
    	
        validServicesBuilder.append("the only services allowed are:\n");
        
        for(ServiceCategory s: ServiceCategory.values()){
            validServicesBuilder.append(s.getName()+"\n");
        }
        
        return validServicesBuilder.toString();
    }
}
