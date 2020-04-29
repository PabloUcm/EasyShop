package presentacion.controllers;

import negocio.ClienteObserver;
import negocio.Modelo;

public class ClienteController {
	private Modelo modelo;
	
	public void addObserver(ClienteObserver o) {
		modelo.addObserver(o);
	}
	
	public ClienteController(Modelo m) {
		this.modelo = m;
	}
	
	public void altaCliente(String dni, String nombre, String telefono) {
		modelo.altaCliente(dni, nombre, telefono);
	}
	
	public void bajaCliente(String dni) {
		 modelo.bajaCliente(dni);
	}
	
	public void getCliente(String dni) {
		modelo.getCliente(dni);
	}
}
