package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;



public class MenuPanel extends JPanel {
   
    //Constructor
	MenuPanel(CardSwitcher switcher) {
		this.switcher = switcher;
		initGUI();
	}
	
	//Atributos
	private CardSwitcher switcher;
	private JButton clientes;
	private JButton personal;
	private JButton productos;
	private JButton ventas;
	private JButton marcas;
	
	
	//Metodos
	private void initGUI() {
		setLayout(new BoxLayout( this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createMatteBorder(
                  0, 0, 0, 1, Color.black));
		
		setPreferredSize((new Dimension(260,800)));
		setOpaque(true);
		setBackground(Color.CYAN.darker());
		
		clientes = crearBoton("        Gestion de clientes", "cliente", 50, "Clientes");
		personal = crearBoton("     Gestion de personal", "empleado", 65, "Personal");
		ventas = crearBoton("          Gestion de ventas", "venta", 45, "Ventas");
		productos = crearBoton("      Gestion de productos", "producto", 45, "Productos");
		marcas = crearBoton("        Gestion de marcas", "marca", 45, "Marcas");
		
		clientes.setBackground(Color.green);
		
		
		ImageIcon imageIcon = new ImageIcon("icons/logo.png"); 
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(250, 120,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg); 
		JLabel picLabel = new JLabel(imageIcon);
		
		
		add(picLabel);
		add(Box.createRigidArea(new Dimension(8, 0)));
		add(clientes);
		add(Box.createRigidArea(new Dimension(0, 8)));
		add(personal);
		add(Box.createRigidArea(new Dimension(0, 8)));
		add(ventas);
		add(Box.createRigidArea(new Dimension(0, 8)));
		add(productos);
		add(Box.createRigidArea(new Dimension(0, 8)));
		add(marcas);
		
	}
	
	private JButton crearBoton(String nombre, String iconDir, int iconSize, String card) {
		JButton button = new JButton(nombre);
		
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBackground(Color.GREEN.darker());
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(252,100));
		button.setMaximumSize(button.getPreferredSize());
		
		ImageIcon imageIcon = new ImageIcon("icons/"+iconDir+".png"); 
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(iconSize, iconSize,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg);  
		button.setIcon(imageIcon);
		
		button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { 
	    		botonActivado(button); 
	    		switcher.switchTo(card);
	    	}
	    });
		
		return button;
	}
	
	private void botonActivado(JButton button) {
		clientes.setBackground(Color.GREEN.darker());
		personal.setBackground(Color.GREEN.darker());
		ventas.setBackground(Color.GREEN.darker());
		productos.setBackground(Color.GREEN.darker());
		marcas.setBackground(Color.GREEN.darker());
		
		button.setBackground(Color.green.brighter());
	}
}
