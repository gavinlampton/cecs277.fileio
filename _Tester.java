/*Gavin Lampton, Alfonso Villalobos
 * March 17, 2020
 * Purpose: This program will write transaction information to a file.
 * Inputs: Data about business transactions.
 * Outputs: The given data.
 */

import java.io.FileNotFoundException;

public class _Tester {
	
	
	public static void main(String[] args)
	{
		PointOfSalesSystem PoS = null;
		
		try
		{
			switch(args.length)
			{
			case 0:
				PoS = new PointOfSalesSystem();
				break;
				
			case 1:
				PoS = new PointOfSalesSystem(args[0]);
				break;
				
			case 2:
				PoS = new PointOfSalesSystem(args[0],args[1]);
				break;
				
			default:
				System.err.println("Invalid number of arguments");
				
			}
		}
		catch(FileNotFoundException e)
		{
			System.err.println(e);
		}
		
		if(PoS != null)
		{
			PoS.setInputPrompt("Please enter a transaction:");
			
			PoS.readInput();
			
			PoS.writeToFile();
			PoS.close();
			
			for(ServiceCategory servCat: ServiceCategory.values())
			{
				PoS.writeToFile(servCat);
				PoS.close();
			}
			
			PoS.clearData();
		}
	}
}
