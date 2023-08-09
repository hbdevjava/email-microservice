package com.hbdev.emailms.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.hbdev.emailms.enums.StatusEmail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data

@Table(name = "TB_EMAIL")
public class EmailModel implements Serializable {
		
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID emailId;
	private String ownertRef;//-> REFERENCIA DO PROPRIETARIO QUE ESTA ENVIANDO ESSA MSG
	private String emailFrom;//-> QUEM ESTA MANDANDO ESSE EMAIL
	private String emailTo;//-> PARA QUEM SERA ENVIADO ESSE EMAIL
	private String subject;//-> TITULO DO EMAIL
	
	@Column(columnDefinition = "TEXT")//-> CORPO DO EMAIL
	//columnDefinition = "TEXT") -> CONSEGUE INSERIR MAIS CARACTERES CASO HAJA NECESSIDADE 
	private String text;
	private LocalDateTime senDateEmail;
	private StatusEmail statusEmail;
}
