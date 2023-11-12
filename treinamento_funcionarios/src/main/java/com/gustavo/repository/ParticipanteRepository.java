package com.gustavo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gustavo.model.Participante;

@Repository
public interface ParticipanteRepository extends CrudRepository<Participante, Integer>{

    @Query("SELECT LAST_INSERT_ID()")
    int getLastId();

    @Query("SELECT * FROM turmaparticipante WHERE Turma = :id")
    List<Participante> findParticipantesByTurma(@Param("id") int id);

    @Query("SELECT * FROM turmaparticipante WHERE Codigo = :id")
    List<Participante> findParticipanteByCodigo(@Param("id") int id);

    @Query("SELECT Funcionario FROM turmaparticipante WHERE Codigo = :id")
    int getFuncionarioByParticipanteCodigo(@Param("id") int id);

    @Query("SELECT Count(Codigo) FROM turmaparticipante WHERE Turma = :id")
    int countParticipantes(@Param("id") int id);
    
    @Modifying
    @Query("INSERT INTO turmaparticipante (Turma, Funcionario) values (:turma, :funcionario)")
    boolean saveParticipante(@Param("turma") int turma, @Param("funcionario") int funcionario);

    @Modifying
    @Query("DELETE from turmaparticipante WHERE Codigo = :id")
    boolean deleteParticipanteByCodigo(@Param("id") int id);

    @Modifying
    @Query("DELETE FROM turmaparticipante WHERE Turma = :turma")
    boolean deleteParticipanteByTurma(@Param("turma") int turma);
}
