package presentacion.view.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import integracion.transfers.TMarca;
import negocio.MarcaObserver;
import presentacion.controllers.MarcaController;


@SuppressWarnings("serial")
public class MarcaTableModel extends AbstractTableModel implements MarcaObserver {
	private  String[] columnNames = {"ID", "CIF", "Nombre", "Pais"};

	private MarcaController controlador;
	private List<TMarca> marcas;
	
	public MarcaTableModel(MarcaController c) {
		this.controlador = c;
		controlador.addObserver(this);
		marcas = new ArrayList<>();
		controlador.listarMarcas();
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

	@Override
	public void altaMarca(TMarca marca) {
		marcas.add(marca);
		fireTableStructureChanged();		
	}

	@Override
	public void bajaMarca(TMarca marca) {
		marcas.remove(marca);
		fireTableStructureChanged();
	}

	@Override
	public void mostrarMarcaId() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarMarca() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listarMarcas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void obtenerMarca(TMarca marca) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarMarca(List<TMarca> marcaList) {
		marcas = marcaList;
		fireTableStructureChanged();
		
	}
}
