package presentacion.view.marcas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import integracion.transfers.TMarca;
import presentacion.controllers.MarcaController;
import presentacion.view.tablas.MarcaTableModel;


public class ListarMarcas {
	private MarcaController controlador;
	
	private JTable marcasTable;
	private MarcaTableModel tableModel;
	
	public ListarMarcas(MarcaController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
		tableModel = new MarcaTableModel(controlador);
		marcasTable = new JTable(tableModel);
	}
	
	public JPanel getDefaultLayout() {
		JPanel listarMarcasPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		marcasTable.setPreferredScrollableViewportSize(new Dimension(600,700));
		
		JScrollPane tablaSP = new JScrollPane(marcasTable);
		tablaSP.getViewport().setBackground(Color.WHITE);
		tablaSP.setMinimumSize(new Dimension(600,700));
		
		c.gridx = 0;
		c.gridy = 0;
		listarMarcasPanel.add(tablaSP, c);
		
		listarMarcasPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
	        public void componentShown(java.awt.event.ComponentEvent evt) {
	            List<TMarca> listaMarcas = controlador.listarMarcas();
	            tableModel.setMarcas(listaMarcas);
	        }
	    });
		
		return listarMarcasPanel;
	}
}
