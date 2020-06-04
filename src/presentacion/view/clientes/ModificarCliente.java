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
import presentacion.view.SwingFactory;

public class ModificarCliente{
	private ClienteController controlador;
	
	private JTextField idTF;
	private JTextField dniTF;
	private JTextField nombreTF;
	private JTextField tfnoTF;
	private JButton modificar;
	private JButton limpiar;
	private ImageIcon modIcon;
	
	public ModificarCliente(ClienteController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
		
		idTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		dniTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		nombreTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		tfnoTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		
		modificar = SwingFactory.getJButton(new Dimension(230,60), "MODIFICAR CLIENTE", 
				                            "icons/modificar", 50, new Color(250,243,58), new Color(230,215,73));
		limpiar = SwingFactory.getJButton(new Dimension(230,60), "LIMPIAR CAMPOS DE TEXTO", 
										  "icons/limpiar", 50, new Color(205,205,205),new Color(166,166,166));
		
		modificar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { modificar(); }
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { limpiar(); }
	    });
		
		modIcon = SwingFactory.getScaledIcon("icons/modificar", 45);
		
	}
	
	public JPanel getDefaultLayout() {
		JPanel modClientePanel = new JPanel(new BorderLayout());
		
		JPanel campos = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		campos.add(SwingFactory.getJLabel(new Dimension(230,50), "     ID CLIENTE:" ,30), c);
		c.gridx = 1;
		c.gridy = 0;
		campos.add(idTF, c);
		c.gridx = 0;
		c.gridy = 1;
		campos.add(SwingFactory.getJLabel(new Dimension(230,50), "                   DNI:" ,30), c);
		c.gridx = 1;
		c.gridy = 1;
		campos.add(dniTF, c);
		c.gridx = 0;
		c.gridy = 2;
		campos.add(SwingFactory.getJLabel(new Dimension(230,50),"         NOMBRE:" ,30), c);
		c.gridx = 1;
		c.gridy = 2;
		campos.add(nombreTF, c);
		c.gridx = 0;
		c.gridy = 3;
		campos.add(SwingFactory.getJLabel(new Dimension(230,50), "     TELEFONO:" ,30), c);
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
    					  +"\nTELEFONO NUEVO: "+ tfnoNuevo +"\n\n �Quieres cambiar los datos de este cliente?";
            int input = JOptionPane.showConfirmDialog(null, msg,"Confirmar cambios en el cliente", 
            		    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, modIcon);
            
            if(input == JOptionPane.OK_OPTION) {
            	if (!tfnoTF.getText().isEmpty()) controlador.modificarCliente(Integer.parseInt(idTF.getText()), dniTF.getText(), 
            																 nombreTF.getText(), tfnoTF.getText());
    			else controlador.modificarCliente(Integer.parseInt(idTF.getText()), dniTF.getText(), 
						 						  nombreTF.getText(), null);
            	JOptionPane.showMessageDialog(null,"Cliente con ID " + idTF.getText() + " modificado con �xito.",
						  					  "INFO",JOptionPane.INFORMATION_MESSAGE);
            }
		} 
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limpiar() {
		idTF.setText("");
		dniTF.setText("");
		nombreTF.setText("");
		tfnoTF.setText("");
	}
}
