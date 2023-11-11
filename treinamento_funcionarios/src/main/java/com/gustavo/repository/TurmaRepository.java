package com.gustavo.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Query("SELECT * FROM turma WHERE Codigo = :id")
    Optional<Turma> findTurmaByCodigo(@Param("id") int id);
    
    @Modifying
    @Query("DELETE from turma WHERE Curso = :id")
    void deleteTurmaByCurso(@Param("id") int id);

    @Modifying
    @Query("INSERT INTO turma (Inicio, Fim, Local_treinamento, Curso) values (:inicio, :fim, :local_treinamento, :curso)")
    boolean saveTurma(@Param("inicio") Date inicio,@Param("fim") Date fim,@Param("local_treinamento") String local_treinamento,@Param("curso") int curso);

    @Query("SELECT LAST_INSERT_ID()")
    int getLastId();

    @Modifying
    @Query("UPDATE turma SET Inicio = :inicio, Fim = :fim, Local_treinamento = :local_treinamento WHERE Codigo = :id")
    boolean updateTurmaById(@Param("id") int codigo, @Param("inicio") Date inicio, @Param("fim") Date fim,@Param("local_treinamento") String local_treinamento);
}
