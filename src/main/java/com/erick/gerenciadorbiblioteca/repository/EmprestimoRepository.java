package com.erick.gerenciadorbiblioteca.repository;

import com.erick.gerenciadorbiblioteca.model.Emprestimo;
import com.erick.gerenciadorbiblioteca.model.Livro;
import com.erick.gerenciadorbiblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findEmprestimosByDataDevolucaoIsNull();

    Optional<List<Emprestimo>> findByLivro_TituloContainsOrUsuario_nomeContains(String titulo, String usuario);

    boolean existsByUsuarioAndLivroAndDataDevolucaoIsNull(Usuario usuario, Livro livro);

    long countByUsuarioAndDataDevolucaoIsNull(Usuario usuario);

    List<Emprestimo> findByLivro(Livro livro);

    Optional<Emprestimo> findByLivroAndDataDevolucaoIsNull(Livro livro);

    List<Emprestimo> findByUsuarioAndDataDevolucaoIsNull(Usuario usuario);

    List<Emprestimo> findByUsuarioAndDataDevolucaoIsNotNull(Usuario usuario);

    List<Emprestimo> findByUsuario(Usuario usuario);

    List<Emprestimo> findByLivroAndDataDevolucaoIsNotNull(Livro livro);

    List<Emprestimo> findEmprestimosByDataDevolucaoIsNotNull();
}
