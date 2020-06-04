package presentacion.view.marcas;

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
import integracion.transfers.TMarca;
import negocio.MarcaObserver;
import presentacion.controllers.MarcaController;
import presentacion.view.SwingFactory;

public class BajaMarca {
	
	private MarcaController controlador;
	
	private JTextField idTF;
	private JButton baja;
	private ImageIcon bajaIcon;
	
	public BajaMarca(MarcaController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
	   
	    idTF = SwingFactory.getJTextField(new Dimension(300,50), 45);
	   
	    baja = SwingFactory.getJButton(new Dimension(230,60), "DAR DE BAJA A LA MARCA", 
									   "icons/baja", 50, new Color(255,85,85), new Color(201,54,54));
	    baja.setAlignmentX(JButton.CENTER_ALIGNMENT);
	   
	    baja.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) { baja(); }
	    });
	    
	    bajaIcon = SwingFactory.getScaledIcon("icons/baja", 45);
	
	}
	
	public JPanel getDefaultLayout() {
		JPanel bajaMarca = new JPanel(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    c.gridx = 0;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    bajaMarca.add(SwingFactory.getJLabel(new Dimension(300,50),"  ID MARCA:",50), c);
	    c.gridx = 1;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    bajaMarca.add(idTF, c);
	    c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    bajaMarca.add(Box.createRigidArea(new Dimension(0, 15)), c);
	    c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.anchor = GridBagConstraints.CENTER;
	    bajaMarca.add(baja, c);
	    
	    return bajaMarca;
	}
	
	private void baja() {
		try {
    		if (idTF.getText().isEmpty()) throw new Exception("Campo sin rellenar.");
			
			TMarca marca = controlador.getMarca(Integer.parseInt(idTF.getText()));
    				    		
    		String msg = "ID: "+marca.getId()+"\nCIF: "+marca.getCIF()+"\nNOMBRE: "+ marca.getNombre() +
    				      "\nPAIS: "+marca.getPais()+"\n\n ¿Quieres dar de baja esta marca?";
            int input = JOptionPane.showConfirmDialog(null, msg,"Confirmar baja de marca", 
            		    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, bajaIcon);
            
            if(input == JOptionPane.OK_OPTION) {
            	controlador.bajaMarca(Integer.parseInt(idTF.getText()));
            	
            	JOptionPane.showMessageDialog(null,"Marca con ID " + idTF.getText() + " dado de baja con exito.",
						  					  "INFO",JOptionPane.INFORMATION_MESSAGE);
            }
		} 
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}