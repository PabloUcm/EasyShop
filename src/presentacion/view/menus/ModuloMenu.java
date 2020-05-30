package presentacion.view.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.view.CardSwitcher;

public class ModuloMenu {
	
	public ModuloMenu(CardSwitcher switcher, int iconSize) {
		this.switcher = switcher;
		this.iconSize = iconSize;
		buttons = new ArrayList<>();
		botonAct = -1;
	}
	
	private CardSwitcher switcher;
	private ArrayList<JButton> buttons;
	private int iconSize;
	private int botonAct;
	
	
	public JPanel getDefaultLayout() {
		JPanel moduloMenuPanel = new JPanel(new FlowLayout( FlowLayout.LEFT ));
		
		moduloMenuPanel.setBorder( BorderFactory.createBevelBorder(1));
		moduloMenuPanel.setBackground(Color.gray.brighter());
		
		for (JButton button : buttons) moduloMenuPanel.add(button);
		
		return moduloMenuPanel;
	}
	
	public void addButton(JButton button, String direccion, String card) {
		buttons.add(button);
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		if (buttons.size() == 1) botonActivado(button);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(170,45));
		button.setMaximumSize(button.getPreferredSize());
		
		ImageIcon icon = new ImageIcon("icons/"+direccion+".png"); 
		Image image = icon.getImage(); 
		Image newimg = image.getScaledInstance(iconSize, iconSize, java.awt.Image.SCALE_SMOOTH); 
		icon = new ImageIcon(newimg);  
		button.setIcon(icon);
		
		button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		botonActivado(button);
	    		switcher.switchTo(card);
	    	}
	    });
	}

	private void botonActivado(JButton buttonActivado) {
		if (botonAct != -1) {
			JButton desactivarBoton = buttons.get(botonAct);
			desactivarBoton.setBorder(BorderFactory.createRaisedBevelBorder());
		}
		
		buttonActivado.setBorder(BorderFactory.createBevelBorder(1));
		
		botonAct = buttons.indexOf(buttonActivado);
	}
}