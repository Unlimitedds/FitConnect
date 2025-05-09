package com.training.microservice.course_service.service;

import com.training.microservice.course_service.model.Kurs;
import com.training.microservice.course_service.repository.KursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KursService {

    @Autowired
    private KursRepository kursRepository;

    public List<Kurs> alleKurse() {
        return kursRepository.findAll();
    }

    public Optional<Kurs> findeNachId(Long id) {
        return kursRepository.findById(id);
    }

    public Kurs speichern(Kurs kurs) {
        // Keine ID setzen! JPA generiert automatisch
        return kursRepository.save(kurs);
    }
}
