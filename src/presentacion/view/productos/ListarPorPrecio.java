package presentacion.view.productos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import integracion.transfers.TProducto;
import presentacion.controllers.ProductoController;
import presentacion.view.SwingFactory;
import presentacion.view.tablas.ProductoTableModel;

public class ListarPorPrecio {
private ProductoController controlador;
	
	private JTable productosTable;
	private ProductoTableModel tableModel;
	
	private JTextField superiorTF;
	private JTextField inferiorTF;
	private JButton confirmar;
	
	
	public ListarPorPrecio(ProductoController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
		tableModel = new ProductoTableModel();
		productosTable = new JTable(tableModel);
		productosTable.setPreferredSize(new Dimension(100,100));
		
		superiorTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		inferiorTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		
		superiorTF.setText("0.0");
		inferiorTF.setText("0.0");
		
		confirmar = SwingFactory.getJButton(new Dimension(250,60), "CONFIRMAR ALTA DE PRODUCTO", 
				"icons/confirmar", 50, Color.GREEN, new Color(130,200,21));
		
		confirmar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { listarPorPrecio(); }
	    });
	}
	
	public JPanel getDefaultLayout() {
		JPanel listarProductosPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		productosTable.setPreferredScrollableViewportSize(new Dimension(100,100));
		
		JScrollPane tablaSP = new JScrollPane(productosTable);
		tablaSP.getViewport().setBackground(Color.WHITE);
		tablaSP.setPreferredSize(new Dimension(600,650));

		c.gridx = 0;
		c.gridy = 0;
		listarProductosPanel.add(SwingFactory.getJLabel(new Dimension(300,45), "SUPERIOR A:" ,25), c);
		c.gridx = 1;
		c.gridy = 0;
		listarProductosPanel.add(superiorTF, c);
		c.gridx = 0;
		c.gridy = 1;
		listarProductosPanel.add(SwingFactory.getJLabel(new Dimension(300,45), "INFERIOR A:" ,25), c);
		c.gridx = 1;
		c.gridy = 1;
		listarProductosPanel.add(inferiorTF, c);
		c.gridx = 0;
		c.gridy = 2;
		listarProductosPanel.add(confirmar, c);
		c.gridx = 0;
		c.gridy = 3;
		listarProductosPanel.add(tablaSP, c);
		
		
		return listarProductosPanel;
	}
	
	private void listarPorPrecio() {
		try {
			double precioSuperior = Double.parseDouble(superiorTF.getText());
			double precioInferior = Double.parseDouble(inferiorTF.getText());
			
			List<TProducto> listaProductos = controlador.listarProductosPorPrecio(precioSuperior,precioInferior);
			
			tableModel.setProductos(listaProductos);
		}
		catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,"Los campos 'precio superior' y 'precio inferior' deben ser un numero", 
										  "ERROR",JOptionPane.ERROR_MESSAGE);
			superiorTF.setText("");
			inferiorTF.setText("");
		}
	}
	
}

