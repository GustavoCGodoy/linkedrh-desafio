package com.gustavo.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="turmaparticipante")
@EqualsAndHashCode(of="codigo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Participante {
    
    @Id
    private int codigo;
    private int turma;
    private int funcionario;
}
