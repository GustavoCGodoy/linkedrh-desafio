package com.gustavo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.service.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    TurmaService service;

    @GetMapping("/{id}")
    public ResponseEntity buscarTurma(@PathVariable int id){
        var Turma = service.procurarTurma(id);
        return ResponseEntity.ok(Turma);
    }
}
