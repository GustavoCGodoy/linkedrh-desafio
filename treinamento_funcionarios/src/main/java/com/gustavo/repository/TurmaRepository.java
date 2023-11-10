package com.gustavo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gustavo.model.Turma;

@Repository
public interface TurmaRepository extends CrudRepository<Turma, Integer>{

    @Query("SELECT * FROM turma WHERE Curso = :id")
    Turma findByCurso(@Param("id") int curso);
}
