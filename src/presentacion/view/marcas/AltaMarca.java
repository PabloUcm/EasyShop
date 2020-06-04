package presentacion.view.marcas;

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

import presentacion.controllers.MarcaController;
import presentacion.view.SwingFactory;

public class AltaMarca{
	
	private MarcaController controlador;
	
	public AltaMarca(MarcaController c) {
		this.controlador = c;
		initGUI();
	}
	
	private JTextField cifTF;
	private JTextField nombreTF;
	private JTextField paisTF;
	private JButton confirmar;
	private JButton limpiar;
	
	private void initGUI() {
		cifTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		nombreTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		paisTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		
		confirmar = SwingFactory.getJButton(new Dimension(230,60), "CONFIRMAR ALTA DE MARCA", 
										    "icons/confirmar", 50, Color.GREEN, new Color(130,200,21));
		limpiar = SwingFactory.getJButton(new Dimension(230,60), "LIMPIAR CAMPOS DE TEXTO", 
				  						  "icons/limpiar", 50, new Color(205,205,205), new Color(166,166,166));
		
		confirmar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { alta();}
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { limpiar(); }
	    });
	}
	
	public JPanel getDefaultLayout() {
		JPanel altaMarcaPanel = new JPanel(new BorderLayout());
		
		JPanel campos = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "                CIF:" ,30), c);
		c.gridx = 1;
		c.gridy = 0;
		campos.add(cifTF, c);
		c.gridx = 0;
		c.gridy = 1;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "      NOMBRE:" ,30), c);
		c.gridx = 1;
		c.gridy = 1;
		campos.add(nombreTF, c);
		c.gridx = 0;
		c.gridy = 2;
		campos.add(SwingFactory.getJLabel(new Dimension(200,50), "              PAIS:" ,30), c);
		c.gridx = 1;
		c.gridy = 2;
		campos.add(paisTF, c);
		c.gridx = 0;
		c.gridy = 3;
		
		JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		botones.setBorder(BorderFactory.createMatteBorder(
                  1, 0, 0, 0, Color.black));
		
		botones.add(confirmar);
		botones.add(limpiar);
		
		altaMarcaPanel.add(campos,BorderLayout.CENTER);
		altaMarcaPanel.add(botones, BorderLayout.SOUTH);
		
		return altaMarcaPanel;
	}
	
	private void alta() {
		try {
			controlador.altaMarca(cifTF.getText(), nombreTF.getText(), paisTF.getText());
			JOptionPane.showMessageDialog(null, "Marca " + nombreTF.getText() + " registrada con exito",
										  "Error icon", JOptionPane.INFORMATION_MESSAGE);
			
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void limpiar() {
		cifTF.setText(" ");
		nombreTF.setText(" ");
		paisTF.setText(" ");
	}
}