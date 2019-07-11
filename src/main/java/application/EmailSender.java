package application;

import com.sun.mail.smtp.SMTPTransport;
import java.security.Security;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailSender {
  private static final Logger LOG = LoggerFactory.getLogger(EmailSender.class);

  private final String sender;
  private final String password;
  private final Properties props;

  EmailSender(final String sender, final String password) {
    this.sender = sender;
    this.password = password;
    props = System.getProperties();
    setupEmailProperties();
  }

  private void setupEmailProperties() {
    Security.addProvider(new BouncyCastleProvider());
    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    props.put("mail.smtps.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
    props.put("mail.smtp.socketFactory.fallback", "false");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.socketFactory.port", "587");
    props.put("mail.smtps.auth", "true");
  }

  void sendMail(final String recipient, final String subject, final String message) {
    final Session session = Session.getInstance(props, null);

    try {
      MimeMessage mimeMessage = new MimeMessage(session);
      mimeMessage.setFrom(new InternetAddress(sender));
      mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
      mimeMessage.setSubject(subject);
      mimeMessage.setText(message, "utf-8");

      // Send message
      SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

      t.connect("smtp.gmail.com", sender, password);
      t.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
      t.close();
    } catch (final MessagingException e) {
      LOG.info("Failed to send email to {}", recipient);
      LOG.info(e.getMessage());
    }
  }
}