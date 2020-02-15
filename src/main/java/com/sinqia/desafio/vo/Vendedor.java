package com.sinqia.desafio.vo;

import java.math.BigDecimal;

/**
 * Classe representa os valores de Vendedor das linhas dos arquivos.
 * @author juliano
 */
public class Vendedor extends Entity {

	private final String cpf;
	private final String nome;
	private final BigDecimal salario;

	public Vendedor(String cpf, String nome, BigDecimal salario) {
		this.cpf = cpf;
		this.nome = nome;
		this.salario = salario;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	@Override
	public String toString() {
		return "Vendedor [cpf=" + cpf + ", name=" + nome + ", salary=" + salario + "]";
	}
}
