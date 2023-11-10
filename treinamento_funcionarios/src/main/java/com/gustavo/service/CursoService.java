package com.gustavo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.dto.CursoDTO;
import com.gustavo.model.Curso;
import com.gustavo.repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;

    public List<Curso> listarCursos(){
        return repository.findAll();
    }

    public void salvarCurso(CursoDTO curso){
        repository.save(curso.nome(), curso.descricao(), curso.duracao());
    }
}
