package com.gustavo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gustavo.model.Curso;

@Repository
@Transactional
public interface CursoRepository extends CrudRepository<Curso, Integer>{

    @Modifying
    @Query("DELETE FROM curso WHERE codigo = :id")
    void deleteCursoById(@Param("id") Integer id);

    @Modifying
    @Query("INSERT INTO curso (Nome, Descricao, Duracao) VALUES (:nome, :descricao, :duracao)")
    boolean saveCurso(@Param("nome") String nome,@Param("descricao") String descricao,@Param("duracao") int duracao);

    @Query("SELECT * FROM curso ORDER BY Codigo")
    List<Curso> findAll();

    @Query("SELECT * from curso where Codigo = :id")
    Optional<Curso> findByCodigo(@Param("id") int id);

    @Modifying
    @Query("UPDATE curso SET Nome = :nome, Descricao = :descricao, Duracao = :duracao WHERE Codigo = :id")
    boolean updateCursoById(@Param("id") int id, @Param("nome") String nome,@Param("descricao") String descricao,@Param("duracao") int duracao);

    @Query("SELECT LAST_INSERT_ID()")
    int getLastId();
}