package com.gustavo.dto;

import java.util.Date;

public record TurmaDTO(Date inicio, Date fim, String local_treinamento, int curso) {
}
