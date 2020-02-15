package com.sinqia.desafio.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Classe representa os valores de Venda das linhas dos arquivos.
 * @author juliano
 */
public class Venda extends Entity implements Comparable<Venda> {

	private final Long id;
	private final List<ItemVenda> itens;
	private final String nomeVendedor;
	private BigDecimal totalVenda = BigDecimal.valueOf(0);

	public Venda(Long id, List<ItemVenda> itens, String nomeVendedor) {
		this.id = id;
		this.itens = itens;
		this.nomeVendedor = nomeVendedor;
		itens.stream().forEach(item -> this.totalVenda = this.totalVenda.add(item.getTotal()));
	}

	public BigDecimal getTotalVenda() {
		return totalVenda;
	}

	public Long getId() {
		return id;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", itens=" + itens + ", nomeVendedor=" + nomeVendedor + ", totalVenda=" + totalVenda
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Venda other = (Venda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(Venda outraVenda) {
		if ((this.totalVenda.compareTo(outraVenda.totalVenda)) == 1) {
			return -1;
		}
		if ((this.totalVenda.compareTo(outraVenda.totalVenda)) == -1) {
			return 1;
		}
		return 0;
	}
}
