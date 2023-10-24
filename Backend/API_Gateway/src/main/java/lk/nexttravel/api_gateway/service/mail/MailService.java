package lk.nexttravel.api_gateway.service.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lk.nexttravel.api_gateway.dto.mail.MailDTO;
import lk.nexttravel.api_gateway.util.builders.MailBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/15/2023
 * Time    : 7:23 AM
 */
@Service
public class MailService {
    @Autowired()
    private JavaMailSender mailSender;

    public boolean sendEmailForNewUserSignup(String address, String name) {
        try{
            final MailDTO mail = new MailBuilder()
                    .From("nexttravelcompany@gmail.com") // For gmail, this field is ignored.
                    .To(address)
                    .Template("mail-template-register-success.html")
                    .AddContext("name", name)
                    .Subject("Your Registration Confirmed!")
                    .createMail();
            sendHTMLEmail(mail);

            return true;
        }catch (Exception e){
            return false;
        }


    }

    public void sendHTMLEmail(MailDTO message) throws MessagingException {
        MimeMessage emailMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailBuilder = new MimeMessageHelper(emailMessage, true);
        mailBuilder.setTo(message.getMailTo());
        mailBuilder.setFrom("nexttravelcompany@gmail.com");
        mailBuilder.setText(message.getMailContent(), true);
        mailBuilder.setSubject(message.getMailSubject());
        mailSender.send(emailMessage);
    }


}
