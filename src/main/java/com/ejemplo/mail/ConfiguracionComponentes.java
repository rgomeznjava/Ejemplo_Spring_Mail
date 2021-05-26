package com.ejemplo.mail;

import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

//@Configuration
public class ConfiguracionComponentes {
	
	
	public static JavaMailSender configuracionJavaMailSender() {
		
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setUsername("raul.gomezn27@gmail.com");
		javaMailSender.setPassword("27grijano");
		javaMailSender.setPort(587); //587
		
		Properties propMail = new Properties();
		propMail.put("mail.smtp.auth", true);
		propMail.put("mail.smtp.ssl.trust", "smtp.gmail.com");
				
		propMail.put("mail.smtp.starttls.enable", true);
		propMail.put("mail.smtp.starttls.required", true);
		propMail.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		
		javaMailSender.setJavaMailProperties(propMail);
		
		return javaMailSender;
	}
}
