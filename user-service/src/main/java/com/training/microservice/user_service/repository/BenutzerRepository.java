package com.training.microservice.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.microservice.user_service.model.Benutzer;

public interface BenutzerRepository extends JpaRepository<Benutzer, Long>{

}
