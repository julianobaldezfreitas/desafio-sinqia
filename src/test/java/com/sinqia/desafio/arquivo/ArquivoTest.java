package com.sinqia.desafio.arquivo;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.sinqia.desafio.exception.ExtensaoException;
import com.sinqia.desafio.exception.MonitorException;
import com.sinqia.desafio.gerenciador.conteudo.GerenciadorConteudo;

@Ignore
public class ArquivoTest {

	private GerenciadorConteudo gerenciador;
	private Arquivo arquivo;

	@Before
	public void setUp() {
		this.arquivo = new Arquivo();
		this.gerenciador = new GerenciadorConteudo();
	}

	@Test
	public void testeValidaSeGerouDados() throws  URISyntaxException, IOException, MonitorException {
		arquivo.percorreArquivoEntrada(pathArquivoValido(), gerenciador);
		assertEquals(gerenciador.getVendedores().size(), 2);
	}
	
	@Test(expected = ExtensaoException.class)
	public void testeExtensaoInvalida() throws IOException, MonitorException, URISyntaxException {
		arquivo.percorreArquivoEntrada(this.pathArquivoExtensaoInvalida(), gerenciador);
	}

	private Path pathArquivoValido() throws URISyntaxException {
		// FIXME pegar arquivos de src/test/resources
		URL url = ArquivoTest.class.getResource("../../../../entrada01.dat");
		return Paths.get(url.toURI());
	}

	private Path pathArquivoExtensaoInvalida() throws URISyntaxException {
		// FIXME pegar arquivos de src/test/resources
		URL url = ArquivoTest.class.getResource("../../../../entrada01.xml");
		return Paths.get(url.toURI());
	}

}
