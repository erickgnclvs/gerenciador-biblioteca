package com.erick.gerenciadorbiblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Livro {

    @Id
    private Long id;
    private String titulo;
    private String autor;

    public Livro(Long id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Livro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
