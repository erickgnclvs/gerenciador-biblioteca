package com.erick.gerenciadorbiblioteca.repository;

import com.erick.gerenciadorbiblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<List<Usuario>> findAllByNomeContainingIgnoreCaseOrEmailContainingIgnoreCase(String nome, String email);
}
