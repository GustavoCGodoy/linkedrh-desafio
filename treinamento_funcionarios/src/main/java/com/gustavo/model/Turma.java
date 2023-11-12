package com.gustavo.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="turma")
@EqualsAndHashCode(of="codigo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Turma {

    @Id
    private int codigo;
    private LocalDate inicio;
    private LocalDate fim;
    private String local_treinamento;
    private int curso;

}
