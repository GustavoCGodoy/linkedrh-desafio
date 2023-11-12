package com.gustavo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="curso")
@EqualsAndHashCode(of="codigo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    
    @Id
    private int codigo;
    private String nome;
    private String descricao;
    private int duracao;
}
