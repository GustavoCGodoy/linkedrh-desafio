package com.gustavo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.dto.ParticipanteDTO;
import com.gustavo.dto.ResponseParticipante;
import com.gustavo.model.Participante;
import com.gustavo.service.ParticipanteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {
    
    @Autowired
    private ParticipanteService service;
    
    @GetMapping("/{id_turma}")
    public ResponseEntity<ResponseParticipante> getParticipantes(@PathVariable int id_turma){
        return service.procurarParticipantes(id_turma);
    }

    @PostMapping
    public ResponseEntity<Participante> postParticipante(@RequestBody @Valid ParticipanteDTO participante){
        return service.salvarParticipante(participante);
    }

    @DeleteMapping("/{id_participacao}")
    public ResponseEntity<String> deleteParticipante(@PathVariable int id_participacao){
        return service.deletarParticipante(id_participacao);
    }
}
