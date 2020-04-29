package view;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class ModuloPanel extends JPanel{
	public ModuloPanel(CardLayout cardLayout) {
		setLayout(cardLayout);
	}
	
	public void addPanel(JPanel panel, String card) {
		add(panel,card);
	}
}