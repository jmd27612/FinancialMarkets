package com.financialMarkets;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;


import com.financialMarkets.userInterface.ConfigFileMaintGUI;

public class Main
{

	
	public static void main(String[] args)
	{
		System.out.println(System.getProperty("user.home"));
		
		//initialize(); 
		
		

	}
	
	/**
	 * Initializes the logger. 
	 * Loads keys for configuration file into memory. 
	 * 
	 */
	private static void initialize()
	{
		//dynamically load java.util.logging properties files from a relative path.
		//Put logging.properties file in default package. 
//		final InputStream inputStream = Main.class.getResourceAsStream("/logging.properties"); 
//		try
//		{
//			LogManager.getLogManager().readConfiguration(inputStream);
//		}
//		catch(Exception e)
//		{
//			System.out.println(e);
//		}
		
		
		
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
		
//		//Run the GUI codes in the Event-dispatching thread for thread-safety
//		SwingUtilities.invokeLater(new Runnable()
//		{
//			public void run()
//			{
//				new ConfigFileMaintGUI(); 
//			}
//		});
		

		
//		for(int i = 0; i < 1000; i ++)
//		{
//			System.out.println(i);
//			try
//			{
//				Thread.sleep(250);
//			} catch (InterruptedException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
	}

}
