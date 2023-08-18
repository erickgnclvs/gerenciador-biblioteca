package com.erick.gerenciadorbiblioteca.controller;

import com.erick.gerenciadorbiblioteca.model.Livro;
import com.erick.gerenciadorbiblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/v1")
public class LivroController {

    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/livros")
    public ResponseEntity<List<Livro>> getLivros() {
        return new ResponseEntity<>(livroService.getLivros(), HttpStatus.OK);
    }

    @PostMapping("/livros")
    public ResponseEntity<Livro> adicionaLivro(@RequestBody Livro livro) {
        return new ResponseEntity<>(livroService.salvaLivro(livro), HttpStatus.CREATED);
    }

    @GetMapping("/livros/{id}")
    public ResponseEntity<Livro> getLivro(@PathVariable Long id) {
        if (livroService.getLivro(id).isPresent()) {
            return new ResponseEntity<>(livroService.getLivro(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/livros/{id}")
    public ResponseEntity<Livro> modificaLivro(@PathVariable Long id, @RequestBody Livro livro) {
        Optional<Livro> livroExistente = livroService.getLivro(id);
        if (livroExistente.isPresent()) {
            livroExistente.get().setAutor(livro.getAutor());
            livroExistente.get().setTitulo(livro.getTitulo());
            return new ResponseEntity<>(livroService.salvaLivro(livroExistente.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
