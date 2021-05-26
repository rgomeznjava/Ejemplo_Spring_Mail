package com.ejemplo.mail;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class Email {
	
	
	private MailSender mailSender;
	
	
	public static void main(String[] args) {
		
		
		Email email = new Email();
		email.sendMail("pepito@gmail.com", "raulgomezn27@gmail.com", "PRUEBA MAIL", "CONDEMOOORRR");
		
		
	}
	
	public Email() {
		
		mailSender = ConfiguracionComponentes.configuracionJavaMailSender();
		
	}
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = this.mailSender;
	}

	
	public void sendMail(String from, String to, String subject, String message) {
		
		SimpleMailMessage sms = new SimpleMailMessage();
		sms.setFrom(from);
		sms.setTo(to);
		sms.setSubject(subject);
		sms.setText(message);
		
		mailSender.send(sms);
	}
}
