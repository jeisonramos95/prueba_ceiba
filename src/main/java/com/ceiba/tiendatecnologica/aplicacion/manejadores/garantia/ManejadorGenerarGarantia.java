package com.ceiba.tiendatecnologica.aplicacion.manejadores.garantia;

import com.ceiba.tiendatecnologica.aplicacion.comando.ComandoProducto;
import com.ceiba.tiendatecnologica.dominio.GarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.excepcion.GarantiaExtendidaException;
import com.ceiba.tiendatecnologica.dominio.servicio.vendedor.ServicioVendedor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManejadorGenerarGarantia {
	private ServicioVendedor servicioVendedor;

	public ManejadorGenerarGarantia(ServicioVendedor servicioVendedor) {
		this.servicioVendedor = servicioVendedor;
	}
	
	@Transactional
	public GarantiaExtendida ejecutar(String codigoProducto, String nombreCliente, ComandoProducto comandoProducto) {
		return this.servicioVendedor.generarGarantia(codigoProducto, nombreCliente, comandoProducto);
	}
}
