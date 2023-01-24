package com.exercicio.dxc.domain.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.exercicio.dxc.domain.Cliente;
import com.exercicio.dxc.domain.dto.ClienteAdicionarDTO;
import com.exercicio.dxc.domain.dto.ClienteAtualizarDTO;
import com.exercicio.dxc.domain.dto.ClienteDTO;
import com.exercicio.dxc.domain.dto.TipoClienteDTO;
import com.exercicio.dxc.domain.enums.TipoCliente;

@Service
public class ClienteConverter {

	public ClienteDTO Parse(Cliente origin) {
		if (origin == null)
			return null;

		return new ClienteDTO(origin.getId(), ParseTipoClienteDTO(TipoCliente.toEnum(origin.getTipo())),
				origin.getCpfOuCnpj(), origin.getNome(), origin.getEmail());
	}

	public List<ClienteDTO> Parse(List<Cliente> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> Parse(obj)).collect(Collectors.toList());
	}

	public Cliente ParseAdicionarDTO(ClienteAdicionarDTO origin) {
		if (origin == null)
			return null;

		return new Cliente(TipoCliente.toEnum(origin.getTipo()), origin.getCpfOuCnpj(), origin.getNome(),
				origin.getEmail());
	}

	public List<Cliente> ParseAdicionarDTO(List<ClienteAdicionarDTO> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseAdicionarDTO(obj)).collect(Collectors.toList());
	}

	public Cliente ParseAtualizarDTO(ClienteAtualizarDTO origin) {
		if (origin == null)
			return null;

		return new Cliente(TipoCliente.toEnum(origin.getTipo()), origin.getCpfOuCnpj(), origin.getNome(),
				origin.getEmail());
	}

	public List<Cliente> ParseAtualizarDTO(List<ClienteAtualizarDTO> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseAtualizarDTO(obj)).collect(Collectors.toList());
	}

	public TipoClienteDTO ParseTipoClienteDTO(TipoCliente origin) {
		if (origin == null)
			return null;

		return new TipoClienteDTO(origin.getId(), origin.getDescricao());
	}

	public List<TipoClienteDTO> ParseTipoClienteDTO(List<TipoCliente> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseTipoClienteDTO(obj)).collect(Collectors.toList());
	}

}
