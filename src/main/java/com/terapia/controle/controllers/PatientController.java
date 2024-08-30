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
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatientById(@PathVariable Integer id) {
        return patientService.findById(id);
    }

    @GetMapping("/{id}/notes")
    public ResponseEntity<List<Note>> getPatientNotes(@PathVariable Integer id) {
        Optional<Patient> patient = patientService.findById(id);
        if(patient.isPresent()) {
            List<Note> notes = noteService.findByPatientId(patient.get().getId());
            return ResponseEntity.ok().body(notes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.save(patient);
        return ResponseEntity.ok(savedPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @RequestBody Patient patientDetails) {
        Optional<Patient> patient = patientService.findById(id);
        if(patient.isPresent()) {
            Patient updatedPatient = patient.get();
            updatedPatient.setName(patientDetails.getName());
            updatedPatient.setBirthday(patientDetails.getBirthday());
            updatedPatient.setInstallment(patientDetails.getInstallment());
            updatedPatient.setStartDate(patientDetails.getStartDate());
            updatedPatient.setLastPayment(patientDetails.getLastPayment());
            return ResponseEntity.ok(patientService.save(updatedPatient));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Integer id) {
        if(patientService.findById(id).isPresent()) {
            patientService.delete(id);
            return ResponseEntity.ok("Patient deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
