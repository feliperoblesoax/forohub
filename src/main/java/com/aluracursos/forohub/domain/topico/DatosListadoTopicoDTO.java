package com.aluracursos.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        String estatus,
        String nombreDelCurso) {

    public DatosListadoTopicoDTO (Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getEstatus(), topico.getNombreDelCurso());
    }
}
