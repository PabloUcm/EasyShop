package presentacion.view.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import integracion.transfers.TCliente;
import integracion.transfers.TVenta;

public class ComprasTableModel extends AbstractTableModel{

	private  String[] columnNames = {"ID", "ID EMPLEADO", "IMPORTE", "FECHA"};
	private List<TVenta> compras;
	
	public ComprasTableModel() {
		compras = new ArrayList<>();
	}
	
	public void setCompras(List<TVenta> lc) { 
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				compras = lc; 
				fireTableStructureChanged();
			}
		});
	}
	
	public void vaciar() {
		compras.clear();
		fireTableStructureChanged();
	}
	
	@Override
	public int getRowCount() {
		return compras.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int i) {
		return columnNames[i];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TVenta compra = compras.get(rowIndex);
		
		if(columnIndex == 0) return compra.getId();
		if(columnIndex == 1) return compra.getIdPersonal();
		if(columnIndex == 2) return compra.getTotal();
		if(columnIndex == 3) return compra.getFecha();
		
		return null;
	}

}
