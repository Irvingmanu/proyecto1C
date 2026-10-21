package mx.edu.utez.proyecto1C.controller.persona.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class RequestUpdatePersonaDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombres;

    @NotBlank(message = "El primer apellido es obligatorio")
    private String primerApellido;

    private String segundoApellido;

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo es inválido")
    private String correo;

    @NotBlank(message = "El curp es obligatorio")
    private String curp;
}