package presentacion.view.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import presentacion.view.CardSwitcher;



public class MainMenu extends JPanel {
   
    //Constructor
	public MainMenu(CardSwitcher switcher) {
		this.switcher = switcher;
		this.buttons = new ArrayList<>();
		botonAct = -1;
		initGUI();
	}
	
	//Atributos
	private CardSwitcher switcher;
	private List<JButton> buttons;
	private MouseListener mlActivo;
	private MouseListener mlNoActivo;
	int botonAct;
	
	//Metodos
	private void initGUI() {
		setLayout(new BoxLayout( this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createMatteBorder(
                  0, 0, 0, 1, Color.black));
		
		setPreferredSize((new Dimension(260,800)));
		setOpaque(true);
		setBackground(Color.CYAN.darker());
		
		ImageIcon imageIcon = new ImageIcon("icons/logo.png"); 
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(250, 120,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg); 
		JLabel picLabel = new JLabel(imageIcon);
		
		add(picLabel);
		add(Box.createRigidArea(new Dimension(8, 0)));
	}
	
	public void addBoton(JButton button, String iconName, int iconSize, String card) {
		buttons.add(button);
		add(button);
		add(Box.createRigidArea(new Dimension(0, 8)));
		
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBackground(Color.GREEN.darker());
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		button.setOpaque(true);
		
		button.setPreferredSize(new Dimension(252,100));
		button.setMaximumSize(button.getPreferredSize());
		
		ImageIcon imageIcon = new ImageIcon("icons/"+iconName+".png"); 
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(iconSize, iconSize,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg);  
		button.setIcon(imageIcon);
		
		mlNoActivo = crearMLNoActivo(button);
		button.addMouseListener(mlNoActivo);
		
		button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { 
	    		botonActivado(button); 
	    		switcher.switchTo(card);
	    	}
	    });
		
		if (buttons.size() == 1) botonActivado(button);
	}
	
	private MouseListener crearMLActivo(JButton button) {
		return new MouseAdapter() {
			 public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	button.setBackground(Color.green.brighter());
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	button.setBackground(Color.green.brighter());
			    }
		};
	}
	
	private MouseListener crearMLNoActivo(JButton button) {
		return new MouseAdapter() {
			 public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	button.setBackground(new Color(54,220,110));
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	button.setBackground(Color.GREEN.darker());
			    }
		};
	}
	
	private void botonActivado(JButton buttonActivado) {
		if(botonAct != -1) {
			JButton desactivarBoton = buttons.get(botonAct);
			desactivarBoton.removeMouseListener(mlActivo);
			
			mlNoActivo = crearMLNoActivo(desactivarBoton);
			desactivarBoton.addMouseListener(mlNoActivo);
			desactivarBoton.setBackground(Color.GREEN.darker());
		}
		
		buttonActivado.removeMouseListener(mlNoActivo);
		mlActivo = crearMLActivo(buttonActivado);
		buttonActivado.addMouseListener(mlActivo);
		buttonActivado.setBackground(Color.green.brighter());
		
		botonAct = buttons.indexOf(buttonActivado);
	}
}
