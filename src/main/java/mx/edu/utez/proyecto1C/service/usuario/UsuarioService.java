package mx.edu.utez.proyecto1C.service.usuario;

import mx.edu.utez.proyecto1C.controller.usuario.dto.RequestCrearUsuarioDto;
import mx.edu.utez.proyecto1C.controller.usuario.dto.RequestUpdateUsuarioDto;
import mx.edu.utez.proyecto1C.dto.RequestCalculadoraDTO;
import mx.edu.utez.proyecto1C.exception.customExceptions.BadRequestException;
import mx.edu.utez.proyecto1C.model.persona.Persona;
import mx.edu.utez.proyecto1C.model.persona.PersonaRepository;
import mx.edu.utez.proyecto1C.model.usuario.Roles;
import mx.edu.utez.proyecto1C.model.usuario.Usuario;
import mx.edu.utez.proyecto1C.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final PersonaRepository personaRepository;
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(PersonaRepository personaRepository, UsuarioRepository usuarioRepository) {
        this.personaRepository = personaRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public List<Usuario> getAllUsers(){
        return usuarioRepository.findAll();
    }


    public Usuario createUser(RequestCrearUsuarioDto payload){

        if (usuarioRepository.findByUsername(payload.getUsername()).isPresent()){
            throw  new BadRequestException("Ese usuario ya esta en uso");
        }

        Persona newPersona = new Persona();
        newPersona.setNombres(payload.getNombres());
        newPersona.setPrimerApellido(payload.getPrimerApellido());
        newPersona.setSegundoApellido(payload.getSegundoApellido());
        newPersona.setFechaNacimiento(payload.getFechaNacimiento());
        newPersona.setCorreo(payload.getCorreo());

        newPersona = personaRepository.save(newPersona);


        Usuario newUser = new Usuario();
        newUser.setUsername(payload.getUsername());
        newUser.setPassword(payload.getPassword());
        newUser.setEnable(true);
        newUser.setRol(
                Roles.valueOf(payload.getRol()
        ));


        newUser.setPersona(newPersona);

        return usuarioRepository.save(newUser);

    }

    public Usuario getUserById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new BadRequestException("El usuario con ID " + id + " no existe")
        );
    }

    public Usuario changeStatus(Long id) {
        Usuario usuario = getUserById(id);
        usuario.setEnable(!usuario.isEnable());
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, RequestUpdateUsuarioDto payload) {
        Usuario usuario = getUserById(id);

        Optional<Usuario> existingUser = usuarioRepository.findByUsername(payload.getUsername());
        if (existingUser.isPresent() && existingUser.get().getId() != id) {
            throw new BadRequestException("El username ya está en uso por otro usuario");
        }

        usuario.setUsername(payload.getUsername());
        usuario.setDescription(payload.getDescription());

        return usuarioRepository.save(usuario);
    }

}
