package presentacion.view.marcas;

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


import integracion.transfers.TMarca;
import presentacion.controllers.MarcaController;
import presentacion.view.SwingFactory;


public class MostrarMarca{
	
	private MarcaController controlador;
	
	private JTextField idTF;
	private JTextArea datosTA;
	private JButton buscar;
	private JButton limpiar;
	
	public MostrarMarca(MarcaController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
		idTF = SwingFactory.getJTextField(new Dimension(290,30), 25);
		
		buscar = SwingFactory.getJButton(new Dimension(140,30), "BUSCAR MARCA", 
				 						 "icons/lupa", 20, new Color(8,213,249), new Color(6,160,190));
		limpiar = SwingFactory.getJButton(new Dimension(170,45), "LIMPIAR BUSQUEDA", 
				  						  "icons/limpiar", 25, new Color(205,205,205), new Color(166,166,166));
		
		buscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { mostrar(); }
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { limpiar(); }
	    });	
		
		datosTA = SwingFactory.getJTextArea(15);
	}
	
	public JPanel getDefaultLayout() {
		JPanel mostrarClientePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JScrollPane datosSP = new JScrollPane(datosTA);
		datosSP.setPreferredSize(new Dimension(550,250));
		datosSP.setMaximumSize(datosSP.getPreferredSize());
		datosSP.setMinimumSize(new Dimension(550,150));
		
		JPanel barraBusqueda = new JPanel(new FlowLayout( FlowLayout.CENTER));
		barraBusqueda.add(SwingFactory.getJLabel(new Dimension(160,50), "    ID MARCA:" ,25));
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
	
	private String busquedaToString(TMarca marca) {
		StringBuilder busqstr = new StringBuilder();
		for (int i = 0; i < 45; i++) busqstr.append(" ");
		busqstr.append("=====================\n");
		for (int i = 0; i < 45; i++) busqstr.append(" ");
		busqstr.append("      DATOS DE LA MARCA   \n");
		for (int i = 0; i < 45; i++) busqstr.append(" ");
		busqstr.append("=====================\n\n");
		busqstr.append("    ID: "+marca.getId()+"\n\n");
		busqstr.append("    CIF: "+marca.getCIF()+"\n\n");
		busqstr.append("    NOMBRE: "+marca.getNombre()+"\n\n");
		if(marca.getPais() != null) busqstr.append("    PAIS: "+marca.getPais());
		else busqstr.append("    PAIS: [Vacio]");
		
		return busqstr.toString();
	}
	
	private void mostrar() {
		try {
			if (idTF.getText().isEmpty()) throw new Exception("Campo sin rellenar.");
			
			TMarca marca = controlador.getMarca(Integer.parseInt(idTF.getText()));
			
			datosTA.setText(busquedaToString(marca));
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
