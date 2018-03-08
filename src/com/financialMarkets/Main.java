package com.financialMarkets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;


import com.financialMarkets.userInterface.ConfigFileMaintGUI;

public class Main
{
	private static Logger logger; 
	private static Handler fileHandler; 
	
	public static void main(String[] args)
	{
		//Initialize the logging hierarchy and create file handler
		try
		{
			initLoggerHierarchy(); 
		}
		catch(Exception e)
		{
			System.err.println("Unable to initialize the logging hierarchy. The program must exit! Error Code: 1");
			System.err.println(e.toString());
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		finally
		{
			System.exit(1); 
		}


		
		
		//initialize(); 
		
		
		//Flush and close the logging file handler
		fileHandler.flush();
		fileHandler.close(); 

	}
	
	/**
	 * Initializes the logging hierarchy for com.financialMarkets
	 * @throws SecurityException
	 * @throws IOException
	 */
	private static void initLoggerHierarchy() throws SecurityException, IOException
	{
		Utilities.logger = Logger.getLogger(""); 
		initLoggerFileHandler(); 
		Utilities.logger.setLevel(Level.ALL); 
		
		Logger.getLogger("com"); 
		Logger.getLogger("com.financialMarkets"); 
		logger = Logger.getLogger(Main.class.getName());
		logger.info("Logging hierarchy initialized");
	}
	
	/**
	 * Initializes the file handler for the logger, written to the logs directory in the current
	 * working directory
	 * @throws SecurityException
	 * @throws IOException
	 */
	private static void initLoggerFileHandler() throws SecurityException, IOException
	{
		String logFileName = "logs/global." + Calendar.getInstance().getTimeInMillis() + ".xml"; 
		fileHandler = new FileHandler(logFileName, false); 
		fileHandler.setLevel(Level.ALL);
		Utilities.logger.addHandler(fileHandler);
	}
	
	
	
	/**
	 * 
	 * Loads keys for configuration file into memory. 
	 * 
	 */
	private static void initialize()
	{

		
		
		
		Utilities.logger = Logger.getLogger("com.financialMarkets"); 
		try
		{
			Handler fileHandler = new FileHandler("‪%t/log.xml", false);
			Utilities.logger.addHandler(fileHandler);
			Utilities.logger.setLevel(Level.ALL);
		} 
		catch (SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		
		
		Utilities.config = new Configuration(); 
		
		//Run the GUI codes in the Event-dispatching thread for thread-safety
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new ConfigFileMaintGUI(); 
			}
		});
		

		
		for(int i = 0; i < 1000; i ++)
		{
			System.out.println(i);
			try
			{
				Thread.sleep(250);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
