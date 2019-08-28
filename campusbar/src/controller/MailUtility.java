/*package controller;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class MailUtility
{
  public static void sendMail (String dest, String testoEmail)
      throws MessagingException
  {
    // Creazione di una mail session
	  final String username = "campusBarUnisa@gmail.com";
      final String password = "vincenzogay";

      Properties props = new Properties();
      props.put("mail.smtp.host", "smtp.mioprovider.it");
      Session session = Session.getDefaultInstance(props);

      
    // Creazione del messaggio da inviare
   MimeMessage message = new MimeMessage(session);
    message.setSubject("recupero password campusBar");
    message.setText("");

    // Aggiunta degli indirizzi del mittente e del destinatario
    InternetAddress fromAddress = new InternetAddress("campusBarUnisa@gmail.com");
    InternetAddress toAddress = new InternetAddress("");
    message.setFrom(fromAddress);
    message.setRecipient(Message.RecipientType.TO, toAddress);

    // Invio del messaggio
    Transport.send(message);
  }
}  

*/