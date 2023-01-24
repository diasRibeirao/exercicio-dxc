package com.exercicio.dxc.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.exercicio.dxc.services.validation.ClienteAdicionar;

@ClienteAdicionar
public class ClienteAdicionarDTO {

	@NotNull(message = "Preenchimento obrigatório")
	private Integer tipo;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpfOuCnpj;

	@Size(min = 5, max = 120, message = "O nome deve possuir entre {min} e {max} caracteres")
	private String nome;

	@Size(min = 5, max = 120, message = "O e-mail deve possuir entre {min} e {max} caracteres")
	private String email;

	public ClienteAdicionarDTO() {

	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
