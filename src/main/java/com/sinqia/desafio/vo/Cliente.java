package com.sinqia.desafio.vo;

/**
 * Classe representa os valores de Cliente das linhas dos arquivos.
 * @author juliano
 */
public class Cliente extends Entity {

	private final String cnpj;
	private final String nome;
	private final String areaNegocio;	
	
	public Cliente(String cnpj, String nome, String areaNegocio) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.areaNegocio = areaNegocio;
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public String getNome() {
		return nome;
	}

	public String getAreaNegocio() {
		return areaNegocio;
	}

	@Override
	public String toString() {
		return "Cliente [cnpj=" + cnpj + ", nome=" + nome + ", areaNegocio=" + areaNegocio + "]";
	}
}
