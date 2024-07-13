package com.aluracursos.forohub.domain.respuesta;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosCrearRespuesta(
        Long id,
        @NotNull
        Long idTopico,
        @NotNull
        Long idUsuario,
        @NotNull
        String respuesta,
        @NotNull
        LocalDateTime fechaCreacion
) {
}
