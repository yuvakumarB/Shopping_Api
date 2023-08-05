package org.shoopingKart.ShoopingApi.service;

import org.shoopingKart.ShoopingApi.dto.EmailConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailSenderService {
	@Autowired
	JavaMailSender sender;

	public String sendEmail(EmailConfiguration configuration) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(configuration.getMerchant().get("email"));
			helper.setText(configuration.getSubject());
			helper.setSubject(configuration.getText());
			sender.send(message);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
			return "mail send";
		} catch (MessagingException e) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
			return "Unable to send mail";
		}
	}

}
