package com.sinqia.desafio.monitor;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.sinqia.desafio.arquivo.ProcessaArquivo;
import com.sinqia.desafio.exception.MonitorException;;


/**
 * Classe principal responsável por executar a aplição.
 * Monitora um diretorio e dispara o processamento dos mesmos
 * quando criados novos arquivos.
 * @author juliano
 */
public class Aplicacao {

	private static final String USER_HOME_PATH = "user.home";

	public static void main(String[] args) throws IOException, InterruptedException {
		monitorDiretorio();
	}

	/**
	 * Monitara um diretório do sistema operacional e observa
	 * eventos de criação de arquivos. Para cada arquivo gerado neste 
	 * diretório faz o processamento do mesmo.
	 * @throws IOException erros na leitura e escrita dos arquivos.
	 * @throws InterruptedException
	 */
	public static void monitorDiretorio() throws IOException, InterruptedException {

		ProcessaArquivo processaArquivo = new ProcessaArquivo();

		String caminhoLotesArquivos = System.getProperty(USER_HOME_PATH).concat(File.separator).concat("data")
				.concat(File.separator).concat("in");

		Path caminhoMonitorado = Paths.get(caminhoLotesArquivos);
		final WatchService monitorDiretorio = FileSystems.getDefault().newWatchService();

		try {
			caminhoMonitorado.register(monitorDiretorio, ENTRY_CREATE);
		} catch (NoSuchFileException e) {
			System.err.println("Erro: Diretório de entrada de dados não existe: " + caminhoLotesArquivos);
		}

		WatchKey itemMonitorado;
		while ((itemMonitorado = monitorDiretorio.take()) != null) {
			for (WatchEvent<?> evento : itemMonitorado.pollEvents()) {

				WatchEvent.Kind<?> tipoEvento = evento.kind();
				final Path arquivoEncontrado = caminhoMonitorado.resolve(evento.context().toString());

				if (ENTRY_CREATE.equals(tipoEvento)) {
					try {
						System.out.println("-- Iniciando processamento do arquivo: " + arquivoEncontrado);
						processaArquivo.processa(arquivoEncontrado);
					} catch (IOException | MonitorException e) {
						e.printStackTrace();
						System.out.println("-- Arquivo não processado: " + arquivoEncontrado);
						continue;
					}
				}
			}

			if (!itemMonitorado.reset()) {
				break;
			}
		}

	}
}
