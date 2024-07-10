package com.aluracursos.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String estatus,
        Integer activo
) {
}
