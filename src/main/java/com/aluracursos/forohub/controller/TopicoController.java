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
import java.util.Optional;

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
                topico.getEstatus(), topico.getActivo(), topico.getNombreDelCurso());
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
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            var datosTopico = new DatosRespuestaTopicoDTO(topico.get().getId(),
                    topico.get().getTitulo(), topico.get().getMensaje(), topico.get().getFechaCreacion(),
                    topico.get().getEstatus(), topico.get().getActivo(), topico.get().getNombreDelCurso());
            return ResponseEntity.ok(datosTopico);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopicoDTO datosActualizarTopicoDTO) {
       Topico topico = topicoRepository.getReferenceById(datosActualizarTopicoDTO.id());
       topico.actualizarDatos(datosActualizarTopicoDTO);
        return ResponseEntity.ok(new DatosRespuestaTopicoDTO(topico.getId(),
                topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getEstatus(), topico.getActivo(), topico.getNombreDelCurso()));
    }

//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity actualizarTopicoPorId(@RequestBody @Valid DatosActualizarTopicoPorIdDTO datosActualizarTopicoPorIdDTO) {
//        //Topico topico = topicoRepository.getReferenceById(datosActualizarTopicoDTO.id());
//        Topico topico = topicoRepository.getReferenceById(datosActualizarTopicoPorIdDTO.Id());
//        topico.actualizarDatosPorId(datosActualizarTopicoPorIdDTO);
//        return ResponseEntity.ok(new DatosRespuestaTopicoDTO(topico.getId(),
//                topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
//                topico.getEstatus(), topico.getActivo()));

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopicoPorId(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopicoPorIdDTO datosActualizarTopicoPorIdDTO) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarDatosPorId(datosActualizarTopicoPorIdDTO);
        return ResponseEntity.ok(new DatosRespuestaTopicoDTO(topico.getId(),
                topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getEstatus(), topico.getActivo(), topico.getNombreDelCurso()));
        //return ResponseEntity.ok(id);
    }




    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

}
