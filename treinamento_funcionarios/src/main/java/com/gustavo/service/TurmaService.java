package com.gustavo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gustavo.dto.TurmaDTO;
import com.gustavo.model.Turma;
import com.gustavo.repository.CursoRepository;
import com.gustavo.repository.TurmaRepository;

import java.util.List;

@Service
public class TurmaService {
    @Autowired
    TurmaRepository repository;
    @Autowired
    CursoRepository cursoRepository;

    public List<Turma> procurarTurma(int curso){
        return repository.findByCurso(curso);
    }

    public ResponseEntity<String> salvarTurma(TurmaDTO turma) throws ResponseStatusException{
        if (cursoRepository.findByCodigo(turma.curso())!=null){
            repository.salvarTurma(turma.inicio(), turma.fim(), turma.local_treinamento(), turma.curso());
            return ResponseEntity.ok().build();
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nenhum curso com o código "+turma.curso() +" existente");
    }
}
