package com.hbdev.emailms.dtos;





import org.springframework.beans.BeanUtils;

import com.hbdev.emailms.models.EmailModel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class EmailDTO {

	@NotBlank
	private String ownertRef;//-> REFERENCIA DO PROPRIETARIO QUE ESTA ENVIANDO ESSA MSG
	@NotBlank
	@Email
	private String emailFrom;//-> QUEM ESTA MANDANDO ESSE EMAIL
	@NotBlank
	@Email
	private String emailTo;//-> PARA QUEM SERA ENVIADO ESSE EMAIL
	@NotBlank
	private String subject;//-> TITULO DO EMAIL
	@NotBlank
	private String text;
	
	
	 public EmailModel convertToEmailModel(){
	        var emailModel = new EmailModel();
	        BeanUtils.copyProperties(this, emailModel);
	        return emailModel;
	    }

	
	
}
