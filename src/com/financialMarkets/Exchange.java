/**
 * The Exchange class is designed to support the EOD data supplier. 
 * 
 * @author Justin Dudley
 * 
 */

package com.financialMarkets; 

import java.util.HashMap;
import java.util.Map;

public class Exchange
{
	private String code; 
	private String name; 
	private String country; 
	private String currency; 
	private String timeZone; 
	private String intraDay; 
	private Map<String, Symbol> symbols; 
	
	public Exchange(String code, String name, String country, String currency, String timeZone, String intraDay)
	{
		this.code = code; 
		this.name = name; 
		this.country = country; 
		this.currency = currency; 
		this.timeZone = timeZone; 
		this.intraDay = intraDay; 
		symbols = new HashMap<String, Symbol>(); 
	}
	
	public String getCode()
	{
		return code; 
	}
	
	public String getName()
	{
		return name; 
	}
	
	public String getCountry()
	{
		return country; 
	}
	
	public String getCurrency()
	{
		return currency; 
	}
	
	public String getTimeZone()
	{
		return timeZone; 
	}
	
	public String getIntraDay()
	{
		return intraDay; 
	}
	
	public void addSymbol(Symbol symbol)
	{
		symbols.put(symbol.getSymbolCode(), symbol); 
	}
	
	public Map<String, Symbol> getSymbols()
	{
		return symbols; 
	}
	
	public int getNumSymbols()
	{
		return symbols.size(); 
	}

	
}
