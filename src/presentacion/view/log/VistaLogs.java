package presentacion.view.log;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import negocio.Modelo;
import presentacion.controllers.ClienteController;
import presentacion.controllers.LogController;
import presentacion.view.CardSwitcher;
import presentacion.view.menus.ModuloMenu;

public class VistaLogs {
	
	private LogController controller;
	private ModuloMenu logsMenu;
	private JPanel logsFuncion;
	
	private MostrarLogs mostrarLogs;
	
	public VistaLogs() {
		this.controller = new LogController(Modelo.getModelo());
		mostrarLogs = new MostrarLogs(controller);
		initGUI();
	}
	
	private void initGUI() {
		CardLayout cardLayout = new CardLayout();
		logsFuncion = new JPanel(cardLayout);
		CardSwitcher switcher = new CardSwitcher(logsFuncion, cardLayout);
		logsMenu = new ModuloMenu(switcher, 35);
		
		addFunction(mostrarLogs.getDefaultLayout(), new JButton("   Lista Logs"), 
				   "x", "x");
	}
	
	public JPanel getDefaultLayout() {
		JPanel logPanel = new JPanel(new BorderLayout());
		
		logPanel.add(logsFuncion, BorderLayout.CENTER);
		logPanel.add(logsMenu.getDefaultLayout(), BorderLayout.NORTH);
		
		return logPanel;
	}
	
	private void addFunction(JPanel panel, JButton button, String iconName, String card) {
		logsFuncion.add(panel, card);
		logsMenu.addButton(button, "Logs/"+iconName, card);
	}
	
}
