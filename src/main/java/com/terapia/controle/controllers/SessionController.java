package com.terapia.controle.controllers;

import com.terapia.controle.models.Session;
import com.terapia.controle.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping
    public List<Session> findAll() {
        return sessionService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Session> findById(@PathVariable Integer id) {
        return sessionService.findById(id);
    }

    @PostMapping
    public Session save(@RequestBody Session session) {
        return sessionService.save(session);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (sessionService.findById(id).isPresent()) {
            sessionService.delete(id);
            return ResponseEntity.ok("Deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Session> update(@PathVariable Integer id, @RequestBody Session sessionDetails) {
        if (sessionService.findById(id).isPresent()) {
            Session updatedSession = new Session(
                    id,
                    sessionDetails.getPatientId(),
                    sessionDetails.getSessionDate(),
                    sessionDetails.getNote(),
                    sessionDetails.getSessionStatus()
            );
            return ResponseEntity.ok(sessionService.save(updatedSession));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
