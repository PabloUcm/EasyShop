package presentacion.controllers;

import java.util.List;

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
	
	public TCliente altaCliente(String dni, String nombre, String telefono) throws Exception {
		return modelo.altaCliente(dni, nombre, telefono);
	}
	
	public void reactivarCliente(TCliente cliente) {
		modelo.reactivarCliente(cliente);
	}
	
	public void bajaCliente(int id) throws Exception {
		 modelo.bajaCliente(id);
	}
	
	public void modificarCliente(int id, String dni, String nombre, String telefono) throws Exception {
		modelo.modificarCliente(id, dni, nombre, telefono);
	}
	
	public TCliente getCliente(int id) throws Exception {
		return modelo.getCliente(id);
	}
	
	public List<TCliente> listarClientes() {
		return modelo.listarClientes();
	}
}
