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

import javax.swing.Box;
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


import integracion.transfers.TProducto;
import presentacion.controllers.ProductoController;
import presentacion.view.SwingFactory;
import presentacion.view.tablas.ProductoTableModel;

public class BuscarProducto {
	private ProductoController controlador;
	
	private JTable porPrecioTable;
	private ProductoTableModel porPreciotableModel;
	
	private JTable porMarcaTable;
	private ProductoTableModel porMarcatableModel;

	private JTextField superiorTF;
	private JTextField inferiorTF;
	
	JRadioButton buscarPorPrecio,buscarPorMarca;
	ButtonGroup btnGroup;
	
	private JButton buscar;
	
	private JComboBox<String> marcasBox;
	private List<String> marcas;
	
	public BuscarProducto(ProductoController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
		porPreciotableModel = new ProductoTableModel();
		porPrecioTable = new JTable(porPreciotableModel);
		
		porMarcatableModel = new ProductoTableModel();
		porMarcaTable = new JTable(porMarcatableModel);
		
		marcasBox = new JComboBox<String>();
		setNombreMarcas();
		marcasBox.setEditable(false); 
		marcasBox.setPreferredSize(new Dimension(550, 30));
		marcasBox.setFont(new Font(marcasBox.getFont().toString(), Font.PLAIN, 20));
		
		buscarPorPrecio = new JRadioButton("Por Precio");
		buscarPorPrecio.setFont(new Font(buscarPorPrecio.getFont().toString(), Font.PLAIN, 20));
		buscarPorMarca = new JRadioButton("Por Marca");
		buscarPorMarca.setFont(new Font(buscarPorMarca.getFont().toString(), Font.PLAIN, 20));
		
		superiorTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		inferiorTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		
		superiorTF.setText("0.0");
		inferiorTF.setText("0.0");
		
		btnGroup = new ButtonGroup();
		btnGroup.add(buscarPorPrecio);
		btnGroup.add(buscarPorMarca);
		
		buscar = SwingFactory.getJButton(new Dimension(140,30), "BUSCAR", 
				 "icons/lupa", 20, new Color(8,213,249), new Color(6,160,190));
		
		buscar.addActionListener(new ActionListener() {
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
		
		JPanel panelRadioButtons = new JPanel(new GridBagLayout());
		GridBagConstraints constraintsButtons = new GridBagConstraints();
		
		constraintsButtons.gridx = 0;
		constraintsButtons.gridx = 0;
		panelRadioButtons.add(buscarPorPrecio, constraintsButtons);
		constraintsButtons.gridx = 0;
		constraintsButtons.gridx = 1;
		panelRadioButtons.add(buscarPorMarca, constraintsButtons);
		
		c.gridx = 0;
		c.gridy = 0;
		buscarProductosPanel.add(Box.createRigidArea(new Dimension(150, 0)), c);
		c.gridx = 1;
		c.gridy = 0;
		buscarProductosPanel.add(SwingFactory.getJLabel(new Dimension(220,45), "BUSCAR POR:" ,25), c);
		c.gridx = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START; 
		buscarProductosPanel.add(panelRadioButtons,c);
		
		
		CardLayout card = new CardLayout();
		JPanel busquedas = new JPanel(card);
		
		busquedas.add(getMarcaLayout(),"marca");
		busquedas.add(getPrecioLayout(),"precio");
		busquedas.setPreferredSize(new Dimension(700,800));
		
		if (marcasBox.getSelectedItem() != null)listarPorMarca(marcasBox.getSelectedItem().toString());
		
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
		
		buscarPorPrecio.setSelected(true);
		card.show(busquedas,"precio");
		
		c.gridx = 0;
		c.gridy = 1;
		buscarProductosPanel.add(Box.createRigidArea(new Dimension(0, 20)), c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.gridheight= 1;
		c.fill = GridBagConstraints.BOTH;
		buscarProductosPanel.add(busquedas,c);
		
		buscarProductosPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
	        public void componentShown(java.awt.event.ComponentEvent evt) {
	        	setNombreMarcas();
	        }
	    });
		
		return buscarProductosPanel;
	}
	
	public JPanel getMarcaLayout() {
		JPanel listarProductosPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		porMarcaTable.setPreferredScrollableViewportSize(new Dimension(600,600));
		
		JScrollPane tablaSP = new JScrollPane(porMarcaTable);
		tablaSP.getViewport().setBackground(Color.WHITE);
		tablaSP.setPreferredSize(new Dimension(600,600));
		tablaSP.setMinimumSize(new Dimension(600,600));
		
		c.gridx = 0;
		c.gridy = 0;
		listarProductosPanel.add(marcasBox, c);
		c.gridx = 0;
		c.gridy = 1;
		listarProductosPanel.add(Box.createRigidArea(new Dimension(0,15)), c);
		c.gridx = 0;
		c.gridy = 2;
		listarProductosPanel.add(tablaSP, c);
		
		return listarProductosPanel;
	}
	
	public JPanel getPrecioLayout() {
		JPanel listarProductosPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		porPrecioTable.setPreferredScrollableViewportSize(new Dimension(600,500));
		
		JScrollPane tablaSP = new JScrollPane(porPrecioTable);
		tablaSP.getViewport().setBackground(Color.WHITE);
		tablaSP.setPreferredSize(new Dimension(600,500));
		tablaSP.setMinimumSize(new Dimension(600,500));

		c.gridx = 0;
		c.gridy = 0;
		listarProductosPanel.add(SwingFactory.getJLabel(new Dimension(600,45), "SUPERIOR A:" ,25), c);
		c.gridx = 1;
		c.gridy = 0;
		listarProductosPanel.add(superiorTF, c);
		c.gridx = 0;
		c.gridy = 1;
		listarProductosPanel.add(SwingFactory.getJLabel(new Dimension(600,45), "INFERIOR A:" ,25), c);
		c.gridx = 1;
		c.gridy = 1;
		listarProductosPanel.add(inferiorTF, c);
		c.gridx = 0;
		c.gridy = 2;
		listarProductosPanel.add(Box.createRigidArea(new Dimension(0,5)), c);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.gridheight= 1;
		listarProductosPanel.add(buscar, c);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight= 1;
		listarProductosPanel.add(Box.createRigidArea(new Dimension(0,15)), c);
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.gridheight= 1;
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
		
		porMarcatableModel.setProductos(listaProductos);
	}
	
	private void listarPorPrecio() {
		try {
			double precioSuperior = Double.parseDouble(superiorTF.getText());
			double precioInferior = Double.parseDouble(inferiorTF.getText());
			
			List<TProducto> listaProductos = controlador.listarProductosPorPrecio(precioSuperior,precioInferior);
			
			porPreciotableModel.setProductos(listaProductos);
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
