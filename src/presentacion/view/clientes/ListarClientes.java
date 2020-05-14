package presentacion.view.clientes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentacion.controllers.ClienteController;
import presentacion.view.tablas.ClienteTableModel;

public class ListarClientes{
	
	private ClienteController controlador;
	
	public ListarClientes(ClienteController c) {
		this.controlador = c;
		initGUI();
	}
	
	private JTable clientesTable;
	
	private void initGUI() {
		ClienteTableModel tableModel = new ClienteTableModel(controlador);
		clientesTable = new JTable(tableModel);
	}
	
	public JPanel getDefaultLayout() {
		JPanel listarClientesPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		clientesTable.setPreferredScrollableViewportSize(new Dimension(600,700));
		
		JScrollPane tablaSP = new JScrollPane(clientesTable);
		tablaSP.getViewport().setBackground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 0;
		listarClientesPanel.add(tablaSP, c);
		
		return listarClientesPanel;
	}
}
