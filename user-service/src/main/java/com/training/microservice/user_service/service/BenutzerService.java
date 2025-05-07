package com.training.microservice.user_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.microservice.user_service.model.Benutzer;
import com.training.microservice.user_service.repository.BenutzerRepository;

@Service
public class BenutzerService {

		@Autowired
		private BenutzerRepository repository;
	
		public Benutzer speichern (Benutzer benutzer) {
			return repository.save(benutzer);
		}
		public List<Benutzer> alleBenutzer() {
			return repository.findAll();
		}
}