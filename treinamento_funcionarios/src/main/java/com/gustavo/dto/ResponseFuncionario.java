package com.gustavo.dto;

import com.gustavo.model.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFuncionario {
    private int codigo;
    private Funcionario funcionario;
}
