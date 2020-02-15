package com.sinqia.desafio.vo;

import java.math.BigDecimal;

/**
 * Classe criada para cálculo dos totais de venda por vendedor. Objetivo de
 * melhorar a performance.
 * 
 * @author juliano
 */
public class VendaPorVendedor implements Comparable<VendaPorVendedor> {

	private String nome;
	private BigDecimal total;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "VendaPorVendedor [nome=" + nome + ", total=" + total + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendaPorVendedor other = (VendaPorVendedor) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public int compareTo(VendaPorVendedor outraVendaVendedor) {
		if (this.total.compareTo(outraVendaVendedor.total) == -1) {
			return -1;
		}
		if (this.total.compareTo(outraVendaVendedor.total) == 1) {
			return 1;
		}
		return 0;
	}

}
