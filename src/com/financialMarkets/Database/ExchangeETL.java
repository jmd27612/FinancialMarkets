/**
 * ExchangeETL Class provides functionality to interface with the Exchange table in the database. It's main purpose is
 *   to ensure the table maintains the most current exchanges as supplied by the data vendor. 
 * 
 * @author Justin Dudley
 */

package com.financialMarkets.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import com.financialMarkets.Utilities;

public class ExchangeETL
{
	private final char[] classKey = "3=svv5EyH-f4*T$hU2*mEj%F@$5FGJBQ%=UDmYm62B$@=@ev*GEbh_6!ycx&*Wsw--5C-Sh?cBLDQ*udSrZC-UT%@cpQ58*Tf#qG5gj@_BrR4A5VDBB5LWB*R?-_Pyu_@NSvrrB@3_#cfDduhrYE844r$c2C3r7&Xe46R#k8dF+6gkP&kShRAkh%39mzrW#cr4j+wLV8=?R_*P$tjnw-J$Z!cwwQ#8C4YF$d-Mb-jYRQ-2VKjC*F3m!U2%?8CTGBj^*BVknHS!BqRPLxHEUUScYstMEqX-QDctBw7RjSbuw+^MP3#J5$gEmCnb4Fygcf#wAyQ6KHjTzQ6HfTZdr#std??Th!r2VLM#qbWnCFPqgpYBczSRQ6kQrUKm+fftFDF_zvyeHYD_?X#WfPA9!p4?N&uYU-Cz!2!T!JZnxLx=-u#xEM_rjb!mn?ECbW9SuETghmrUb+7Ep39Ye=t64tz-e9+5n4njCF6gkk!BywNF3+j^EbAaAL-hMSsB*FRa+t".toCharArray(); 
	private Connection db = null; 
	private Logger localLogger; 
	
	public ExchangeETL()
	{
		localLogger = Logger.getLogger("com.financialMarkets.Database.ExchangeETL"); 
		db = openDatabaseConnection(); 
	}
	
	
	//Opens connection to PostgreSQL database server
	private Connection openDatabaseConnection()
	{
		String connectionURL = "jdbc:postgresql://" + Utilities.config.getProperty("EXCHANGEETL_POSTGRESQL_SERVER_ADDRESS", classKey).toString() + "/" + Utilities.config.getProperty("EXCHANGEETL_POSTGRESQL_DATABASE_NAME", classKey).toString(); 
		Connection db = null;  
		
		try
		{
			db = DriverManager.getConnection(connectionURL, Utilities.config.getProperty("EXCHANGEETL_POSTGRESQL_USER", classKey).toString(), Utilities.config.getProperty("EXCHANGEETL_POSTGRESQL_PASSWORD", classKey).toString()); 
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage()); 
		}
		
		return db; 
	}
	
	//Connection test returns the number if rows in the table
	public int testConnection(String tableName)
	{
		String SQL = "SELECT COUNT(*) FROM " + tableName; 
		int count = 0; 
		
		try
		{
			Statement statement = db.createStatement();
			ResultSet rs = statement.executeQuery(SQL); 
			rs.next(); 
			count = rs.getInt(1); 
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage()); 
		}
		
		return count; 
	}
	
	//Method will add or modify entries in the exchange table in the database to bring it up to date
	public void insertExchange(Exchange exchange)
	{
		String SQL1 = "SELECT COUNT(*) FROM exchange_list WHERE code = '" + exchange.getCode() + "'"; 
		int count = 0; 
		
		try
		{
			Statement statement = db.createStatement(); 
			ResultSet rs = statement.executeQuery(SQL1); 
			rs.next(); 
			count = rs.getInt(1); 
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		if(count != 0)
		{
			String SQL2 = "SELECT code, name, country, currency, time_zone, intra_day FROM exchange_list WHERE code = '" + exchange.getCode() + "'"; 
			try
			{
				Statement statement2 = db.createStatement(); 
				ResultSet rs2 = statement2.executeQuery(SQL2); 
				rs2.next(); 
				
				//Name
				if(rs2.getString(2).equalsIgnoreCase((exchange.getName())) == false)
				{
					
					String SQL4 = "UPDATE exchange_list SET name = ? WHERE code = ?"; 
					PreparedStatement statement4 = db.prepareStatement(SQL4); 
					statement4.setString(1, exchange.getName());
					statement4.setString(2, exchange.getCode());
					statement4.executeUpdate(); 
				}
				
				//Country
				if(rs2.getString(3).equalsIgnoreCase((exchange.getCountry())) == false)
				{
					String SQL4 = "UPDATE exchange_list SET country = ? WHERE code = ?"; 
					PreparedStatement statement4 = db.prepareStatement(SQL4); 
					statement4.setString(1, exchange.getCountry());
					statement4.setString(2, exchange.getCode());
					statement4.executeUpdate(); 
				}
				
				//Currency
				if(rs2.getString(4).equalsIgnoreCase((exchange.getCurrency())) == false)
				{
					
					String SQL4 = "UPDATE exchange_list SET currency = ? WHERE code = ?"; 
					PreparedStatement statement4 = db.prepareStatement(SQL4); 
					statement4.setString(1, exchange.getCurrency());
					statement4.setString(2, exchange.getCode());
					statement4.executeUpdate(); 
				}
				
				//Time Zone
				if(rs2.getString(5).equalsIgnoreCase((exchange.getTimeZone())) == false)
				{
					
					String SQL4 = "UPDATE exchange_list SET time_zone = ? WHERE code = ?"; 
					PreparedStatement statement4 = db.prepareStatement(SQL4); 
					statement4.setString(1, exchange.getTimeZone());
					statement4.setString(2, exchange.getCode());
					statement4.executeUpdate(); 
				}
				
				//IntraDay
				if(rs2.getString(6).equalsIgnoreCase((exchange.getIntraDay())) == false)
				{
					
					String SQL4 = "UPDATE exchange_list SET intra_day = ?::boolean WHERE code = ?"; 
					PreparedStatement statement4 = db.prepareStatement(SQL4); 
					statement4.setString(1, exchange.getIntraDay());
					statement4.setString(2, exchange.getCode());
					statement4.executeUpdate(); 
				}
				
				
				
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		else
		{
			String SQL3 = "INSERT INTO exchange_list(code, name, country, currency, time_zone, intra_day) VALUES (?,?,?,?,?,?::boolean)"; 
			
			try
			{
				PreparedStatement statement3 = db.prepareStatement(SQL3);
						{
							statement3.setString(1, exchange.getCode());
							statement3.setString(2, exchange.getName());
							statement3.setString(3, exchange.getCountry());
							statement3.setString(4, exchange.getCurrency());
							statement3.setString(5, exchange.getTimeZone());
							statement3.setString(6, exchange.getIntraDay());
							statement3.executeUpdate(); 
						}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		
		}
		
		
		
		
	}
	
	public void close()
	{
		try
		{
			db.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
}
