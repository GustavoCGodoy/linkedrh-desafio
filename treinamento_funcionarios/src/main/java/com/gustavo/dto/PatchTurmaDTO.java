package com.gustavo.dto;

import java.time.LocalDate;

public record PatchTurmaDTO(LocalDate inicio, LocalDate fim, String local_treinamento) {
}