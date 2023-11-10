package com.gustavo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gustavo.model.Curso;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Integer>{

    @Query("DELETE FROM curso WHERE codigo = :id")
    void deleteById(@Param("id") Integer id);

    @Modifying
    @Query("INSERT INTO curso (Nome, Descricao, Duracao) VALUES (:nome, :descricao, :duracao)")
    void save(@Param("nome") String nome,@Param("descricao") String descricao,@Param("duracao") int duracao);

    @Query("SELECT * FROM curso")
    List<Curso> findAll();

}