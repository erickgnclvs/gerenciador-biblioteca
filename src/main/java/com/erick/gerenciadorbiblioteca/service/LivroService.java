package com.erick.gerenciadorbiblioteca.service;

import com.erick.gerenciadorbiblioteca.model.Livro;
import com.erick.gerenciadorbiblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> getLivros() {
        return livroRepository.findAll();
    }

    public Livro adicionaLivro(Livro livro) {
        return livroRepository.save(livro);
    }
}
