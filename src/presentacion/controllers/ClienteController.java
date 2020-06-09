package presentacion.controllers;

import java.util.List;

import integracion.transfers.TCliente;
import integracion.transfers.TVenta;
import negocio.Modelo;

public class ClienteController {
	private Modelo modelo;
	
	public ClienteController(Modelo m) {
		this.modelo = m;
	}
	
	public TCliente altaCliente(TCliente cliente) throws Exception {
		return modelo.altaCliente(cliente);
	}
	
	public void reactivarCliente(int id) throws Exception {
		modelo.reactivarCliente(id);
	}
	
	public void bajaCliente(int id) throws Exception {
		 modelo.bajaCliente(id);
	}
	
	public void modificarCliente(TCliente cliente) throws Exception {
		modelo.modificarCliente(cliente);
	}
	
	public TCliente getCliente(int id) throws Exception {
		return modelo.getCliente(id);
	}
	
	public List<TCliente> listarClientes() {
		return modelo.listarClientes();
	}
	
	public List<TVenta> getCompras(int id) throws Exception{
		return modelo.getCompras(id);
	}
}
