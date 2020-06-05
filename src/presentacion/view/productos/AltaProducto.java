package presentacion.view.productos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import presentacion.controllers.ProductoController;
import presentacion.view.SwingFactory;

public class AltaProducto {
	
	private ProductoController controlador;
	
	private JTextField nombreTF;
	private JTextField precioTF;
	private JTextField procesadorTF;
	private JTextField ramTF;
	private JTextField discoDuroTF;
	private JTextField tarjetaGraficaTF;
	private JTextField placaBaseTF;
	private JComboBox<String> marcasBox;
	private JComboBox<String> productosBox;
	private JTextArea descripcion;
	private JScrollPane descripcionSP;
	private String marcas[] = {"Msi","Asus","Acer","Apple"};
	private String productos[] = {"PC","Perifericos"};
	
	private JButton confirmar;
	private JButton limpiar;
	
	public AltaProducto(ProductoController controlador) {
		this.controlador = controlador;
		initGUI();
	}
	
	private void initGUI() {
		
		nombreTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		precioTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		procesadorTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		discoDuroTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		ramTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		tarjetaGraficaTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		placaBaseTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		
		marcasBox = new JComboBox<String>(marcas);
		marcasBox.setSelectedIndex(0); 
		marcasBox.setEditable(true); 
		
		productosBox = new JComboBox<String>(productos);
		productosBox.setSelectedIndex(0); 
		productosBox.setEditable(true);
		
		descripcion = SwingFactory.getJTextArea(15);
		descripcionSP = new JScrollPane(descripcion);
		descripcionSP.setPreferredSize(new Dimension(550,100));
		descripcionSP.setMaximumSize(descripcionSP.getPreferredSize());
		descripcionSP.setMinimumSize(new Dimension(550,150));
		
		confirmar = SwingFactory.getJButton(new Dimension(230,60), "CONFIRMAR ALTA DE CLIENTE", 
				"icons/confirmar", 50, Color.GREEN, new Color(130,200,21));
		limpiar = SwingFactory.getJButton(new Dimension(230,60), "LIMPIAR CAMPOS DE TEXTO", 
			  "icons/limpiar", 50, new Color(205,205,205), new Color(166,166,166));
		
		confirmar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { }
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { limpiar(); }
	    });	
	}
	
	public JPanel getPCLayout() {
		JPanel altaProductoPanel = new JPanel(new BorderLayout());
		
		JPanel campos = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "               PRODUCTO:" ,30), c);
		c.gridx = 1;
		c.gridy = 0;
		campos.add(productosBox, c);
		c.gridx = 0;
		c.gridy = 2;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "                MARCA:" ,30), c);
		c.gridx = 1;
		c.gridy = 2;
		campos.add(marcasBox, c);
		c.gridx = 0;
		c.gridy = 3;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "                NOMBRE:" ,30), c);
		c.gridx = 1;
		c.gridy = 3;
		campos.add(nombreTF, c);
		c.gridx = 0;
		c.gridy = 4;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "     PRECIO:" ,30), c);
		c.gridx = 1;
		c.gridy = 4;
		campos.add(precioTF, c);
		c.gridx = 0;
		c.gridy = 5;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "  PROCESADOR:" ,30), c);
		c.gridx = 1;
		c.gridy = 5;
		campos.add(procesadorTF, c);
		c.gridx = 0;
		c.gridy = 6;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "  RAM:" ,30), c);
		c.gridx = 1;
		c.gridy = 6;
		campos.add(ramTF, c);
		c.gridx = 0;
		c.gridy = 7;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "  DISCO DURO:" ,30), c);
		c.gridx = 1;
		c.gridy = 7;
		campos.add(discoDuroTF, c);
		c.gridx = 0;
		c.gridy = 8;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "  TARJETA GRAFICA:" ,30), c);
		c.gridx = 1;
		c.gridy = 8;
		campos.add(tarjetaGraficaTF, c);
		c.gridx = 0;
		c.gridy = 9;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), " PLACA BASE:" ,30), c);
		c.gridx = 1;
		c.gridy = 9;
		campos.add(placaBaseTF, c);
		c.gridx = 1;
		c.gridy = 10;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "  DESCRIPCION:" ,30), c);
		c.gridx = 1;
		c.gridy = 11;
		campos.add(descripcionSP, c);
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout( FlowLayout.CENTER ));
		botones.setBorder(BorderFactory.createMatteBorder(
                  1, 0, 0, 0, Color.black));
		
		botones.add(confirmar);
		botones.add(limpiar);
		
		altaProductoPanel.add(campos,BorderLayout.CENTER);
		altaProductoPanel.add(botones, BorderLayout.SOUTH);
		
		return altaProductoPanel;
	}
	
	private void limpiar() {
		precioTF.setText("");
		procesadorTF.setText("");
		ramTF.setText("");
		tarjetaGraficaTF.setText("");
	}
}
