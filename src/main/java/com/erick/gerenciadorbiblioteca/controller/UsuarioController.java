package com.erick.gerenciadorbiblioteca.controller;

import com.erick.gerenciadorbiblioteca.model.Usuario;
import com.erick.gerenciadorbiblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getUsuarios(@RequestParam(required = false) String pesquisa) {
        if (pesquisa != null) {
            Optional<List<Usuario>> usuariosPesquisa = usuarioService.buscaUsuarios(pesquisa);
            if (usuariosPesquisa.isPresent()) {
                return new ResponseEntity<>(usuariosPesquisa.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(usuarioService.getUsuarios(), HttpStatus.OK);
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> adicionaUsuario(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuarioService.salvaUsuario(usuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        if (usuarioService.getUsuario(id).isPresent()) {
            return new ResponseEntity<>(usuarioService.getUsuario(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> modificaUsuario(@PathVariable Long id, @RequestBody Usuario novoUsuario) {
        Optional<Usuario> usuario = usuarioService.getUsuario(id);
        if (usuario.isPresent()) {
            usuario.get().setEmail(novoUsuario.getEmail());
            usuario.get().setNome(novoUsuario.getNome());
            usuario.get().setTelefone(novoUsuario.getTelefone());
            try {
                return new ResponseEntity<>(usuarioService.salvaUsuario(usuario.get()), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> removeUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuario(id);
        if (usuario.isPresent()) {
            usuarioService.removeUsuario(usuario.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
