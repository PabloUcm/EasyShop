package negocio;

import java.util.List;

import integracion.transfers.TCliente;

public interface ClienteObserver {
	public void altaCliente(TCliente cliente);
	public void bajaCliente(TCliente cliente);
	public void mostrarClienteId();
	public void modificarCliente();
	public void listarClientes();
	public void obtenerCliente(TCliente cliente);
	void mostrarCliente(List<TCliente> clienteList);
}
