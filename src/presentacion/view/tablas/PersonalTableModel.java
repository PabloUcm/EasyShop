package presentacion.view.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import integracion.transfers.TPersonal;

import presentacion.controllers.PersonalController;

public class PersonalTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  String[] columnNames = {"ID", "DNI", "Nombre", "Sueldo","Telefono"};

	private PersonalController controlador;
	private List<TPersonal> empleados;
	
	public PersonalTableModel(PersonalController c){
		controlador = c;
		empleados = new ArrayList<>();
		controlador.listarEmpleados();
		
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return empleados.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		TPersonal e = empleados.get(rowIndex);
		
		switch(columnIndex) {
			case 0: return e.getId();
			case 1: return e.getDni();
			case 2: return e.getNombre();
			case 3: return e.getSueldo();
			case 4: return e.getTelefono();
			default: return null;
		}
	
	}

}
