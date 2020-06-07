package presentacion.view.clientes;
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
import presentacion.controllers.ClienteController;
import presentacion.view.SwingFactory;

public class AltaCliente{
	
	private ClienteController controlador;
	
	private JTextField dniTF;
	private JTextField nombreTF;
	private JTextField tfnoTF;
	private JButton confirmar;
	private JButton limpiar;
	
	public AltaCliente(ClienteController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
		dniTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		nombreTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		tfnoTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		
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
		JPanel altaClientePanel = new JPanel(new BorderLayout());
		
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
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "  TELEFONO:" ,30), c);
		c.gridx = 1;
		c.gridy = 2;
		campos.add(tfnoTF, c);
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout( FlowLayout.CENTER ));
		botones.setBorder(BorderFactory.createMatteBorder(
                  1, 0, 0, 0, Color.black));
		
		botones.add(confirmar);
		botones.add(limpiar);
		
		altaClientePanel.add(campos,BorderLayout.CENTER);
		altaClientePanel.add(botones, BorderLayout.SOUTH);
		
		return altaClientePanel;
	}
	
	private void alta() {
		try {
			String telefono = tfnoTF.getText().trim();
			if (telefono.equals("")) telefono = null;
			else telefono = tfnoTF.getText();
			
			TCliente cliente = controlador.altaCliente(dniTF.getText(), nombreTF.getText(), telefono);
			if (cliente == null) {
				JOptionPane.showMessageDialog(null,"Cliente " + nombreTF.getText() + " registrado con exito",
										  "INFO",JOptionPane.INFORMATION_MESSAGE);
				limpiar();
			}
			else {
				Object[] options = {"Modificar","No modificar","No reactivar"};
				int n = JOptionPane.showOptionDialog(null,
						 "Este cliente ya estaba registrado, ¿Quieres reactivarlo y modificar sus valores?:", "Advertencia",
						 JOptionPane.YES_NO_CANCEL_OPTION,
						 JOptionPane.WARNING_MESSAGE,
						 null,
						 options,
						 options[1]); 
				
				if(n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION ){
					if (n == JOptionPane.YES_OPTION) {
						cliente.setDni(dniTF.getText());
						cliente.setNombre(nombreTF.getText());
						cliente.setTelefono(telefono);
					}
					controlador.reactivarCliente(cliente);
					JOptionPane.showMessageDialog(null,"Cliente " + cliente.getNombre() + " reactivado con exito",
												  "INFO",JOptionPane.INFORMATION_MESSAGE);
				}
				limpiar();
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limpiar() {
		dniTF.setText("");
		nombreTF.setText("");
		tfnoTF.setText("");
	}

}
