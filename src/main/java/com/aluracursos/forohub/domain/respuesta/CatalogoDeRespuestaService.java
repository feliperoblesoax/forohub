package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import com.aluracursos.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogoDeRespuestaService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RespuestaRepository respuestaRepository;
    public void crearRespuesta(DatosCrearRespuesta datosCrearRespuesta) {

        if (topicoRepository.findById(datosCrearRespuesta.idTopico()).isPresent()) {
            throw new ValidacionDeIntegridad("Este id de topico no fue encontrado");
        }

        if (datosCrearRespuesta.idUsuario()!=null && topicoRepository.existsById(datosCrearRespuesta.idUsuario())) {
            throw new ValidacionDeIntegridad("Este id de topico no fue encontrado");
        }

        var topico = topicoRepository.findById(datosCrearRespuesta.idTopico()).get();
        var usuario = seleccionarUsuario(datosCrearRespuesta);
        var respuesta = new Respuesta(null, topico, usuario, datosCrearRespuesta.respuesta(), datosCrearRespuesta.fechaCreacion());
        respuestaRepository.save(respuesta);
    }

    private Usuario seleccionarUsuario(DatosCrearRespuesta datosCrearRespuesta) {
        if (datosCrearRespuesta.idUsuario()!=nul) {
            return usuarioRepository.getReferenceById(datosCrearRespuesta.idUsuario());
        }
        return usuarioRepository.getReferenceById(2L);
    }
}
