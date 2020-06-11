package presentacion.view.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import integracion.transfers.TCliente;

@SuppressWarnings("serial")
public class ClienteTableModel extends AbstractTableModel{
	
	private  String[] columnNames = {"ID", "DNI", "Nombre", "Telefono"};
	private List<TCliente> clientes;
	
	public ClienteTableModel() {
		clientes = new ArrayList<>();
	}
	
	public void setClientes(List<TCliente> lc) { 
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				clientes = lc; 
				fireTableStructureChanged();
			}
		});
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

}
