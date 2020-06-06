package presentacion.view.productos;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import presentacion.controllers.ProductoController;
import presentacion.view.SwingFactory;

public class ModificarProducto {
private ProductoController controlador;
	
	private JTextField idTF;
	private JTextField nombreTF;
	private JComboBox<String> marcasBox;
	private List<String> marcas;
	private JTextField precioTF;
	private JTextArea descripcion;
	private JScrollPane descripcionSP;
	
	private JComboBox<String> productosBox;
	private String productos[] = {"PC","Periferico"};
	
	//PC
	private JTextField procesadorTF;
	private JTextField ramTF;
	private JTextField discoDuroTF;
	private JTextField tarjetaGraficaTF;
	private JTextField placaBaseTF;
	
	//Perifericos
	private JComboBox<String> tPerifericoBox;
	private String categorias[] = {"Entrada", "Salida", "Entrada/Salida"};
	private JTextField conexionTF;
		
	private JButton confirmar;
	private JButton limpiar;
	
	public ModificarProducto(ProductoController controlador) {
		this.controlador = controlador;
		initGUI();
	}
	
	private void initGUI() {
		
		idTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		nombreTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		precioTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		procesadorTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		discoDuroTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		ramTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		tarjetaGraficaTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		placaBaseTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		conexionTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		
		marcasBox = new JComboBox<String>();
		setNombreMarcas();
		marcasBox.setSelectedIndex(0); 
		marcasBox.setEditable(false); 
		marcasBox.setPreferredSize(new Dimension(550, 35));
		marcasBox.setFont(new Font(marcasBox.getFont().toString(), Font.PLAIN, 25));
		
		productosBox = new JComboBox<String>(productos);
		productosBox.setSelectedIndex(0); 
		productosBox.setEditable(false);
		productosBox.setPreferredSize(new Dimension(550, 35));
		productosBox.setFont(new Font(productosBox.getFont().toString(), Font.PLAIN, 25));
		
		tPerifericoBox = new JComboBox<String>(categorias);
		tPerifericoBox.setSelectedIndex(0); 
		tPerifericoBox.setEditable(false);
		tPerifericoBox.setPreferredSize(new Dimension(550, 35));
		tPerifericoBox.setFont(new Font(productosBox.getFont().toString(), Font.PLAIN, 25));
		
		descripcion = SwingFactory.getJTextArea(25, true);
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
	
	public JPanel getDefaultLayout() {
		JPanel modProductoPanel = new JPanel(new BorderLayout());
		
		JPanel campos = new JPanel(new GridBagLayout());
		GridBagConstraints constraintsCampos = new GridBagConstraints();
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 0;
		campos.add(SwingFactory.getJLabel(new Dimension(300,50), "ID:" ,30), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 0;
		campos.add(idTF, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 2;
		campos.add(SwingFactory.getJLabel(new Dimension(300,50), "PRODUCTO:" ,30), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 2;
		campos.add(productosBox, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 3;
		campos.add(SwingFactory.getJLabel(new Dimension(300,50), "MARCA:" ,30), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 3;
		campos.add(marcasBox, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 4;
		campos.add(SwingFactory.getJLabel(new Dimension(300,50), "NOMBRE:" ,30), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 4;
		campos.add(nombreTF, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 5;
		campos.add(SwingFactory.getJLabel(new Dimension(300,50), "PRECIO:" ,30), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 5;
		campos.add(precioTF, constraintsCampos);
		
		CardLayout clProducto = new CardLayout();
		JPanel camposProducto = new JPanel(clProducto); 
		
		JPanel camposPC = new JPanel(new GridBagLayout());
		GridBagConstraints constraintsPC = new GridBagConstraints();
		
		constraintsPC.gridx= 0;
		constraintsPC.gridy = 0;
		camposPC.add(SwingFactory.getJLabel(new Dimension(300,50), "PROCESADOR:" ,30), constraintsPC);
		constraintsPC.gridx= 1;
		constraintsPC.gridy = 0;
		camposPC.add(procesadorTF, constraintsPC);
		constraintsPC.gridx= 0;
		constraintsPC.gridy = 1;
		camposPC.add(SwingFactory.getJLabel(new Dimension(300,50), "RAM:" ,30), constraintsPC);
		constraintsPC.gridx= 1;
		constraintsPC.gridy = 1;
		camposPC.add(ramTF, constraintsPC);
		constraintsPC.gridx= 0;
		constraintsPC.gridy = 2;
		camposPC.add(SwingFactory.getJLabel(new Dimension(300,50), "DISCO DURO:" ,30), constraintsPC);
		constraintsPC.gridx= 1;
		constraintsPC.gridy = 2;
		camposPC.add(discoDuroTF, constraintsPC);
		constraintsPC.gridx= 0;
		constraintsPC.gridy = 3;
		camposPC.add(SwingFactory.getJLabel(new Dimension(300,50), "TARJETA GRAFICA:" ,30), constraintsPC);
		constraintsPC.gridx= 1;
		constraintsPC.gridy = 3;
		camposPC.add(tarjetaGraficaTF, constraintsPC);
		constraintsPC.gridx= 0;
		constraintsPC.gridy = 4;
		camposPC.add(SwingFactory.getJLabel(new Dimension(300,50), "PLACA BASE:" ,30), constraintsPC);
		constraintsPC.gridx= 1;
		constraintsPC.gridy = 4;
		camposPC.add(placaBaseTF, constraintsPC);
		
		JPanel camposPeriferico = new JPanel(new GridBagLayout());
		GridBagConstraints constraintsPrfco = new GridBagConstraints();
		
		constraintsPrfco.gridx= 0;
		constraintsPrfco.gridy = 0;
		camposPeriferico.add(SwingFactory.getJLabel(new Dimension(300,50), "CATEGORIA:" ,30), constraintsPrfco);
		constraintsPrfco.gridx= 1;
		constraintsPrfco.gridy = 0;
		camposPeriferico.add(tPerifericoBox, constraintsPrfco);
		constraintsPrfco.gridx= 0;
		constraintsPrfco.gridy = 1;
		camposPeriferico.add(SwingFactory.getJLabel(new Dimension(300,50), "CONEXION:" ,30), constraintsPrfco);
		constraintsPrfco.gridx= 1;
		constraintsPrfco.gridy = 1;
		camposPeriferico.add(conexionTF, constraintsPrfco);
		
		camposProducto.add(camposPC, productos[0]);
		camposProducto.add(camposPeriferico, productos[1]);
		
		productosBox.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                clProducto.show(camposProducto, productosBox.getSelectedItem().toString());
	            }
	        });
		
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 6;
		constraintsCampos.gridheight = 1;
		constraintsCampos.gridwidth = 2;
		campos.add(camposProducto, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 8;
		constraintsCampos.gridheight = 1;
		constraintsCampos.gridwidth = 2;
		campos.add(SwingFactory.getJLabel(new Dimension(265,50), "   DESCRIPCION:" ,30), constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 9;
		campos.add(descripcionSP, constraintsCampos);
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout( FlowLayout.CENTER ));
		botones.setBorder(BorderFactory.createMatteBorder(
                  1, 0, 0, 0, Color.black));
		
		botones.add(confirmar);
		botones.add(limpiar);
		
		modProductoPanel.add(campos,BorderLayout.CENTER);
		modProductoPanel.add(botones, BorderLayout.SOUTH);
		
		modProductoPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
	        public void componentShown(java.awt.event.ComponentEvent evt) {
	            setNombreMarcas();
	        }
	    });
		
		return modProductoPanel;
	}
	
	private void limpiar() {
		nombreTF.setText("");
		precioTF.setText("");
		procesadorTF.setText("");
		discoDuroTF.setText("");
		ramTF.setText("");
		tarjetaGraficaTF.setText("");
		placaBaseTF.setText("");
		conexionTF.setText("");
	}
	
	private void setNombreMarcas() {
		marcas = controlador.getNombreMarcas();
		
		if (marcas.size() == 0) {
			String[] nombreMarcas = {"[Vacio]"};
			marcasBox.setModel(new DefaultComboBoxModel<String>(nombreMarcas));
		}
		else marcasBox.setModel(new DefaultComboBoxModel<String>(marcas.toArray(new String[0])));
	}
}
