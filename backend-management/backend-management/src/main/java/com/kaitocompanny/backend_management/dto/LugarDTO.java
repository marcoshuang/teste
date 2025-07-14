package com.kaitocompanny.backend_management.dto;


public class LugarDTO {
    private Long id;
    private String nome;
    private String localizacao;
    private String urlFoto;
    private Integer avaliacao;
    private CategoriaDTO categoria;
    
    
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
	public CategoriaDTO getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}
	
	
	public LugarDTO() {

	}
	public LugarDTO(Long id, String nome, String localizacao, String urlFoto, Integer avaliacao,
			CategoriaDTO categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.localizacao = localizacao;
		this.urlFoto = urlFoto;
		this.avaliacao = avaliacao;
		this.categoria = categoria;
	}

}
