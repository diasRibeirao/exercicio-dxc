package com.exercicio.dxc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.exercicio.dxc.domain.enums.TipoCliente;

@Entity
@Table(name = "CLIENTE")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TIPO")
	private Integer tipo;

	@Column(name = "CPF_OU_CNPJ", unique = true)
	private String cpfOuCnpj;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "EMAIL", unique = true)
	private String email;

	public Cliente() {

	}

	public Cliente(TipoCliente tipo, String cpfOuCnpj, String nome, String email) {
		this.tipo = (tipo == null) ? null : tipo.getId();
		this.cpfOuCnpj = cpfOuCnpj;
		this.nome = nome;
		this.email = email;
	}

	public Cliente(Long id, TipoCliente tipo, String cpfOuCnpj, String nome, String email) {
		this(tipo, cpfOuCnpj, nome, email);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public static void updateData(Cliente novoCliente, Cliente cliente) {		
		novoCliente.setTipo(cliente.getTipo());
		novoCliente.setCpfOuCnpj(cliente.getCpfOuCnpj());
		novoCliente.setNome(cliente.getNome());
		novoCliente.setEmail(cliente.getEmail());
	}

}
