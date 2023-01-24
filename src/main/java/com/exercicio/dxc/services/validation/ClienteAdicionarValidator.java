package com.exercicio.dxc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.exercicio.dxc.controllers.exceptions.FieldMessage;
import com.exercicio.dxc.domain.Cliente;
import com.exercicio.dxc.domain.dto.ClienteAdicionarDTO;
import com.exercicio.dxc.repositories.ClienteRepository;
import com.exercicio.dxc.utils.Utils;

public class ClienteAdicionarValidator implements ConstraintValidator<ClienteAdicionar, ClienteAdicionarDTO> {

	@Autowired
	private ClienteRepository repository;

	@Override
	public void initialize(ClienteAdicionar ann) {
	}

	@Override
	public boolean isValid(ClienteAdicionarDTO clienteAdicionarDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<FieldMessage>();
		
		if (!Utils.isValidEmail(clienteAdicionarDTO.getEmail())) {
			list.add(new FieldMessage("email", "E-mail inválido"));
		}
		
		Cliente aux = repository.findByEmail(clienteAdicionarDTO.getEmail());
		if (aux != null) {			
			list.add(new FieldMessage("email", "E-mail já cadastrado"));
		}
		
		if (Utils.isClientePF(clienteAdicionarDTO.getTipo())) {
			if (!Utils.isValidCPF(clienteAdicionarDTO.getCpfOuCnpj())) {
				list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
			} else {
				aux = repository.findByCpfOuCnpj(clienteAdicionarDTO.getCpfOuCnpj());
				if (aux != null) {
					list.add(new FieldMessage("cpfOuCnpj", "CPF já cadastrado"));
				}
			}
		}
					
		if (Utils.isClientePJ(clienteAdicionarDTO.getTipo())) {
			if (!Utils.isValidCNPJ(clienteAdicionarDTO.getCpfOuCnpj())) {
				list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
			} else {
				aux = repository.findByCpfOuCnpj(clienteAdicionarDTO.getCpfOuCnpj());
				if (aux != null) {
					list.add(new FieldMessage("cpfOuCnpj", "CNPJ já cadastrado"));
				}
			}
		}
				
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();
	}
	
}
