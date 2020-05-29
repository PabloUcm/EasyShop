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

public class ModificarCliente implements ClienteObserver {
	private ClienteController controlador;
	
	public ModificarCliente(ClienteController c) {
		this.controlador = c;
		initGUI();
	}
	
	private JTextField idTF;
	private JTextField dniTF;
	private JTextField nombreTF;
	private JTextField tfnoTF;
	private JButton modificar;
	private JButton limpiar;
	private ImageIcon modIcon;
	
	private void initGUI() {
		idTF = crearTextField();
		dniTF = crearTextField();
		nombreTF = crearTextField();
		tfnoTF = crearTextField();
		
		modIcon= new ImageIcon("icons/modificar.png"); 
		Image image = modIcon.getImage(); 
		Image newimg = image.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH); 
		modIcon = new ImageIcon(newimg);
		
		modificar = crearBoton("MODIFICAR CLIENTE", new Color(250,243,58), 
							   new Color(230,215,73), "modificar");
		limpiar = crearBoton("LIMPIAR CAMPOS DE TEXTO", new Color(205,205,205), 
							 new Color(166,166,166), "limpiar");
		
		modificar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { modificar(); }
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		idTF.setText(" ");
	    		dniTF.setText(" ");
	    		nombreTF.setText(" ");
	    		tfnoTF.setText(" ");
	    	}
	    });
		
	}
	
	public JPanel getDefaultLayout() {
		JPanel modClientePanel = new JPanel(new BorderLayout());
		
		JPanel campos = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		campos.add(crearJLabel("     ID CLIENTE:"), c);
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
		campos.add(crearJLabel("     TELEFONO:"), c);
		c.gridx = 1;
		c.gridy = 3;
		campos.add(tfnoTF, c);
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout( FlowLayout.CENTER ));
		botones.setBorder(BorderFactory.createMatteBorder(
                  1, 0, 0, 0, Color.black));
		
		botones.add(modificar);
		botones.add(limpiar);
		
		modClientePanel.add(campos,BorderLayout.CENTER);
		modClientePanel.add(botones, BorderLayout.SOUTH);
		
		return modClientePanel;
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
	
	private void modificar() {
		try {
    		if (idTF.getText().isEmpty() || dniTF.getText().isEmpty() || nombreTF.getText().isEmpty()) {
    			throw new Exception("Campo(s) sin rellenar.");
    		}
			
			TCliente cliente = controlador.getCliente(Integer.parseInt(idTF.getText()));
			
			String tfno;
			if (cliente.getTelefono() == null) tfno = "[Vacio]";
			else tfno = cliente.getTelefono();
			
			String tfnoNuevo; 
			if (tfnoTF.getText().isEmpty()) tfnoNuevo = "[Vacio]";
			else tfnoNuevo = tfnoTF.getText();
    				    		
    		String msg = "ID: "+cliente.getId()+"\n\nDNI: "+cliente.getDni()+"\nNUEVO DNI: "+dniTF.getText()+"\n\nNOMBRE: "
    					  + cliente.getNombre() +"\nNUEVO NOMBRE: " + nombreTF.getText() + "\n\nTELEFONO: "+tfno
    					  +"\nTELEFONO NUEVO: "+ tfnoNuevo +"\n\n ¿Quieres cambiar los datos de este cliente?";
            int input = JOptionPane.showConfirmDialog(null, msg,"Confirmar cambios en el cliente", 
            		    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, modIcon);
            
            if(input == JOptionPane.OK_OPTION) {
            	if (!tfnoTF.getText().isEmpty()) controlador.modificarCliente(Integer.parseInt(idTF.getText()), dniTF.getText(), 
            																 nombreTF.getText(), tfnoTF.getText());
    			else controlador.modificarCliente(Integer.parseInt(idTF.getText()), dniTF.getText(), 
						 						  nombreTF.getText(), null);
            	JOptionPane.showMessageDialog(null,"Cliente con ID " + idTF.getText() + " modificado con éxito.",
						  					  "INFO",JOptionPane.INFORMATION_MESSAGE);
            }
		} 
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void altaCliente() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void bajaCliente() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarClienteId() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void modificarCliente() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void listarClientes() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void obtenerCliente(TCliente cliente) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mostrarCliente(List<TCliente> clienteList) {
		// TODO Auto-generated method stub
		
	}
	
}
