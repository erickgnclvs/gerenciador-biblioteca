package com.erick.gerenciadorbiblioteca.service;

import com.erick.gerenciadorbiblioteca.model.Emprestimo;
import com.erick.gerenciadorbiblioteca.model.Livro;
import com.erick.gerenciadorbiblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Livro salvaLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public Optional<Livro> getLivro(Long id) {
        return livroRepository.findById(id);
    }

    public void removeLivro(Livro livro) {
        livroRepository.delete(livro);
    }

    public Optional<List<Livro>> buscaLivros(String pesquisa) {
        return livroRepository.findAllByAutorContainingIgnoreCaseOrTituloContainingIgnoreCase(pesquisa, pesquisa);
    }

    public boolean livroExiste(Long id) {
        return livroRepository.findById(id).isPresent();
    }
}
