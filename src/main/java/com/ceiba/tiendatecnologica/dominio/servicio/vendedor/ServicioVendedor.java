package com.ceiba.tiendatecnologica.dominio.servicio.vendedor;

import com.ceiba.tiendatecnologica.aplicacion.comando.ComandoProducto;
import com.ceiba.tiendatecnologica.dominio.GarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.Producto;
import com.ceiba.tiendatecnologica.dominio.excepcion.GarantiaExtendidaException;
import com.ceiba.tiendatecnologica.dominio.repositorio.RepositorioGarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.repositorio.RepositorioProducto;

import java.util.Calendar;
import java.util.Date;

public class ServicioVendedor {

	public static final String EL_PRODUCTO_TIENE_GARANTIA = "El producto ya cuenta con una garantía extendida";

	private RepositorioProducto repositorioProducto;
	private RepositorioGarantiaExtendida repositorioGarantia;

	public ServicioVendedor(RepositorioProducto repositorioProducto, RepositorioGarantiaExtendida repositorioGarantia) {
		this.repositorioProducto = repositorioProducto;
		this.repositorioGarantia = repositorioGarantia;
	}

	public void generarGarantia(String codigo, String nombreCliente) {
		Producto producto = this.repositorioProducto.obtenerPorCodigo(codigo);
		double precioGarantia;
		Date fechaFinGarantia;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if(producto.getPrecio() > 500000){
		  precioGarantia = 0.2 * producto.getPrecio();
		  int day = 0;
		  int count = 1;
		  while(count < 200){
			 calendar.add(Calendar.DAY_OF_YEAR, 1);
			 day = calendar.get(Calendar.DAY_OF_WEEK);
			 if (day != 2){
				count++;
			 }
		  }
		  if(day == 1) {
			 calendar.add(Calendar.DAY_OF_YEAR, 2);
			  }
			} else {
			  precioGarantia = 0.1 * producto.getPrecio();
			  calendar.add(Calendar.DAY_OF_YEAR, 100);
			}
			fechaFinGarantia = calendar.getTime();
		this.repositorioGarantia.agregar(new GarantiaExtendida(producto, new Date(), fechaFinGarantia, precioGarantia, nombreCliente));
	}

	public boolean tieneGarantia(String codigo) {
		return this.repositorioGarantia.obtenerProductoConGarantiaPorCodigo(codigo) != null;
	}
}
