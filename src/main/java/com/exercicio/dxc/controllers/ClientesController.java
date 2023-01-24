package com.exercicio.dxc.controllers;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exercicio.dxc.domain.Cliente;
import com.exercicio.dxc.domain.dto.ClienteAdicionarDTO;
import com.exercicio.dxc.domain.dto.ClienteAtualizarDTO;
import com.exercicio.dxc.domain.dto.ClienteDTO;
import com.exercicio.dxc.domain.dto.TipoClienteDTO;
import com.exercicio.dxc.domain.dto.converter.ClienteConverter;
import com.exercicio.dxc.domain.enums.TipoCliente;
import com.exercicio.dxc.services.ClienteService;
import com.exercicio.dxc.services.rabbitmq.RabbitMQProducerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/clientes")
@Tag(name = "Clientes")
public class ClientesController {

	@Autowired
	private ClienteService service;
	
	@Autowired
	public RabbitMQProducerService producer;

	@Autowired
	private ClienteConverter converter;

	@Operation(summary = "Listar todos os clientes")
	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<ClienteDTO> list = converter.Parse(service.findAll());
		return ResponseEntity.ok().body(list);
	}

	@Operation(summary = "Buscar o cliente pelo id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> find(@PathVariable Long id) {
		ClienteDTO obj = converter.Parse(service.find(id));
		return ResponseEntity.ok().body(obj);
	}
	
	@Operation(summary = "Listar os tipos de cliente")
	@GetMapping(value = "/tipos")
	public ResponseEntity<List<TipoClienteDTO>> tipos() {
		List<TipoClienteDTO> list = converter.ParseTipoClienteDTO(Arrays.asList(TipoCliente.values()));
		return ResponseEntity.ok().body(list);
	}

	@Operation(summary = "Adicionar um cliente")
	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteAdicionarDTO clienteAdicionarDTO) {
		Cliente cliente = converter.ParseAdicionarDTO(clienteAdicionarDTO);
		cliente = service.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Operation(summary = "Atualizar um cliente")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteAtualizarDTO objDto, @PathVariable Long id) {
		Cliente obj = converter.ParseAtualizarDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Deletar um cliente")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Envia uma mesagem para fila do RabbitMQ")
	@GetMapping("/mensagem")
    public ResponseEntity<String> sendMessage(@RequestParam("mensagem") String message){
        producer.sendMessage(message);
        return ResponseEntity.ok("Mensagem enviada com sucesso");
    }
}
