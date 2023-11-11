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

import com.gustavo.dto.CursoDTO;
import com.gustavo.model.Curso;
import com.gustavo.repository.CursoRepository;
import com.gustavo.repository.ParticipanteRepository;
import com.gustavo.repository.TurmaRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;
    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private ParticipanteRepository participanteRepository;

    public List<Curso> listarCursos(){
        return repository.findAll();
    }

    public ResponseEntity<Optional<Curso>> salvarCurso(CursoDTO curso){
        repository.saveCurso(curso.nome(), curso.descricao(), curso.duracao());
        Optional<Curso> newCurso = repository.findByCodigo(repository.getLastId());
        URI uri = UriComponentsBuilder.fromPath("localhost:8080/cursos").build().toUri();
        return ResponseEntity.created(uri).body(newCurso);
    }

    public Optional<Curso> procurarPorCodigo(int id){
            return repository.findByCodigo(id);
        }

    public ResponseEntity<String> deletarCurso(int id) throws ResponseStatusException{
        if (repository.findByCodigo(id).isPresent()){
            List<Integer> turmasApagadas = turmaRepository.findCodigoByCurso(id);
            for (Integer integer : turmasApagadas) {
                participanteRepository.deleteParticipanteByTurma(integer.intValue());
            }
            turmaRepository.deleteTurmaByCurso(id);
            repository.deleteCursoById(id);
            return ResponseEntity.ok().build();
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum curso com código "+id+" encontrado.");
    }

    public ResponseEntity<Curso> atualizarCurso(int id, CursoDTO curso) throws ResponseStatusException{
        if (repository.findByCodigo(id).isPresent()){
            repository.updateCursoById(id, curso.nome(), curso.descricao(), curso.duracao());

            return ResponseEntity.ok(repository.findByCodigo(id).get());
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum curso com código "+id+" encontrado.");
    }
}
