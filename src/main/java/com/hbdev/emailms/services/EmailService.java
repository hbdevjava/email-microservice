package com.hbdev.emailms.services;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbdev.emailms.enums.StatusEmail;
import com.hbdev.emailms.models.EmailModel;
import com.hbdev.emailms.repositories.EmailRepository;

@Service // -> BEAN DO SPRING TIPO SERVICE
public class EmailService {

	Logger logger = LogManager.getLogger(EmailService.class);

	@Autowired
	EmailRepository emailRepository;

	@Autowired

	private JavaMailSender javaMailSender;

	//@SuppressWarnings("finally")
	@Transactional // -> AE ALGO DE ERRADO ELE FAZ UM RO
	public EmailModel sendEmail(EmailModel emailModel) {
		try {
			
			emailModel.setSenDateEmail(LocalDateTime.now());
			
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmailFrom());
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());
			javaMailSender.send(message);

			emailModel.setStatusEmail(StatusEmail.SENT);
			logger.info("Email sent successfully to: {} ", emailModel.getEmailTo());
		} catch (MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
			logger.error("Email with error: {} ", emailModel.toString());
			logger.error("Error {} ", e);

		} finally {
			emailModel = emailRepository.save(emailModel);
            logger.info("Email saved successfully emailId: {} ", emailModel.getEmailId());
			return emailModel;
		}

	}

	public Page<EmailModel> findAll(Pageable pageable) {
        return  emailRepository.findAll(pageable);
    }

	public Optional<EmailModel> findById(UUID emailId) {
		return emailRepository.findById(emailId);
	}

}
