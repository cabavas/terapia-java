package com.terapia.controle.controllers;

import com.terapia.controle.models.Note;
import com.terapia.controle.models.Patient;
import com.terapia.controle.service.NoteService;
import com.terapia.controle.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Note> findAll() {
        return noteService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Note> findById(@PathVariable Integer id) {
        return noteService.findById(id);
    }

    @PostMapping
    public Note save(@RequestBody Note note) {
        return noteService.save(note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable Integer id, @RequestBody Note noteDetails) {
        Optional<Note> noteOptional = noteService.findById(id);
        if (noteOptional.isPresent()) {
            Note updatedNote = noteOptional.get();
            updatedNote.setBody(noteDetails.getBody());
            updatedNote.setDate(noteDetails.getDate());

            if (noteDetails.getPatient() != null && noteDetails.getPatient().getId() != null) {
                Integer patientId = noteDetails.getPatient().getId();
                Optional<Patient> patientOptional = patientService.findById(patientId);
                if (patientOptional.isPresent()) {
                    updatedNote.setPatient(patientOptional.get());
                } else {
                    return ResponseEntity.badRequest().body(null);
                }
            }
            return ResponseEntity.ok(noteService.save(updatedNote));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (noteService.findById(id).isPresent()) {
            noteService.delete(id);
            return ResponseEntity.ok("Note deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
