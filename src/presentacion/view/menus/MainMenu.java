package presentacion.view.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



public class MainMenu {
   
    //Constructor
	public MainMenu(CardSwitcher switcher) {
		this.switcher = switcher;
		this.buttons = new ArrayList<>();
		this.buttonListeners = new HashMap<>();
		botonAct = -1;
	}
	
	//Atributos
	private CardSwitcher switcher;
	private List<JButton> buttons;
	private Map<JButton,MouseListener> buttonListeners;
	int botonAct;
	
	//Metodos

	public JPanel getDefaultLayout() {
		JPanel mainMenuPanel = new JPanel();
		
		mainMenuPanel.setLayout(new BoxLayout( mainMenuPanel, BoxLayout.PAGE_AXIS));
		mainMenuPanel.setBorder(BorderFactory.createMatteBorder(
                  0, 0, 0, 1, Color.black));
		
		mainMenuPanel.setPreferredSize((new Dimension(260,800)));
		mainMenuPanel.setOpaque(true);
		mainMenuPanel.setBackground(Color.CYAN.darker());
		
		ImageIcon logo = new ImageIcon("icons/logo.png"); 
		Image image = logo.getImage();
		Image newimg = image.getScaledInstance(250, 120,  java.awt.Image.SCALE_SMOOTH); 
		logo = new ImageIcon(newimg); 
		JLabel picLabel = new JLabel(logo);
		
		mainMenuPanel.add(picLabel);
		mainMenuPanel.add(Box.createRigidArea(new Dimension(8, 0)));
		
		for (JButton button : buttons) {
			mainMenuPanel.add(button);
			mainMenuPanel.add(Box.createRigidArea(new Dimension(0, 8)));
		}
		
		return mainMenuPanel;
	}
	
	public void addBoton(JButton button, String iconName, int iconSize, String card) {
		buttons.add(button);
		
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
		
		crearMLNoActivo(button);
		
		button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { 
	    		botonActivado(button); 
	    		switcher.switchTo(card);
	    	}
	    });
		
		if (buttons.size() == 1) botonActivado(button);
	}
	
	private void crearMLNoActivo(JButton button) {
		MouseListener ml = new MouseAdapter() {
			 public void mouseEntered(MouseEvent evt) {
			    	button.setBackground(new Color(54,220,110));
			    }

			    public void mouseExited(MouseEvent evt) {
			    	button.setBackground(Color.GREEN.darker());
			    }
		};
		
		button.addMouseListener(ml);
		buttonListeners.put(button, ml);
	}
	
	private void botonActivado(JButton buttonActivado) {
		if(botonAct != -1) {
			JButton desactivarBoton = buttons.get(botonAct);
			crearMLNoActivo(desactivarBoton);
			desactivarBoton.setBackground(Color.GREEN.darker());
		}
		
		buttonActivado.removeMouseListener(buttonListeners.get(buttonActivado));
		buttonListeners.remove(buttonActivado);
		buttonActivado.setBackground(Color.green.brighter());
		
		botonAct = buttons.indexOf(buttonActivado);
	}
}
