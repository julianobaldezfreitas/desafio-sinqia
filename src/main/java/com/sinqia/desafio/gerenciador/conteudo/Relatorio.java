package com.sinqia.desafio.gerenciador.conteudo;

/**
 * Classe responsável por formatar os relatórios
 * da análise de dados.
 * @author juliano
 */
public class Relatorio {

	/**
	 * Gera relatório com os dados solicitados.
	 * 
	 * @param instância de gerenciadorConteudo.
	 * @return relatório formatado modo texto.
	 */
	public String geraRelatorio(GerenciadorConteudo gerenciadorConteudo) {
		StringBuffer texto = new StringBuffer();
		texto.append(String.format(
				"Quantidade de clientes nos arquivos: %d ", 
				gerenciadorConteudo.getQtdClientes()));
		texto.append("\n");
		texto.append(String.format(
				"Quantidade de vendedores nos arquivos: %d ",
				gerenciadorConteudo.getQtdVendedores()));
		texto.append("\n");
		texto.append(String.format(
				"ID da venda mais cara: %d ",
				gerenciadorConteudo.getIdVendaMaisCara()));
		texto.append("\n");
		texto.append(String.format(
				"O pior vendedor é: %s ",
				gerenciadorConteudo.getPiorVendedor()));

		return texto.toString();
	}

}
