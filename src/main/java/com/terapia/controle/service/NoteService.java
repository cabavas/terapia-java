package com.terapia.controle.service;

import com.terapia.controle.models.Note;
import com.terapia.controle.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Optional<Note> findById(Integer id) {
        return  noteRepository.findById(id);
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public void delete(Integer id) {
        noteRepository.deleteById(id);
    }

    public List<Note> findByPatientId(Integer patientId) {
        return noteRepository.findByPatientId(patientId);
    }
}
