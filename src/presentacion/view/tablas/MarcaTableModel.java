package presentacion.view.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;


import integracion.transfers.TMarca;



@SuppressWarnings("serial")
public class MarcaTableModel extends AbstractTableModel {
	private  String[] columnNames = {"ID", "CIF", "Nombre", "Pais"};
	private List<TMarca> marcas;
	
	public MarcaTableModel() {
		marcas = new ArrayList<>();
	}
	
	public void setMarcas(List<TMarca> lm) { 
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				marcas = lm; 
				fireTableStructureChanged();
			}
		});
	}
	
	@Override
	public int getRowCount() {
		return marcas.size();
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
		TMarca m = marcas.get(rowIndex);
		
		switch(columnIndex) {
			case 0: return m.getId();
			case 1: return m.getCIF();
			case 2: return m.getNombre();
			case 3: return m.getPais();
			default: return null;
		}
	}

}
