package com.sinqia.desafio.vo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.sinqia.desafio.exception.CodigoLinhaException;

/**
 * Classe que instancia as entidades Venda, Vendedor e Cliente
 * de acordo com o código da linha dos arquivos.
 * @author juliano
 */
public class EntityFactory {

	private static final String SEPARADOR_DADOS_LINHA = "ç";
	private static final String SEPARADOR_LISTA_ITEM_VENDA = "-";
	private static final String SEPARADOR_DADOS_ITEM_VENDA = ",";

	private static final String CODIGO_VENDEDOR = "001";
	private static final String CODIGO_CLIENTE = "002";
	private static final String CODIGO_VENDA = "003";

	/**
	 * Cria objetos Entity concretos (Venda, Cliente e Vendedor).
	 * De acordo com o c�digo da linha no arquivo
	 * cria a instância das classes que as representam.
	 * 
	 * @param linhaDoArquivo linha do arquivo percorrido.
	 * @return instância das classes concretas de Entity.
	 * @throws CodigoLinhaException c�digo de linha inválido.
	 */
	public static Entity getEntity(String linhaDoArquivo) throws CodigoLinhaException {
		if (getCodigoTipoDado(linhaDoArquivo).equals(CODIGO_VENDEDOR)) {
			return criaVendedor(linhaDoArquivo);
		}

		if (getCodigoTipoDado(linhaDoArquivo).equals(CODIGO_CLIENTE)) {
			return criaCliente(linhaDoArquivo);
		}

		if (getCodigoTipoDado(linhaDoArquivo).equals(CODIGO_VENDA)) {
			return criaVenda(linhaDoArquivo);
		}

		throw new CodigoLinhaException();
	}

	private static String getCodigoTipoDado(String linhaDoArquivo) {
		String codTipoDados = linhaDoArquivo.substring(0, 3);
		return codTipoDados;
	}
	
	private static String[] separaDadosDaLinha(String linhaDoArquivo) {
		return linhaDoArquivo.split(SEPARADOR_DADOS_LINHA);
	}

	private static Vendedor criaVendedor(String linhaDoArquivo) {
		String[] data = separaDadosDaLinha(linhaDoArquivo);
		Vendedor vendedor = new Vendedor(data[1], data[2], new BigDecimal(data[3]));
		return vendedor;
	}

	private static Cliente criaCliente(String linhaDoArquivo) {
		String[] data = separaDadosDaLinha(linhaDoArquivo);
		Cliente cliente = new Cliente(data[1], data[2], data[3]);
		return cliente;
	}

	private static Venda criaVenda(String linhaDoArquivo) {
		String[] data = separaDadosDaLinha(linhaDoArquivo);
		Venda venda = new Venda(Long.valueOf(data[1]), criaItensVenda(data[2]), data[3]);
		return venda;
	}

	private static List<ItemVenda> criaItensVenda(String itensDeVendaDoArquivo) {
		String[] listaItens = itensDeVendaDoArquivo.replace("[", "").replace("]", "").split(SEPARADOR_DADOS_ITEM_VENDA);

		List<String[]> itens = Arrays.stream(listaItens).map(item -> item.split(SEPARADOR_LISTA_ITEM_VENDA))
				.collect(Collectors.toList());

		return itens.stream()
				.map(item -> new ItemVenda(Long.valueOf(item[0]), Integer.valueOf(item[1]), new BigDecimal(item[2])))
				.collect(Collectors.toList());
	}
	
	public static VendaPorVendedor criaVendaPorVendedor(Venda venda) {
		VendaPorVendedor vendaPorVendedor = new	VendaPorVendedor();
		vendaPorVendedor.setNome(venda.getNomeVendedor());
		vendaPorVendedor.setTotal(venda.getTotalVenda());
		return vendaPorVendedor;
	}
}
