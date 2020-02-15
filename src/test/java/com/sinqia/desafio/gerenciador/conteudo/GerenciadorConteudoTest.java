package com.sinqia.desafio.gerenciador.conteudo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import com.sinqia.desafio.exception.MonitorException;
import com.sinqia.desafio.vo.Venda;
import com.sinqia.desafio.vo.VendaPorVendedor;

public class GerenciadorConteudoTest {

	GerenciadorConteudo gerenciadorConteudo;
	List<String> linhasArquivo = new ArrayList<String>();

	@Before()
	public void setUp() {
		this.gerenciadorConteudo = new GerenciadorConteudo();

		linhasArquivo.add("001ç1234567891234çPedroç50000");
		linhasArquivo.add("002ç2345675434544345çJose da SilvaçRural");
		linhasArquivo.add("003ç21ç[1-5-10,2-1-1.50,3-40-1.55]çVanessa");
		//linhasArquivo.add("005ç21çVanessa");
		linhasArquivo.add("003ç22ç[1-34-1000,2-33-1.50,3-40-0.10]çJuliano");
	}

	@Test
	public void testeDadosVendedor() throws MonitorException {
		String linha = "001ç1234567891234çPedroç50000";
		this.gerenciadorConteudo.geraConteudo(linha);
		assertEquals(gerenciadorConteudo.getVendedores().size(), 1);

		assertTrue(gerenciadorConteudo.getVendas().isEmpty());
		assertTrue(gerenciadorConteudo.getClientes().isEmpty());
		assertTrue(gerenciadorConteudo.getVendaPorVendedor().isEmpty());
	}
	
	@Test
	public void testeDadosCliente() throws MonitorException {
		String linha = "002ç2345675434544345çJose da SilvaçRural";
		this.gerenciadorConteudo.geraConteudo(linha);
		assertEquals(gerenciadorConteudo.getClientes().size(), 1);

		assertTrue(gerenciadorConteudo.getVendas().isEmpty());
		assertTrue(gerenciadorConteudo.getVendedores().isEmpty());
		assertTrue(gerenciadorConteudo.getVendaPorVendedor().isEmpty());
	}
	
	@Test
	public void testeDadosVenda() throws MonitorException {
		String linha = "003ç21ç[1-34-1000,2-33-1.50,3-40-0.10]çVanessa";
		this.gerenciadorConteudo.geraConteudo(linha);
		assertEquals(gerenciadorConteudo.getVendas().size(), 1);

		assertTrue(gerenciadorConteudo.getClientes().isEmpty());
		assertTrue(gerenciadorConteudo.getVendedores().isEmpty());
		assertEquals(gerenciadorConteudo.getVendaPorVendedor().size(), 1);
	}
	
	@Test
	public void testeDadosItemVenda() throws MonitorException {
		String linha = "003ç21ç[1-34-1000,2-33-1.50,3-40-0.10]çVanessa";
		this.gerenciadorConteudo.geraConteudo(linha);
		
		assertEquals(gerenciadorConteudo.getVendas().size(), 1);
		assertEquals(gerenciadorConteudo.getVendaPorVendedor().size(), 1);
		
		Venda venda = gerenciadorConteudo.getVendas().get(0);	
	
		assertEquals(venda.getItens().size(), 3);
		
		//1-34-1000
		assertThat(venda.getItens().get(0).getTotal(),
				Matchers.comparesEqualTo(
						BigDecimal.valueOf(34).multiply(BigDecimal.valueOf(1000))));
		//2-33-1.50
		assertThat(venda.getItens().get(1).getTotal(),
				Matchers.comparesEqualTo(
						BigDecimal.valueOf(33).multiply(BigDecimal.valueOf(1.50))));
		
		//3-40-0.10
		assertThat(venda.getItens().get(2).getTotal(),
						Matchers.comparesEqualTo(
								BigDecimal.valueOf(40).multiply(BigDecimal.valueOf(0.10))));
	}
	
	@Test
	public void testeDadosVendaPorVendedor() throws MonitorException {
		
		geraConteudoComVariasLinhas();	
		
		assertEquals(gerenciadorConteudo.getVendaPorVendedor().size(), 2);
		
		Map<String, VendaPorVendedor> vendaVendedor = gerenciadorConteudo.getVendaPorVendedor();
		
		//34053.50
		//003ç22ç[1-34-1000,2-33-1.50,3-40-0.10]çJuliano
		assertEquals(vendaVendedor.size(), 2);
		assertThat(vendaVendedor.get("Juliano").getTotal(), Matchers.comparesEqualTo(new BigDecimal(34053.50)));
	
		//113.50
		//003ç21ç[1-5-10,2-1-1.50,3-40-1.55]çVanessa
		System.out.println(vendaVendedor.get("Vanessa").getTotal());
		assertThat(vendaVendedor.get("Vanessa").getTotal(), Matchers.comparesEqualTo(new BigDecimal(113.50)));
	}
	
	@Test
	public void testeQuantidadeVendedores() throws MonitorException {
		geraConteudoComVariasLinhas();	
		assertEquals(gerenciadorConteudo.getQtdVendedores(), 1);
	}
	
	@Test
	public void testeQuantidadeClientes() throws MonitorException {
		geraConteudoComVariasLinhas();	
		assertEquals(gerenciadorConteudo.getQtdClientes(), 1);
	}
	

	@Test
	public void testeIdVendaMaisCara() throws MonitorException {
		geraConteudoComVariasLinhas();	
		assertEquals(gerenciadorConteudo.getIdVendaMaisCara(), Long.valueOf(22));
	}
	
	@Test
	public void testePiorVendedor() throws MonitorException {
		geraConteudoComVariasLinhas();	
		assertEquals(gerenciadorConteudo.getPiorVendedor(), "Vanessa");
	}
	
	private void geraConteudoComVariasLinhas() throws MonitorException {
		for(String linha: this.linhasArquivo) {
			this.gerenciadorConteudo.geraConteudo(linha);
		}	
	}
}
