package com.gustavo.dto;

import com.gustavo.model.Turma;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTurma {
    private Turma turma;
    private int participantes;
}
