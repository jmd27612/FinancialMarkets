/**
 * The Configuration class provides the application with configuration parameters that are securely stored in an XML file. 
 * Parameters are stored in key/value pairs. The parameter key is stored in plain-text. The parameter value is encrypted to 
 * prevent sensitive information, such as database application passwords, being written to easily accessible files. 
 * 
 * The encryption key is comprised of three parts:
 * 	1. A constant character array in the Utilities class. 
 * 	2. A key file secured by the operating system at /etc/keystore. 
 * 	3. A unique character array in each class. 
 * 
 * This implementation restricts portability to a server containing the keyfile in the keystore directory. Permissions to open, 
 * modify, copy, delete, etc. the keyfile are enforced by the operating system. Each class is only able to retrieve parameters 
 * for itself. Calls to retrieve other parameters will return garbage. 
 * 
 * Crypto provided by Jasypt Encryption library. 
 * 
 * @author Justin Dudley
 *
 */

package com.financialMarkets; 

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jasypt.util.text.StrongTextEncryptor;

public class Configuration
{
	private StrongTextEncryptor encryptor = null; 
	private Properties properties = null; 
	private Logger localLogger; 
	private char[] keyFile;
	
	/**
	 * Constructor opens a LOGGER, loads the keyfile, initializes the properties object, and loads
	 * the configuration from the XML file. 
	 * @throws InvalidPropertiesFormatException  	if the properties object contains invalid keys or values 
	 * @throws FileNotFoundException  				if the XML configuration file or keyfile cannot be found 
	 * @throws IOException 							if there is an error while writing to the XML configuration file 
	 */
	//TODO fix exceptions
	
	public Configuration()
	{
		Utilities.LOGGER.entering("Configuration", "Configuration");
		
		//Initialize LOGGER
		localLogger = Logger.getLogger("com.financialMarkets.Configuration"); 
		
		//Load the keyfile from the keystore
		localLogger.info("Loading keyfile");
		keyFile = new char[Utilities.KEY_LENGTH]; 
		try
		{
			FileInputStream keyFileStream = new FileInputStream(Utilities.KEYFILE_PATH); 
			for(int i = 0; i < Utilities.KEY_LENGTH; i++)
			{
				keyFile[i] = (char)keyFileStream.read(); 
			}
			keyFileStream.close(); 
		}
		catch(FileNotFoundException e)
		{
			localLogger.log(Level.SEVERE, e.getMessage(), e);
		}
		catch(Exception e)
		{
			localLogger.log(Level.SEVERE, e.getMessage(), e);
		}
		localLogger.info("Keyfile loaded from keystore"); 
		
		//Initialize and load the properties object
		properties = new Properties(); 
		try
		{
			properties.loadFromXML(new FileInputStream(Utilities.CONFIG_PATH));
			localLogger.info("Configuration loaded from XML file");
		}
		catch(InvalidPropertiesFormatException e)
		{
			localLogger.log(Level.SEVERE, e.getMessage(), e);
		}
		catch(FileNotFoundException e)
		{
			localLogger.log(Level.SEVERE, e.getMessage(), e);
		}
		catch(IOException e)
		{
			localLogger.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	/**
	 * Utility method: Generates the full encryption key using the three key sources. 
	 * @param classKey [512]		The encryption key from the calling class
	 * @return fullKey		The complete encryption key
	 */
	private char[] generateKey(char[] classKey)
	{
		char[] fullKey = new char[Utilities.KEY_LENGTH]; 
		int keyCounter = 0; 
		
		for(int i = 0; i < Utilities.KEY_LENGTH; i = i + 3)
		{
			fullKey[i] = Utilities.ENCRYPTION_KEY[keyCounter]; 
			fullKey[i + 1] = keyFile[keyCounter]; 
			fullKey[i + 2] = classKey[keyCounter]; 
			keyCounter++; 		
		}

		return fullKey; 
	}
	
	/**
	 * Utility method: Encrypts the plain text parameter
	 * @param plainText			The plain text configuration value 
	 * @param classKey [512]	The encryption key for the calling class
	 * @return encrypted text	The encrypted configuration value
	 */
	private String encrypt(String plainText, char[] classKey)
	{
		encryptor = new StrongTextEncryptor(); 
		encryptor.setPasswordCharArray(generateKey(classKey));
		String encryptedText = encryptor.encrypt(plainText); 
		encryptor = null; 
		return encryptedText; 
	}
	
	/**
	 * Utility method: Decrypts the parameter
	 * @param encryptedText		The encrypted text
	 * @param classKey [512]	The encryption key from the calling class
	 * @return plainText		The plain text configuration value
	 */
	private char[] decrypt(String encryptedText, char[] classKey)
	{
		encryptor = new StrongTextEncryptor(); 
		encryptor.setPasswordCharArray(generateKey(classKey));
		char[] plainText = encryptor.decrypt(encryptedText).toCharArray(); 
		encryptor = null; 
		return plainText; 
	}
	
	/**
	 * Utility method: Writes the parameters in the parameter object to the XML configuration file
	 * @throws InvalidPropertiesFormatException  	if the properties object contains invalid keys or values 
	 * @throws FileNotFoundException  				if the XML configuration file cannot be found
	 * @throws IOException 							if there is an error while writing to the XML configuration file
	 */
	private void writeConfigToFile()
	{
		localLogger.info("Writing configuration to file.");
		try
		{
			properties.storeToXML(new FileOutputStream(Utilities.CONFIG_PATH), "Financial Markets Analytics Application Configuration");
		} 
		catch(InvalidPropertiesFormatException e)
		{
			localLogger.log(Level.SEVERE, e.getMessage(), e);
		}
		catch(FileNotFoundException e)
		{
			localLogger.log(Level.SEVERE, e.getMessage(), e);
		}
		catch(IOException e)
		{
			localLogger.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	/**
	 * Returns the configuration value for the requested key 
	 * @param propertyName 		The key of the configuration property
	 * @param classKey [512]	The encryption key for the calling class
	 * @return propertyValue 	The value of the configuration property
	 * 
	 */
	public char[] getProperty(String propertyName, char[] classKey)
	{
		localLogger.info(String.format("Key Requested: [%s]", propertyName));
		return decrypt(properties.getProperty(propertyName), classKey); 
	}
	
	/**
	 * Adds or updates a configuration value with the requested key
	 * @param propertyName		The key of the configuration property
	 * @param plainText			The value of the configuration property
	 * @param classKey [512]		The encryption key for the calling class
	 */
	public void setProperty(String propertyName, char[] plainText, char[] classKey)
	{
		localLogger.info(String.format("Adding/Updating property [%s].",propertyName));
		properties.setProperty(propertyName, encrypt(plainText.toString(), classKey)); 
		writeConfigToFile(); 
	}
	
	/**
	 * Removes a property from the current configuration and updates the configuration file
	 * @param propertyName	The name of the configuration property
	 */
	public void deleteProperty(String propertyName)
	{
		localLogger.warning(String.format("Delete Property Requested: [%s]", propertyName));
		properties.remove(propertyName); 
		writeConfigToFile(); 
	}
	
	/**
	 * Returns a Set of Strings containing the names of each property in the configuration
	 * @return propertyList		list of properties
	 */
	public Set<String> getPropertiesList()
	{	
		localLogger.info("Property List requested.");
		return properties.stringPropertyNames(); 
	}
	
}
