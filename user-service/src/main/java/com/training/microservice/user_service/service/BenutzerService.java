package com.training.microservice.user_service.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.training.microservice.user_service.model.Benutzer;
import com.training.microservice.user_service.repository.BenutzerRepository;

@Service
public class BenutzerService {

    @Autowired
    private BenutzerRepository repository;

    public List<Benutzer> alleBenutzer() {
        return repository.findAll();
    }

    public Optional<Benutzer> findeNachId(String benutzerId) {
        return repository.findById(Long.valueOf(benutzerId));
    }

    public Benutzer speichern(Benutzer benutzer) {
        return repository.save(benutzer);
    }

    public void loeschen(String benutzerId) {
        try {
            repository.deleteById(Long.valueOf(benutzerId));
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Benutzer mit ID " + benutzerId + " existiert nicht");
        }
    }

    public Benutzer aktualisieren(Long id, Benutzer neuerBenutzer) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Benutzer mit ID " + id + " existiert nicht");
        }
        neuerBenutzer.setId(id);
        return repository.save(neuerBenutzer);
    }
}
