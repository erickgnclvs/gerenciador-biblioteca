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
        Usuario usuarioSalvo = usuarioService.salvaUsuario(usuario);
        if (usuarioSalvo != null) {
            return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
