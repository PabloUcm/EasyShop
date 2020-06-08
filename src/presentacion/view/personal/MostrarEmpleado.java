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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import integracion.transfers.TPersonal;
import presentacion.controllers.PersonalController;
import presentacion.view.SwingFactory;


public class MostrarEmpleado{
	
	private PersonalController controlador;
	
	private JTextField idTF;
	private JTextArea datosTA;
	private JButton buscar;
	private JButton limpiar;
	
	public MostrarEmpleado(PersonalController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
		idTF = SwingFactory.getJTextField(new Dimension(290,30), 25);
		
		buscar = SwingFactory.getJButton(new Dimension(160,30), "BUSCAR EMPLEADO", 
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
		JPanel mostrarPersonalPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JScrollPane datosSP = new JScrollPane(datosTA);
		datosSP.setPreferredSize(new Dimension(550,250));
		datosSP.setMaximumSize(datosSP.getPreferredSize());
		datosSP.setMinimumSize(new Dimension(550,150));
		
		JPanel barraBusqueda = new JPanel(new FlowLayout( FlowLayout.CENTER));
		barraBusqueda.add(SwingFactory.getJLabel(new Dimension(190,50), " ID EMPLEADO:" ,25));
		barraBusqueda.add(idTF);
		barraBusqueda.add(Box.createRigidArea(new Dimension(10, 0)));
		barraBusqueda.add(buscar);
		barraBusqueda.setMinimumSize(new Dimension(300,150));
		
		c.gridx = 0;
		c.gridy = 0;
		mostrarPersonalPanel.add(barraBusqueda, c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.anchor = GridBagConstraints.CENTER;
	    mostrarPersonalPanel.add(datosSP, c);
	    c.weightx = 0.0;
		c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    mostrarPersonalPanel.add(Box.createRigidArea(new Dimension(0,15)),c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 3;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    mostrarPersonalPanel.add(limpiar,c);
		
		return mostrarPersonalPanel;
	}
	
	private String busquedaToString(TPersonal empleado) {
		StringBuilder busqstr = new StringBuilder();
		
		for (int i = 0; i < 45; i++) busqstr.append(" ");
		busqstr.append("=====================\n");
		for (int i = 0; i < 45; i++) busqstr.append(" ");
		busqstr.append("   DATOS DEL EMPLEADO   \n");
		for (int i = 0; i < 45; i++) busqstr.append(" ");
		busqstr.append("=====================\n\n");
		
		busqstr.append("    ID: "+empleado.getId()+"\n\n");
		busqstr.append("    DNI: "+empleado.getDni()+"\n\n");
		busqstr.append("    NOMBRE: "+empleado.getNombre()+"\n\n");
		busqstr.append("    TELEFONO: "+empleado.getTelefono()+"\n\n");
		busqstr.append("    SUELDO: "+empleado.getSueldo()+"\n\n");
		busqstr.append("    HORARIO: "+empleado.getHorario()+"\n\n");
		
		return busqstr.toString();
	}
	
	private void mostrar() {
		try {
			if (idTF.getText().isEmpty()) throw new Exception("Campo sin rellenar.");
			
			TPersonal empleado = controlador.getPersonal(Integer.parseInt(idTF.getText()));
			
			datosTA.setText(busquedaToString(empleado));
			datosTA.setCaretPosition(0);
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
