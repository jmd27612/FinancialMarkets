package com.financialMarkets;

import java.util.logging.Logger;

/**
 * Contains constants and variables for application operations
 * 
 * @author Justin Dudley
 *
 */
public final class Utilities
{
	public static final String CONFIG_PATH= "C:\\Users\\Justin Dudley\\Desktop\\EOD_DATA_ETL_Configuration.xml"; 
	
	public static final String KEYFILE_PATH = "C:\\etc\\keystore\\Financial_Markets_Encryption_Keyfile.txt"; 
	
	protected static final char[] ENCRYPTION_KEY = "DQSXSS$?5=P8v-deY_&=kHY=+NvBaz=jTvm5TvYT@Ru%@TJrRmr!gumh=vuwh3F7rq&fAw%aWWQPn2f%MkQPR6FupU7X5Hnm8V!Rv*uxcL*aTe7m&Wg*3*-x3mqzf+c84TNFK-sq$=JpDxF2C7CVXzwPMQJbB_XB^Jk-TS!cjEvDL?ZShG3?Q-qntSBRYqKcJsf!k$Kr$5KrWbc#Pgt!NhcwCyb7eUEEr^68YeGzZ!8fP25Qs5EVLmC-Fm_mFsjSqKtB6bju$-^@8ZRvLUCB@QjLU+YFpaL+A=af_VQ%958V6Kf^a_&4f5Sjxp=KW-FJ_rQ7pGNp7vG47$RBsWgx*pNUXN#s!#%dq8Amkq6vxB?dC?!Z3paBMa9BNTQ?6AQ5#@ub7R9bx$4QNfRX*f!pdgBtMHx2t=L^fS!VMDE?y+YwS&kXF@avt^unh-pw6Q2QkQQe!umzdbYbTQUADa$RNRGQ3L*Et7Vn&gW-tPzr5bztgE4qzD%PxA$AmMxy^JCu".toCharArray(); 
	
	protected static final int KEY_LENGTH = 1536; 
	
	public static Configuration config; 
	
	public static Logger logger; 
	
	//GUI
	public static final String APPLICATION_NAME = "Financial Market Analytics"; 
	
	/**
	 * A private constructor to prevent instantiation of the Utilities class. 
	 */
	private Utilities()
	{
		
	}
	
}
