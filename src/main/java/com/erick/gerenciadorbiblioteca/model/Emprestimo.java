package com.erick.gerenciadorbiblioteca.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Emprestimo {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "usuario_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Usuario usuario;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "livro_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Livro livro;
    @NotNull
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo() {
    }

    public Emprestimo(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
    }

    public Emprestimo(Long id, Usuario usuario, Livro livro) {
        this.id = id;
        this.usuario = usuario;
        this.livro = livro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuario() {
        return usuario.getId();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getLivro() {
        return livro.getId();
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", usuario=" + usuario.getNome() +
                ", livro=" + livro.getTitulo() +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }
}
