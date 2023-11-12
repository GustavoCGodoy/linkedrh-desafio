package com.gustavo.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.gustavo.dto.ParticipanteDTO;
import com.gustavo.dto.ResponseFuncionario;
import com.gustavo.dto.ResponseParticipante;
import com.gustavo.model.Participante;
import com.gustavo.repository.FuncionarioRepository;
import com.gustavo.repository.ParticipanteRepository;
import com.gustavo.repository.TurmaRepository;

@Service
public class ParticipanteService {
    @Autowired
    private ParticipanteRepository repository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired TurmaService turmaService;

    public ResponseEntity<ResponseParticipante> procurarParticipantes(int id){
        turmaService.verificarExistenciaTurma(id);
        List<Participante> participantes = repository.findParticipantesByTurma(id);

        if(participantes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<ResponseFuncionario> funcionarios = new ArrayList<ResponseFuncionario>();
        ResponseParticipante responseParticipante = new ResponseParticipante();

        for (Participante participante : participantes) {
            ResponseFuncionario tempFuncionario = new ResponseFuncionario(participante.getCodigo(),funcionarioRepository.findFuncionarioByCodigo(participante.getFuncionario()));
            funcionarios.add(tempFuncionario);
        }

        responseParticipante.setTurma(turmaRepository.findTurmaByCodigo(id).get(0));
        responseParticipante.setParticipantes(funcionarios);
        return ResponseEntity.ok(responseParticipante);
    }

    public ResponseEntity<Participante> salvarParticipante(ParticipanteDTO participante){
        turmaService.verificarExistenciaTurma(participante.turma());
        if(repository.verifyParticipanteExistence(participante.turma(), participante.funcionario()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Funcionário "+participante.funcionario()+" já participa da Turma "+participante.turma());
        }
        repository.saveParticipante(participante.turma(), participante.funcionario());
        Participante newParticipante = repository.findParticipanteByCodigo(repository.getLastId()).get(0);
        URI uri = UriComponentsBuilder.fromPath("localhost:8080/participantes/{turma_id}").buildAndExpand(participante.turma()).toUri();
        return ResponseEntity.created(uri).body(newParticipante);
    }


    public ResponseEntity<String> deletarParticipante(int id){

        if(repository.findParticipanteByCodigo(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nenhum participante de código "+id+" encontrado.");
        }

        repository.deleteParticipanteByCodigo(id);
        return ResponseEntity.ok().build();
    }
    
}
