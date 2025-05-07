package com.training.microservice.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.microservice.user_service.model.Benutzer;
import com.training.microservice.user_service.service.BenutzerService;

@RestController
@RequestMapping("/api/benutzer")
public class BenutzerController {

	//http:localhost:8081/api/benutzer/alle
	
	@Autowired
	private BenutzerService benutzerService;
	
	@GetMapping
	public List<Benutzer> alle() {
		return benutzerService.alleBenutzer();
	}
}
