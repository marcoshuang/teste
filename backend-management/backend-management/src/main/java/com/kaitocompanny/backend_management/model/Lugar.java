package com.kaitocompanny.backend_management.model;

import jakarta.persistence.*;

@Entity

@Table(name = "lugares")
public class Lugar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column
    private String localizacao;

    @Column(name = "url_foto")
    private String urlFoto;

    @Column
    private Integer avaliacao;

    // Construtores
    public Lugar() {}

    public Lugar(String nome, Categoria categoria, String localizacao, String urlFoto, Integer avaliacao) {
        this.nome = nome;
        this.categoria = categoria;
        this.localizacao = localizacao;
        this.urlFoto = urlFoto;
        this.avaliacao = avaliacao;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }
}
