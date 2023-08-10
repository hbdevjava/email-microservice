package com.hbdev.emailms.controllers;


import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;







import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	Logger logger = LogManager.getLogger(EmailController.class);//-> (EmailController.class) CLASSE QUE VAI GERAR ESSE LOGGER
	
	@Autowired
	EmailService emailService;
	
	 @PostMapping
	    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDTO emailDto) {
	        return new ResponseEntity<>(emailService.sendEmail(emailDto.convertToEmailModel()), HttpStatus.CREATED);
	    }
	 
	 @GetMapping
	    public ResponseEntity<Page<EmailModel>> getAllEmails(@PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable){
	        logger.trace("TRACE");//-> 	UNICO EM TODAS AS CHAMDAS
	        logger.debug("DEBUG");
	        logger.info("INFO");
	        logger.warn("WARN");//-> MOSTRA ALGO EM ESTADO DE ALERTA
	        logger.error("ERROR");//-> QUANDO OCORRE ALGO INESPERADO
	        logger.fatal("FATAL");//->ERROR BEM CRITICO (ERROR NO SISTEMA)
	        return new ResponseEntity<>(emailService.findAll(pageable), HttpStatus.OK);
	    }

	    @GetMapping("{emailId}")
	    public ResponseEntity<Object> getOneEmail(@PathVariable(value="emailId") UUID emailId){
	        Optional<EmailModel> emailModelOptional = emailService.findById(emailId);
	        if(!emailModelOptional.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
	        }else {
	            return ResponseEntity.status(HttpStatus.OK).body(emailModelOptional.get());
	        }
	    }

	
}
