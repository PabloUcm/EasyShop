package presentacion.view.clientes;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentacion.controllers.ClienteController;
import presentacion.view.tablas.ClienteTableModel;

public class ListarClientes extends JPanel {
	private ClienteController controlador;
	
	public ListarClientes(ClienteController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
		ClienteTableModel tableModel = new ClienteTableModel(controlador);
		JTable clientesTable = new JTable(tableModel);
		
		clientesTable.setPreferredScrollableViewportSize(new Dimension(600,700));
		
		JScrollPane scrollPane = new JScrollPane(clientesTable);
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		add(scrollPane);
	}
}
