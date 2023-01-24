package com.exercicio.dxc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.exercicio.dxc.constants.ClientePessoaFisicaTest;
import com.exercicio.dxc.constants.ClientePessoaJuridicaTest;
import com.exercicio.dxc.domain.Cliente;
import com.exercicio.dxc.domain.enums.TipoCliente;
import com.exercicio.dxc.services.ClienteService;
import com.exercicio.dxc.services.exceptions.ObjectNotFoundException;
import com.exercicio.dxc.utils.Utils;

@SpringBootTest()
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class ClienteServiceTests {

	@Autowired
	ClienteService service;

	@Test
	@Order(1)    
	@DisplayName("Deve retornar vazio ao buscar todos os clientes")
	public void deveRetornarVazioTodosOsClientes() {
		List<Cliente> clientes = service.findAll();
		assertThat(clientes).isEmpty();
	}

	@Test
	@Order(2)    
	@DisplayName("Deve inserir um cliente pessoa física")
	public void deveInserirClientePF() {
		Cliente cliente = service.insert(new Cliente(TipoCliente.PESSOA_FISICA, ClientePessoaFisicaTest.CPF, ClientePessoaFisicaTest.NOME, ClientePessoaFisicaTest.EMAIL));
		assertThat(cliente.getId()).isEqualTo(ClientePessoaFisicaTest.ID);
		assertThat(cliente.getTipo()).isEqualTo(TipoCliente.PESSOA_FISICA.getId());
		assertThat(cliente.getCpfOuCnpj()).isEqualTo(ClientePessoaFisicaTest.CPF);
		assertThat(cliente.getNome()).isEqualTo(ClientePessoaFisicaTest.NOME);
		assertThat(cliente.getEmail()).isEqualTo(ClientePessoaFisicaTest.EMAIL);
	}
	
	@Test
	@Order(3)    
	@DisplayName("Buscar um cliente pessoa física pelo id")
	public void deveBuscarUmClientePF() {
		Cliente cliente = service.find(1L);
		assertThat(cliente.getId()).isEqualTo(ClientePessoaFisicaTest.ID);
		assertThat(cliente.getTipo()).isEqualTo(TipoCliente.PESSOA_FISICA.getId());
		assertThat(cliente.getCpfOuCnpj()).isEqualTo(ClientePessoaFisicaTest.CPF);
		assertThat(cliente.getNome()).isEqualTo(ClientePessoaFisicaTest.NOME);
		assertThat(cliente.getEmail()).isEqualTo(ClientePessoaFisicaTest.EMAIL);
	}
	
	@Test
	@Order(4)    
	@DisplayName("Deve inserir um cliente pessoa jurídica")
	public void deveInserirClientePJ() {
		Cliente cliente = service.insert(new Cliente(TipoCliente.PESSOA_JURIDICA, ClientePessoaJuridicaTest.CNPJ, ClientePessoaJuridicaTest.NOME, ClientePessoaJuridicaTest.EMAIL));
		assertThat(cliente.getId()).isEqualTo(ClientePessoaJuridicaTest.ID);
		assertThat(cliente.getTipo()).isEqualTo(TipoCliente.PESSOA_JURIDICA.getId());
		assertThat(cliente.getCpfOuCnpj()).isEqualTo(ClientePessoaJuridicaTest.CNPJ);
		assertThat(cliente.getNome()).isEqualTo(ClientePessoaJuridicaTest.NOME);
		assertThat(cliente.getEmail()).isEqualTo(ClientePessoaJuridicaTest.EMAIL);
	}
		
	@Test
	@Order(5)    
	@DisplayName("Buscar um cliente pessoa jurídica pelo id")
	public void deveBuscarUmClientePJ() {
		Cliente cliente = service.find(ClientePessoaJuridicaTest.ID);
		assertThat(cliente.getId()).isEqualTo(ClientePessoaJuridicaTest.ID);
		assertThat(cliente.getTipo()).isEqualTo(TipoCliente.PESSOA_JURIDICA.getId());
		assertThat(cliente.getCpfOuCnpj()).isEqualTo(ClientePessoaJuridicaTest.CNPJ);
		assertThat(cliente.getNome()).isEqualTo(ClientePessoaJuridicaTest.NOME);
		assertThat(cliente.getEmail()).isEqualTo(ClientePessoaJuridicaTest.EMAIL);
	}
	
	@Test
	@Order(6)    
	@DisplayName("Deve atualizar cliente")
	public void deveAtualizarCliente() {
		Cliente cliente = service.find(ClientePessoaFisicaTest.ID);
		cliente.setNome(ClientePessoaFisicaTest.NOME_NOVO);
		service.update(cliente);
		
		cliente = service.find(ClientePessoaFisicaTest.ID);
		assertThat(cliente.getNome()).isEqualTo(ClientePessoaFisicaTest.NOME_NOVO);
	}
	
	@Test
	@Order(7)    
	@DisplayName("Deve ser um cliente pessoa física")
	public void deveSerClientePF() {
		Cliente cliente = service.find(ClientePessoaFisicaTest.ID);
		assertTrue(Utils.isClientePF(cliente.getTipo()));
	}
	
	@Test
	@Order(8)    
	@DisplayName("Deve retornar todos os clientes")
	public void deveRetornarTodosOsClientes() {
		List<Cliente> clientes = service.findAll();
		assertThat(clientes.size()).isEqualTo(2);
	}

	@Test
	@Order(9)    
	@DisplayName("Deve excluir um cliente")
	public void deveDeletar() {
		service.delete(ClientePessoaJuridicaTest.ID);
		
		try {
			service.find(ClientePessoaJuridicaTest.ID);
	    } catch (ObjectNotFoundException e) {
	        assertEquals("Cliente não encontrado! Id: 2", e.getMessage());
	    }
	}
}
