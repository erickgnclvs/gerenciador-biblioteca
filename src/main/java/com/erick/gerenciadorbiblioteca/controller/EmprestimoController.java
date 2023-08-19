package com.erick.gerenciadorbiblioteca.controller;

import com.erick.gerenciadorbiblioteca.model.Emprestimo;
import com.erick.gerenciadorbiblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
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
}
