package com.sinqia.desafio.vo;

import java.math.BigDecimal;

/**
 * Classe representa os valores de ItemVenda das linhas dos arquivos.
 * @author juliano
 */
public class ItemVenda extends Entity {

	private final Long id;
	private final Integer quantidade;
	private final BigDecimal precoUnitario;
	private BigDecimal total;

	public ItemVenda(Long id, Integer quantidade, BigDecimal precoUnitario) {
		this.id = id;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.total = precoUnitario.multiply(new BigDecimal(quantidade));
	}
	
	public Long getId() {
		return id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public BigDecimal getTotal() {
		return total;
	}

	@Override
	public String toString() {
		return "ItemVenda [id=" + id + ", quantidade=" + quantidade + ", precoUnitario=" + precoUnitario + ", total="
				+ total + "]";
	}

}
