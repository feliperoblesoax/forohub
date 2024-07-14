package com.aluracursos.forohub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        Long idTopico,
        Long idUsuario,
        String respuesta,
        LocalDateTime fechaCreacion) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getTopico().getId(),
                respuesta.getUsuario().getId(), respuesta.getRespuesta(),
                respuesta.getData());
    }
}
