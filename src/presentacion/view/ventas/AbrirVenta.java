package presentacion.view.ventas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import integracion.transfers.TCliente;
import integracion.transfers.TPersonal;
import integracion.transfers.TProducto;
import integracion.transfers.TVenta;
import presentacion.controllers.VentaController;
import presentacion.view.SwingFactory;

public class AbrirVenta implements ActionListener{

	private VentaController controlador;
	
	private JTextField clienteTF;
	private JTextField personalTF;
	private JButton confirmar;
	private JButton limpiar;
	
	private JList<String> productsList, selectedProductsList;
	private DefaultListModel<String> selectedProductsModel, productsModel;
	private JButton buttonSelect, buttonUnselect;
	private List<TProducto> productos;
	
	private HashMap<String,Integer> mapaProductos;
	
	public AbrirVenta(VentaController controlador) {
		this.controlador = controlador;
		productos = new ArrayList<>();
		initGUI();
	}
	
	private void initGUI(){
		clienteTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		personalTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		
		confirmar = SwingFactory.getJButton(new Dimension(230,60), "CERRAR VENTA", 
				"icons/confirmar", 50, Color.GREEN, new Color(130,200,21));
		limpiar = SwingFactory.getJButton(new Dimension(230,60), "LIMPIAR CAMPOS DE TEXTO", 
					  "icons/limpiar", 50, new Color(205,205,205), new Color(166,166,166));
		
		selectedProductsModel = new DefaultListModel<String>();
		productsModel = new DefaultListModel<String>();
		
		buttonSelect = new JButton("Agregar producto");
		buttonSelect.addActionListener(this);
		buttonUnselect = new JButton("Eliminar producto");
		buttonUnselect.addActionListener(this);
		
		for(int i = 0; i < productos.size(); i++) productsModel.addElement(productos.get(i).getNombre());
		productsList = new JList<String>(productsModel);
		productsList.setVisibleRowCount(10);
		productsList.setFixedCellHeight(25);
		productsList.setFixedCellWidth(200);
		productsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		productsList.setFont(new Font(productsList.getFont().toString(), Font.PLAIN, 15));

		selectedProductsList = new JList<String>(selectedProductsModel);
		selectedProductsList.setVisibleRowCount(10);
		selectedProductsList.setFixedCellHeight(25);
		selectedProductsList.setFixedCellWidth(200);
		selectedProductsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		selectedProductsList.setFont(new Font(selectedProductsList.getFont().toString(), Font.PLAIN, 15));
		
		confirmar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e ) { cerrarVenta(); }
		});
		
		limpiar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e ) { limpiar(); }
		});	
	}
	
	public JPanel getDefaultLayout() {
		JPanel abrirVentaPanel= new JPanel(new BorderLayout());
		
		JScrollPane list1 = new JScrollPane(productsList);
		list1.setPreferredSize(new Dimension(200,350));
		JScrollPane list2 = new JScrollPane(selectedProductsList);
		list2.setPreferredSize(new Dimension(200,350));
		
		JPanel campos = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "ID CLIENTE:" ,30), c);
		c.gridx = 1;
		c.gridy = 0;
		campos.add(clienteTF, c);
		c.gridx = 0;
		c.gridy = 1;
		campos.add(SwingFactory.getJLabel(new Dimension(250,50), "ID EMPLEADO:" ,30), c);
		c.gridx = 1;
		c.gridy = 1;
		campos.add(personalTF, c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		campos.add(SwingFactory.getJLabel(new Dimension(220,80), "PRODUCTOS:" ,30), c);
		
		JPanel listas = new JPanel(new GridBagLayout());
		GridBagConstraints contraintsListas = new GridBagConstraints();
		
		contraintsListas.gridx = 0;
		contraintsListas.gridy = 1;
		listas.add(SwingFactory.getJLabel(new Dimension(170,30), " Productos disponibles" ,15), contraintsListas);
		contraintsListas.gridx = 1;
		contraintsListas.gridy = 1;
		listas.add(Box.createRigidArea(new Dimension(150, 0)), contraintsListas);
		contraintsListas.gridx = 2;
		contraintsListas.gridy = 1;
		listas.add(SwingFactory.getJLabel(new Dimension(160,30), "  Productos a vender" ,15), contraintsListas);
		contraintsListas.gridx = 0;
		contraintsListas.gridy = 2;
		listas.add(list1, contraintsListas);
		contraintsListas.gridx = 2;
		contraintsListas.gridy = 2;
		listas.add(list2, contraintsListas);
		contraintsListas.gridx = 0;
		contraintsListas.gridy = 3;
		contraintsListas.gridwidth = 2;
		contraintsListas.gridheight = 1;
		listas.add(Box.createRigidArea(new Dimension(0, 10)), contraintsListas);
		contraintsListas.gridx = 0;
		contraintsListas.gridy = 4;
		contraintsListas.gridwidth = 1;
		contraintsListas.gridheight = 1;
		listas.add(buttonSelect, contraintsListas);
		contraintsListas.gridx = 2;
		contraintsListas.gridy = 4;
		listas.add(buttonUnselect, contraintsListas);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.gridheight = 1;
		campos.add(listas, c);
		
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout( FlowLayout.CENTER ));
		botones.setBorder(BorderFactory.createMatteBorder(
                  1, 0, 0, 0, Color.black));
		
		botones.add(confirmar);
		botones.add(limpiar);
		
		abrirVentaPanel.add(campos,BorderLayout.CENTER);
		abrirVentaPanel.add(botones, BorderLayout.SOUTH);
		
		abrirVentaPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
	        public void componentShown(java.awt.event.ComponentEvent evt) {
	        	listar();
	        }
	    });
		
		return abrirVentaPanel;
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		int i = 0;
		if(e.getSource() == buttonSelect)
		{
			List<String> from = productsList.getSelectedValuesList();
			for (String s : from) selectedProductsModel.addElement(s);
		}else if(e.getSource() == buttonUnselect) {
			 List<String> to = selectedProductsList.getSelectedValuesList();
			 int[] toindex = selectedProductsList.getSelectedIndices();

			 for(i = (toindex.length-1); i >=0; i--)
				 selectedProductsModel.remove(toindex[i]);
			 }
		}
	
	private void listar() {
		productos = controlador.listarProductos();
		
		selectedProductsModel = new DefaultListModel<String>();
		productsModel = new DefaultListModel<String>();
		
		for(int i = 0; i < productos.size(); i++) productsModel.addElement(productos.get(i).getNombre());
		productsList.setModel(productsModel);
		
		selectedProductsList.setModel(selectedProductsModel);
	}
	
	private void cerrarVenta() {
		try {
			if (clienteTF.getText().trim().equals("") || personalTF.getText().trim().equals("")) {
				throw new Exception("Campo sin rellenar.");
			}
			
			if (selectedProductsModel.getSize() == 0) throw new Exception("Lista de productos a vender vacia.");
			
			TCliente cliente = controlador.getCliente(Integer.parseInt(clienteTF.getText()));
			TPersonal personal = controlador.getPersonal(Integer.parseInt(personalTF.getText()));
			
			mapaProductos = new HashMap<>();
			
			List<String> from = new ArrayList<>();
		    for (int i = 0; i < selectedProductsModel.getSize(); i++) {
		             from.add(String.valueOf(selectedProductsModel.getElementAt(i)));
		     }
				
			for (String s : from) {
				if(mapaProductos.containsKey(s)) mapaProductos.replace(s, mapaProductos.get(s) + 1);
				else mapaProductos.put(s, 1);
			}
			
			List<String> list = new ArrayList<>(mapaProductos.keySet());
			for(String producto: list) controlador.comprobarStock(producto, mapaProductos.get(producto));
			
			TVenta venta = new TVenta();
			venta.setIdCliente(Integer.parseInt(clienteTF.getText()));
			venta.setIdPersonal(Integer.parseInt(personalTF.getText()));
			venta.setFecha(new Date());
			
			controlador.cerrarVenta(venta,mapaProductos);
			
			JOptionPane.showMessageDialog(null,"Venta efectuada con exito",
					  "INFO",JOptionPane.INFORMATION_MESSAGE);
			limpiar();
		
		} catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,"Los campos 'ID cliente' e 'ID empleado' deben ser un numero.", 
										  "ERROR",JOptionPane.ERROR_MESSAGE);
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limpiar() {
		clienteTF.setText("");
		personalTF.setText("");
		selectedProductsModel = new DefaultListModel<String>();
		selectedProductsList.setModel(selectedProductsModel);
	}
}
