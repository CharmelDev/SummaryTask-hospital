package ua.nure.biletska.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class SendMailSSL {

private static final Logger log = Logger.getLogger(SendMailSSL.class);

	public static void SendMailToUser(String email, String messageToUser) {
		log.trace("Command start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("charma2019@gmail.com","forex2025");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("charma2019@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Discharging information");
			message.setText(messageToUser);

			Transport.send(message);
			log.debug("Message send to patient: " + email);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		log.trace("Commands end");
	}
}	