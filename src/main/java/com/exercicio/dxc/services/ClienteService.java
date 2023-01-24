package com.exercicio.dxc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.exercicio.dxc.domain.Cliente;
import com.exercicio.dxc.repositories.ClienteRepository;
import com.exercicio.dxc.services.exceptions.DataIntegrityException;
import com.exercicio.dxc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente find(Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " + id));
	}

	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return repository.save(cliente);
	}

	public Cliente update(Cliente cliente) {
		Cliente novoCliente = find(cliente.getId());
		Cliente.updateData(novoCliente, cliente);
		return repository.save(novoCliente);
	}

	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir");
		}
	}
}
