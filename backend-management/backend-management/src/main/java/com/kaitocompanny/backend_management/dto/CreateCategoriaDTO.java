package com.kaitocompanny.backend_management.dto;



import jakarta.validation.constraints.NotBlank;

public class CreateCategoriaDTO {
	
@NotBlank(message = "O nome é obrigatório")
private String nome;

@NotBlank(message = "A descrição é obrigatória")
private String descricao;

 public CreateCategoriaDTO() {}

 public CreateCategoriaDTO(String nome, String descricao) {
     this.nome = nome;
     this.descricao = descricao;
 }

 // getters e setters
 public String getNome() { return nome; }
 public void setNome(String nome) { this.nome = nome; }

 public String getDescricao() { return descricao; }
 public void setDescricao(String descricao) { this.descricao = descricao; }
}
