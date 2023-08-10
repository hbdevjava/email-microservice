package com.hbdev.emailms.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.hbdev.emailms.dtos.EmailDTO;
import com.hbdev.emailms.models.EmailModel;
import com.hbdev.emailms.services.EmailService;

//CLASSE QUE VAI FICAR ESCUTANDO A FILA 

@Component
public class EmailConsumer {//-> SOLICITAÇÕES VIA MENSAGERIA
	
	@Autowired
	EmailService emailService;
	
	//METODO QUE VAI FICAR ESCUTANDO A FILA (OUVINTE)
	 @RabbitListener(queues = "${spring.rabbitmq.queue}")//-> spring.rabbitmq.queue=ms.email DEFINIDA NO ARQUIVO PROPERTIES
	 public void listen(@Payload EmailDTO emailDto) {
		 emailService.sendEmail(emailDto.convertToEmailModel());	
	    }
	//QUEM NAO FOI DEFIDO NENHUM EXCHANGE ENTAO ELE USA EXCHANGE DEFAULT
}

