package com.hbdev.emailms.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbdev.emailms.dtos.EmailDTO;
import com.hbdev.emailms.models.EmailModel;
import com.hbdev.emailms.services.EmailService;

import jakarta.validation.Valid;

@RestController//-> BEAN DO SPRING TIPO CONTROLLER
@RequestMapping("/api/sending-email")
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@PostMapping
	public  ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDTO emailDTO) {
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDTO, emailModel);
		emailService.sendEmail(emailModel);
		return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
		
	}
	
}
