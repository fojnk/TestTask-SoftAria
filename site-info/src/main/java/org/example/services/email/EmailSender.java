package org.example.services.email;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.example.services.loader.ContentLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailSender {
    private static final String MAIL_PROPS = "mail.properties";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    /**
     * send message to recipients
     *
     * @param content    - message text that will be sent
     * @param recipients - a string containing a list of recipients, separated by commas
     */
    public static void sendEmail(String content, String recipients) {
        Properties mailProps = readProperties();
        String fromEmail = mailProps.getProperty(LOGIN);

        Session mailSession = Session.getDefaultInstance(mailProps);
        Message message = null;
        try {
            message = createMessage(mailSession, fromEmail, content, recipients);
            Transport.send(message, fromEmail, mailProps.getProperty(PASSWORD));
        } catch (MessagingException e) {
            System.out.println("Failed on email sending: " + e.getMessage());
        }

    }

    /**
     * load mail properties from source file
     *
     * @return - mail properties
     */
    private static Properties readProperties() {
        final Properties mailProps = new Properties();
        try (InputStream input = ContentLoader.class.getClassLoader().getResourceAsStream(MAIL_PROPS)) {
            mailProps.load(input);
        } catch (IOException e) {
            System.out.println("Failed on loading mail properties: " + e.getMessage());
        }
        return mailProps;
    }

    private static Message createMessage(Session mailSession, String fromEmail, String msg, String recipients)
            throws MessagingException {

        Message message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(recipients));
        message.setSubject("Report");

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        return message;
    }
}
