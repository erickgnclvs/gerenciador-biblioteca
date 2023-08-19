package com.erick.gerenciadorbiblioteca.service;

import com.erick.gerenciadorbiblioteca.model.Emprestimo;
import com.erick.gerenciadorbiblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public Optional<List<Emprestimo>> buscaEmprestimos(String pesquisa) {
        return emprestimoRepository.findByLivro_TituloContainsOrUsuario_nomeContains(pesquisa, pesquisa);
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimoRepository.findAll();
    }
}
