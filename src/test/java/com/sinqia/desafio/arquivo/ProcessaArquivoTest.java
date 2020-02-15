package com.sinqia.desafio.arquivo;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;	

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.sinqia.desafio.exception.MonitorException;
import com.sinqia.desafio.gerenciador.conteudo.GerenciadorConteudo;

@Ignore
public class ProcessaArquivoTest {

	private ProcessaArquivo processaArquivo;
	private static Path caminhoMonitorado = null;
	
	private Arquivo arquivo;

	@BeforeClass
	public static void abreArquivoDeTeste() throws URISyntaxException, IOException {
		java.net.URL url = ArquivoTest.class.getResource("../../../../entrada01.dat");
		caminhoMonitorado = java.nio.file.Paths.get(url.toURI());
	}

	@Before
	public void setUp() throws IOException, URISyntaxException {
		this.processaArquivo = new ProcessaArquivo();
		
		// Mock parcial do objeto Arquivo 
		// nao gera arquivo de saída
		arquivo =  Mockito.spy(new Arquivo());
		Mockito.doNothing().when(arquivo).criaArquivoSaida(Mockito.anyObject());
		processaArquivo.setArquivo(arquivo);
	}

	@Test
	public void testaConteudoGerenciador() throws IOException, MonitorException {
		processaArquivo.processa(caminhoMonitorado);
		GerenciadorConteudo gerenciadorConteudo = processaArquivo.getConteudo();
		assertEquals(gerenciadorConteudo.getVendedores().size() , 2);
	}
}
