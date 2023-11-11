package com.gustavo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.dto.PatchTurmaDTO;
import com.gustavo.dto.TurmaDTO;
import com.gustavo.model.Turma;
import com.gustavo.service.TurmaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    TurmaService service;

    @GetMapping("/{id_curso}")
    public ResponseEntity<List<Turma>> getTurma(@PathVariable int id_curso){
        return ResponseEntity.ok(service.procurarTurma(id_curso));
    }

    @PostMapping
    public ResponseEntity<Optional<Turma>> postTurma(@RequestBody @Valid TurmaDTO turma){
        return service.salvarTurma(turma);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Turma> patchTurma(@PathVariable int id, @RequestBody @Valid PatchTurmaDTO turma){
        System.out.println(turma);
        return service.atualizarTurma(id, turma);
    }
}
