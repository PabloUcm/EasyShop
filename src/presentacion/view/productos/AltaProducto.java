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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import integracion.transfers.TCliente;
import integracion.transfers.TPc;
import integracion.transfers.TPeriferico;
import integracion.transfers.TProducto;
import presentacion.controllers.ProductoController;
import presentacion.view.SwingFactory;

public class AltaProducto {
	
	private ProductoController controlador;
	
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
	
	public AltaProducto(ProductoController controlador) {
		this.controlador = controlador;
		initGUI();
	}
	
	private void initGUI() {
		
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
		
		confirmar = SwingFactory.getJButton(new Dimension(250,60), "CONFIRMAR ALTA DE PRODUCTO", 
				"icons/confirmar", 50, Color.GREEN, new Color(130,200,21));
		limpiar = SwingFactory.getJButton(new Dimension(250,60), "LIMPIAR CAMPOS DE TEXTO", 
			  "icons/limpiar", 50, new Color(205,205,205), new Color(166,166,166));
		
		confirmar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { alta(); }
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { limpiar(); }
	    });	
	}
	
	public JPanel getDefaultLayout() {
		JPanel altaProductoPanel = new JPanel(new BorderLayout());
		
		JPanel campos = new JPanel(new GridBagLayout());
		GridBagConstraints constraintsCampos = new GridBagConstraints();
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 0;
		campos.add(SwingFactory.getJLabel(new Dimension(300,45), "PRODUCTO:" ,25), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 0;
		campos.add(productosBox, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 2;
		campos.add(SwingFactory.getJLabel(new Dimension(300,45), "MARCA:" ,25), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 2;
		campos.add(marcasBox, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 3;
		campos.add(SwingFactory.getJLabel(new Dimension(300,45), "UPC:" ,25), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 3;
		campos.add(upcTF, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 4;
		campos.add(SwingFactory.getJLabel(new Dimension(300,45), "NOMBRE:" ,25), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 4;
		campos.add(nombreTF, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 5;
		campos.add(SwingFactory.getJLabel(new Dimension(300,45), "PRECIO:" ,25), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 5;
		campos.add(precioTF, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 6;
		campos.add(SwingFactory.getJLabel(new Dimension(300,45), "CANTIDAD:" ,25), constraintsCampos);
		constraintsCampos.gridx = 1;
		constraintsCampos.gridy = 6;
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
		
		camposProducto.add(camposPC, productos[0]);
		
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
		
		camposProducto.add(camposPeriferico, productos[1]);
		
		productosBox.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                clProducto.show(camposProducto, productosBox.getSelectedItem().toString());
	            }
	        });
		
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 7;
		constraintsCampos.gridheight = 1;
		constraintsCampos.gridwidth = 2;
		campos.add(camposProducto, constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 8;
		constraintsCampos.gridheight = 1;
		constraintsCampos.gridwidth = 2;
		campos.add(SwingFactory.getJLabel(new Dimension(265,50), "      DESCRIPCION:" ,25), constraintsCampos);
		constraintsCampos.gridx = 0;
		constraintsCampos.gridy = 9;
		campos.add(descripcionSP, constraintsCampos);
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout( FlowLayout.CENTER ));
		botones.setBorder(BorderFactory.createMatteBorder(
                  1, 0, 0, 0, Color.black));
		
		botones.add(confirmar);
		botones.add(limpiar);
		
		altaProductoPanel.add(campos,BorderLayout.CENTER);
		altaProductoPanel.add(botones, BorderLayout.SOUTH);
		
		altaProductoPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
	        public void componentShown(java.awt.event.ComponentEvent evt) {
	            setNombreMarcas();
	        }
	    });
		
		return altaProductoPanel;
	}
	
	private void alta()  {
		String tipo = productosBox.getSelectedItem().toString();
		String nombreMarca = null;
		if (marcas.size() > 0) nombreMarca = marcasBox.getSelectedItem().toString();
		
		try {
			if (upcTF.getText().trim().equals("") || nombreTF.getText().trim().equals("") || 
				precioTF.getText().trim().equals("") || cantidadTF.getText().trim().equals(""))
			{ 
				throw new Exception("Campos sin rellenar."); 
			}
			
			if (nombreMarca == null) throw new Exception("El producto debe estar asociado a una marca.");
			
			String desc = descripcion.getText().trim(); 
			if (desc.equals("")) desc = null;
			else desc = descripcion.getText();
			
			if (tipo.equals("PC")) { altaPC(desc,nombreMarca); }
			else if (tipo.equals("Periferico")) { altaPeriferico(desc,nombreMarca); }
				
			limpiar();
			
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
		}
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
	
	private void altaPC(String desc, String nombreMarca) throws Exception {
		if (tarjetaGraficaTF.getText().trim().equals("") || ramTF.getText().trim().equals("") || 
				procesadorTF.getText().trim().equals("") || placaBaseTF.getText().trim().equals("") || 
				discoDuroTF.getText().trim().equals(""))
		{ 
			throw new Exception("Campos sin rellenar."); 
		}
		
			
		TPc pc = new TPc();
		
		pc.setUPC(upcTF.getText());
		pc.setNombre(nombreTF.getText());
		pc.setTipo("PC");
		pc.setPrecio(Double.parseDouble(precioTF.getText()));
		pc.setCantidad(Integer.parseInt(cantidadTF.getText()));
		pc.setDescripcion(desc);
		pc.setTarjetagrafica(tarjetaGraficaTF.getText());
		pc.setRam(ramTF.getText());
		pc.setProcesador(procesadorTF.getText());
		pc.setPlacabase(placaBaseTF.getText());
		pc.setDiscoduro(discoDuroTF.getText());
		
		TPc pcYaRegistrado = (TPc) controlador.altaProducto(pc, nombreMarca);
		
		if (pcYaRegistrado == null) {
			JOptionPane.showMessageDialog(null,"PC " + nombreTF.getText() + " dado de alta con exito.",
					   "INFO",JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			Object[] options = {"Modificar","No modificar","No reactivar"};
			int n = JOptionPane.showOptionDialog(null,
					 "Este PC ya estaba registrado, ¿Quieres reactivarlo y modificar sus valores?:", "Advertencia",
					 JOptionPane.YES_NO_CANCEL_OPTION,
					 JOptionPane.WARNING_MESSAGE,
					 null,
					 options,
					 options[1]); 
			
			
			if (n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION) {
				controlador.reactivarProducto(pcYaRegistrado.getId());
				
				if (n == JOptionPane.YES_OPTION) {
					pc.setId(pcYaRegistrado.getId());
					controlador.modificarProducto(pc);
				}
				
				JOptionPane.showMessageDialog(null,"PC " + pc.getNombre() + " reactivado con exito",
						   "INFO",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private void altaPeriferico(String desc, String nombreMarca) throws Exception {
		if (conexionTF.getText().trim().equals("")) throw new Exception("Campos sin rellenar."); 
		
		TPeriferico periferico = new TPeriferico();
		
		periferico.setUPC(upcTF.getText());
		periferico.setNombre(nombreTF.getText());
		periferico.setTipo("Periferico");
		periferico.setPrecio(Double.parseDouble(precioTF.getText()));
		periferico.setCantidad(Integer.parseInt(cantidadTF.getText()));
		periferico.setDescripcion(desc);
		periferico.setTipoPeriferico(tPerifericoBox.getSelectedItem().toString());
		periferico.setConexion(conexionTF.getText());
		
		TPeriferico prfcoYaRegistrado = (TPeriferico) controlador.altaProducto(periferico, nombreMarca);
		if (prfcoYaRegistrado == null) {
			JOptionPane.showMessageDialog(null,"Periferico " + nombreTF.getText() + " dado de alta con exito.",
					   "INFO",JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			Object[] options = {"Modificar","No modificar","No reactivar"};
			int n = JOptionPane.showOptionDialog(null,
					 "Este periferico ya estaba registrado, ¿Quieres reactivarlo y modificar sus valores?:", "Advertencia",
					 JOptionPane.YES_NO_CANCEL_OPTION,
					 JOptionPane.WARNING_MESSAGE,
					 null,
					 options,
					 options[1]); 
			
			
			if (n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION) {
				controlador.reactivarProducto(prfcoYaRegistrado.getId());
				
				if (n == JOptionPane.YES_OPTION) {
					periferico.setId(prfcoYaRegistrado.getId());
					controlador.modificarProducto(periferico);
				}
				
				JOptionPane.showMessageDialog(null,"Periferico " + periferico.getNombre() + " reactivado con exito",
						   "INFO",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}	
	
	private void setNombreMarcas() {
		marcas = controlador.getNombreMarcas();
		
		marcasBox.setModel(new DefaultComboBoxModel<String>(marcas.toArray(new String[0])));
		
		if (marcas.size() > 0) marcasBox.setSelectedIndex(0); 
	}
}
