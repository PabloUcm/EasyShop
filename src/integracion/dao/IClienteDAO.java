package integracion.dao;

import java.util.List;

import integracion.transfers.TCliente;

public interface IClienteDAO {
	
	public TCliente getClienteByDNI(String dni);
	public TCliente getClienteByID(int id);
	public List<TCliente> getAllClientes();
	public int altaCliente(TCliente cliente);
	public void bajaCliente(int id);
	public void modificarCliente(int id, TCliente cliente);
}
