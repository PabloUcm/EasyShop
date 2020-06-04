package presentacion.controllers;

import negocio.Modelo;

public class ProductoController {
private Modelo modelo;
	
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
