package com.aluracursos.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopicoDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        LocalDateTime fechaCreacion,
        @NotBlank
        String estatus,
        @NotNull
        Integer activo,
        @NotBlank
        String nombreDelCurso
) {
}
