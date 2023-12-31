package com.erick.gerenciadorbiblioteca.controller;

import com.erick.gerenciadorbiblioteca.model.Emprestimo;
import com.erick.gerenciadorbiblioteca.model.EmptyJson;
import com.erick.gerenciadorbiblioteca.model.Livro;
import com.erick.gerenciadorbiblioteca.service.EmprestimoService;
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
    private final EmprestimoService emprestimoService;

    @Autowired
    public LivroController(LivroService livroService, EmprestimoService emprestimoService) {
        this.livroService = livroService;
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/livros")
    public ResponseEntity<List<Livro>> getLivros(@RequestParam(required = false) String pesquisa) {
        if (pesquisa != null) {
            Optional<List<Livro>> livrosPesquisa = livroService.buscaLivros(pesquisa);
            if (livrosPesquisa.isPresent()) {
                return new ResponseEntity<>(livrosPesquisa.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(livroService.getLivros(), HttpStatus.OK);
        }
    }

    @PostMapping("/livros")
    public ResponseEntity<Livro> adicionaLivro(@RequestBody Livro livro) {
        try {
            return new ResponseEntity<>(livroService.salvaLivro(livro), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<Livro> modificaLivro(@PathVariable Long id, @RequestBody Livro novoLivro) {
        Optional<Livro> livro = livroService.getLivro(id);
        if (livro.isPresent()) {
            livro.get().setAutor(novoLivro.getAutor());
            livro.get().setTitulo(novoLivro.getTitulo());
            try {
                return new ResponseEntity<>(livroService.salvaLivro(livro.get()), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/livros/{id}")
    public ResponseEntity<Livro> removeLivro(@PathVariable Long id) {
        Optional<Livro> livro = livroService.getLivro(id);
        if (livro.isPresent()) {
            livroService.removeLivro(livro.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/livros/{id}/emprestimos")
    public ResponseEntity<List<Emprestimo>> getEmprestimos(@PathVariable Long id) {
        if (livroService.livroExiste(id)) {
            return new ResponseEntity<>(emprestimoService.getEmprestimosPorLivro(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/livros/{id}/emprestimos/ativo")
    public ResponseEntity<?> getEmprestimoAtivo(@PathVariable Long id) {
        if (livroService.livroExiste(id)) {
            Optional<Emprestimo> emprestimoAtivo = emprestimoService.getEmprestimoAtivoLivro(id);
            if (emprestimoAtivo.isPresent()) {
                return new ResponseEntity<>(emprestimoAtivo.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new EmptyJson(), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/livros/{id}/emprestimos/devolvidos")
    public ResponseEntity<List<Emprestimo>> getEmprestimosDevolvidos(@PathVariable Long id) {
        if (livroService.livroExiste(id)) {
            return new ResponseEntity<>(emprestimoService.getEmprestimosDevolvidosLivro(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
