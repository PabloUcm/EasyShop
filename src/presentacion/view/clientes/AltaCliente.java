package presentacion.view.clientes;
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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import integracion.transfers.TCliente;
import negocio.ClienteObserver;
import presentacion.controllers.ClienteController;

public class AltaCliente{
	
	private ClienteController controlador;
	
	public AltaCliente(ClienteController c) {
		this.controlador = c;
		initGUI();
	}
	
	private JTextField dniTF;
	private JTextField nombreTF;
	private JTextField tfnoTF;
	private JButton confirmar;
	private JButton limpiar;
	
	private void initGUI() {
		dniTF = crearTextField();
		nombreTF = crearTextField();
		tfnoTF = crearTextField();
		
		confirmar = crearBoton("CONFIRMAR ALTA DE CLIENTE", Color.GREEN, 
							   new Color(130,200,21), "confirmar");
		limpiar = crearBoton("LIMPIAR CAMPOS DE TEXTO", new Color(205,205,205), 
				 			 new Color(166,166,166), "limpiar");
		
		
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
		campos.add(crearJLabel("                DNI:"), c);
		c.gridx = 1;
		c.gridy = 0;
		campos.add(dniTF, c);
		c.gridx = 0;
		c.gridy = 1;
		campos.add(crearJLabel("      NOMBRE:"), c);
		c.gridx = 1;
		c.gridy = 1;
		campos.add(nombreTF, c);
		c.gridx = 0;
		c.gridy = 2;
		campos.add(crearJLabel("  TELEFONO:"), c);
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
	
	private JLabel crearJLabel(String texto) {
		JLabel jl = new JLabel(texto);
		jl.setFont(new Font(jl.getFont().toString(), Font.BOLD, 30));
		jl.setPreferredSize(new Dimension(200,50));
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
	
	private JButton crearBoton(String texto, Color colorNormal, Color colorMouse, String icono) {
		JButton button = new JButton(texto);
		
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		button.setOpaque(true);
		
		button.setPreferredSize(new Dimension(230,60));
		button.setMaximumSize(button.getPreferredSize());
		
		button.setBackground(colorNormal);
		
		ImageIcon imageIcon = new ImageIcon("icons/"+icono+".png"); 
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg);  
		button.setIcon(imageIcon);

		button.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	button.setBackground(colorMouse);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	button.setBackground(colorNormal);
		    }
		    
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	button.setBorder(BorderFactory.createBevelBorder(1));
		    }
		    
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	button.setBorder(BorderFactory.createRaisedBevelBorder());
		    }
		});
		
		return button;
	}
	
	private void alta() {
		try {
			TCliente cliente = controlador.altaCliente(dniTF.getText(), nombreTF.getText(), tfnoTF.getText());
			if (cliente == null) {
				JOptionPane.showMessageDialog(null,"Cliente " + nombreTF.getText() + " registrado con exito",
										  "INFO",JOptionPane.INFORMATION_MESSAGE);
				limpiar();
			}
			else {
				Object[] options = {"Modificar","No modificar","No registrar"};
				int n = JOptionPane.showOptionDialog(null,
						 "Este cliente ya estaba registrado, ¿Quieres modificar sus valores?:", "Advertencia",
						 JOptionPane.YES_NO_CANCEL_OPTION,
						 JOptionPane.WARNING_MESSAGE,
						 null,
						 options,
						 options[1]); 
				
				if(n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION ){
					if (n == JOptionPane.YES_OPTION) {
						cliente.setDni(dniTF.getText());
						cliente.setNombre(nombreTF.getText());
						cliente.setTelefono(tfnoTF.getText());
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
		
	}

}
