package presentacion.view.personal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import integracion.transfers.TCliente;
import integracion.transfers.TPersonal;
import presentacion.controllers.PersonalController;
import presentacion.view.tablas.ClienteTableModel;
import presentacion.view.tablas.PersonalTableModel;


public class ListarEmpleados {
	private PersonalController controlador;
	
	private JTable personalTable;
	private PersonalTableModel tableModel;
	
	public ListarEmpleados(PersonalController c) {
		this.controlador = c;
		initGUI();
	}
	
	
	private void initGUI() {
		tableModel = new PersonalTableModel();
		personalTable = new JTable(tableModel);
	}
	
	public JPanel getDefaultLayout() {
		JPanel listarEmpleadosPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		personalTable.setPreferredScrollableViewportSize(new Dimension(600,700));
		
		JScrollPane tablaSP = new JScrollPane(personalTable);
		tablaSP.getViewport().setBackground(Color.WHITE);
		tablaSP.setMinimumSize(new Dimension(600,700));
		
		c.gridx = 0;
		c.gridy = 0;
		listarEmpleadosPanel.add(tablaSP, c);

		listarEmpleadosPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
	        public void componentShown(java.awt.event.ComponentEvent evt) {
	            listar();
	        }
	    });
		
		return listarEmpleadosPanel;
	}
	
	private void listar() {
		List<TPersonal> listaPersonal = controlador.listarPersonal();
        tableModel.setPersonal(listaPersonal);
	}
}
