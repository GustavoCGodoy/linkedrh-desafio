package com.gustavo.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="funcionario")
@EqualsAndHashCode(of="codigo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    @Id
    private int codigo;
    private String nome;
    private String cpf;
    private LocalDate nascimento;
    private String cargo;
    private LocalDate admissao;
    private boolean status_funcionario;
}
