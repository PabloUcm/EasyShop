package presentacion.controllers;

import negocio.ClienteObserver;
import negocio.Modelo;
import negocio.ProductoObserver;

public class ProductoController {
private Modelo modelo;
	
	public void addObserver(ProductoObserver o) {
		modelo.addObserver(o);
	}
	
	public ProductoController(Modelo m) {
		this.modelo = m;
	}
	
	public void altaProducto(String dni, String nombre, String telefono) {
		modelo.altaProducto(dni, nombre, telefono);
	}
	
	public void bajaProducto(String dni) {
		 modelo.bajaProducto(dni);
	}
}
