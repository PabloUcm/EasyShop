package presentacion.view.productos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
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

public class MostrarProducto {
	private ProductoController controlador;
	
	private JTextField idTF;
	private JTextArea datosTA;
	private JButton buscar;
	private JButton limpiar;
	
	public MostrarProducto(ProductoController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
		idTF = SwingFactory.getJTextField(new Dimension(290,30), 25);
		
		buscar = SwingFactory.getJButton(new Dimension(180,30), "BUSCAR PRODUCTO", 
										 "icons/lupa", 20, new Color(8,213,249), new Color(6,160,190));
		limpiar = SwingFactory.getJButton(new Dimension(170,45), "LIMPIAR BUSQUEDA", 
										  "icons/limpiar", 25, new Color(205,205,205), new Color(166,166,166));
		
		buscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { mostrar(); }
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { limpiar(); }
	    });	
		
		datosTA = SwingFactory.getJTextArea(15, false);
	}
	
	public JPanel getDefaultLayout() {
		JPanel mostrarProductoPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JScrollPane datosSP = new JScrollPane(datosTA);
		datosSP.setPreferredSize(new Dimension(550,450));
		datosSP.setMaximumSize(datosSP.getPreferredSize());
		datosSP.setMinimumSize(new Dimension(550,250));
		
		JPanel barraBusqueda = new JPanel(new FlowLayout( FlowLayout.CENTER));
		barraBusqueda.add(SwingFactory.getJLabel(new Dimension(200,50), " ID PRODUCTO:" ,25));
		barraBusqueda.add(idTF);
		barraBusqueda.add(Box.createRigidArea(new Dimension(10, 0)));
		barraBusqueda.add(buscar);
		barraBusqueda.setMinimumSize(new Dimension(300,150));
		
		c.gridx = 0;
		c.gridy = 0;
		mostrarProductoPanel.add(barraBusqueda, c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.anchor = GridBagConstraints.CENTER;
	    mostrarProductoPanel.add(datosSP, c);
	    c.weightx = 0.0;
		c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    mostrarProductoPanel.add(Box.createRigidArea(new Dimension(0,15)),c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 3;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    mostrarProductoPanel.add(limpiar,c);
		
		return mostrarProductoPanel;
	}
	
	private String busquedaToString(TProducto producto) {
		StringBuilder busqstr = new StringBuilder();
		
		for (int i = 0; i < 45; i++) busqstr.append(" ");
		busqstr.append("=====================\n");
		for (int i = 0; i < 45; i++) busqstr.append(" ");
		busqstr.append("      DATOS DEL PRODUCTO   \n");
		for (int i = 0; i < 45; i++) busqstr.append(" ");
		busqstr.append("=====================\n\n");
		
		busqstr.append("    ID: "+producto.getId()+"\n\n");
		busqstr.append("    UPC: "+producto.getUPC()+"\n\n");
		busqstr.append("    NOMBRE: "+producto.getNombre()+"\n\n");
		busqstr.append("    ID MARCA: "+producto.getMarcaId()+"\n\n");
		busqstr.append("    TIPO: "+producto.getTipo()+"\n\n");
		busqstr.append("    PRECIO: "+producto.getPrecio()+"\n\n");
		busqstr.append("    CANTIDAD: "+producto.getCantidad()+"\n\n");
		
		if (producto.getTipo().equals("PC")) PcToString(busqstr, (TPc) producto);
		else if (producto.getTipo().equals("Periferico")) PerifericoToString(busqstr, (TPeriferico) producto); 
		
		String desc;
		if (producto.getDescripcion() == null) desc = "Sin descripcion";
		else desc = producto.getDescripcion();
		
		busqstr.append("    Descripcion: "+desc+"\n\n");
		
		return busqstr.toString();
	}
	
	private void PcToString(StringBuilder busqstr, TPc pc) {
		busqstr.append("    PROCESADOR: "+pc.getProcesador()+"\n\n");
		busqstr.append("    RAM: "+pc.getRam()+"\n\n");
		busqstr.append("    DISCO DURO: "+pc.getDiscoduro()+"\n\n");
		busqstr.append("    TARJETA GRAFICA: "+pc.getTarjetagrafica()+"\n\n");
		busqstr.append("    PLACA BASE: "+pc.getPlacabase()+"\n\n");
	}
	
	private void PerifericoToString(StringBuilder busqstr, TPeriferico periferico) {
		busqstr.append("    TIPO PERIFERICO: "+periferico.getTipoPeriferico()+"\n\n");
		busqstr.append("    RAM: "+periferico.getConexion()+"\n\n");
	}
	
	private void mostrar() {
		try {
			if (idTF.getText().trim().equals("")) throw new Exception("Campo sin rellenar.");
			
			int id = Integer.parseInt(idTF.getText());
			
			TProducto producto = controlador.getProductoById(id, "NONE");
			
			if (producto.getTipo().equals("PC")) producto = (TPc) controlador.getPcById(id);
			else if (producto.getTipo().equals("Periferico")) producto = (TPeriferico) controlador.getPerifericoById(id);
			
			datosTA.setText(busquedaToString(producto));
			datosTA.setCaretPosition(0);
		}
		catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,"El campo 'ID' debe ser un numero.", "ERROR",JOptionPane.ERROR_MESSAGE);
			limpiar();
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limpiar() {
		idTF.setText("");
		datosTA.setText("");
	}
}
