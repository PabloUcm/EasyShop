package presentacion.view.personal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentacion.controllers.PersonalController;
import presentacion.view.tablas.PersonalTableModel;


public class ListarEmpleados {
	private PersonalController controlador;
	private JTable personalTable;
	public ListarEmpleados(PersonalController c) {
		this.controlador = c;
		initGUI();
	}
	
	
	
	private void initGUI() {
		PersonalTableModel tableModel = new PersonalTableModel(controlador);
		personalTable = new JTable(tableModel);
	}
	
	public JPanel getDefaultLayout() {
		JPanel listarEmpleadosPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		personalTable.setPreferredScrollableViewportSize(new Dimension(600,700));
		
		JScrollPane tablaSP = new JScrollPane(personalTable);
		tablaSP.getViewport().setBackground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 0;
		listarEmpleadosPanel.add(tablaSP, c);
		
		return listarEmpleadosPanel;
	}
}
