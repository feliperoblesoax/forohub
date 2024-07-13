package com.aluracursos.forohub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        Long idTopico,
        Long idUsuario,
        String respuesta,
        LocalDateTime fechaCreacion) {
}
