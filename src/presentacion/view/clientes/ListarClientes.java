package presentacion.view.clientes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import integracion.transfers.TCliente;
import presentacion.controllers.ClienteController;
import presentacion.view.tablas.ClienteTableModel;

public class ListarClientes {
	
	private ClienteController controlador;
	
	private JTable clientesTable;
	private ClienteTableModel tableModel;
	
	public ListarClientes(ClienteController c) {
		this.controlador = c;
		initGUI();
	}
		
	private void initGUI() {
		tableModel = new ClienteTableModel(controlador);
		clientesTable = new JTable(tableModel);
	}
	
	public JPanel getDefaultLayout() {
		JPanel listarClientesPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		clientesTable.setPreferredScrollableViewportSize(new Dimension(600,700));
		
		JScrollPane tablaSP = new JScrollPane(clientesTable);
		tablaSP.getViewport().setBackground(Color.WHITE);
		tablaSP.setMinimumSize(new Dimension(600,700));
		
		c.gridx = 0;
		c.gridy = 0;
		listarClientesPanel.add(tablaSP, c);
		
		listarClientesPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
	        public void componentShown(java.awt.event.ComponentEvent evt) {
	        	listar();
	        }
	    });
		
		return listarClientesPanel;
	}
	
	private void listar() {
		 List<TCliente> listaClientes = controlador.listarClientes();
         tableModel.setClientes(listaClientes);
	}
}
