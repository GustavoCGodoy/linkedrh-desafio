package com.gustavo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gustavo.dto.CursoDTO;
import com.gustavo.model.Curso;
import com.gustavo.repository.CursoRepository;
import com.gustavo.repository.TurmaRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;
    @Autowired
    private TurmaRepository turmaRepository;

    public List<Curso> listarCursos(){
        return repository.findAll();
    }

    public ResponseEntity<String> salvarCurso(CursoDTO curso){
        repository.save(curso.nome(), curso.descricao(), curso.duracao());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public Optional<Curso> procurarPorCodigo(int id){
            return repository.findByCodigo(id);
        }

    public ResponseEntity<String> deletarCurso(int id) throws ResponseStatusException{
        if (repository.findByCodigo(id).isPresent()){
            turmaRepository.deleteTurmaByCurso(id);
            repository.deleteCursoById(id);
            return ResponseEntity.ok().build();
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum curso com código "+id+" encontrado.");
    }
}
