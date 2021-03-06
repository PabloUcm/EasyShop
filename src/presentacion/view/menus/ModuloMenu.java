package presentacion.view.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import presentacion.view.CardSwitcher;

public class ModuloMenu {
	
	private CardSwitcher switcher;
	private ArrayList<JButton> buttons;
	private int iconSize;
	private int botonAct;
	private String currentCard;
	
	public ModuloMenu(CardSwitcher switcher, int iconSize) {
		this.switcher = switcher;
		this.iconSize = iconSize;
		buttons = new ArrayList<>();
		botonAct = -1;
	}
	
	public JPanel getDefaultLayout() {
		JPanel moduloMenuPanel = new JPanel(new FlowLayout( FlowLayout.LEFT ));
		
		moduloMenuPanel.setBorder( BorderFactory.createBevelBorder(1));
		moduloMenuPanel.setBackground(Color.gray.brighter());
		
		for (JButton button : buttons) moduloMenuPanel.add(button);
		
		return moduloMenuPanel;
	}
	
	
	public JToolBar getToolBar() {
		JToolBar toolbar = new JToolBar();
		
		toolbar.setFloatable(false);
		toolbar.setBackground(Color.gray.brighter());
		toolbar.setBorder( BorderFactory.createBevelBorder(1));
		
		toolbar.add(Box.createRigidArea(new Dimension(0, 55)));
		
		for (JButton button : buttons) { 
			toolbar.add(Box.createRigidArea(new Dimension(5, 0)));
			toolbar.add(button);
		}
		
		return toolbar;
	}
	
	public void addButton(JButton button, String iconDir, String card) {
		buttons.add(button);
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		
		if (buttons.size() == 1) {
			activarBoton(button);
			switcher.switchTo(card);
			currentCard = card;
		}
		
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(170,45));
		button.setMaximumSize(button.getPreferredSize());
		button.setMinimumSize(new Dimension(100,35));
		
		ImageIcon icon = new ImageIcon("icons/"+iconDir+".png"); 
		Image image = icon.getImage(); 
		Image newimg = image.getScaledInstance(iconSize, iconSize, java.awt.Image.SCALE_SMOOTH); 
		icon = new ImageIcon(newimg);  
		button.setIcon(icon);
		
		button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		activarBoton(button);
	    		switcher.switchTo(card);
	    		currentCard = card;
	    	}
	    });
	}

	private void activarBoton(JButton button) {
		if (botonAct != -1) {
			JButton desactivarBoton = buttons.get(botonAct);
			desactivarBoton.setBorder(BorderFactory.createRaisedBevelBorder());
		}
		
		button.setBorder(BorderFactory.createBevelBorder(1));
		
		botonAct = buttons.indexOf(button);
	}
	
	public void currentCard() {
		switcher.reset();
		switcher.switchTo(currentCard);
	}
}