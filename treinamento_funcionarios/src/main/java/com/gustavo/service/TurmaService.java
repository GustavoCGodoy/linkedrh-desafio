package com.gustavo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.model.Turma;
import com.gustavo.repository.TurmaRepository;

@Service
public class TurmaService {
    @Autowired
    TurmaRepository repository;

    public Turma procurarTurma(int curso){
        return repository.findByCurso(curso);
    }
}
