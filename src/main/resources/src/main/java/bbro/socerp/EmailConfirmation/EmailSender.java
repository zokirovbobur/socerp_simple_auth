package bbro.socerp.EmailConfirmation;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailSender {

    public void sendMail(String confirmationCode, String userEmail) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("owlconsulting.uzb@gmail.com", "Owlconsulting2018");
            }
        });
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("owlconsulting.uzb@gmail.com", false));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            msg.setSubject("test email");
            //msg.setContent("test email", "text/html");
            msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("<h1 >Confirmation Code: "+confirmationCode+" </h1>", "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();

            //      attachPart.attachFile("/var/tmp/image19.png");
            //    multipart.addBodyPart(attachPart);
            msg.setContent(multipart);
            Transport.send(msg);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }

    }
}
