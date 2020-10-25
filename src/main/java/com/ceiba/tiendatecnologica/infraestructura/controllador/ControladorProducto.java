package com.ceiba.tiendatecnologica.infraestructura.controllador;

import com.ceiba.tiendatecnologica.aplicacion.comando.ComandoProducto;
import com.ceiba.tiendatecnologica.aplicacion.manejadores.producto.ManejadorCrearProducto;
import com.ceiba.tiendatecnologica.aplicacion.manejadores.producto.ManejadorObtenerProducto;
import com.ceiba.tiendatecnologica.dominio.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ControladorProducto {
	@Autowired
	private final ManejadorCrearProducto manejadorCrearProducto;
	@Autowired
	private final ManejadorObtenerProducto manejadorObtenerProducto;

	public ControladorProducto(ManejadorCrearProducto manejadorCrearProducto, ManejadorObtenerProducto manejadorObtenerProducto) {
		this.manejadorCrearProducto = manejadorCrearProducto;
		this.manejadorObtenerProducto = manejadorObtenerProducto;
	}

	@PostMapping
		public void agregar(@RequestBody ComandoProducto comandoProducto) {
		this.manejadorCrearProducto.ejecutar(comandoProducto);
	}

	@GetMapping("/{id}")
		public Producto buscar(@PathVariable(name = "id") String codigo) {
		return this.manejadorObtenerProducto.ejecutar(codigo);
	}
}
