/*Gavin Lampton, Alfonso Villalobos
 * March 17, 2020
 * Purpose: This program will write transaction information to a file.
 * Inputs: Data about business transactions.
 * Outputs: The given data.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 *This class brings everything together to simulate a point of sales system.
 * @author gavin
 */
public class PointOfSalesSystem implements AutoCloseable{
    
	public static final String delimiter = ";";
	public static final String defaultOutputFile = "Sales.txt";
	
    private PrintWriter pw;
    private TextReader reader;
    private ArrayList<Sale> storage;
    
    private String inputPrompt;
    private static final String exitCode = "end";
    
    /**
     * Initializes this Point of Sale System to read from console and write to the default file.
     */
    public PointOfSalesSystem()
    {
    	try 
    	{
			pw = new PrintWriter(defaultOutputFile);
		} 
    	catch (FileNotFoundException e)
    	{
			System.err.println(e);
			e.printStackTrace();
		}
    	
    	reader = new TextReader();
    	storage = new ArrayList<Sale>();
    }
    
    /**
     * Initializes this Point of Sale System to read from a given file and output to a default named file.
     * @param input
     * @throws FileNotFoundException
     */
    public PointOfSalesSystem(String input) throws FileNotFoundException
    {
    	this(input,null);
    }
    
    /**
     * Initializes this Point of Sale System with a specified input and output file.
     * @param input
     * @param output
     * @throws FileNotFoundException
     */
    public PointOfSalesSystem(String input, String output) throws FileNotFoundException
    {
		setOutputFile(output);
    	
		if(input == null)
		{
			reader = new TextReader();
		}
		else
		{
			reader = new TextReader(input);
		}
		
		storage = new ArrayList<Sale>();
    }
    
    /**
     * Sets the user prompt given when taking input.
     * @param prompt - the string which users will see.
     */
    public void setInputPrompt(String prompt)
    {
    	this.inputPrompt = prompt;
    }
    
    /**
     * Sets output to a file named by the given string.
     * @param output - the file name.
     * @throws FileNotFoundException
     */
    public void setOutputFile(String output) throws FileNotFoundException
    {
    	if(output == null)
		{
			output = defaultOutputFile;
		}
		
		pw = new PrintWriter(output);
    }
    
    /**
     * Sets the input source to the console.
     */
    public void setInputToConsole()
    {
    	reader.setToConsole();
    }
    
    /**
     * Sets input source to the file named by the given string.
     * @param fileName - the file name.
     * @throws FileNotFoundException
     */
    public void setInputFile(String fileName) throws FileNotFoundException
    {
		setInputFile(new File(fileName));
    }
    
    /**
     * Sets input source to the specified file.
     * @param f - the file which input will be read from.
     * @throws FileNotFoundException 
     */
    public void setInputFile(File f) throws FileNotFoundException
    {
    	if(!f.exists())
		{
			throw new FileNotFoundException("Cannot find "+f.toString());
		}
		else if(!f.canRead())
		{
			throw new FileNotFoundException("Cannot read "+f.toString());
		}
		
    	reader = new TextReader(f);
    }
    
    /**
     * Returns whether this object is reading from file.
     * @return true if reading from file, otherwise false.
     */
    public boolean isReadingFromFile()
    {
    	return reader.isFile();
    }
    
    /**
     * Reads input from either a file or the console.
     */
    public void readInput()
    {
    	boolean isLooping = true;
    	boolean isGivingPrompt = inputPrompt != null && !reader.isFile();
    	
    	String holder;
    	Sale currentTransaction;
    	
    	while(isLooping)
    	{
    		
        	if(isGivingPrompt)
        	{
            	System.out.println(inputPrompt);
        	}
        	
    		holder = reader.nextLine();
    		
    		if(holder.isEmpty() || holder.equalsIgnoreCase(exitCode))
    		{
    			isLooping = false;
    		}
    		else
    		{
    			isLooping = reader.hasInput();
        		
        		try
        		{
                	currentTransaction = createSaleObject(holder);
                	storage.add(currentTransaction);
        		}
        		catch(InvalidTransaction e)
        		{
        			System.err.println(e.toString());
        		}
    		}
    	}
    }
    
    /**
     * Will write a file based on all data currently stored.
     */
    public void writeToFile()
    {
    	for(Sale s: storage)
    	{
    		pw.write(s.toString());
    		pw.write("\n");
    	}
    }
    
    /**
     * Will read input and write a file named after a service category or do nothing if this object doesn't have that category.
     * @param service - the service category which will be printed.
     */
    public void writeToFile(ServiceCategory service)
    {    	
    	if(has(service))
    	{
        	try
        	{
    			setOutputFile(service.getName()+".txt");
    		} 
        	catch (FileNotFoundException e)
        	{
    			System.err.println(e.toString());
    			e.printStackTrace();
    		}

        	for(Sale s: storage)
        	{
        		if(service.equals(s.getService()))
        		{
            		pw.write(s.toString());
            		pw.write("\n");
        		}
        	}
    	}
    }
    
    /**
     * Will clear all stored data.
     */
    public void clearData()
    {
    	storage.clear();
    }

    /**
     * Will close all resources used by this object.
     */
	@Override
	public void close() 
	{
		pw.close();
		reader.close();
	}
    
    private boolean has(ServiceCategory givenCategory)
    {
    	for(Sale s:storage)
    	{
    		if(givenCategory.equals(s.getService()))
    		{
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    private Sale createSaleObject(String s) throws InvalidTransaction
    {
    	StringTokenizer tokenizer = new StringTokenizer(s,delimiter);

    	String clientName = Sale.garbageString;
    	ServiceCategory servType = ServiceCategory.MISC;
    	Double cost = 0.0;
    	String date = Sale.garbageString;
		
    	if(tokenizer.countTokens()>Sale.numOfFields)
    	{
    		throw new InvalidTransaction("Invalid transaction format.");
    	}
    	
    	
		for(int counter = 0; tokenizer.hasMoreTokens(); counter++) 
		{
			switch(counter)
			{
			case 0:
				clientName = tokenizer.nextToken();
			break;
			
			case 1:
				String givenServType = tokenizer.nextToken();
				servType = ServiceCategory.getCategory(givenServType);
				
				if(servType == null)
				{
					throw new InvalidServiceType(givenServType);
				}
				break;
				
			case 2:
				try
				{
					cost = Double.parseDouble(tokenizer.nextToken());
				}
				catch(NumberFormatException num)
				{
					throw new InvalidTransaction("Invalid parse attempt");
				}
				break;
				
			case 3:
				date = tokenizer.nextToken();
				break;
			}
		}
		
		return new Sale(clientName, servType, cost, date);
    }
}