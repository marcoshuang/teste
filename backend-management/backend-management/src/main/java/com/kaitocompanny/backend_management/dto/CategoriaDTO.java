package com.kaitocompanny.backend_management.dto;

public class CategoriaDTO {
 private Long id;
 private String nome;
 private String descricao;

 public CategoriaDTO() {}

 public CategoriaDTO(Long id, String nome, String descricao) {
     this.id = id;
     this.nome = nome;
     this.descricao = descricao;
 }

 // getters e setters
 public Long getId() { return id; }
 public void setId(Long id) { this.id = id; }

 public String getNome() { return nome; }
 public void setNome(String nome) { this.nome = nome; }

 public String getDescricao() { return descricao; }
 public void setDescricao(String descricao) { this.descricao = descricao; }
}
