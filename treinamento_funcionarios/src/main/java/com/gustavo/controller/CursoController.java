package com.gustavo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.gustavo.dto.CursoDTO;
import com.gustavo.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    CursoService service;

    @GetMapping
    public ResponseEntity getAllCursos(){
        var allCursos = service.listarCursos();
        return ResponseEntity.ok(allCursos);
    }

    @PostMapping
    public ResponseEntity postCurso(@RequestBody @Valid CursoDTO curso){
        service.salvarCurso(curso);
        return ResponseEntity.ok().build();
    }
}
