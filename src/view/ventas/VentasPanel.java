package view.ventas;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import view.CardSwitcher;
import view.ModuloMenu;
import view.ModuloPanel;
import view.clientes.AltaCliente;

public class VentasPanel extends JPanel {
	public VentasPanel() {
		initGUI();
	}
	private ModuloMenu ventasMenu;
	private ModuloPanel ventasPanel;
	
	private void initGUI() {
		setLayout(new BorderLayout());
		
		CardLayout cardLayout = new CardLayout();
		ventasPanel = new ModuloPanel(cardLayout);
		CardSwitcher switcher = new CardSwitcher(ventasPanel, cardLayout);
		ventasMenu = new ModuloMenu(switcher, 30);
		
		addFuncion(new AltaCliente(), new JButton("   Abrir venta"), "abrirventa", "AbrirVenta");
		addFuncion(new AltaCliente(), new JButton("   Mostrar venta"), "mostrarventa", "MostrarVenta");
		addFuncion(new ListarVentas(), new JButton("   Listar venta"), "listarventa", "ListarVentas");
		
		
		add(ventasPanel, BorderLayout.CENTER);
		add(ventasMenu, BorderLayout.NORTH);
		
	}
	
	private void addFuncion(JPanel panel, JButton button, String iconName, String card) {
		ventasPanel.addPanel(panel, card);
		ventasMenu.addButton(button, "Ventas/"+iconName, card);
	}
}