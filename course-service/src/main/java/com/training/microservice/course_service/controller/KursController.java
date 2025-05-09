package com.training.microservice.course_service.controller;

import com.training.microservice.course_service.model.Benutzer;
import com.training.microservice.course_service.model.Kurs;
import com.training.microservice.course_service.service.KursService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kurs")
@CrossOrigin(origins = "*") // Optional für CORS
public class KursController {

    private static final Logger logger = LoggerFactory.getLogger(KursController.class);

    @Autowired
    private KursService kursService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Ruft einen Benutzer über einen anderen Microservice ab.
     */
    public Benutzer getUserById(String id) {
        String url = "http://localhost:8081/api/benutzer/" + id;
        try {
            Benutzer benutzer = restTemplate.getForObject(url, Benutzer.class);
            logger.info("Benutzer erfolgreich geladen: {}", benutzer);
            return benutzer;
        } catch (Exception e) {
            logger.error("Fehler beim Laden des Benutzers mit ID {}: {}", id, e.getMessage());
            return null;
        }
    }

    /**
     * Gibt alle Kurse zurück.
     */
    @GetMapping
    public List<Kurs> alle() {
        Benutzer benutzer = getUserById("1");
        if (benutzer == null) {
            logger.warn("Benutzer konnte nicht geladen werden.");
        }
        return kursService.alleKurse();
    }

    /**
     * Gibt einen Kurs anhand seiner ID zurück.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Kurs> findeNachId(@PathVariable Long id) {
        logger.info("Suche Kurs mit ID: {}", id);
        Optional<Kurs> kurs = kursService.findeNachId(id);
        return kurs.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Erstellt einen neuen Kurs.
     */
    @PostMapping
    public ResponseEntity<Kurs> erzeugen(@RequestBody Kurs kurs) {
        logger.info("Neuer Kurs wird gespeichert: {}", kurs);
        Kurs gespeichert = kursService.speichern(kurs);
        return ResponseEntity.ok(gespeichert);
    }
}
