package com.aluracursos.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopicoDTO(
        @NotNull
        Long id,
        String mensaje,
        String estatus
) {
}
