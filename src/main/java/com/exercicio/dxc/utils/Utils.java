package com.exercicio.dxc.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.exercicio.dxc.domain.enums.TipoCliente;


public class Utils {

	// CPF
	private static final int[] WEIGHT_SSN = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	// CNPJ
	private static final int[] WEIGHT_TIN = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	
	// EMAIL
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
	
	public static String somenteNumeros(String cpfOuCnpj) {
		if (!StringUtils.isBlank(cpfOuCnpj)) {
			return cpfOuCnpj.replaceAll("[^\\d ]", "");
		}

		return "";
	}

	private static int calculate(final String str, final int[] weight) {
		int sum = 0;
		for (int i = str.length() - 1, digit; i >= 0; i--) {
			digit = Integer.parseInt(str.substring(i, i + 1));
			sum += digit * weight[weight.length - str.length() + i];
		}
		sum = 11 - sum % 11;
		return sum > 9 ? 0 : sum;
	}
	
	public static boolean isValidCPF(String cpf) {
		cpf = somenteNumeros(cpf);

		if ((cpf == null) || (cpf.length() != 11) || cpf.matches(cpf.charAt(0) + "{11}"))
			return false;

		final Integer digit1 = calculate(cpf.substring(0, 9), WEIGHT_SSN);
		final Integer digit2 = calculate(cpf.substring(0, 9) + digit1, WEIGHT_SSN);
		return cpf.equals(cpf.substring(0, 9) + digit1.toString() + digit2.toString());
	}

	public static boolean isValidCNPJ(String cnpj) {
		cnpj = somenteNumeros(cnpj);

		if ((cnpj == null) || (cnpj.length() != 14) || cnpj.matches(cnpj.charAt(0) + "{14}"))
			return false;

		final Integer digit1 = calculate(cnpj.substring(0, 12), WEIGHT_TIN);
		final Integer digit2 = calculate(cnpj.substring(0, 12) + digit1, WEIGHT_TIN);
		return cnpj.equals(cnpj.substring(0, 12) + digit1.toString() + digit2.toString());
	}

	public static boolean isClientePF(Integer tipo) {
		if (tipo == null) {
			return false;
		}

		return tipo.equals(TipoCliente.PESSOA_FISICA.getId());
	}

	public static boolean isClientePJ(Integer tipo) {
		if (tipo == null) {
			return false;
		}

		return tipo.equals(TipoCliente.PESSOA_JURIDICA.getId());
	}
	
	public static boolean isValidEmail(String email) {
		 return PATTERN.matcher(email).matches();
	}

}
