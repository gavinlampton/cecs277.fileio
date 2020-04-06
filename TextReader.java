/*Gavin Lampton, Alfonso Villalobos
 * March 17, 2020
 * Purpose: This program will write transaction information to a file.
 * Inputs: Data about business transactions.
 * Outputs: The given data.
 */

//We can change between either of these implementations at any time without changing anything else.


import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;

public class TextReader implements AutoCloseable{
	
	private Scanner sc;
	private BufferedReader br;
	private boolean isFile;
	
	/**
	 * Creates a Text Reader object which reads from System.in.
	 */
	TextReader()
	{
		sc = new Scanner(System.in);
	}
	
	/**
	 * Creates a Text Reader object which reads from the specified file name. Will read from System.in if file can't be accessed.
	 * @param fileName - the name of the file to read input from.
	 */
	TextReader(String fileName)
	{
		this(new File(fileName));
	}
	
	/**
	 * Creates a Text Reader object which reads from the specified file. Will read from System.in if file can't be accessed.
	 * @param file - the file to read input from.
	 */
	TextReader(File file)
	{
		try
		{
			sc = new Scanner(file);
			isFile = true;
		}
		catch(java.io.FileNotFoundException e)
		{
			isFile = false;
			sc = new Scanner(System.in);
		}
	}
	
	/**
	 * Sets the text reader to read from System.in.
	 */
	public void setToConsole()
	{
		if(isFile)
		{
			close();
			
			sc = new Scanner(System.in);
			isFile = false;
		}
	}
	
	/**
	 * Sets the text reader to read from the specified file.
	 * @param fileName - the file to read from.
	 * @return Whether the input was successfully set to the new file.
	 */
	public boolean setToFile(String fileName)
	{
		return setToFile(new File(fileName));
	}
	
	/**
	 * Sets the text reader to read from the specified file.
	 * @param file - the file to read from.
	 * @return Whether the input was successfully set to the new file.
	 */
	public boolean setToFile(File file)
	{
		Scanner backup = sc;
		
		try
		{
			sc = new Scanner(file);
			isFile = true;
			return true;
		}
		catch(java.io.IOException e)
		{
			sc = backup;
			return false;
		}
		
	}
	
	
	/**
	 * Reads a line of input.
	 * @return Input until the next newline character.
	 */
	public String nextLine()
	{
		if(hasInput())
		{
			return sanitizeInput(sc.nextLine());
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * Returns whether this object is reading a file or not.
	 * @return true if reading a file, otherwise false.
	 */
	public boolean isFile()
	{
		return isFile;
	}
	
	/**
	 * Returns whether this object has more input.
	 * @return false if reading a file and not more input is available, otherwise true.
	 */
	public boolean hasInput()
	{
		if(isFile)
		{
			return sc.hasNext();
		}
		else
		{
			return true;
		}
	}

	/**
	 * Closes all resources used by the object.
	 */
	@Override
	public void close()
	{
		if(isFile)
		{
			sc.close();
		}
	}
	
	/**
	 * Gives object name
	 * @return object name.
	 */
	@Override
	public String toString()
	{
		return super.toString();
	}
	
	/*-----private--------*/
	
	private String sanitizeInput(String s)
	{
		StringBuilder sb = new StringBuilder(s);
		
		for(int x = 0; x < sb.length(); x++)
		{
			if(sb.charAt(x)=='"')
			{
				if(sb.charAt(x-1)!='\\')
				{
					sb.insert(x-1, '\\');
				}
			}
			
		}
		
		return sb.toString();
	}
	
}