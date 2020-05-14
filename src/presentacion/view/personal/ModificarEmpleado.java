package presentacion.view.personal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.controllers.PersonalController;

public class ModificarEmpleado {
	
	private PersonalController controlador;
	
	public ModificarEmpleado(PersonalController c) {
		this.controlador = c;
		initGUI();
	}
	
	private JTextField idTF;
	private JTextField dniTF;
	private JTextField nombreTF;
	private JTextField sueldoTF;
	private JTextField tfnoTF;
	private JButton modificar;
	private JButton limpiar;
	
	private void initGUI() {
		
		idTF = crearTextField();
		dniTF = crearTextField();
		nombreTF = crearTextField();
		sueldoTF = crearTextField();
		tfnoTF = crearTextField();
		
		modificar = crearBoton("MODIFICAR EMPLEADO", Color.ORANGE.darker(), "modificar");
		limpiar = crearBoton("LIMPIAR CAMPOS DE TEXTO", Color.GRAY.brighter(), "limpiar");
		
		modificar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		
	    	}
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		idTF.setText(" ");
	    		dniTF.setText(" ");
	    		nombreTF.setText(" ");
	    		sueldoTF.setText(" ");
	    		tfnoTF.setText(" ");
	    	}
	    });
	}
	
	public JPanel getDefaultLayout() {
		JPanel modEmpPanel = new JPanel(new BorderLayout());
		
		JPanel campos = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		campos.add(crearJLabel("ID EMPLEADO:"), c);
		c.gridx = 1;
		c.gridy = 0;
		campos.add(idTF, c);
		c.gridx = 0;
		c.gridy = 1;
		campos.add(crearJLabel("                   DNI:"), c);
		c.gridx = 1;
		c.gridy = 1;
		campos.add(dniTF, c);
		c.gridx = 0;
		c.gridy = 2;
		campos.add(crearJLabel("         NOMBRE:"), c);
		c.gridx = 1;
		c.gridy = 2;
		campos.add(nombreTF, c);
		c.gridx = 0;
		c.gridy = 3;
		campos.add(crearJLabel("          SUELDO:"), c);
		c.gridx = 1;
		c.gridy = 3;
		campos.add(sueldoTF, c);
		c.gridx = 0;
		c.gridy = 4;
		campos.add(crearJLabel("     TELEFONO:"), c);
		c.gridx = 1;
		c.gridy = 4;
		campos.add(tfnoTF, c);
		
		JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		botones.setBorder(BorderFactory.createMatteBorder(
                  1, 0, 0, 0, Color.black));
		
		botones.add(modificar);
		botones.add(limpiar);
		
		modEmpPanel.add(campos,BorderLayout.CENTER);
		modEmpPanel.add(botones, BorderLayout.SOUTH);
		
		return modEmpPanel;
	}
	
	private JLabel crearJLabel(String texto) {
		JLabel jl = new JLabel(texto);
		jl.setFont(new Font(jl.getFont().toString(), Font.BOLD, 30));
		jl.setPreferredSize(new Dimension(230,50));
		jl.setMaximumSize(jl.getPreferredSize());
		return jl;
	}
	
	private JTextField crearTextField() {
		JTextField tf = new JTextField();
		tf.setFont(new Font(tf.getFont().toString(), Font.PLAIN, 25));
		tf.setPreferredSize(new Dimension(550,35));
		tf.setMaximumSize(tf.getPreferredSize());
		return tf;
	}
	
	private JButton crearBoton(String texto, Color color, String icono) {
		JButton button = new JButton(texto);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(230,60));
		button.setMaximumSize(button.getPreferredSize());
		button.setBackground(color);
		
		ImageIcon imageIcon = new ImageIcon("icons/"+icono+".png"); 
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg);  
		button.setIcon(imageIcon);

		return button;
	}
}