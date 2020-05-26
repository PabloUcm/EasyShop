package presentacion.controllers;

import negocio.ClienteObserver;
import negocio.MarcaObserver;
import negocio.Modelo;

public class MarcaController {
	private Modelo modelo;
	
	public void addObserver(MarcaObserver o) {
		modelo.addObserver(o);
	}
	
	public MarcaController(Modelo m) {
		this.modelo = m;
	}
	
	public void altaMarca(String dni, String nombre, String telefono) throws Exception {
		modelo.altaCliente(dni, nombre, telefono);
	}
	
	public void bajaMarca(String dni) {
		 modelo.bajaCliente(dni);
	}	
}
