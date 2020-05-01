package presentacion.view;

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

public class ModuloMenu extends JPanel {
	public ModuloMenu(CardSwitcher switcher, int iconSize) {
		this.switcher = switcher;
		this.iconSize = iconSize;
		buttons = new ArrayList<>();
		botonAct = -1;
		initGUI();
	}
	
	private CardSwitcher switcher;
	private ArrayList<JButton> buttons;
	private int iconSize;
	private int botonAct;
	
	private void initGUI() {
		setLayout( new FlowLayout( FlowLayout.LEFT ));
		setBorder( BorderFactory.createBevelBorder(1));
		setBackground(Color.gray.brighter());
	}
	
	public void addButton(JButton button, String direccion, String card) {
		add(button);
		buttons.add(button);
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		if (buttons.size() == 1) botonActivado(button);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(170,45));
		button.setMaximumSize(button.getPreferredSize());
		
		ImageIcon imageIcon = new ImageIcon("icons/"+direccion+".png"); 
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(iconSize, iconSize, java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg);  
		button.setIcon(imageIcon);
		
		button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		botonActivado(button);
	    		switcher.switchTo(card);
	    	}
	    });
	}
	
	public void reset() {
		switcher.reset();
		botonActivado(buttons.get(0));
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