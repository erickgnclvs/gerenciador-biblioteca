package com.erick.gerenciadorbiblioteca.service;

import com.erick.gerenciadorbiblioteca.model.Emprestimo;
import com.erick.gerenciadorbiblioteca.model.Livro;
import com.erick.gerenciadorbiblioteca.model.Usuario;
import com.erick.gerenciadorbiblioteca.repository.EmprestimoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        Livro livro = livroService.getLivro(emprestimo.getLivro()).orElseThrow();
        Usuario usuario = usuarioService.getUsuario(emprestimo.getUsuario()).orElseThrow();
        boolean emprestimoExiste = emprestimoRepository.existsByUsuarioAndLivroAndDataDevolucaoIsNull(usuario, livro);
        boolean livroDisponivel = livro.isDisponivel();
        long emprestimosAtivos = emprestimoRepository.countByUsuarioAndDataDevolucaoIsNull(usuario);
        if (emprestimoExiste) {
            throw new IllegalArgumentException("Empréstimo já existe.");
        } else if (!livroDisponivel) {
            throw new IllegalStateException("Livro não disponível.");
        } else if (emprestimosAtivos >= 2) {
            throw new IllegalStateException("Usuário já possui dois empréstimos ativos.");
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
        return emprestimoRepository.findEmprestimosByDataDevolucaoIsNotNull();
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        return emprestimoRepository.findEmprestimosByDataDevolucaoIsNull();
    }

    public List<Emprestimo> getEmprestimosPorLivro(Long id) {
        Optional<Livro> livro = livroService.getLivro(id);
        if (livro.isPresent()) {
            return emprestimoRepository.findByLivro(livro.get());
        } else {
            throw new EntityNotFoundException("Livro não existe.");
        }
    }

    public Optional<Emprestimo> getEmprestimoAtivoLivro(Long id) {
        Optional<Livro> livro = livroService.getLivro(id);
        if (livro.isPresent()) {
            return emprestimoRepository.findByLivroAndDataDevolucaoIsNull(livro.get());
        } else {
            throw new EntityNotFoundException("Livro não existe.");
        }
    }

    public List<Emprestimo> getEmprestimosDevolvidosLivro(Long id) {
        Optional<Livro> livro = livroService.getLivro(id);
        if (livro.isPresent()) {
            return emprestimoRepository.findByLivroAndDataDevolucaoIsNotNull(livro.get());
        } else {
            throw new EntityNotFoundException("Livro não existe.");
        }
    }

    public List<Emprestimo> getEmprestimosAtivosUsuario(Usuario usuario) {
        return emprestimoRepository.findByUsuarioAndDataDevolucaoIsNull(usuario);
    }

    public List<Emprestimo> getEmprestimosDevolvidosUsuario(Usuario usuario) {
        return emprestimoRepository.findByUsuarioAndDataDevolucaoIsNotNull(usuario);
    }

    public List<Emprestimo> getEmprestimosUsuario(Usuario usuario) {
        return emprestimoRepository.findByUsuario(usuario);
    }
}
