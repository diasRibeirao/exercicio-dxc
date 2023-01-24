package com.exercicio.dxc.domain.dto;

public class ClienteDTO {
	private Long id;
	private TipoClienteDTO tipo;
	private String cpfOuCnpj;
	private String nome;
	private String email;

	public ClienteDTO() {

	}

	public ClienteDTO(TipoClienteDTO tipo, String cpfOuCnpj, String nome, String email) {
		this.tipo = tipo;
		this.cpfOuCnpj = cpfOuCnpj;
		this.nome = nome;
		this.email = email;
	}

	public ClienteDTO(Long id, TipoClienteDTO tipo, String cpfOuCnpj, String nome, String email) {
		this(tipo, cpfOuCnpj, nome, email);
		this.id = id;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoClienteDTO getTipo() {
		return tipo;
	}

	public void setTipo(TipoClienteDTO tipo) {
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