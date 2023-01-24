package com.exercicio.dxc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.exercicio.dxc.controllers.exceptions.FieldMessage;
import com.exercicio.dxc.domain.Cliente;
import com.exercicio.dxc.domain.dto.ClienteAtualizarDTO;
import com.exercicio.dxc.repositories.ClienteRepository;
import com.exercicio.dxc.utils.Utils;

public class ClienteAtualizarValidator implements ConstraintValidator<ClienteAtualizar, ClienteAtualizarDTO> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ClienteRepository repository;

	@Override
	public void initialize(ClienteAtualizar ann) {
	}

	@Override
	public boolean isValid(ClienteAtualizarDTO clienteAtualizarDTO, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));

		List<FieldMessage> list = new ArrayList<FieldMessage>();

		if (!Utils.isValidEmail(clienteAtualizarDTO.getEmail())) {
			list.add(new FieldMessage("email", "E-mail inválido"));
		}

		Cliente aux = repository.findByEmail(clienteAtualizarDTO.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "E-mail já cadastrado"));
		}

		if (Utils.isClientePF(clienteAtualizarDTO.getTipo())) {
			if (!Utils.isValidCPF(clienteAtualizarDTO.getCpfOuCnpj())) {
				list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
			} else {
				aux = repository.findByCpfOuCnpj(clienteAtualizarDTO.getCpfOuCnpj());
				if (aux != null && !aux.getId().equals(uriId)) {
					list.add(new FieldMessage("cpfOuCnpj", "CPF já cadastrado"));
				}
			}
		}

		if (Utils.isClientePJ(clienteAtualizarDTO.getTipo())) {
			if (!Utils.isValidCNPJ(clienteAtualizarDTO.getCpfOuCnpj())) {
				list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
			} else {
				aux = repository.findByCpfOuCnpj(clienteAtualizarDTO.getCpfOuCnpj());
				if (aux != null && !aux.getId().equals(uriId)) {
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
