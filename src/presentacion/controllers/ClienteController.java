package presentacion.controllers;

import integracion.transfers.TCliente;
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
	
	public void altaCliente(String dni, String nombre, String telefono) throws Exception {
		modelo.altaCliente(dni, nombre, telefono);
	}
	
	public void bajaCliente(String dni) {
		 modelo.bajaCliente(dni);
	}
	
	public TCliente getCliente(int id) throws Exception {
		return modelo.getCliente(id);
	}
	
	public void listarClientes() {
		modelo.listarClientes();
	}
}
