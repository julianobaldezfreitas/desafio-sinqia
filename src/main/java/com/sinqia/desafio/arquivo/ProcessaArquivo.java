package com.sinqia.desafio.arquivo;

import java.io.IOException;
import java.nio.file.Path;

import com.sinqia.desafio.exception.MonitorException;
import com.sinqia.desafio.gerenciador.conteudo.GerenciadorConteudo;

/**
 * Classe responsável pela orquestração do processamento
 * dos dados. Chama as operações de arquivo, de gerenciamento
 * de conteúdo e de impressão do relatório.
 * @author juliano
 */
public class ProcessaArquivo {

	private Arquivo arquivo = new Arquivo();
	private final GerenciadorConteudo gerenciadorConteudo = new GerenciadorConteudo();

	public void processa(Path caminhoDoArquivo) throws IOException, MonitorException {
		try {
			
			arquivo.percorreArquivoEntrada(caminhoDoArquivo, this.gerenciadorConteudo);
			arquivo.criaArquivoSaida(this.gerenciadorConteudo);
			
			System.out.println("Arquivo processado com sucesso: " 
					+ caminhoDoArquivo.toString());

		} catch (IOException | MonitorException e) {
			throw e;
		}
	}

	public GerenciadorConteudo getConteudo() {
		return this.gerenciadorConteudo;
	}
	
	//FIXME injeção para teste
	public void setArquivo(Arquivo novaInstancia) {
		this.arquivo = novaInstancia;
	}

}
