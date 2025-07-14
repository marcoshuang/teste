package com.kaitocompanny.backend_management.dto;


import jakarta.validation.constraints.*;

public class CreateLugarDTO {
  @NotBlank(message = "O nome é obrigatório")
  private String nome;

  @NotNull(message = "A categoria é obrigatória")
  private Long categoriaId;

  @NotBlank(message = "A localização é obrigatória")
  private String localizacao;

  @NotBlank(message = "A URL da foto é obrigatória")
  private String urlFoto;

  @NotNull(message = "A avaliação é obrigatória")
  @Min(value = 1, message = "Avaliação mínima é 1")
  @Max(value = 5, message = "Avaliação máxima é 5")
  private Integer avaliacao;

 public String getNome() {
  return nome;
 }

 public void setNome(String nome) {
  this.nome = nome;
 }

 public Integer getAvaliacao() {
  return avaliacao;
 }

 public void setAvaliacao(Integer avaliacao) {
  this.avaliacao = avaliacao;
 }

 public String getLocalizacao() {
  return localizacao;
 }

 public void setLocalizacao(String localizacao) {
  this.localizacao = localizacao;
 }

 public Long getCategoriaId() {
  return categoriaId;
 }

 public void setCategoriaId(Long categoriaId) {
  this.categoriaId = categoriaId;
 }

 public String getUrlFoto() {
  return urlFoto;
 }

 public void setUrlFoto(String urlFoto) {
  this.urlFoto = urlFoto;
 }
}
