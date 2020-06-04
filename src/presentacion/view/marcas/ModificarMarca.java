package presentacion.view.marcas;

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

import integracion.transfers.TMarca;
import negocio.MarcaObserver;
import presentacion.controllers.MarcaController;
import presentacion.view.SwingFactory;


public class ModificarMarca {
	
	private MarcaController controlador;
	
	private JTextField idTF;
	private JTextField cifTF;
	private JTextField nombreTF;
	private JTextField paisTF;
	private JButton modificar;
	private JButton limpiar;
	private ImageIcon modIcon;
	
	public ModificarMarca(MarcaController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
		idTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		cifTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		nombreTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		paisTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		
		modificar = SwingFactory.getJButton(new Dimension(230,60), "MODIFICAR MARCA", 
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
		campos.add(SwingFactory.getJLabel(new Dimension(230,50), "       ID MARCA:" ,30), c);
		c.gridx = 1;
		c.gridy = 0;
		campos.add(idTF, c);
		c.gridx = 0;
		c.gridy = 1;
		campos.add(SwingFactory.getJLabel(new Dimension(230,50), "                   CIF:" ,30), c);
		c.gridx = 1;
		c.gridy = 1;
		campos.add(cifTF, c);
		c.gridx = 0;
		c.gridy = 2;
		campos.add(SwingFactory.getJLabel(new Dimension(230,50), "         NOMBRE:" ,30), c);
		c.gridx = 1;
		c.gridy = 2;
		campos.add(nombreTF, c);
		c.gridx = 0;
		c.gridy = 3;
		campos.add(SwingFactory.getJLabel(new Dimension(230,50), "                 PAIS:" ,30), c);
		c.gridx = 1;
		c.gridy = 3;
		campos.add(paisTF, c);
		
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
    		if (idTF.getText().isEmpty() || cifTF.getText().isEmpty() || nombreTF.getText().isEmpty()) {
    			throw new Exception("Campo(s) sin rellenar.");
    		}
			
			TMarca marca = controlador.getMarca(Integer.parseInt(idTF.getText()));
			
			String pais;
			if (marca.getPais() == null) pais = "[Vacio]";
			else pais = marca.getPais();
			
			String paisNuevo; 
			if (paisTF.getText().isEmpty()) paisNuevo = "[Vacio]";
			else paisNuevo = paisTF.getText();
    				    		
    		String msg = "ID: "+marca.getId()+"\n\nCIF: "+marca.getCIF()+"\nNUEVO CIF: "+cifTF.getText()+"\n\nNOMBRE: "
    					  + marca.getNombre() +"\nNUEVO NOMBRE: " + nombreTF.getText() + "\n\nPAIS: "+pais
    					  +"\nPAIS NUEVO: "+ paisNuevo +"\n\n ¿Quieres cambiar los datos de esta marca?";
            int input = JOptionPane.showConfirmDialog(null, msg,"Confirmar cambios en la marca", 
            		    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, modIcon);
            
            if(input == JOptionPane.OK_OPTION) {
            	if (!paisTF.getText().isEmpty()) controlador.modificarMarca(Integer.parseInt(idTF.getText()), cifTF.getText(), 
            																 nombreTF.getText(), paisTF.getText());
    			else controlador.modificarMarca(Integer.parseInt(idTF.getText()), cifTF.getText(), 
						 						  nombreTF.getText(), null);
            	JOptionPane.showMessageDialog(null,"Marca con ID " + idTF.getText() + " modificada con exito.",
						  					  "INFO",JOptionPane.INFORMATION_MESSAGE);
            }
		} 
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limpiar() {
		idTF.setText(" ");
		cifTF.setText(" ");
		nombreTF.setText(" ");
		paisTF.setText(" ");
	}
}
