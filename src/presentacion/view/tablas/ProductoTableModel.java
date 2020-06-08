package presentacion.view.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import integracion.transfers.TCliente;
import integracion.transfers.TProducto;
import presentacion.controllers.ProductoController;

public class ProductoTableModel extends AbstractTableModel{

	private  String[] columnNames = {"ID", "UPC", "Nombre", "Tipo", "Precio", "Cantidad"};
	private List<TProducto> productos;
	
	public ProductoTableModel() {
		productos = new ArrayList<>();
	}
	
	@Override
	public int getRowCount() {
		return productos.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TProducto p = productos.get(rowIndex);
		
		if(columnIndex == 0) return String.valueOf(p.getId());
		if(columnIndex == 1) return p.getUPC();
		if(columnIndex == 2) return p.getNombre();
		if (columnIndex == 3) return p.getTipo();
		if(columnIndex == 4) return String.valueOf(p.getPrecio());
		if(columnIndex == 5) return String.valueOf(p.getCantidad());
		
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	public void setProductos(List<TProducto> lp) { 
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				productos = lp; 
				fireTableStructureChanged();
			}
		});
	}

}
