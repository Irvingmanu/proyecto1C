package mx.edu.utez.proyecto1C.controller.usuario;


import jakarta.validation.Valid;
import mx.edu.utez.proyecto1C.controller.usuario.dto.RequestCrearUsuarioDto;
import mx.edu.utez.proyecto1C.controller.usuario.dto.RequestUpdateUsuarioDto;
import mx.edu.utez.proyecto1C.model.usuario.Usuario;
import mx.edu.utez.proyecto1C.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers(){
        return ResponseEntity
                .status(200)
                .body(
                        this.usuarioService.getAllUsers()
                );
    }

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody @Valid RequestCrearUsuarioDto payload){
        return ResponseEntity
                .status(201)
                .body(
                        this.usuarioService.createUser(payload)
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id){
        return ResponseEntity
                .status(200)
                .body(
                        this.usuarioService.getUserById(id)
                );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Usuario> toggleUserStatus(@PathVariable Long id){
        return ResponseEntity
                .status(200)
                .body(
                        this.usuarioService.changeStatus(id)
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody @Valid RequestUpdateUsuarioDto payload){
        return ResponseEntity
                .status(200)
                .body(
                        this.usuarioService.updateUsuario(id, payload)
                );
    }
}
