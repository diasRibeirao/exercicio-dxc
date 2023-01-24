package com.exercicio.dxc.domain.dto;

public class TipoClienteDTO {
	private Integer id;
	private String descricao;
	
	public TipoClienteDTO() {
		
	}

	public TipoClienteDTO(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
