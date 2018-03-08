package com.financialMarkets;

import java.util.Properties;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailHandler extends Handler
{

	@Override
	public void close() throws SecurityException
	{
		//Method not used
	}

	@Override
	public void flush()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void publish(LogRecord record)
	{
//		StringBuilder sb = new StringBuilder(); 
//		sb.append("Log Level: " + record.getLevel() + "\n"); 
//		sb.append("Milliseconds: " + record.getMillis() + "\n"); 
//		sb.append("Logger Name: " + record.getLoggerName() + "\n"); 
//		sb.append("Message: " + record.getMessage() + "\n"); 
//		sb.append("Source Class: " + record.getSourceClassName() + "\n"); 
//		sb.append("Source Method: " + record.getSourceMethodName() + "\n"); 
//		sb.append("Thread ID: " + record.getThreadID() + "\n"); 
//		sb.append("Parameters: " + record.getParameters() + "\n"); 
//		if(record.getThrown() != null)
//		{
//			sb.append("Cause: " + record.getThrown().getCause() + "\n"); 
//			sb.append("Stack Trace: " + record.getThrown().getStackTrace() + "\n"); 
//		}
//		else
//		{
//			sb.append("No exception thrown..."); 
//		}
//
//		
//		System.out.println(sb.toString());
//		String subject = "This is my subject"; //"Financial Markets Log Message: " + record.getMessage(); 
//		EmailSender emailSender = new EmailSender("This is the body", subject); 
//		emailSender.run();
		
//		EmailSender sender = new EmailSender("This is a test log message from MAIN", "TESTING123"); 
//		try
//		{
//			sender.run();
//		}
//		catch(Exception e)
//		{
//			System.out.println(e.getMessage());
//		}
		
		

		
	}
	
}
