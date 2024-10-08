package com.terapia.controle.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patientId;

    @Column(name = "session_date")
    private LocalDate sessionDate;

    @OneToOne
    @JoinColumn(name = "note")
    private Note note;

    @Column(name = "session_status")
    private String sessionStatus;

    public Session() {
    }

    public Session(Integer id, Patient patientId, LocalDate sessionDate, Note note, String sessionStatus) {
        this.id = id;
        this.patientId = patientId;
        this.sessionDate = sessionDate;
        this.note = note;
        this.sessionStatus = sessionStatus;
    }

    public Integer getId() {
        return id;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
