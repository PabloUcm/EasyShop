package presentacion.view.ventas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import integracion.transfers.TProducto;
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
		
		buttonSelect = new JButton(">>");
		buttonSelect.addActionListener(this);
		buttonUnselect = new JButton("<<");
		buttonUnselect.addActionListener(this);
		
		for(int i = 0; i < productos.size(); i++) productsModel.addElement(productos.get(i).getNombre());
		productsList = new JList<String>(productsModel);
		productsList.setVisibleRowCount(10);
		productsList.setFixedCellHeight(20);
		productsList.setFixedCellWidth(140);
		productsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);	

		selectedProductsList = new JList<String>(selectedProductsModel);
		selectedProductsList.setVisibleRowCount(10);
		selectedProductsList.setFixedCellHeight(20);
		selectedProductsList.setFixedCellWidth(140);
		selectedProductsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		confirmar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e ) {  }
		});
		
		limpiar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e ) { limpiar(); }
		});	
	}
	
	public JPanel getDefaultLayout() {
		listar();
		JPanel abrirVentaPanel= new JPanel(new BorderLayout());
		
		JScrollPane list1 = new JScrollPane(productsList);
		JScrollPane list2 = new JScrollPane(selectedProductsList); 
		
		JPanel campos = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "                DNI CLIENTE:" ,30), c);
		c.gridx = 1;
		c.gridy = 0;
		campos.add(clienteTF, c);
		c.gridx = 0;
		c.gridy = 1;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "                DNI EMPLEADO:" ,30), c);
		c.gridx = 1;
		c.gridy = 1;
		campos.add(personalTF, c);
		c.gridx = 0;
		c.gridy = 2;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "               PRODUCTOS:" ,30), c);
		c.gridx = 0;
		c.gridy = 3;
		campos.add(buttonSelect, c);
		c.gridx = 1;
		c.gridy = 3;
		campos.add(buttonUnselect, c);
		c.gridx = 0;
		c.gridy = 4;
		campos.add(list1, c);
		c.gridx = 1;
		c.gridy = 4;
		campos.add(list2, c);
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout( FlowLayout.CENTER ));
		botones.setBorder(BorderFactory.createMatteBorder(
                  1, 0, 0, 0, Color.black));
		
		botones.add(confirmar);
		botones.add(limpiar);
		
		abrirVentaPanel.add(campos,BorderLayout.CENTER);
		abrirVentaPanel.add(botones, BorderLayout.SOUTH);
		
		return abrirVentaPanel;
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		int i = 0;
		if(e.getSource() == buttonSelect)
		{
			int[] fromindex = productsList.getSelectedIndices();
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
		
		buttonSelect = new JButton(">>");
		buttonSelect.addActionListener(this);
		buttonUnselect = new JButton("<<");
		buttonUnselect.addActionListener(this);
		
		for(int i = 0; i < productos.size(); i++) productsModel.addElement(productos.get(i).getNombre());
		productsList = new JList<String>(productsModel);
		productsList.setVisibleRowCount(10);
		productsList.setFixedCellHeight(20);
		productsList.setFixedCellWidth(140);
		productsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);	

		selectedProductsList = new JList<String>(selectedProductsModel);
		selectedProductsList.setVisibleRowCount(10);
		selectedProductsList.setFixedCellHeight(20);
		selectedProductsList.setFixedCellWidth(140);
		selectedProductsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}
	
	private void limpiar() {
		clienteTF.setText("");
		personalTF.setText("");
	}
}
