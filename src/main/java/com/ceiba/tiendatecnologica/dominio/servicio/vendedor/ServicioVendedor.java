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
	public static final String ESTE_PRODUCTO_NO_CUENTA_CON_GARANTIA = "Este producto no cuenta con garantía extendida";
	public static final String ESTE_PRODUCTO_SI_CUENTA_CON_GARANTIA = "La garantia se generó exitosamente";

	private RepositorioProducto repositorioProducto;
	private RepositorioGarantiaExtendida repositorioGarantia;

	public ServicioVendedor(RepositorioProducto repositorioProducto, RepositorioGarantiaExtendida repositorioGarantia) {
		this.repositorioProducto = repositorioProducto;
		this.repositorioGarantia = repositorioGarantia;
	}

	public String generarGarantia(String codigo, String nombreCliente, ComandoProducto comandoProducto) {

		int count = 0;
		for (int i = 0; i < codigo.length(); i++) {
			if (codigo.charAt(i) == 'a' || codigo.charAt(i) == 'e' || codigo.charAt(i) == 'i' || codigo.charAt(i) == 'o' || codigo.charAt(i) == 'u' || codigo.charAt(i) == 'A'  || codigo.charAt(i) == 'E' || codigo.charAt(i) == 'I' || codigo.charAt(i) == 'O' || codigo.charAt(i) == 'U') {
				count++;
			}
		}
		if(count == 3){
			return ESTE_PRODUCTO_NO_CUENTA_CON_GARANTIA;


			// return new Error("Error", ESTE mensajes);
		}
		this.repositorioGarantia.agregar(codigo, nombreCliente, comandoProducto);
		return ESTE_PRODUCTO_SI_CUENTA_CON_GARANTIA;
	}

	public boolean tieneGarantia(String codigo) {
		return this.repositorioGarantia.obtenerProductoConGarantiaPorCodigo(codigo) != null;
	}
}
