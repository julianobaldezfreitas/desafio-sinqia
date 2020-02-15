package com.sinqia.desafio.gerenciador.conteudo;

/**
 * Classe respons�vel por formatar os relat�rios
 * da an�lise de dados.
 * @author juliano
 */
public class Relatorio {

	/**
	 * Gera relat�rio com os dados solicitados.
	 * 
	 * @param inst�ncia de gerenciadorConteudo.
	 * @return relat�rio formatado modo texto.
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
				"O pior vendedor �: %s ",
				gerenciadorConteudo.getPiorVendedor()));

		return texto.toString();
	}

}
