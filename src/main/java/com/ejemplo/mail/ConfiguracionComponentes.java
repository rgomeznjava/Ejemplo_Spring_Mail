package com.ejemplo.mail;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class ConfiguracionComponentes {
	
	@Autowired //Acceso a todas las propiedades de los PropertySource
	private  Environment env;
	
	
	@Bean("MAIL_SERNDER")
	public JavaMailSender configuracionJavaMailSender() {
		
		/*
		smtp.gmail.com
		Requiere SSL: Sí
		Requiere TLS: Sí (si está disponible)
		Requiere autenticación: Sí
		Puerto para SSL: 465
		Puerto para TLS/STARTTLS: 587
		 */
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		
		System.out.println(" ----> env: "+ env);
	
		javaMailSender.setHost(env.getProperty("mail.host"));
		javaMailSender.setPort(Integer.parseInt(env.getProperty("mail.port")));  

		javaMailSender.setUsername(env.getProperty("mail.username"));  
		javaMailSender.setPassword(env.getProperty("mail.password"));  
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", Boolean.parseBoolean(env.getProperty("mail.smtp.auth")));
		properties.put("mail.smtp.ssl.trust", env.getProperty("mail.smtp.ssl.trust"));
		properties.put("mail.smtp.starttls.enable",  Boolean.parseBoolean( env.getProperty("mail.smtp.starttls.enable")));
		properties.put("mail.smtp.starttls.required",  Boolean.parseBoolean(  env.getProperty("mail.smtp.starttls.required")));
		properties.put("mail.debug", env.getProperty("mail.debug"));
		
		
		javaMailSender.setJavaMailProperties(properties);


		return javaMailSender;
	}
	
	
	@Bean("MAIL_FOLDER_INBOX")
	public Folder configuracionFolderReceiver() {

		Folder emailFolder = null;
		try {
		
			Properties properties = new Properties();
			properties.put("mail.pop3.host",  env.getProperty("mail.pop3.host"));  
			properties.put("mail.pop3.port", Integer.parseInt(env.getProperty("mail.pop3.port")) );
			//properties.put("mail.pop3.starttls.enable", Boolean.parseBoolean(env.getProperty("mail.pop3.starttls.enable")));
	     
			Session emailSession = Session.getDefaultInstance(properties);
			Store store = null;
	  
	    	store = emailSession.getStore(env.getProperty("mail.pop3.store"));
	        store.connect( env.getProperty("mail.pop3.host"), env.getProperty("mail.username"), env.getProperty("mail.password"));
	        emailFolder = store.getFolder( env.getProperty("mail.pop3.folder"));
	   
	    } catch (MessagingException e) {
	    	e.printStackTrace();
	    }
	    return emailFolder;
	}
		
}
	
