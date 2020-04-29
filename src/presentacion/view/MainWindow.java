package presentacion.view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainWindow extends JFrame {
	
	
	public MainWindow() {
		super("EasyShop");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		mainPanel.setPreferredSize((new Dimension(1350,800)));
		
		CardLayout cardLayout = new CardLayout();
		CenterPanel centerPanel = new CenterPanel(cardLayout);
		CardSwitcher switcher = new CardSwitcher(centerPanel, cardLayout);
		MenuPanel menuPanel = new MenuPanel(switcher);
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(menuPanel, BorderLayout.LINE_START);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}