package com.exercicio.dxc.domain.enums;

import com.exercicio.dxc.services.exceptions.ObjectNotFoundException;

public enum TipoCliente {
	PESSOA_FISICA(1, "Pessoa Física"), 
	PESSOA_JURIDICA(2, "Pessoa Jurídica");

	private Integer id;
	private String descricao;

	private TipoCliente(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer id) {
		if (id == null) {
			return null;
		}

		for (TipoCliente tipoCliente : TipoCliente.values()) {
			if (id.equals(tipoCliente.getId())) {
				return tipoCliente;
			}
		}

		throw new ObjectNotFoundException("ID tipo de cliente inválido: " + id);
	}

}
