package com.training.microservice.user_service.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.training.microservice.user_service.model.Benutzer;
import com.training.microservice.user_service.service.BenutzerService;

@CrossOrigin(origins = "*") // Wichtig für Browser-Zugriff über Apache (CORS-Freigabe)
@RestController
@RequestMapping("/api/benutzer")
public class BenutzerController {

    @Autowired
    private BenutzerService benutzerService;

    // Alle Benutzer abrufen
    @GetMapping
    public List<Benutzer> alle() {
        return benutzerService.alleBenutzer();
    }

    // Benutzer nach ID abrufen
    @GetMapping("/{id}")
    public ResponseEntity<Benutzer> findeNachId(@PathVariable("id") String benutzerId) {
        return benutzerService.findeNachId(benutzerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Benutzer erstellen
    @PostMapping
    public ResponseEntity<Benutzer> erzeugen(@RequestBody Benutzer benutzer) {
        try {
            Benutzer gespeichert = benutzerService.speichern(benutzer);
            return ResponseEntity.ok(gespeichert);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Benutzer löschen
    @DeleteMapping("/{id}")
    public ResponseEntity<?> loesche(@PathVariable String id) {
        try {
            benutzerService.loeschen(id);
            return ResponseEntity.noContent().build(); // 204 OK
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    // Benutzer aktualisieren
    @PutMapping("/{id}")
    public ResponseEntity<?> aktualisieren(@PathVariable String id, @RequestBody Benutzer benutzer) {
        try {
            Benutzer aktualisiert = benutzerService.aktualisieren(Long.valueOf(id), benutzer);
            return ResponseEntity.ok(aktualisiert); // 200 OK
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
