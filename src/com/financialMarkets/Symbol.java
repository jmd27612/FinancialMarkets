package com.financialMarkets; 


public class Symbol
{
	private String symbolCode; 
	private String symbolName; 

	
	public Symbol(String symbolCode, String symbolName)
	{
		this.symbolCode = symbolCode; 
		this.symbolName = symbolName; 
	}
	
	public String getSymbolCode()
	{
		return symbolCode; 
	}
	
	public String getSymbolName()
	{
		return symbolName; 
	}
	
}
