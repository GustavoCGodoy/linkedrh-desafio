package com.gustavo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gustavo.model.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer>{

    @Query("SELECT * FROM funcionario WHERE Codigo = :id")
    Funcionario findFuncionarioByCodigo(@Param("id") int id);
}