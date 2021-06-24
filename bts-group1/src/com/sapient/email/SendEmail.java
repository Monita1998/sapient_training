// Java program to send email
package com.sapient.email;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail
{

public static void sendMail(String recepient,String owner,String name,String description,String priority,String status) throws MessagingException {
	System.out.println("preparing to send");
	
	Properties properties  = new Properties();
	properties.put("mail.smtp.auth","true");
	properties.put("mail.smtp.starttls.enable","true");
	properties.put("mail.smtp.host","smtp.gmail.com");
	properties.put("mail.smtp.port","587");
	String myAccountEmail = "xabc44797@gmail.com";
	String password = "@passWord";
	
	Session session = Session.getInstance(properties,new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(myAccountEmail, password);
			
		}
	});
	
	String msg = "Hi "+owner+"\n"+
			"Name of the created bug "+name+"\n"+
			"Description = "+description+"\n"+
			"priority = "+priority+"\n"+
			"status = "+status+"\n\n"+
			"Thanks & Regards"+"\n"+
			"Bug Tracking System";
	
	Message message = prepareMessage(session,myAccountEmail,recepient,msg);
	Transport.send(message);
	System.out.println("Successfully sent");
}

private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String msg) {
	Message message = new MimeMessage(session);
	try {
		message.setFrom(new InternetAddress(myAccountEmail));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
		message.setSubject("Bug - Info");
		
		message.setText(msg);
		return message;
	} catch (MessagingException e) {
		
		e.printStackTrace();
	}
	return null;
}
}
