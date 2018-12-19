package cl.altic.dashboardtransit.service;

import javax.mail.MessagingException;

import cl.altic.dashboardtransit.model.ContactoCommand;

public interface SendMailService {
	public void sendMail(ContactoCommand contacto) throws MessagingException;
}
