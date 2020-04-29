package presentacion.view.marcas;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.view.CardSwitcher;
import presentacion.view.ModuloMenu;
import presentacion.view.ModuloPanel;
import presentacion.view.clientes.AltaCliente;
import presentacion.view.clientes.BajaCliente;
import presentacion.view.personal.AltaEmpleado;

public class MarcasPanel extends JPanel {
	public MarcasPanel() {
		initGUI();
	}
	private ModuloMenu marcasMenu;
	private ModuloPanel marcasPanel;
	
	private void initGUI() {
		setLayout(new BorderLayout());
		
		CardLayout cardLayout = new CardLayout();
		marcasPanel = new ModuloPanel(cardLayout);
		CardSwitcher switcher = new CardSwitcher(marcasPanel, cardLayout);
		marcasMenu = new ModuloMenu(switcher, 45);
		
		addFuncion(new AltaMarca(), new JButton("   Alta marca"), "altamarca", "AltaMarca");
		addFuncion(new BajaMarca(), new JButton("   Baja marca"), "bajamarca", "BajaMarca");
		addFuncion(new AltaEmpleado(), new JButton("Modificar marca"), "modmarca", "ModificarMarca");
		addFuncion(new AltaMarca(), new JButton("  Mostrar marca"), "mostrarmarca", "MostrarMarca");
		addFuncion(new AltaMarca(), new JButton("   Listar marca"), "listarmarca", "ListarMarcas");
		
		add(marcasPanel, BorderLayout.CENTER);
		add(marcasMenu, BorderLayout.NORTH);
	}
	
	private void addFuncion(JPanel panel, JButton button, String iconName, String card) {
		marcasPanel.addPanel(panel, card);
		marcasMenu.addButton(button, "Marcas/"+iconName, card);
	}
}