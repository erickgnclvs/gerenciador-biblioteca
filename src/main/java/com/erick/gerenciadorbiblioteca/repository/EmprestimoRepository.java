package com.erick.gerenciadorbiblioteca.repository;

import com.erick.gerenciadorbiblioteca.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    Optional<List<Emprestimo>> findByLivro_TituloContainsOrUsuario_nomeContains(String titulo, String usuario);
}
