package presentacion.view.ventas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import integracion.transfers.TVenta;
import presentacion.controllers.VentaController;
import presentacion.view.tablas.VentaTableModel;

public class ListarVenta {

	private VentaController controlador;
	
	private JTable ventasTable;
	private VentaTableModel tableModel;
	
	public ListarVenta(VentaController c) {
		this.controlador = c;
		initGUI();
	}
		
	private void initGUI() {
		tableModel = new VentaTableModel();
		ventasTable = new JTable(tableModel);
	}
	
	public JPanel getDefaultLayout() {
		JPanel listarProductosPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		ventasTable.setPreferredScrollableViewportSize(new Dimension(600,700));
		
		JScrollPane tablaSP = new JScrollPane(ventasTable);
		tablaSP.getViewport().setBackground(Color.WHITE);
		tablaSP.setMinimumSize(new Dimension(600,700));
		
		c.gridx = 0;
		c.gridy = 0;
		listarProductosPanel.add(tablaSP, c);
		
		listarProductosPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
	        public void componentShown(java.awt.event.ComponentEvent evt) {
	        	listar();
	        }
	    });
		
		return listarProductosPanel;
	}
	
	private void listar() {
		List<TVenta> listaVentas = controlador.listarVentas();
    	tableModel.setVentas(listaVentas);
	}
}
