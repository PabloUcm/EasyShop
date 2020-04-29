package presentacion.view.ventas;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;


public class VentasTableModel extends AbstractTableModel{
	
	private  String[] columnNames = {"ID",
						        	 "Importe",
						        	 "Fecha"};
	
	private ArrayList<Venta> ventas;
	
	VentasTableModel() {
		ventas = new ArrayList<>();
		Date d1 = new Date(2020,2,23);
		for (int i = 1; i < 51; i++) {
			Venta v = new Venta(i, i*100, d1);
			ventas.add(v);
		}
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
		Venta v = ventas.get(rowIndex);
		
		switch(columnIndex) {
			case 0: return v.getID();
			case 1: return v.getImporte();
			case 2: return v.getFecha();
			default: return null;
		}
	}
}