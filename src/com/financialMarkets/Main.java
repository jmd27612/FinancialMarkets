package com.financialMarkets;

import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import com.financialMarkets.userInterface.ConfigFileMaintGUI;

public class Main
{

	
	public static void main(String[] args)
	{
		initialize(); 
		
		

	}
	
	/**
	 * Initializes the logger. 
	 * Loads keys for configuration file into memory. 
	 * 
	 */
	private static void initialize()
	{
		Utilities.logger = Logger.getLogger("com.financialMarkets"); 
		
		Utilities.config = new Configuration(); 
		
		//Run the GUI codes in the Event-dispatching thread for thread-safety
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new ConfigFileMaintGUI(); 
			}
		});
		

		
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
