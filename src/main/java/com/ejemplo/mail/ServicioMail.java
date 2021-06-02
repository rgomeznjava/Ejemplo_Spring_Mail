package com.ejemplo.mail;

import java.io.File;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("MAIL_SERVICE")
public class ServicioMail {
	
	@Autowired
	@Qualifier("MAIL_SERNDER")
	private JavaMailSender mailSender;
	
	
	@Autowired
	@Qualifier("MAIL_FOLDER_INBOX")
	private Folder mailFolderInbox;
	
    
	
	public void sendSimpleMailMessage(String from, String to, String subject, String message) throws MessagingException {
		
	
		SimpleMailMessage sms = new SimpleMailMessage();
		sms.setFrom(from);
		sms.setTo(to);
		sms.setSubject(subject);
		sms.setText(message);
		
		mailSender.send(sms);
	}
	
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException {
			   
		
		MimeMessage message = mailSender.createMimeMessage();
			     
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
			    
		helper.setFrom("noreply@condemor.com");
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);
			        
		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment(file.getFilename(), file);

		mailSender.send(message);
	}
	
	//@Scheduled(fixedRate = 5000)
	public synchronized void readMailsInbox() throws Exception {
		
		try {
				
			mailFolderInbox.open(Folder.READ_ONLY);
			System.out.println( "TOTAL MAILS: "+ mailFolderInbox.getMessageCount());
				
			Message[] messages = mailFolderInbox.getMessages();
	   
			for (int i = 0; i < messages.length; i++) {
					
				Message message = messages[i];        
				    
				System.out.println((i+1) + " " +message.getSubject() + "   --> " + message.getFrom()[0]);  				    
				//System.out.println("Text: " + message.getContent().toString());  
			}
				
			mailFolderInbox.close();
				
		} catch (MessagingException e) {
				e.printStackTrace();
		}
	}
		
}
