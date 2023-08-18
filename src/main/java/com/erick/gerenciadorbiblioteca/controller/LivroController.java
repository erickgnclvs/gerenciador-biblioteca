package com.erick.gerenciadorbiblioteca.controller;

import com.erick.gerenciadorbiblioteca.model.Livro;
import com.erick.gerenciadorbiblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivroController {

    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/livros")
    public ResponseEntity<List<Livro>> getLivros() {
        List<Livro> livros = livroService.getLivros();
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    @PostMapping("/livros")
    public ResponseEntity<Livro> adicionaLivro(@RequestBody Livro livro) {
        Livro novoLivro = livroService.adicionaLivro(livro);
        return new ResponseEntity<>(novoLivro, HttpStatus.CREATED);
    }
}
