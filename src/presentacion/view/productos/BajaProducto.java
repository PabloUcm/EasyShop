package presentacion.view.productos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentacion.controllers.ProductoController;
import presentacion.view.SwingFactory;

public class BajaProducto {
	ProductoController controlador;
	
	private JTextField idTF;
	private JButton baja;
	private ImageIcon bajaIcon;
	
	public BajaProducto(ProductoController c) {
      this.controlador = c;
	  initGUI();
	}
	
	private void initGUI() {
	   
	    idTF = SwingFactory.getJTextField(new Dimension(300,50), 45);
	    
	    baja = SwingFactory.getJButton(new Dimension(230,60), "DAR DE BAJA AL PRODUCTO", 
				"icons/baja", 50, new Color(255,85,85), new Color(201,54,54));
	    baja.setAlignmentX(JButton.CENTER_ALIGNMENT);
	    
	    baja.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) { baja(); }
	    });
	    
	    bajaIcon = SwingFactory.getScaledIcon("icons/baja", 45);
	    
	}
	
	public JPanel getDefaultLayout() {
		JPanel bajaProductoPanel = new JPanel(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    c.gridx = 0;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    bajaProductoPanel.add(SwingFactory.getJLabel(new Dimension(300,50),"ID PRODUCTO:",50), c);
	    c.gridx = 1;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    bajaProductoPanel.add(idTF, c);
	    c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    bajaProductoPanel.add(Box.createRigidArea(new Dimension(0, 15)), c);
	    c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.anchor = GridBagConstraints.CENTER;
	    bajaProductoPanel.add(baja, c);
	    
	    return bajaProductoPanel;
	}
	
	private void baja() {
		
	}
}
