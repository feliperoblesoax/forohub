package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaTopicoDTO> registrarTopico(@RequestBody @Valid DatosRegistroTopicoDTO datosRegistroTopicoDTO, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopicoDTO));
        DatosRespuestaTopicoDTO datosRespuestaTopicoDTO = new DatosRespuestaTopicoDTO(topico.getId(),
                topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getEstatus(), topico.getActivo());
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopicoDTO);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicoDTO>> listadoTopico(@PageableDefault(size=10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
//        return topicoRepository.findAll(paginacion).map(DatosListadoTopicoDTO::new);
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion).map(DatosListadoTopicoDTO::new));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopicoDTO> retornaDatosTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopicoDTO(topico.getId(),
                topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getEstatus(), topico.getActivo());
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarTopicoDTO datosActualizarTopicoDTO) {
       Topico topico = topicoRepository.getReferenceById(datosActualizarTopicoDTO.id());
       topico.actualizarDatos(datosActualizarTopicoDTO);
        return ResponseEntity.ok(new DatosRespuestaTopicoDTO(topico.getId(),
                topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getEstatus(), topico.getActivo()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

}
