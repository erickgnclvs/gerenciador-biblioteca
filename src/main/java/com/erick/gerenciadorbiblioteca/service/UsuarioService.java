package com.erick.gerenciadorbiblioteca.service;

import com.erick.gerenciadorbiblioteca.model.Livro;
import com.erick.gerenciadorbiblioteca.model.Usuario;
import com.erick.gerenciadorbiblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<List<Usuario>> buscaUsuarios(String pesquisa) {
        return usuarioRepository.findAllByNomeContainingIgnoreCaseOrEmailContainingIgnoreCase(pesquisa, pesquisa);
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }
}
