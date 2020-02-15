package com.sinqia.desafio.gerenciador.conteudo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinqia.desafio.exception.MonitorException;
import com.sinqia.desafio.vo.Cliente;
import com.sinqia.desafio.vo.Entity;
import com.sinqia.desafio.vo.EntityFactory;
import com.sinqia.desafio.vo.Venda;
import com.sinqia.desafio.vo.VendaPorVendedor;
import com.sinqia.desafio.vo.Vendedor;

/**
 * Classe responsável por gerenciar e acessar
 * os dados obtidos na leitura dos arquivos.
 * @author juliano
 */
public class GerenciadorConteudo {

	private List<Vendedor> vendedores = new ArrayList<Vendedor>();
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private List<Venda> vendas = new ArrayList<Venda>();
	private Map<String, VendaPorVendedor> vendaPorVendedor = new HashMap<String, VendaPorVendedor>();

	/**
	 * Para cada linha do arquivo gera a instancia de um
	 * objeto (Entity). De acordo com o tipo da Entity (Venda,
	 * Vendedor, Cliente) adiciona em uma lista específica por
	 * tipo.
	 * 
	 * A medida que a leitura dos dados é realizada os mesmos já
	 * estão sendo filtrados. Para geração dos relatórios solicitados
	 * os dados são capturados sem impacto de performance.
	 * 
	 * Evita percorrer muitas vezes listas em memória no Java.
	 * @param linhaDoArquivo linha do arquivo percorrido.
	 * @throws MonitorException linha do arquivo não tem o codigo esperado da Entity (Venda, Cliente, Vendedor) 
	 */
	public void geraConteudo(String linhaDoArquivo) throws MonitorException {

		Entity linha = EntityFactory.getEntity(linhaDoArquivo);

		if (linha instanceof Vendedor) {
			this.vendedores.add((Vendedor) linha);
		}

		if (linha instanceof Cliente) {
			this.clientes.add((Cliente) linha);
		}

		if (linha instanceof Venda) {
			Venda venda = (Venda) linha;
			this.vendas.add(venda);
			this.adicionaVendaPorVendedor(venda);
		}	
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public Map<String, VendaPorVendedor> getVendaPorVendedor() {
		return vendaPorVendedor;
	}

	private void adicionaVendaPorVendedor(Venda venda) {
		VendaPorVendedor novaVendaPorVendedor = EntityFactory.criaVendaPorVendedor(venda);
		if (this.vendaPorVendedor.containsKey(venda.getNomeVendedor())) {
			VendaPorVendedor vendaVendedorExistente = this.vendaPorVendedor.get(venda.getNomeVendedor());
			novaVendaPorVendedor.setTotal(novaVendaPorVendedor.getTotal().add(vendaVendedorExistente.getTotal()));
		}
		this.vendaPorVendedor.put(novaVendaPorVendedor.getNome(), novaVendaPorVendedor);
	}
	
	/*
	 * GERA DADOS PARA O RELATÓRIO
	 */
	public int getQtdClientes() {
		if(!getClientes().isEmpty()) {
			return getClientes().size();
		}
		return 0;
	}
	
	public int getQtdVendedores() {
		if(!getVendedores().isEmpty()) {
			return getVendedores().size();
		}
		return 0;
	}
	
	public Long getIdVendaMaisCara() {
		if(!getVendas().isEmpty()) {
		Collections.sort(getVendas());
		return getVendas().get(0).getId();
		} else {
			return Long.valueOf(0);
		}
	}
	
	public String getPiorVendedor() {
		if(!getVendaPorVendedor().isEmpty()) {
			List<VendaPorVendedor> vendaPorVendedorLista = new ArrayList<>(this.getVendaPorVendedor().values());
			Collections.sort(vendaPorVendedorLista);
			return vendaPorVendedorLista.get(0).getNome();
		} else {
			return "Não foram encontrados dados de Venda.";
		}
	}

}
