package presentacion.view.ventas;

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

import integracion.transfers.TLineaVenta;
import integracion.transfers.TMarca;
import integracion.transfers.TProducto;
import integracion.transfers.TVenta;
import presentacion.controllers.MarcaController;
import presentacion.controllers.VentaController;
import presentacion.view.SwingFactory;

public class MostrarVenta {
	
	private VentaController controlador;
	
	private JTextField idTF;
	private JTextArea datosTA;
	private JButton buscar;
	private JButton limpiar;
	
	public MostrarVenta(VentaController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
		idTF = SwingFactory.getJTextField(new Dimension(290,30), 25);
		
		buscar = SwingFactory.getJButton(new Dimension(140,30), "BUSCAR VENTA", 
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
		JPanel mostrarClientePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JScrollPane datosSP = new JScrollPane(datosTA);
		datosSP.setPreferredSize(new Dimension(550,250));
		datosSP.setMaximumSize(datosSP.getPreferredSize());
		datosSP.setMinimumSize(new Dimension(550,150));
		
		JPanel barraBusqueda = new JPanel(new FlowLayout( FlowLayout.CENTER));
		barraBusqueda.add(SwingFactory.getJLabel(new Dimension(160,50), "    ID VENTA:" ,25));
		barraBusqueda.add(idTF);
		barraBusqueda.add(Box.createRigidArea(new Dimension(10, 0)));
		barraBusqueda.add(buscar);
		barraBusqueda.setMinimumSize(new Dimension(300,150));
		
		c.gridx = 0;
		c.gridy = 0;
		mostrarClientePanel.add(barraBusqueda, c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.anchor = GridBagConstraints.CENTER;
	    mostrarClientePanel.add(datosSP, c);
	    c.weightx = 0.0;
		c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    mostrarClientePanel.add(Box.createRigidArea(new Dimension(0,15)),c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 3;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    mostrarClientePanel.add(limpiar,c);
		
		return mostrarClientePanel;
	}
	
	private String busquedaToString(TVenta venta) {
		StringBuilder busqstr = new StringBuilder();
		
		for (int i = 0; i < 45; i++) busqstr.append(" ");
		busqstr.append("=====================\n");
		for (int i = 0; i < 45; i++) busqstr.append(" ");
		busqstr.append("      DATOS DE LA VENTA   \n");
		for (int i = 0; i < 45; i++) busqstr.append(" ");
		busqstr.append("=====================\n\n");
		
		busqstr.append("    ID: "+venta.getId()+"\n\n");
		busqstr.append("    ID CLIENTE: "+venta.getIdCliente()+"\n\n");
		busqstr.append("    ID EMPLEADO: "+venta.getIdPersonal()+"\n\n");
		busqstr.append("    FECHA: "+venta.getFecha()+"\n\n\n");
		
		for (int i = 0; i < venta.getNumeroLineas(); i++) {
			lineaToString(busqstr, venta.getLineaVenta(i));
		}
		
		busqstr.append("\n    IMPORTE TOTAL: "+venta.getTotal()+"€\n\n\n");
		
		return busqstr.toString();
	}
	
	private void lineaToString(StringBuilder busqstr, TLineaVenta linea) {
		
		
		busqstr.append("    "+linea.getUnidades()+" x "+linea.getNombre()+" -------- "+linea.getUnidades()*linea.getPrecio_unitario()+"€\n\n");
	}
	
	private void mostrar() {
		try {
			if (idTF.getText().trim().equals("")) throw new Exception("Campo sin rellenar.");
			
			TVenta venta = controlador.getVenta(Integer.parseInt(idTF.getText()));
			
			datosTA.setText(busquedaToString(venta));
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

