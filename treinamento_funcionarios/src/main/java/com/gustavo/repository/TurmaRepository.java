package com.gustavo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gustavo.model.Turma;

@Repository
public interface TurmaRepository extends CrudRepository<Turma, Integer>{

    @Query("SELECT * FROM turma WHERE Curso = :id")
    List<Turma> findByCurso(@Param("id") int curso);

    @Modifying
    @Query("DELETE from turma WHERE Curso = :id")
    void deleteTurmaByCurso(@Param("id") int id);

    @Modifying
    @Query("INSERT INTO turma (Inicio, Fim, Local_treinamento, Curso) values (:inicio, :fim, :local_treinamento, :curso)")
    void salvarTurma(@Param("inicio") Date inicio,@Param("fim") Date fim,@Param("local_treinamento") String local_treinamento,@Param("curso") int curso);
}
