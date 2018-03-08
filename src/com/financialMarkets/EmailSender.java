package com.financialMarkets;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public final class EmailSender implements Runnable
{
	private String logMessage;
	private String subjectLine; 
	
	
	public EmailSender(String logMessage, String subjectLine)
	{
		this.logMessage = logMessage; 
		this.subjectLine = subjectLine; 
	}
	
	@Override
	public void run()
	{
		System.out.println("EMAILING " + Utilities.EMAIL_ADDRESS + "\n" + 
				"WITH SUBJECT " + subjectLine + "\nMESSAGE\n" + logMessage);
		
//		Properties properties = new Properties(); 
//		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.port", "587");
//		properties.put("mail.smtp.host", "smtp.mailgun.org"); 
//		
//		Session session = Session.getInstance(properties,
//		          new javax.mail.Authenticator() {
//		            protected PasswordAuthentication getPasswordAuthentication() {
//		                return new PasswordAuthentication("postmaster@sandbox6e3e03a5944f412890721dc9d8ff5d45.mailgun.org", "eb5813310e6d5e7a4ef6d4d656a93339-2b4c5a6c-884a3a6a");
//		            }
//		          }); 
//		
//		try
//		{
//			MimeMessage message = new MimeMessage(session); 
//			message.setFrom(new InternetAddress(Utilities.EMAIL_ADDRESS));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(Utilities.EMAIL_ADDRESS));
//			message.setSubject(subjectLine);
//			message.setText(logMessage);
//			
//			Transport.send(message);
//			
//		}
//		catch(Exception e)
//		{
//			System.out.println(e.getMessage());
//		}
		
		
		
        
	}

}
