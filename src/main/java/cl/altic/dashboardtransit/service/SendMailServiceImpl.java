package cl.altic.dashboardtransit.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.altic.dashboardtransit.model.ContactoCommand;

@Service
public class SendMailServiceImpl implements SendMailService {

    Logger logger = LoggerFactory.getLogger(SendMailServiceImpl.class);
	public void sendMail1(ContactoCommand contacto) {

	    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
	    String remitente = "altic.mensajería";  //Para la dirección nomcuenta@gmail.com

	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", "altic2018");    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(contacto.getEmail(), true));   //Se podrían añadir varios de la misma manera
	        message.setSubject(contacto.getAsunto());
	        message.setText(contacto.getMensaje());
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, "altic2018");
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	}

	@Override
    public void sendMail(ContactoCommand contacto) throws MessagingException {
        //Setting up configurations for the email connection to the Google SMTP server using TLS
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("altic.mensajeria@gmail.com", "altic2018");
            }
        });
        try {
            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(session);
            //Storing the comma seperated values to email addresses
//            String to = contacto.getEmail();
//            String to = "dashboard.transporte.uc@gmail.com";
            String to = "luis.sepulveda.a@gmail.com,contacto@altic.cl";
            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
            InternetAddress[] address = InternetAddress.parse(to, true);
            //Setting the recepients from the address variable
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setFrom(new InternetAddress(contacto.getEmail()));
//            msg.setFrom(contacto.getNombre());
            msg.setReplyTo(InternetAddress.parse(contacto.getEmail(), true));
//            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
            msg.setSubject(contacto.getAsunto());
            msg.setSentDate(new Date());
            StringBuilder mensaje = new StringBuilder("De: ");
            mensaje.append(contacto.getNombre());
            mensaje.append("\r\n");
            mensaje.append("\r\n");
            mensaje.append("Mensaje: ");
            mensaje.append("\r\n");
            mensaje.append(contacto.getMensaje());
            msg.setText(mensaje.toString());
            msg.setHeader("XPriority", "1");
            Transport.send(msg);
            logger.info("Mail has been sent successfully");
        } catch (MessagingException mex) {
            logger.error("Unable to send an email" + mex);
            throw mex;
        }
    }
}
