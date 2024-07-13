package com.aluracursos.forohub.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="topicos")
@Entity(name="Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String estatus;
    private Integer activo;
    private String nombreDelCurso;

    public Topico(DatosRegistroTopicoDTO datosRegistroTopicoDTO) {
        this.titulo = datosRegistroTopicoDTO.titulo();
        this.mensaje = datosRegistroTopicoDTO.mensaje();
        this.fechaCreacion = datosRegistroTopicoDTO.fechaCreacion();
        this.estatus = datosRegistroTopicoDTO.estatus();
        this.activo = 1;
        this.nombreDelCurso = datosRegistroTopicoDTO.nombreDelCurso();
    }

    public void actualizarDatos(DatosActualizarTopicoDTO datosActualizarTopicoDTO) {
        if (datosActualizarTopicoDTO.mensaje() != null) {
            this.mensaje = datosActualizarTopicoDTO.mensaje();
        }
        if (datosActualizarTopicoDTO.estatus() != null) {
            this.estatus = datosActualizarTopicoDTO.estatus();
        }

    }

    public void desactivarTopico() {
        this.activo = 0;
    }

    public void actualizarDatosPorId(DatosActualizarTopicoPorIdDTO datosActualizarTopicoPorIdDTO) {
        if (datosActualizarTopicoPorIdDTO.mensaje() != null) {
            this.mensaje = datosActualizarTopicoPorIdDTO.mensaje();
        }
        if (datosActualizarTopicoPorIdDTO.estatus() != null) {
            this.estatus = datosActualizarTopicoPorIdDTO.estatus();
        }
    }
}


