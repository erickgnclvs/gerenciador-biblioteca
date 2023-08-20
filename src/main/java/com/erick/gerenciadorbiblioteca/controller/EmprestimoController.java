package com.erick.gerenciadorbiblioteca.controller;

import com.erick.gerenciadorbiblioteca.model.Emprestimo;
import com.erick.gerenciadorbiblioteca.model.Livro;
import com.erick.gerenciadorbiblioteca.model.Usuario;
import com.erick.gerenciadorbiblioteca.service.EmprestimoService;
import com.erick.gerenciadorbiblioteca.service.LivroService;
import com.erick.gerenciadorbiblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;
    private final LivroService livroService;
    private final UsuarioService usuarioService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService, LivroService livroService, UsuarioService usuarioService) {
        this.emprestimoService = emprestimoService;
        this.livroService = livroService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/emprestimos")
    public ResponseEntity<List<Emprestimo>> getEmprestimos(@RequestParam(required = false) String pesquisa) {
        if (pesquisa != null) {
            Optional<List<Emprestimo>> emprestimosPesquisa = emprestimoService.buscaEmprestimos(pesquisa);
            if (emprestimosPesquisa.isPresent()) {
                return new ResponseEntity<>(emprestimosPesquisa.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(emprestimoService.getEmprestimos(), HttpStatus.OK);
        }
    }

    @GetMapping("/emprestimos/{id}")
    public ResponseEntity<Emprestimo> getEmprestimo(@PathVariable Long id) {
        Optional<Emprestimo> emprestimo = emprestimoService.getEmprestimo(id);
        if (emprestimo.isPresent()) {
            return new ResponseEntity<>(emprestimo.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/emprestimos")
    public ResponseEntity<Emprestimo> adicionaEmprestimo(@RequestBody Emprestimo emprestimo) {
        Optional<Livro> livro = livroService.getLivro(emprestimo.getLivro());
        Optional<Usuario> usuario = usuarioService.getUsuario(emprestimo.getUsuario());
        if (livro.isPresent() && usuario.isPresent()) {
            emprestimo.setLivro(livro.get());
            emprestimo.setUsuario(usuario.get());
            emprestimo.setDataEmprestimo(LocalDate.now());
            try {
                return new ResponseEntity<>(emprestimoService.adicionaEmprestimo(emprestimo), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/emprestimos/{id}")
    public ResponseEntity<Emprestimo> devolveLivro(@PathVariable Long id) {
        Optional<Emprestimo> emprestimo = emprestimoService.getEmprestimo(id);
        if (emprestimo.isPresent()) {
            return new ResponseEntity<>(emprestimoService.devolveLivro(emprestimo.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
