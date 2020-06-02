package presentacion.view.clientes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import integracion.transfers.TCliente;
import negocio.ClienteObserver;
import presentacion.controllers.ClienteController;

public class BajaCliente{
	
	ClienteController controlador;
	
	public BajaCliente(ClienteController c) {
      this.controlador = c;
	  initGUI();
	}
	
	private JLabel idJL;
	private JTextField idTF;
	private JButton baja;
	private ImageIcon bajaIcon;
	
	private void initGUI() {
	    idJL = new JLabel("ID CLIENTE:");
	    idJL.setFont(new Font(idJL.getFont().toString(), Font.BOLD, 50));
	    idJL.setPreferredSize(new Dimension(300,50));
	    idJL.setMaximumSize(idJL.getPreferredSize());
	   
	    idTF = new JTextField();
	    idTF.setFont(new Font(idTF.getFont().toString(), Font.PLAIN, 45));
	    idTF.setPreferredSize(new Dimension(300,50));
	    idTF.setMaximumSize(idTF.getPreferredSize());
	   
	    baja = new JButton("DAR DE BAJA AL CLIENTE");
	    baja.setContentAreaFilled(false);
	    baja.setFocusPainted(false);
	    baja.setBorder(BorderFactory.createRaisedBevelBorder());
	    baja.setOpaque(true);
	    baja.setPreferredSize(new Dimension(230,65));
	    baja.setMaximumSize(baja.getPreferredSize());
	    baja.setBackground(new Color(255,85,85));
	    baja.setAlignmentX(JButton.CENTER_ALIGNMENT);
	   
	    bajaIcon= new ImageIcon("icons/baja.png"); 
	    Image image = bajaIcon.getImage(); 
	    Image newimg = image.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH); 
	    bajaIcon = new ImageIcon(newimg);  
	    baja.setIcon(bajaIcon);
	    

	    baja.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) { baja(); }
	    });
	    
	    baja.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	baja.setBackground(new Color(201,54,54));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	baja.setBackground(new Color(255,85,85));
		    }
		    
		    public void mousePressed(MouseEvent evt) {
		    	baja.setBorder(BorderFactory.createBevelBorder(1));
		    }
		    
		    public void mouseReleased(MouseEvent evt) {
		    	baja.setBorder(BorderFactory.createRaisedBevelBorder());
		    }
		});
	}
	
	public JPanel getDefaultLayout() {
		JPanel bajaClientePanel = new JPanel(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    c.gridx = 0;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    bajaClientePanel.add(idJL, c);
	    c.gridx = 1;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    bajaClientePanel.add(idTF, c);
	    c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    bajaClientePanel.add(Box.createRigidArea(new Dimension(0, 15)), c);
	    c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.anchor = GridBagConstraints.CENTER;
	    bajaClientePanel.add(baja, c);
	    
	    return bajaClientePanel;
	}
	
	private void baja() {
		try {
    		if (idTF.getText().isEmpty()) throw new Exception("Campo sin rellenar.");
			
			TCliente cliente = controlador.getCliente(Integer.parseInt(idTF.getText()));
    				    		
    		String msg = "ID: "+cliente.getId()+"\nDNI: "+cliente.getDni()+"\nNOMBRE: "+ cliente.getNombre() +
    				      "\nTELEFONO: "+cliente.getTelefono()+"\n\n �Quieres dar de baja a este cliente?";
            int input = JOptionPane.showConfirmDialog(null, msg,"Confirmar baja de cliente", 
            		    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, bajaIcon);
            
            if(input == JOptionPane.OK_OPTION) {
            	controlador.bajaCliente(Integer.parseInt(idTF.getText()));
            	
            	JOptionPane.showMessageDialog(null,"Cliente con ID " + idTF.getText() + " dado de baja con exito.",
						  					  "INFO",JOptionPane.INFORMATION_MESSAGE);
            }
		} 
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
}

