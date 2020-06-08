package presentacion.controllers;

import java.util.List;

import integracion.transfers.TProducto;
import negocio.Modelo;

public class VentaController {
private Modelo modelo;
	
	public VentaController(Modelo m) {
		this.modelo = m;
	}
	
	public List<TProducto> listarProductos(){
		return modelo.listarProductos();
	}
}
