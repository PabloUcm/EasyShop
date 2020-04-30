package integracion.dao;

import java.util.List;

import integracion.transfers.TCliente;

public interface IClienteDAO {
	public TCliente getClienteByDNI(String dni);
	
	public List<TCliente> getAllClientes();
}
