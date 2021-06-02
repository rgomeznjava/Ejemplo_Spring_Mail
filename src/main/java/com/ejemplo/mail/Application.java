package com.ejemplo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "com.ejemplo")
@PropertySource("classpath:application.properties")
public class Application {

    public static void main(String[] args) {

        ApplicationContext context= new AnnotationConfigApplicationContext(Application.class);

        Application app = context.getBean(Application.class);
        app.start();
       
    }

    
    @Autowired
    @Qualifier("MAIL_SERVICE")
    public ServicioMail servicioMail;
   
    
    private void start() {
    	
    	try {
    		
    		//SEND MAILS
    		servicioMail.sendSimpleMailMessage("pepito@gmail.com", "rgomeznippon0@gmail.com", "PRUEBA MAIL ", "Soy un mail de prueba");
			
    		//Indicar ruta y archivo a anexar
    		//servicioMail.sendMessageWithAttachment("rgomeznippon0@gmail.com", "PRUEBA MAIL CON ANEXO", "Soy un mail con anexado ","ruta anexo y archivo");
				
    		//READ MAILS INBOX
			servicioMail.readMailsInbox();
	
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }
}

