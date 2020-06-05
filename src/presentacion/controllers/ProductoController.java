package presentacion.controllers;

import java.util.List;

import integracion.transfers.TProducto;
import negocio.Modelo;

public class ProductoController {
private Modelo modelo;
	
	public ProductoController(Modelo m) {
		this.modelo = m;
	}
	
	public List<TProducto> listarProductos(){
		return modelo.listarProductos();
	}
}
