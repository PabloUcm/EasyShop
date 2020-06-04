package presentacion.view.tablas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import negocio.Entity;
import negocio.Log;
import negocio.LogObserver;
import presentacion.controllers.LogController;

@SuppressWarnings("serial")
public class LogTableModel extends AbstractTableModel implements LogObserver {
	
	private LogController controller;
	private String[] columnNames = {"Numero", "Tipo", "Entidad", "Hora"};
	private List<Log> logs;
	
	
	public LogTableModel(LogController controller) {
		this.controller = controller;
		controller.addObserver(this);
		logs = new ArrayList<>();
	}
	
	@Override
	public int getRowCount() {
		return logs.size();
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
		Log l = logs.get(rowIndex);
		
		if(columnIndex == 0) return logs.size();
		else if(columnIndex == 1) return l.getTipo();
		else if(columnIndex == 2) return l.getEntidad().toString();
		else if(columnIndex == 3) return l.getFecha().toString();
			
		return null;
	}

	@Override
	public void alta(Entity entity) {
		logs.add(new Log("Alta",entity, new Date()));
		fireTableStructureChanged();
	}

	@Override
	public void baja(Entity entity) {
		logs.add(new Log("Baja",entity, new Date()));	
		fireTableStructureChanged();
	}

	@Override
	public void modificar(Entity entity) {
		logs.add(new Log("Modificar",entity, new Date()));
		fireTableStructureChanged();
	}

	@Override
	public void mostrar(Entity entity) {
		logs.add(new Log("Mostrar",entity, new Date()));
		fireTableStructureChanged();
	}

	@Override
	public void listar(Entity entity) {
		logs.add(new Log("Listar",entity, new Date()));	
		fireTableStructureChanged();	
	}

}
