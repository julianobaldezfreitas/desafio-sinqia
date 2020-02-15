package com.sinqia.desafio.vo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import com.sinqia.desafio.exception.CodigoLinhaException;

public class EntityFactoryTest {

	private List<String> linhasArquivo = new ArrayList<String>();

	@Before
	public void setUp() {
		linhasArquivo.add("001Á1234567891234ÁPedroÁ50000");
		linhasArquivo.add("002Á2345675434544345ÁJose da SilvaÁRural");
		linhasArquivo.add("003Á21Á[1-34-1000,2-33-1.50,3-40-0.10]ÁVanessa");
		linhasArquivo.add("005Á21ÁVanessa");
		linhasArquivo.add("003Á22Á[1-34-1000,2-33-1.50,3-40-0.10]ÁVanessa");
	}

	@Test
	public void testeCriaInstaciaVendedor() throws CodigoLinhaException {
		
		Entity entityCriada = EntityFactory.getEntity(linhasArquivo.get(0));
		assertEquals(entityCriada.getClass(), Vendedor.class);
		
		Vendedor vendedor = (Vendedor) entityCriada;
		assertEquals(vendedor.getCpf(), "1234567891234"); 
		assertEquals(vendedor.getNome(), "Pedro"); 
		assertThat(vendedor.getSalario(),
				Matchers.comparesEqualTo(
						BigDecimal.valueOf(50000)));
	}

	@Test
	public void testeCriaInstaciaCliente() throws CodigoLinhaException {
		Entity entityCriada = EntityFactory.getEntity(linhasArquivo.get(1));
		assertEquals(entityCriada.getClass(), Cliente.class);
		
		Cliente cliente = (Cliente) entityCriada;
		assertEquals(cliente.getCnpj(), "2345675434544345"); 
		assertEquals(cliente.getNome(), "Jose da Silva"); 
		assertEquals(cliente.getAreaNegocio(), "Rural"); 
		
	}

	@Test
	public void testeCriaInstaciaVenda() throws CodigoLinhaException {
		Entity entityCriada = EntityFactory.getEntity(linhasArquivo.get(2));
		assertEquals(entityCriada.getClass(), Venda.class);
	
		//003Á21Á[1-34-1000,2-33-1.50,3-40-0.10]ÁVanessa
		Venda venda = (Venda) entityCriada;
		assertEquals(venda.getId(), Long.valueOf(21)); 
		assertEquals(venda.getNomeVendedor(), "Vanessa"); 
		assertEquals(venda.getItens().size(), 3);
		
		//ItemVenda
		assertEquals(venda.getItens().get(0).getId(), Long.valueOf(1));
		assertEquals(venda.getItens().get(0).getQuantidade(), Integer.valueOf(34));
		assertThat(venda.getItens().get(0).getPrecoUnitario(),
				Matchers.comparesEqualTo(BigDecimal.valueOf(1000)));
		assertThat(venda.getItens().get(0).getTotal(),
				Matchers.comparesEqualTo(
						BigDecimal.valueOf(34).multiply(BigDecimal.valueOf(1000))));
		
		assertEquals(venda.getItens().get(1).getId(), Long.valueOf(2));
		assertEquals(venda.getItens().get(1).getQuantidade(), Integer.valueOf(33));
		assertThat(venda.getItens().get(1).getPrecoUnitario(),
				Matchers.comparesEqualTo(BigDecimal.valueOf(1.50)));
		assertThat(venda.getItens().get(1).getTotal(),
				Matchers.comparesEqualTo(
						BigDecimal.valueOf(33).multiply(BigDecimal.valueOf(1.50))));
		
		assertEquals(venda.getItens().get(2).getId(), Long.valueOf(3));
		assertEquals(venda.getItens().get(2).getQuantidade(), Integer.valueOf(40));
		assertThat(venda.getItens().get(2).getPrecoUnitario(),
				Matchers.comparesEqualTo(BigDecimal.valueOf(0.10)));
		assertThat(venda.getItens().get(2).getTotal(),
				Matchers.comparesEqualTo(
						BigDecimal.valueOf(40).multiply(BigDecimal.valueOf(0.10))));
		
	}

	@Test(expected = CodigoLinhaException.class)
	public void testeCriaInstaciaInvalida() throws CodigoLinhaException {
		EntityFactory.getEntity(linhasArquivo.get(3));	
	}
	
	@Test
	public void validaValoresVendedor() {
		
	}

}
