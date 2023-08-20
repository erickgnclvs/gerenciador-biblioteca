package com.erick.gerenciadorbiblioteca.service;

import com.erick.gerenciadorbiblioteca.model.Emprestimo;
import com.erick.gerenciadorbiblioteca.model.Livro;
import com.erick.gerenciadorbiblioteca.model.Usuario;
import com.erick.gerenciadorbiblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroService livroService;
    private final UsuarioService usuarioService;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroService livroService, UsuarioService usuarioService) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroService = livroService;
        this.usuarioService = usuarioService;
    }

    public Optional<List<Emprestimo>> buscaEmprestimos(String pesquisa) {
        return emprestimoRepository.findByLivro_TituloContainsOrUsuario_nomeContains(pesquisa, pesquisa);
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo adicionaEmprestimo(Emprestimo emprestimo) {
        Livro livro = livroService.getLivro(emprestimo.getLivro()).get();
        Usuario usuario = usuarioService.getUsuario(emprestimo.getUsuario()).get();
        boolean emprestimoExiste = emprestimoRepository.existsByUsuarioAndLivroAndDataDevolucaoIsNull(usuario, livro);
        boolean livroDisponivel = livro.isDisponivel();
        long emprestimosAtivos = emprestimoRepository.countByUsuarioAndDataDevolucaoIsNull(usuario);
        if (emprestimoExiste || !livroDisponivel || emprestimosAtivos >= 2) {
            throw new RuntimeException();
        } else {
            livro.setDisponivel(false);
            return emprestimoRepository.save(emprestimo);
        }
    }

    public Optional<Emprestimo> getEmprestimo(Long id) {
        return emprestimoRepository.findById(id);
    }

    public Emprestimo devolveLivro(Emprestimo emprestimo) {
        emprestimo.setDataDevolucao(LocalDate.now());
        livroService.getLivro(emprestimo.getLivro()).orElseThrow().setDisponivel(true);
        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> getEmprestimosDevolvidos() {
        List<Emprestimo> devolvidos = new ArrayList<>();
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        if (!emprestimos.isEmpty()) {
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getDataDevolucao() != null) {
                    devolvidos.add(emprestimo);
                }
            }
        }
        return devolvidos;
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        List<Emprestimo> ativos = new ArrayList<>();
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        if (!emprestimos.isEmpty()) {
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getDataDevolucao() == null) {
                    ativos.add(emprestimo);
                }
            }
        }
        return ativos;
    }
}
