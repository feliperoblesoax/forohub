package com.aluracursos.forohub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuarioDTO(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String correo,
        @NotBlank
        String password
) {
}
