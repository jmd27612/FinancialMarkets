package com.financialMarkets;

import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import com.financialMarkets.userInterface.ConfigurationFileMaintenanceGUI;

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
				new ConfigurationFileMaintenanceGUI(); 
			}
		});
		
		//ConfigurationFileMaintenanceGUI gui = new ConfigurationFileMaintenanceGUI(); 
		
		//Utilities.config.setProperty("POSTGRESQL_DATABASE_NAME","Hello", "s8m3x%8y7qwwLX8QNW+W&m3G2P5*tz&GDUU=WqLqHZkc=Tbqa?^$4@FFJXFkegX*grM^$BB%85aWLYWVQQj4nMfzPY$GN2P%RBE#S=Ahn?uEJ2*tLMNr3M#JFHn@j$yHhM#nn#r7qh=%aEf^p^rp8WgXvCe9p+Rk7sKah3EH9^Pr#=g$#SmukX#-5ac^9^mtwdE_Hst?p%?zr+Am7b%DNNaX!@!CD=kXh*q*Sct?mRjqfQA@s^eca!f8z5x2f_PL$eCdk^zsvUEq7tXBz-Fj-s@5d$ZQS&TbzLyB!u5dFpZcTwA+r?AvP5j7x6@%b+^F%WpbdZ*-ZM&!jD7f5fwVj&Vp#WRFmE$K?XM^9W=y@GLjGqf%T833PedcbVnz*WsfrR@sjN&J!*gUjJ4QgfGk-V5B%J^fTPzTeTb+=&HXFTD$^4uek&fmgxXYA^!_uN?Y-EVddAL-MPUv=^qWJqBwDnNEB+?G*MAG!$4xP7d=2YfCs74ep!pYyyD4ghFukbpg".toCharArray());
		
		System.out.println(Utilities.config.getProperty("POSTGRESQL_DATABASE_NAME", "s8m3x%8y7qwwLX8QNW+W&m3G2P5*tz&GDUU=WqLqHZkc=Tbqa?^$4@FFJXFkegX*grM^$BB%85aWLYWVQQj4nMfzPY$GN2P%RBE#S=Ahn?uEJ2*tLMNr3M#JFHn@j$yHhM#nn#r7qh=%aEf^p^rp8WgXvCe9p+Rk7sKah3EH9^Pr#=g$#SmukX#-5ac^9^mtwdE_Hst?p%?zr+Am7b%DNNaX!@!CD=kXh*q*Sct?mRjqfQA@s^eca!f8z5x2f_PL$eCdk^zsvUEq7tXBz-Fj-s@5d$ZQS&TbzLyB!u5dFpZcTwA+r?AvP5j7x6@%b+^F%WpbdZ*-ZM&!jD7f5fwVj&Vp#WRFmE$K?XM^9W=y@GLjGqf%T833PedcbVnz*WsfrR@sjN&J!*gUjJ4QgfGk-V5B%J^fTPzTeTb+=&HXFTD$^4uek&fmgxXYA^!_uN?Y-EVddAL-MPUv=^qWJqBwDnNEB+?G*MAG!$4xP7d=2YfCs74ep!pYyyD4ghFukbpg".toCharArray()));
 
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
