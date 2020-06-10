package presentacion.view.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import integracion.transfers.TVenta;


public class VentaTableModel extends AbstractTableModel{
	private  String[] columnNames = {"ID", "ID Cliente", "ID Empleado", "Importe", "Fecha"};
	private List<TVenta> ventas;
	
	public VentaTableModel() {
		ventas = new ArrayList<>();
	}
	
	public void setVentas(List<TVenta> lv) { 
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				ventas = lv; 
				fireTableStructureChanged();
			}
		});
	}
	
	@Override
	public int getRowCount() {
		return ventas.size();
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
		TVenta v = ventas.get(rowIndex);
		
		switch(columnIndex) {
			case 0: return v.getId();
			case 1: return v.getIdCliente();
			case 2: return v.getIdPersonal();
			case 3: return v.getTotal();
			case 4: return v.getFecha();
			default: return null;
		}
	}

}
