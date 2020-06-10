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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import integracion.transfers.TPc;
import integracion.transfers.TPeriferico;
import integracion.transfers.TProducto;
import presentacion.controllers.ProductoController;
import presentacion.view.SwingFactory;

public class ModificarProducto {
private ProductoController controlador;
	
	private JTextField idTF;
	private JTextField upcTF;
	private JTextField nombreTF;
	private JComboBox<String> marcasBox;
	private List<String> marcas;
	private JTextField precioTF;
	private JTextField cantidadTF;
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
	private ImageIcon modIcon;
	
	public ModificarProducto(ProductoController controlador) {
		this.controlador = controlador;
		initGUI();
	}
	
	private void initGUI() {
		
		idTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		upcTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		nombreTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		precioTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		cantidadTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		procesadorTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		discoDuroTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		ramTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		tarjetaGraficaTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		placaBaseTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		conexionTF = SwingFactory.getJTextField(new Dimension(550,25), 20);
		
		marcasBox = new JComboBox<String>();
		setNombreMarcas();
		marcasBox.setEditable(false); 
		marcasBox.setPreferredSize(new Dimension(550, 30));
		marcasBox.setFont(new Font(marcasBox.getFont().toString(), Font.PLAIN, 20));
		
		productosBox = new JComboBox<String>(productos);
		productosBox.setSelectedIndex(0); 
		productosBox.setEditable(false);
		productosBox.setPreferredSize(new Dimension(550, 30));
		productosBox.setFont(new Font(productosBox.getFont().toString(), Font.PLAIN, 20));
		
		tPerifericoBox = new JComboBox<String>(categorias);
		tPerifericoBox.setSelectedIndex(0); 
		tPerifericoBox.setEditable(false);
		tPerifericoBox.setPreferredSize(new Dimension(550, 30));
		tPerifericoBox.setFont(new Font(productosBox.getFont().toString(), Font.PLAIN, 20));
		
		
		descripcion = SwingFactory.getJTextArea(20, true);
		descripcionSP = new JScrollPane(descripcion);
		descripcionSP.setPreferredSize(new Dimension(550,100));
		descripcionSP.setMaximumSize(descripcionSP.getPreferredSize());
		descripcionSP.setMinimumSize(new Dimension(550,150));
		
		confirmar = SwingFactory.getJButton(new Dimension(230,60), "MODIFICAR PRODUCTO", 
				"icons/modificar", 50, new Color(250,243,58), new Color(230,215,73));
		limpiar = SwingFactory.getJButton(new Dimension(230,60), "LIMPIAR CAMPOS DE TEXTO", 
			  "icons/limpiar", 50, new Color(205,205,205), new Color(166,166,166));
		
		confirmar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {modificar(); }
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { limpiar(); }
	    });	
		
		modIcon = SwingFactory.getScaledIcon("icons/modificar", 45);
	}
	
	public JPanel getDefaultLayout() {
		JPanel modProductoPanel = new JPanel(new BorderLayout());
		
		JPanel campos = new JPanel(new GridBagLayout());
		GridBagConstraints constraintsCampos = new GridBagConstraints();
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 0;
		campos.add(SwingFactory.getJLabel(new Dimension(300,45), "ID:" ,25), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 0;
		campos.add(idTF, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 2;
		campos.add(SwingFactory.getJLabel(new Dimension(300,45), "PRODUCTO:" ,25), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 2;
		campos.add(productosBox, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 3;
		campos.add(SwingFactory.getJLabel(new Dimension(300,45), "MARCA:" ,25), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 3;
		campos.add(marcasBox, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 4;
		campos.add(SwingFactory.getJLabel(new Dimension(300,45), "UPC:" ,25), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 4;
		campos.add(upcTF, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 5;
		campos.add(SwingFactory.getJLabel(new Dimension(300,45), "NOMBRE:" ,25), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 5;
		campos.add(nombreTF, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 6;
		campos.add(SwingFactory.getJLabel(new Dimension(300,45), "PRECIO:" ,25), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 6;
		campos.add(precioTF, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 7;
		campos.add(SwingFactory.getJLabel(new Dimension(300,45), "CANTIDAD:" ,25), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 7;
		campos.add(cantidadTF, constraintsCampos);
		
		CardLayout clProducto = new CardLayout();
		JPanel camposProducto = new JPanel(clProducto); 
		
		JPanel camposPC = new JPanel(new GridBagLayout());
		GridBagConstraints constraintsPC = new GridBagConstraints();
		
		constraintsPC.gridx= 0;
		constraintsPC.gridy = 0;
		camposPC.add(SwingFactory.getJLabel(new Dimension(300,45), "PROCESADOR:" ,25), constraintsPC);
		constraintsPC.gridx= 1;
		constraintsPC.gridy = 0;
		camposPC.add(procesadorTF, constraintsPC);
		constraintsPC.gridx= 0;
		constraintsPC.gridy = 1;
		camposPC.add(SwingFactory.getJLabel(new Dimension(300,45), "RAM:" ,25), constraintsPC);
		constraintsPC.gridx= 1;
		constraintsPC.gridy = 1;
		camposPC.add(ramTF, constraintsPC);
		constraintsPC.gridx= 0;
		constraintsPC.gridy = 2;
		camposPC.add(SwingFactory.getJLabel(new Dimension(300,45), "DISCO DURO:" ,25), constraintsPC);
		constraintsPC.gridx= 1;
		constraintsPC.gridy = 2;
		camposPC.add(discoDuroTF, constraintsPC);
		constraintsPC.gridx= 0;
		constraintsPC.gridy = 3;
		camposPC.add(SwingFactory.getJLabel(new Dimension(300,45), "TARJETA GRAFICA:" ,25), constraintsPC);
		constraintsPC.gridx= 1;
		constraintsPC.gridy = 3;
		camposPC.add(tarjetaGraficaTF, constraintsPC);
		constraintsPC.gridx= 0;
		constraintsPC.gridy = 4;
		camposPC.add(SwingFactory.getJLabel(new Dimension(300,45), "PLACA BASE:" ,25), constraintsPC);
		constraintsPC.gridx= 1;
		constraintsPC.gridy = 4;
		camposPC.add(placaBaseTF, constraintsPC);
		
		JPanel camposPeriferico = new JPanel(new GridBagLayout());
		GridBagConstraints constraintsPrfco = new GridBagConstraints();
		
		constraintsPrfco.gridx= 0;
		constraintsPrfco.gridy = 0;
		camposPeriferico.add(SwingFactory.getJLabel(new Dimension(300,45), "CATEGORIA:" ,25), constraintsPrfco);
		constraintsPrfco.gridx= 1;
		constraintsPrfco.gridy = 0;
		camposPeriferico.add(tPerifericoBox, constraintsPrfco);
		constraintsPrfco.gridx= 0;
		constraintsPrfco.gridy = 1;
		camposPeriferico.add(SwingFactory.getJLabel(new Dimension(300,45), "CONEXION:" ,25), constraintsPrfco);
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
		constraintsCampos.gridy = 8;
		constraintsCampos.gridheight = 1;
		constraintsCampos.gridwidth = 2;
		campos.add(camposProducto, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 9;
		constraintsCampos.gridheight = 1;
		constraintsCampos.gridwidth = 2;
		campos.add(SwingFactory.getJLabel(new Dimension(265,50), "   DESCRIPCION:" ,25), constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 10;
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
	private void setNombreMarcas() {
		marcas = controlador.getNombreMarcas();
		
		if (marcas.size() == 0) {
			String[] nombreMarcas = {"[Vacio]"};
			marcasBox.setModel(new DefaultComboBoxModel<String>(nombreMarcas));
		}
		else marcasBox.setModel(new DefaultComboBoxModel<String>(marcas.toArray(new String[0])));
	}
	
	
	private void modificar() {
		TPc pc = null;
		TPeriferico periferico = null;
		
		try {
			if (idTF.getText().trim().equals("") || upcTF.getText().trim().equals("") ||
				nombreTF.getText().trim().equals("") || precioTF.getText().trim().equals("") ||
				cantidadTF.getText().trim().equals("")) 
			{
				throw new Exception("Campo(s) sin rellenar.");
			}
			
			String desc;
			if (descripcion.getText().trim().equals("")) desc = null;
			else desc = descripcion.getText();
			
			if (productosBox.getSelectedItem().equals("PC")) {
				pc = (TPc) controlador.getProductoById(Integer.parseInt(idTF.getText()), "PC");
			}
			else if (productosBox.getSelectedItem().equals("Periferico")) {
				periferico = (TPeriferico) controlador.getProductoById(Integer.parseInt(idTF.getText()),"PERIFERICO");
			}
			
    		
			String msg = "¿Quieres cambiar los datos de este producto?";
			
			int input = JOptionPane.showConfirmDialog(null, msg,"Confirmar cambios en el producto", 
    		    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, modIcon);	
			
			if(input == JOptionPane.YES_OPTION) {
				if(pc != null) {
					if (procesadorTF.getText().trim().equals("") || ramTF.getText().trim().equals("") ||
						discoDuroTF.getText().trim().equals("") || placaBaseTF.getText().trim().equals(""))
					{
						throw new Exception("Campo(s) sin rellenar.");
					}
					
					pc.setUPC(upcTF.getText());
					pc.setNombre(nombreTF.getText());	
					pc.setPrecio(Double.parseDouble(precioTF.getText()));
					pc.setCantidad(Integer.parseInt(cantidadTF.getText()));
					pc.setProcesador(procesadorTF.getText());
					pc.setRam(ramTF.getText());
					pc.setDiscoduro(discoDuroTF.getText());
					pc.setPlacabase(placaBaseTF.getText());
					pc.setDescripcion(desc);
					
					controlador.modificarProducto(pc);
					
					JOptionPane.showMessageDialog(null,"Producto con ID " + idTF.getText() + " modificado con exito.",
							  "INFO",JOptionPane.INFORMATION_MESSAGE);
					limpiar();
					
				}else {
					if (conexionTF.getText().trim().equals("")) throw new Exception("Campo sin rellenar.");
					
					periferico.setUPC(upcTF.getText());
					periferico.setNombre(nombreTF.getText());
					periferico.setPrecio(Double.parseDouble(precioTF.getText()));
					periferico.setCantidad(Integer.parseInt(cantidadTF.getText()));
					periferico.setTipoPeriferico(tPerifericoBox.getSelectedItem().toString());
					periferico.setConexion(conexionTF.getText());
					periferico.setDescripcion(desc);
					
					controlador.modificarProducto(periferico);
					
					JOptionPane.showMessageDialog(null,"Producto con ID " + idTF.getText() + " modificado con exito.",
							  "INFO",JOptionPane.INFORMATION_MESSAGE);
					limpiar();
				}
			}
	
		} 
		catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,"Los campos 'ID', 'precio' y 'cantidad' deben ser un numero.", 
										  "ERROR",JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limpiar() {
		idTF.setText("");
		upcTF.setText("");
		nombreTF.setText("");
		precioTF.setText("");
		cantidadTF.setText("");
		procesadorTF.setText("");
		discoDuroTF.setText("");
		ramTF.setText("");
		tarjetaGraficaTF.setText("");
		placaBaseTF.setText("");
		conexionTF.setText("");
	}
}
