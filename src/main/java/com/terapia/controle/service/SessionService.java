package com.terapia.controle.service;

import com.terapia.controle.models.Session;
import com.terapia.controle.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    public Optional<Session> findById(Integer id) {
        return sessionRepository.findById(id);
    }

    public Session save(Session session) {
        return sessionRepository.save(session);
    }

    public void delete(Integer id) {
        sessionRepository.deleteById(id);
    }
}
