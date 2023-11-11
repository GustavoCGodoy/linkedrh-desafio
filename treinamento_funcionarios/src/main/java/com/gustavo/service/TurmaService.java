package com.gustavo.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.gustavo.dto.PatchTurmaDTO;
import com.gustavo.dto.ResponseTurma;
import com.gustavo.dto.TurmaDTO;
import com.gustavo.model.Turma;
import com.gustavo.repository.CursoRepository;
import com.gustavo.repository.ParticipanteRepository;
import com.gustavo.repository.TurmaRepository;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository repository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private ParticipanteRepository participanteRepository;
    private List<ResponseTurma> responseTurma;


    public ResponseEntity<List<ResponseTurma>> procurarTurma(int curso) throws ResponseStatusException{
        List<Turma> turmas = repository.findByCurso(curso);
        if (turmas.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhuma turma encontrada com o curso de código "+curso+" encontrada");
        }
        responseTurma = new ArrayList<ResponseTurma>();
        for (Turma turma : turmas) {
            responseTurma.add(new ResponseTurma(turma,participanteRepository.countParticipantes(turma.getCodigo())));
        }
        return ResponseEntity.ok(responseTurma);
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
        if ((repository.findTurmaByCodigo(id).isEmpty())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Turma com o código "+id+" inexistente");
        }
        else{
            repository.updateTurmaById(id, turma.inicio(), turma.fim(), turma.local_treinamento());
            Turma updateTurma = repository.findTurmaByCodigo(id).get(0);
            URI uri = UriComponentsBuilder.fromPath("localhost:8080/turmas/{id}").buildAndExpand(updateTurma.getCurso()).toUri();
            return ResponseEntity.created(uri).body(updateTurma);
        } 
    }



    public ResponseEntity<String> deletarTurma(int id){
        if ((repository.findTurmaByCodigo(id).isEmpty())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Turma com o código "+id+" inexistente");
        }
        else{
            participanteRepository.deleteParticipanteByTurma(id);
            repository.deleteTurmaByCodigo(id);
            return ResponseEntity.ok().build();
        }
    }

    
}
