package com.sinqia.desafio.arquivo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sinqia.desafio.exception.CodigoLinhaException;
import com.sinqia.desafio.exception.ExtensaoException;
import com.sinqia.desafio.exception.MonitorException;
import com.sinqia.desafio.gerenciador.conteudo.GerenciadorConteudo;
import com.sinqia.desafio.gerenciador.conteudo.Relatorio;

/**
 * Classe que representa o arquivo a ser analisado
 * dentro do lote. Possui operações para percorrer
 * um arquivo e gerar arquivos de saída.
 * @author juliano
 */
public class Arquivo {

	private static final String USER_HOME_PATH = "user.home";
	private static final String EXTENSAO_PERMITIDA = ".dat";
	private static final String PATH_BASE = "data";
	private static final String PATH_SAIDA = "out";
	private static final String NOME_ARQUIVO_SAIDA = "relatorio.done.dat";

	/**
	 * Percorre arquivo e extrai dados das linhas.
	 * 
	 * @param caminhoDoArquivo caminho do arquivo no sistema operacional.
	 * @param gerenciador instância do gerenciador de dados.
	 * @return instância do gerenciador de dados preenchidas com os dados do arquivo.
	 * @throws IOException erros de escrita e leitura nos arquivos.
	 * @throws MonitorException erros específicos da aplição.
	 */
	public GerenciadorConteudo percorreArquivoEntrada(Path caminhoDoArquivo, GerenciadorConteudo gerenciador)
			throws IOException, MonitorException {
		try {
			if (isExtensaoPermitida(caminhoDoArquivo)) {
				for (String linha : Files.readAllLines(caminhoDoArquivo, StandardCharsets.UTF_8)) {
					try {
						gerenciador.geraConteudo(linha);
					} catch (CodigoLinhaException e) {
						System.out.println("linha nao computada...");
						continue;
					}
				}
			} else {
				throw new ExtensaoException();
			}
		} catch (IOException | MonitorException e) {
			throw e;
		}
		return gerenciador;
	}

	private boolean isExtensaoPermitida(Path caminhoCompletoDoarquivo) {
		return getNomeDoArquivo(caminhoCompletoDoarquivo).contains(EXTENSAO_PERMITIDA);
	}

	private String getNomeDoArquivo(Path caminhoCompletoDoarquivo) {
		return caminhoCompletoDoarquivo.toString();
	}

	private Path criaPathArquivoSaida() {
		String caminhoBase = System.getProperty(USER_HOME_PATH).concat(File.separator).concat(PATH_BASE);
		File diretorioSaida = new File(caminhoBase.concat(File.separator).concat(PATH_SAIDA));
		if (!diretorioSaida.exists()) {
			diretorioSaida.mkdir();
		}
		return Paths.get(diretorioSaida.toString().concat(File.separator).concat(NOME_ARQUIVO_SAIDA));
	}

	public void criaArquivoSaida(GerenciadorConteudo gerenciadorConteudo) throws IOException {
		Path caminhoArquivoSaida = this.criaPathArquivoSaida();

		Relatorio rel = new Relatorio();

		PrintWriter writer = new PrintWriter(Files.newBufferedWriter(caminhoArquivoSaida));
		writer.println(rel.geraRelatorio(gerenciadorConteudo));
		writer.flush();
	}
}
