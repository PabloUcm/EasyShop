package presentacion.view.personal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import presentacion.controllers.PersonalController;
import presentacion.view.SwingFactory;

public class HistorialEmpleado {
	
	private PersonalController controlador;
	
	private JTextField idTF;
	private JTextArea historialTA;
	private JButton buscar;
	private JButton limpiar;
	
	public HistorialEmpleado(PersonalController c){
		controlador = c;
		initGUI();
		
	}
	
	private void initGUI(){
		idTF = SwingFactory.getJTextField(new Dimension(290,30), 25);
		
		buscar = SwingFactory.getJButton(new Dimension(150,30), "BUSCAR HISTORIAL", 
				 						 "icons/lupa", 20, new Color(8,213,249),  new Color(6,160,190));
		limpiar = SwingFactory.getJButton(new Dimension(170,45), "LIMPIAR BUSQUEDA", 
				                          "icons/limpiar", 25, new Color(205,205,205),  new Color(166,166,166));
		
		buscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		//historialTA.setText(historialToString());
	    		historialTA.setCaretPosition(0);
	    	}
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { limpiar(); }
	    });
		
		historialTA = SwingFactory.getJTextArea(13, false);
	}
	
	public JPanel getDefaultLayout() {
		JPanel histClientePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JScrollPane historialSP = new JScrollPane(historialTA);
		historialSP.setPreferredSize(new Dimension(550,600));
		historialSP.setMaximumSize(historialSP.getPreferredSize());
		
		JPanel barraBusqueda = new JPanel(new FlowLayout( FlowLayout.CENTER));
		barraBusqueda.add(SwingFactory.getJLabel(new Dimension(160,50), "ID EMPLEADO:" ,25));
		barraBusqueda.add(idTF);
		barraBusqueda.add(Box.createRigidArea(new Dimension(10, 0)));
		barraBusqueda.add(buscar);
		
		c.gridx = 0;
		c.gridy = 0;
		histClientePanel.add(barraBusqueda, c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.anchor = GridBagConstraints.CENTER;
	    histClientePanel.add(historialSP, c);
	    c.weightx = 0.0;
		c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    histClientePanel.add(Box.createRigidArea(new Dimension(0,15)),c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 3;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    histClientePanel.add(limpiar,c);
		
		return histClientePanel;
	}
	
	/*
	private String historialToString() {
		StringBuilder histstr = new StringBuilder();
		
		for (int i = 0; i < 33; i++) histstr.append(" ");
		histstr.append("====================================\n");
		for (int i = 0; i < 38; i++) histstr.append(" ");
		histstr.append("Historial de compras del cliente con ID: "+idTF.getText()+"\n");
		for (int i = 0; i < 33; i++) histstr.append(" ");
		histstr.append("====================================\n\n");
		
		
		return histstr.toString();
	}
	*/
	
	private void limpiar() {
		idTF.setText("");
		historialTA.setText("");
	}

}
