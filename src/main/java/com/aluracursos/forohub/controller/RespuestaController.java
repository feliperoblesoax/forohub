package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.respuesta.CatalogoDeRespuestaService;
import com.aluracursos.forohub.domain.respuesta.DatosCrearRespuesta;
import com.aluracursos.forohub.domain.respuesta.DatosDetalleRespuesta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private CatalogoDeRespuestaService catalogoDeRespuestaService;
    @PostMapping
    @Transactional
    public ResponseEntity crearRespuesta(@RequestBody @Valid DatosCrearRespuesta datosCrearRespuesta) {
        var response = catalogoDeRespuestaService.crearRespuesta(datosCrearRespuesta);
        return ResponseEntity.ok(response);
    }
}
