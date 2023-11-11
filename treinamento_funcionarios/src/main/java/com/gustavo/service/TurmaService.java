package com.gustavo.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.gustavo.dto.PatchTurmaDTO;
import com.gustavo.dto.TurmaDTO;
import com.gustavo.model.Turma;
import com.gustavo.repository.CursoRepository;
import com.gustavo.repository.TurmaRepository;

@Service
public class TurmaService {
    @Autowired
    TurmaRepository repository;
    @Autowired
    CursoRepository cursoRepository;

    public List<Turma> procurarTurma(int curso){
        return repository.findByCurso(curso);
    }

    public ResponseEntity<Optional<Turma>> salvarTurma(TurmaDTO turma) throws ResponseStatusException{
        if ((cursoRepository.findByCodigo(turma.curso()).isPresent())&&(repository.saveTurma(turma.inicio(), turma.fim(), turma.local_treinamento(), turma.curso()))){
            Optional<Turma> newTurma = repository.findById(repository.getLastId());
            URI uri = UriComponentsBuilder.fromPath("localhost:8080/turmas/{codigo}").buildAndExpand(turma.curso()).toUri();
            return ResponseEntity.created(uri).body(newTurma);
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nenhum curso com o código "+turma.curso() +" existente");
    }

    public ResponseEntity<Turma> atualizarTurma(int id, PatchTurmaDTO turma){
        if ((repository.findTurmaByCodigo(id).isPresent())&&(repository.updateTurmaById(id, turma.inicio(), turma.fim(), turma.local_treinamento()))){
            Turma updateTurma = repository.findTurmaByCodigo(id).get();
            URI uri = UriComponentsBuilder.fromPath("localhost:8080/turmas/{id}").buildAndExpand(updateTurma.getCurso()).toUri();
            return ResponseEntity.created(uri).body(updateTurma);
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma turma possui o código "+id);
    } 
}
