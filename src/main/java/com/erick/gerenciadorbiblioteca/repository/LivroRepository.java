package com.erick.gerenciadorbiblioteca.repository;

import com.erick.gerenciadorbiblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
