package presentacion.view.productos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import integracion.transfers.TProducto;
import presentacion.controllers.ProductoController;
import presentacion.view.tablas.ProductoTableModel;

public class ListarPorMarca {
	private ProductoController controlador;
	
	private JTable productosTable;
	private ProductoTableModel tableModel;
	
	private JComboBox<String> marcasBox;
	private List<String> marcas;
	
	public ListarPorMarca(ProductoController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
		tableModel = new ProductoTableModel();
		productosTable = new JTable(tableModel);
		
		marcasBox = new JComboBox<String>();
		setNombreMarcas();
		marcasBox.setEditable(false); 
		marcasBox.setPreferredSize(new Dimension(550, 30));
		marcasBox.setFont(new Font(marcasBox.getFont().toString(), Font.PLAIN, 20));
		
		marcasBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               listarPorMarca(marcasBox.getSelectedItem().toString());
            }
        });
	}
	
	public JPanel getDefaultLayout() {
		JPanel listarProductosPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		productosTable.setPreferredScrollableViewportSize(new Dimension(600,700));
		
		JScrollPane tablaSP = new JScrollPane(productosTable);
		tablaSP.getViewport().setBackground(Color.WHITE);
		tablaSP.setPreferredSize(new Dimension(600,650));
		
		c.gridx = 0;
		c.gridy = 0;
		listarProductosPanel.add(marcasBox, c);
		c.gridx = 0;
		c.gridy = 2;
		listarProductosPanel.add(tablaSP, c);
		
		return listarProductosPanel;
	}
	
	private void listarPorMarca(String nombreMarca) {
		List<TProducto> listaProductos = new ArrayList<>();;
		try {
			listaProductos = controlador.listarProductosPorMarca(nombreMarca);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
		}
		
    	tableModel.setProductos(listaProductos);
	}
	
	private void setNombreMarcas() {
		marcas = controlador.getNombreMarcas();
		
		marcasBox.setModel(new DefaultComboBoxModel<String>(marcas.toArray(new String[0])));
		
		if (marcas.size() > 0) marcasBox.setSelectedIndex(0); 
	}
}
