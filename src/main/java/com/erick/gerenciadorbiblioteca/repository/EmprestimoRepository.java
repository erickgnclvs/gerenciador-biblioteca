package com.erick.gerenciadorbiblioteca.repository;

import com.erick.gerenciadorbiblioteca.model.Emprestimo;
import com.erick.gerenciadorbiblioteca.model.Livro;
import com.erick.gerenciadorbiblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    Optional<List<Emprestimo>> findByLivro_TituloContainsOrUsuario_nomeContains(String titulo, String usuario);

    boolean existsByUsuarioAndLivroAndDataDevolucaoIsNull(Usuario usuario, Livro livro);

    long countByUsuarioAndDataDevolucaoIsNull(Usuario usuario);
}
