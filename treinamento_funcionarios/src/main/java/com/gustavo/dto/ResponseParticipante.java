package com.gustavo.dto;

import java.util.List;

import com.gustavo.model.Turma;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseParticipante {

    private Turma turma;
    private List<ResponseFuncionario> participantes;
}
