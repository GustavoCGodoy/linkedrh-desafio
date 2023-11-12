package com.gustavo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.dto.CursoDTO;
import com.gustavo.model.Curso;
import com.gustavo.service.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    
    @Autowired
    CursoService service;

    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos(){
        return service.listarCursos();
    }

    @PostMapping
    public ResponseEntity<Optional<Curso>> postCurso(@RequestBody @Valid CursoDTO curso){
        return service.salvarCurso(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCurso(@PathVariable int id){
        return service.deletarCurso(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> putCurso(@PathVariable int id, @RequestBody @Valid CursoDTO cursoDTO){
        return service.atualizarCurso(id, cursoDTO);
    }
}
