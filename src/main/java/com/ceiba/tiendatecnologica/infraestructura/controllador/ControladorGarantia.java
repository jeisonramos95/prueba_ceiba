package com.ceiba.tiendatecnologica.infraestructura.controllador;

import com.ceiba.tiendatecnologica.aplicacion.comando.ComandoProducto;
import com.ceiba.tiendatecnologica.aplicacion.manejadores.garantia.ManejadorGenerarGarantia;
import com.ceiba.tiendatecnologica.aplicacion.manejadores.garantia.ManejadorObtenerGarantia;
import com.ceiba.tiendatecnologica.dominio.GarantiaExtendida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/garantia")
public class ControladorGarantia {
	@Autowired
	private final ManejadorObtenerGarantia manejadorObtenerGarantia;

	@Autowired
	private final ManejadorGenerarGarantia mananeManejadorGenerarGarantia;

	public ControladorGarantia(ManejadorObtenerGarantia manejadorObtenerGarantia, ManejadorGenerarGarantia manejadorGenerarGarantia) {
		this.manejadorObtenerGarantia = manejadorObtenerGarantia;
		this.mananeManejadorGenerarGarantia = manejadorGenerarGarantia;
	}

	@PostMapping("/{idProducto}/{nombreCliente}")
	public GarantiaExtendida generar(@PathVariable(name = "idProducto") String codigoProducto, @PathVariable(name = "nombreCliente") String nombreCliente, @RequestBody ComandoProducto comandoProducto) {
		return this.mananeManejadorGenerarGarantia.ejecutar(codigoProducto, nombreCliente, comandoProducto);
	}

	@GetMapping("/{id}")
	public GarantiaExtendida buscar(@PathVariable(name = "id") String codigo) {
		return this.manejadorObtenerGarantia.ejecutar(codigo);
	}
}
