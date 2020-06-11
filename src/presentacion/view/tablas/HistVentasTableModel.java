package presentacion.view.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import integracion.transfers.TVenta;

@SuppressWarnings("serial")
public class HistVentasTableModel extends AbstractTableModel{

	private  String[] columnNames = {"ID", "ID CLIENTE", "IMPORTE", "FECHA"};
	private List<TVenta> histVentas;
	
	public HistVentasTableModel() {
		histVentas = new ArrayList<>();
	}
	
	public void setHistorialVentas(List<TVenta> lhv) { 
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				histVentas = lhv; 
				fireTableStructureChanged();
			}
		});
	}
	
	public void vaciar() {
		histVentas.clear();
		fireTableStructureChanged();
	}
	
	@Override
	public int getRowCount() {
		return histVentas.size();
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
		TVenta compra = histVentas.get(rowIndex);
		
		if(columnIndex == 0) return compra.getId();
		if(columnIndex == 1) return compra.getIdCliente();
		if(columnIndex == 2) return compra.getTotal();
		if(columnIndex == 3) return compra.getFecha();
		
		return null;
	}

}