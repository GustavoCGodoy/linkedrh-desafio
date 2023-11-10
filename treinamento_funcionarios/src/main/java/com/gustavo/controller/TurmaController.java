package com.gustavo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.dto.TurmaDTO;
import com.gustavo.model.Turma;
import com.gustavo.service.TurmaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    TurmaService service;

    @GetMapping("/{id}")
    public ResponseEntity<List<Turma>> buscarTurma(@PathVariable int id){
        return ResponseEntity.ok(service.procurarTurma(id));
    }

    @PostMapping
    public ResponseEntity<String> criarTurma(@RequestBody @Valid TurmaDTO turma){
        return service.salvarTurma(turma);
    }
}
