package presentacion.view.personal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import integracion.transfers.TCliente;
import integracion.transfers.TPersonal;
import presentacion.controllers.PersonalController;
import presentacion.view.SwingFactory;

public class AltaEmpleado{
	
	private PersonalController controlador;
	
	private JTextField dniTF;
	private JTextField nombreTF;
	private JTextField sueldoTF;
	private JTextField tfnoTF;
	private JTextField horarioTF;
	private JButton confirmar;
	private JButton limpiar;
	
	public AltaEmpleado(PersonalController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
		dniTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		nombreTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		sueldoTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		tfnoTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		horarioTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		
		confirmar = SwingFactory.getJButton(new Dimension(230,60), "CONFIRMAR ALTA DE CLIENTE", 
											"icons/confirmar", 50, Color.GREEN, new Color(130,200,21));
		limpiar = SwingFactory.getJButton(new Dimension(230,60), "LIMPIAR CAMPOS DE TEXTO", 
				  						  "icons/limpiar", 50, new Color(205,205,205), new Color(166,166,166));
		
		confirmar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { alta(); }
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { limpiar(); }
	    });
	}
	
	public JPanel getDefaultLayout() {
		JPanel altaEmpPanel = new JPanel(new BorderLayout());
		
		JPanel campos = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "                DNI:" ,30), c);
		c.gridx = 1;
		c.gridy = 0;
		campos.add(dniTF, c);
		c.gridx = 0;
		c.gridy = 1;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "      NOMBRE:" ,30), c);
		c.gridx = 1;
		c.gridy = 1;
		campos.add(nombreTF, c);
		c.gridx = 0;
		c.gridy = 2;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "       SUELDO:" ,30), c);
		c.gridx = 1;
		c.gridy = 2;
		campos.add(sueldoTF, c);
		c.gridx = 0;
		c.gridy = 3;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "  TELEFONO:" ,30), c);
		c.gridx = 1;
		c.gridy = 3;
		campos.add(tfnoTF, c);
		c.gridx = 0;
		c.gridy = 4;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "     HORARIO:" ,30), c);
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
	
	private void alta() {
		try {
			TPersonal personal = controlador.altaPersonal(dniTF.getText(), nombreTF.getText(),sueldoTF.getText(),
					                                      tfnoTF.getText(), horarioTF.getText());
			if (personal == null) {
				JOptionPane.showMessageDialog(null,"Empleado" + nombreTF.getText() + " registrado con exito",
										  "INFO",JOptionPane.INFORMATION_MESSAGE);
				limpiar();
			}
			else {
				Object[] options = {"Modificar","No modificar","No registrar"};
				int n = JOptionPane.showOptionDialog(null,
						 "Este empleado ya estaba registrado, ¿Quieres modificar sus valores?:", "Advertencia",
						 JOptionPane.YES_NO_CANCEL_OPTION,
						 JOptionPane.WARNING_MESSAGE,
						 null,
						 options,
						 options[1]); 
				
				if(n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION ){
					if (n == JOptionPane.YES_OPTION) {
						personal.setDni(dniTF.getText());
					    personal.setNombre(nombreTF.getText());
						personal.setTelefono(tfnoTF.getText());
					}
					controlador.reactivarPersonal(personal);
					JOptionPane.showMessageDialog(null,"Cliente " + personal.getNombre() + " reactivado con exito",
																	   "INFO",JOptionPane.INFORMATION_MESSAGE);
				}
				limpiar();
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limpiar() {
		dniTF.setText(" ");
		nombreTF.setText(" ");
		sueldoTF.setText(" ");
		tfnoTF.setText(" ");
	}
}