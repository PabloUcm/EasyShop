package presentacion.view.personal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import integracion.transfers.TPersonal;
import negocio.PersonalObserver;
import presentacion.controllers.PersonalController;

public class AltaEmpleado implements PersonalObserver{
	
	private PersonalController controlador;
	
	public AltaEmpleado(PersonalController c) {
		this.controlador = c;
		controlador.addObserver(this);
		initGUI();
	}
	
	private JTextField dniTF;
	private JTextField nombreTF;
	private JTextField sueldoTF;
	private JTextField tfnoTF;
	private JTextField horarioTF;
	private JButton confirmar;
	private JButton limpiar;
	
	private void initGUI() {
		dniTF = crearTextField();
		nombreTF = crearTextField();
		sueldoTF = crearTextField();
		tfnoTF = crearTextField();
		horarioTF = crearTextField();
		
		confirmar = crearBoton("CONFIRMAR ALTA DE EMPLEADO", Color.GREEN, "confirmar");
		limpiar = crearBoton("LIMPIAR CAMPOS DE TEXTO", Color.GRAY.brighter(), "limpiar");
		
		confirmar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		alta();
	    	}
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		dniTF.setText(" ");
	    		nombreTF.setText(" ");
	    		sueldoTF.setText(" ");
	    		tfnoTF.setText(" ");
	    	}
	    });
	}
	
	public JPanel getDefaultLayout() {
		JPanel altaEmpPanel = new JPanel(new BorderLayout());
		
		JPanel campos = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		campos.add(crearJLabel("           DNI:"), c);
		c.gridx = 1;
		c.gridy = 0;
		campos.add(dniTF, c);
		c.gridx = 0;
		c.gridy = 1;
		campos.add(crearJLabel("        NOMBRE:"), c);
		c.gridx = 1;
		c.gridy = 1;
		campos.add(nombreTF, c);
		c.gridx = 0;
		c.gridy = 2;
		campos.add(crearJLabel("        SUELDO:"), c);
		c.gridx = 1;
		c.gridy = 2;
		campos.add(sueldoTF, c);
		c.gridx = 0;
		c.gridy = 3;
		campos.add(crearJLabel("     TELEFONO:"), c);
		c.gridx = 1;
		c.gridy = 3;
		campos.add(tfnoTF, c);
		c.gridx = 0;
		c.gridy = 4;
		campos.add(crearJLabel("    HORARIO:"), c);
		c.gridx = 1;
		c.gridy = 4;
		campos.add(horarioTF, c);
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout( FlowLayout.CENTER ));
		botones.setBorder(BorderFactory.createMatteBorder(
                  1, 0, 0, 0, Color.black));
		
		botones.add(confirmar);
		botones.add(limpiar);
		
		altaEmpPanel.add(campos,BorderLayout.CENTER);
		altaEmpPanel.add(botones, BorderLayout.SOUTH);
		
		return altaEmpPanel;
	}
	
	private JLabel crearJLabel(String texto) {
		JLabel jl = new JLabel(texto);
		jl.setFont(new Font(jl.getFont().toString(), Font.BOLD, 30));
		jl.setAlignmentX(Component.CENTER_ALIGNMENT);
		jl.setPreferredSize(new Dimension(230,50));
		jl.setMaximumSize(jl.getPreferredSize());
		return jl;
	}
	
	private JTextField crearTextField() {
		JTextField tf = new JTextField();
		tf.setFont(new Font(tf.getFont().toString(), Font.PLAIN, 25));
		tf.setAlignmentX(Component.CENTER_ALIGNMENT);
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
		button.setPreferredSize(new Dimension(250,60));
		button.setMaximumSize(button.getPreferredSize());
		button.setBackground(color);
		
		ImageIcon imageIcon = new ImageIcon("icons/"+icono+".png"); 
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg);  
		button.setIcon(imageIcon);

		return button;
	}
	private void alta() {
		try {
			controlador.altaPersonal(dniTF.getText(), nombreTF.getText(), sueldoTF.getText(),tfnoTF.getText(),horarioTF.getText());
			JOptionPane.showMessageDialog(null,"Empleado " + nombreTF.getText() + " registrado con exito",
										  "Error icon",JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	@Override
	public void altaEmpleado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bajaEmpleado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarEmpleadoId() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarEmpleado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listarEmpleados() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void obtenerEmpleado(TPersonal empleado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarEmpleado(List<TPersonal> empleadoList) {
		// TODO Auto-generated method stub
		
	}
}