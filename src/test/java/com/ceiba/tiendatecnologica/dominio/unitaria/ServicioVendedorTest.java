package com.ceiba.tiendatecnologica.dominio.unitaria;


import com.ceiba.tiendatecnologica.aplicacion.comando.ComandoProducto;
import com.ceiba.tiendatecnologica.dominio.GarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.Producto;
import com.ceiba.tiendatecnologica.dominio.servicio.vendedor.ServicioVendedor;
import com.ceiba.tiendatecnologica.dominio.repositorio.RepositorioGarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.repositorio.RepositorioProducto;
import com.ceiba.tiendatecnologica.testdatabuilder.ProductoTestDataBuilder;
import org.junit.Test;

import static com.ceiba.tiendatecnologica.dominio.servicio.vendedor.ServicioVendedor.ESTE_PRODUCTO_NO_CUENTA_CON_GARANTIA;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioVendedorTest {

	@Test
	public void productoYaTieneGarantiaTest() {
		
		// arrange
		ProductoTestDataBuilder productoTestDataBuilder = new ProductoTestDataBuilder();
		
		Producto producto = productoTestDataBuilder.build();
		
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
		
		when(repositorioGarantia.obtenerProductoConGarantiaPorCodigo(producto.getCodigo())).thenReturn(producto);
		
		ServicioVendedor servicioVendedor = new ServicioVendedor(repositorioProducto, repositorioGarantia);
		
		// act 
		boolean existeProducto = servicioVendedor.tieneGarantia(producto.getCodigo());
		
		//assert
		assertTrue(existeProducto);
	}
	
	@Test
	public void productoNoTieneGarantiaTest() {
		
		// arrange
		ProductoTestDataBuilder productoestDataBuilder = new ProductoTestDataBuilder();
		
		Producto producto = productoestDataBuilder.build(); 
		
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
		
		when(repositorioGarantia.obtenerProductoConGarantiaPorCodigo(producto.getCodigo())).thenReturn(null);
		
		ServicioVendedor servicioVendedor = new ServicioVendedor(repositorioProducto, repositorioGarantia);
		
		// act 
		boolean existeProducto =  servicioVendedor.tieneGarantia(producto.getCodigo());
		
		//assert
		assertFalse(existeProducto);
	}

	@Test (expected = RuntimeException.class)
	public void generarGarantiaCodigoTresVocaes(){

		//arrange
		ProductoTestDataBuilder productoestDataBuilder = new ProductoTestDataBuilder();

		productoestDataBuilder.conCodigo("F12ASEU89");
		ComandoProducto comandoProducto = new ComandoProducto("FEUA89", "Tv LG", 600000.00);
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);

		//act
		ServicioVendedor servicioVendedor = new ServicioVendedor(repositorioProducto, repositorioGarantia);

		try
		{
			GarantiaExtendida response = servicioVendedor.generarGarantia(null, null, comandoProducto);
		}
		catch(RuntimeException re)
		{
			assertEquals(ESTE_PRODUCTO_NO_CUENTA_CON_GARANTIA, re.getMessage());
			throw re;
		}
		fail("No se lanza el error en generar garantia!");

	}

	@Test
	public void generarGarantiaCodigoNoContieneTresVocales(){

		//arrange
		ProductoTestDataBuilder productoestDataBuilder = new ProductoTestDataBuilder();

		ComandoProducto comandoProducto = new ComandoProducto("FEU789", "Tv LG", 600000.00);

		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
		ServicioVendedor servicioVendedor = new ServicioVendedor(repositorioProducto, repositorioGarantia);

		//act
		GarantiaExtendida response = servicioVendedor.generarGarantia(comandoProducto.getCodigo(), "Jeison", comandoProducto);

		//assert
		assertEquals("FEU789", response.getProducto().getCodigo());

	}

	@Test
	public void retornaTrueConCodigoTresVocales(){
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
		ServicioVendedor servicioVendedor = new ServicioVendedor(repositorioProducto, repositorioGarantia);

		//act
		boolean result = servicioVendedor.tieneTresVocales("AEILK");
		assertEquals(result, true);

	}

	@Test
	public void retornaFalseConCodigoDiferenteTresVocales(){
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
		ServicioVendedor servicioVendedor = new ServicioVendedor(repositorioProducto, repositorioGarantia);

		//act
		boolean result = servicioVendedor.tieneTresVocales("ARILK");
		assertEquals(result, false);

	}

	@Test
	public void precioGarantiaConPrecioProductoMayorAQuinientos() {
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
		ServicioVendedor servicioVendedor = new ServicioVendedor(repositorioProducto, repositorioGarantia);
		ComandoProducto comandoProducto = new ComandoProducto("FEU789", "Tv LG", 600000.00);

		//act
		GarantiaExtendida garantia = servicioVendedor.obtenerGarantia(comandoProducto, "Jeison");

		assertTrue(garantia.getPrecioGarantia() == 120000.00);
	}

	@Test
	public void precioGarantiaConPrecioProductoMenorIgualAQuinientos() {
		RepositorioGarantiaExtendida repositorioGarantia = mock(RepositorioGarantiaExtendida.class);
		RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
		ServicioVendedor servicioVendedor = new ServicioVendedor(repositorioProducto, repositorioGarantia);
		ComandoProducto comandoProducto = new ComandoProducto("FEU789", "Tv LG", 400000.00);

		//act
		GarantiaExtendida garantia = servicioVendedor.obtenerGarantia(comandoProducto, "Jeison");

		assertTrue(garantia.getPrecioGarantia() == 40000.00);
	}
}
