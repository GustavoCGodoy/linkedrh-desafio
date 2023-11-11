package com.gustavo.dto;

import java.time.LocalDate;

public record TurmaDTO(LocalDate inicio, LocalDate fim, String local_treinamento, int curso) {
}
