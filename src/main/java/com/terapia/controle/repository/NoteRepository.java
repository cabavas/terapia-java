package com.terapia.controle.repository;

import com.terapia.controle.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByPatientId(Integer patientId);
}
