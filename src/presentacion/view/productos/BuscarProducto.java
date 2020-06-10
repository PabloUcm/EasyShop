package presentacion.view.productos;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.FlowLayout;


import integracion.transfers.TProducto;
import presentacion.controllers.ProductoController;
import presentacion.view.SwingFactory;
import presentacion.view.tablas.ProductoTableModel;

public class BuscarProducto {
	private ProductoController controlador;
	
	private JTable productosTable;
	private ProductoTableModel tableModel;

	private JTextField superiorTF;
	private JTextField inferiorTF;
	
	JRadioButton buscarPorPrecio,buscarPorMarca;
	ButtonGroup btnGroup;
	
	private JButton confirmar;
	
	private JComboBox<String> marcasBox;
	private List<String> marcas;
	
	public BuscarProducto(ProductoController c) {
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
		
		buscarPorPrecio = new JRadioButton("Por Precio");
		buscarPorMarca = new JRadioButton("Por Marca");
		
		superiorTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		inferiorTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		
		superiorTF.setText("0.0");
		inferiorTF.setText("0.0");
		
		btnGroup = new ButtonGroup();
		btnGroup.add(buscarPorPrecio);
		btnGroup.add(buscarPorMarca);
		
		confirmar = SwingFactory.getJButton(new Dimension(250,60), "CONFIRMAR ALTA DE PRODUCTO", 
				"icons/confirmar", 50, Color.GREEN, new Color(130,200,21));
		
		confirmar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { listarPorPrecio(); }
	    });
		
		marcasBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               listarPorMarca(marcasBox.getSelectedItem().toString());
            }
        });
	}
	
	public JPanel getDefaultLayout() {
		JPanel buscarProductosPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel panelRadioButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelRadioButtons.add(buscarPorPrecio);
		panelRadioButtons.add(buscarPorMarca);
		
		c.gridx = 0;
		c.gridy = 0;
		buscarProductosPanel.add(SwingFactory.getJLabel(new Dimension(200,45), "BUSCAR POR:" ,25), c);
		c.gridx = 1;
		c.gridy = 0;
		buscarProductosPanel.add(panelRadioButtons,c);
		
		
		CardLayout card = new CardLayout();
		JPanel busquedas = new JPanel(card);
		
		busquedas.add(getMarcaLayout(),"marca");
		busquedas.add(getPrecioLayout(),"precio");
		
		buscarPorPrecio.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	if(buscarPorPrecio.isSelected()) card.show(busquedas,"precio");
	        }
	    });
		
		buscarPorMarca.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	if(buscarPorMarca.isSelected()) card.show(busquedas, "marca");
	        }
	    });
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		buscarProductosPanel.add(busquedas,c);
		
		return buscarProductosPanel;
	}
	
	public JPanel getMarcaLayout() {
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
	
	public JPanel getPrecioLayout() {
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
	
	private void listarPorMarca(String nombreMarca) {
		List<TProducto> listaProductos = new ArrayList<>();;
		try {
			listaProductos = controlador.listarProductosPorMarca(nombreMarca);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
		}
		
    	tableModel.setProductos(listaProductos);
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
	
	private void setNombreMarcas() {
		marcas = controlador.getNombreMarcas();
		
		marcasBox.setModel(new DefaultComboBoxModel<String>(marcas.toArray(new String[0])));
		
		if (marcas.size() > 0) marcasBox.setSelectedIndex(0); 
	}
}
