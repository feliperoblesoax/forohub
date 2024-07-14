package com.aluracursos.forohub.domain.respuesta.validaciones;

import com.aluracursos.forohub.domain.respuesta.DatosCrearRespuesta;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoActivo implements ValidadorDeConsultas{
    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(DatosCrearRespuesta datosCrearRespuesta) {
        if (datosCrearRespuesta.idTopico()==null) {
            return;
        }

        Integer topicoActivo = topicoRepository.findActivoById(datosCrearRespuesta.idTopico());

        if (topicoActivo==0) {
            throw new ValidationException("No puedes agregar una respuesta a un topico inactivo");
        }
    }
}
