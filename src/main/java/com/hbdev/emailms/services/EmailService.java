package com.hbdev.emailms.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hbdev.emailms.enums.StatusEmail;
import com.hbdev.emailms.models.EmailModel;
import com.hbdev.emailms.repositories.EmailRepository;

@Service // -> BEAN DO SPRING TIPO SERVICE
public class EmailService {

	@Autowired
	EmailRepository emailRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@SuppressWarnings("finally")
	public EmailModel sendEmail(EmailModel emailModel) {
		emailModel.setSenDateEmail(LocalDateTime.now());
	try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmailFrom());
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());
			javaMailSender.send(message);
			
			emailModel.setStatusEmail(StatusEmail.SENT);
		
	} catch(MailException e){
		
			emailModel.setStatusEmail(StatusEmail.ERROR);
	} finally {
			 
			return emailRepository.save(emailModel);
		}
	
	}

}



























