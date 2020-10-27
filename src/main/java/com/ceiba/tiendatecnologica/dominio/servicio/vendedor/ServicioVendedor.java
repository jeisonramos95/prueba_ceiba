package com.ceiba.tiendatecnologica.dominio.servicio.vendedor;

import com.ceiba.tiendatecnologica.aplicacion.comando.ComandoProducto;
import com.ceiba.tiendatecnologica.aplicacion.fabrica.FabricaProducto;
import com.ceiba.tiendatecnologica.dominio.GarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.Producto;
import com.ceiba.tiendatecnologica.dominio.excepcion.GarantiaExtendidaException;
import com.ceiba.tiendatecnologica.dominio.repositorio.RepositorioGarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.repositorio.RepositorioProducto;

import java.util.Calendar;
import java.util.Date;

public class ServicioVendedor {

	public static final String ESTE_PRODUCTO_NO_CUENTA_CON_GARANTIA = "Este producto no cuenta con garantía extendida";

	private RepositorioProducto repositorioProducto;
	private RepositorioGarantiaExtendida repositorioGarantia;

	private FabricaProducto fabricaProducto;

	public ServicioVendedor(RepositorioProducto repositorioProducto, RepositorioGarantiaExtendida repositorioGarantia) {
		this.repositorioProducto = repositorioProducto;
		this.repositorioGarantia = repositorioGarantia;
		this.fabricaProducto = new FabricaProducto();
	}

	public GarantiaExtendida generarGarantia(String codigo, String nombreCliente, ComandoProducto comandoProducto) {

		// Regla #3
		if(tieneTresVocales(comandoProducto.getCodigo())){
			throw new GarantiaExtendidaException(ESTE_PRODUCTO_NO_CUENTA_CON_GARANTIA);
		}

		// Reglas #4 y #5
		GarantiaExtendida garantia = obtenerGarantia(comandoProducto, nombreCliente);
		this.repositorioGarantia.agregar(garantia);
		return garantia;
	}

	public GarantiaExtendida obtenerGarantia(ComandoProducto comandoProducto, String nombreCliente) {
		Producto producto = this.fabricaProducto.crearProducto(comandoProducto);
		double precioGarantia = 0.0;
		Date fechaFinGarantia = new Date();
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
		GarantiaExtendida garantia = new GarantiaExtendida(producto, new Date(), fechaFinGarantia, precioGarantia, nombreCliente);
		return garantia;
	}

	public boolean tieneTresVocales(String codigo) {
		int count = 0;
		for (int i = 0; i < codigo.length(); i++) {
			if (codigo.charAt(i) == 'a' || codigo.charAt(i) == 'e' || codigo.charAt(i) == 'i' || codigo.charAt(i) == 'o' || codigo.charAt(i) == 'u' || codigo.charAt(i) == 'A'  || codigo.charAt(i) == 'E' || codigo.charAt(i) == 'I' || codigo.charAt(i) == 'O' || codigo.charAt(i) == 'U') {
				count++;
			}
		}
		if(count == 3){
			return true;
		}
		return false;
	}

	public boolean tieneGarantia(String codigo) {
		return this.repositorioGarantia.obtenerProductoConGarantiaPorCodigo(codigo) != null;
	}
}
