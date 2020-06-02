package presentacion.view.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import integracion.transfers.TCliente;
import negocio.ClienteObserver;
import presentacion.controllers.ClienteController;

public class ClienteTableModel extends AbstractTableModel implements ClienteObserver {
	private  String[] columnNames = {"ID", "DNI", "Nombre", "Telefono"};

	private ClienteController controlador;
	private List<TCliente> clientes;
	
	public ClienteTableModel(ClienteController c) {
		this.controlador = c;
		controlador.addObserver(this);
		clientes = new ArrayList<>();
		controlador.listarClientes();
	}
	
	@Override
	public int getRowCount() {
		return clientes.size();
	}
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TCliente c = clientes.get(rowIndex);
		
		switch(columnIndex) {
			case 0: return c.getId();
			case 1: return c.getDni();
			case 2: return c.getNombre();
			case 3: return c.getTelefono();
			default: return null;
		}
	}

	@Override
	public void altaCliente(TCliente cliente) {
		clientes.add(cliente);
		fireTableStructureChanged();		
	}

	@Override
	public void bajaCliente(TCliente cliente) {
		clientes.remove(cliente);
		fireTableStructureChanged();	
	}

	@Override
	public void mostrarClienteId() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarCliente() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listarClientes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void obtenerCliente(TCliente cliente) {
	}

	@Override
	public void mostrarCliente(List<TCliente> clienteList) {
		clientes = clienteList;
		fireTableStructureChanged();
	}
}
