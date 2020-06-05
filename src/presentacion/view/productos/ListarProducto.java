package presentacion.view.productos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import integracion.transfers.TProducto;
import presentacion.controllers.ProductoController;
import presentacion.view.tablas.ProductoTableModel;

public class ListarProducto {

	private ProductoController controlador;
	
	private JTable productosTable;
	private ProductoTableModel tableModel;
	
	public ListarProducto(ProductoController c) {
		this.controlador = c;
		initGUI();
	}
		
	private void initGUI() {
		tableModel = new ProductoTableModel(controlador);
		productosTable = new JTable(tableModel);
	}
	
	public JPanel getDefaultLayout() {
		JPanel listarProductosPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		productosTable.setPreferredScrollableViewportSize(new Dimension(600,700));
		
		JScrollPane tablaSP = new JScrollPane(productosTable);
		tablaSP.getViewport().setBackground(Color.WHITE);
		tablaSP.setMinimumSize(new Dimension(600,700));
		
		c.gridx = 0;
		c.gridy = 0;
		listarProductosPanel.add(tablaSP, c);
		
		listarProductosPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
	        public void componentShown(java.awt.event.ComponentEvent evt) {
	        	List<TProducto> listaProductos = controlador.listarProductos();
	        	tableModel.setProductos(listaProductos);
	        }
	    });
		
		return listarProductosPanel;
	}
}
