package com.hbdev.emailms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hbdev.emailms.models.EmailModel;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, Long> {

}
