package mx.edu.utez.proyecto1C.controller.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdateUsuarioDto {
    @NotBlank(message = "El username no puede estar vacío")
    private String username;

    private String description;
}