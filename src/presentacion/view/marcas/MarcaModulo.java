package presentacion.view.marcas;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import negocio.Modelo;
import presentacion.controllers.MarcaController;
import presentacion.view.CardSwitcher;
import presentacion.view.menus.ModuloMenu;

public class MarcaModulo {
	
	private MarcaController controlador;
	
	private AltaMarca altaMarca;
	private BajaMarca bajaMarca;
	//private ModificarMarca modMarca;
	//private MostrarMarca mostrarMarca;
	//private ListarMarca listarMarca;
	
	public MarcaModulo() {
		controlador = new MarcaController(Modelo.getModelo());
		
		altaMarca = new AltaMarca(controlador);
		bajaMarca = new BajaMarca(controlador);
		//modMarca = new ModificarMarca(controlador);
		//mostrarMarca = new MostrarMarca(controlador);
		//listarMarca = new ListarMarca(controlador);
		
		initGUI();
	}
	private ModuloMenu marcasMenu;
	private JPanel marcasFuncion;
	
	private void initGUI() {
		CardLayout cardLayout = new CardLayout();
		marcasFuncion = new JPanel(cardLayout);
		CardSwitcher switcher = new CardSwitcher(marcasFuncion, cardLayout);
		marcasMenu = new ModuloMenu(switcher, 45);
		
		addFuncion(altaMarca.getDefaultLayout(), new JButton("   Alta marca"), 
				   "altamarca", "AltaMarca");
		addFuncion(bajaMarca.getDefaultLayout(), new JButton("   Baja marca"), 
				   "bajamarca", "BajaMarca");
		//addFuncion(modMarca.getDefaultLayout(), new JButton("Modificar marca"), "modmarca", "ModificarMarca");
		//addFuncion(mostrarMarca.getDefaultLayout(), new JButton("  Mostrar marca"), "mostrarmarca", "MostrarMarca");
		//addFuncion(listarMarca.getDefaultLayout(), new JButton("   Listar marca"), "listarmarca", "ListarMarcas");
	}
	
	public JPanel getDefaultLayout() {
		JPanel marcaPanel = new JPanel(new BorderLayout());
		
		marcaPanel.add(marcasFuncion, BorderLayout.CENTER);
		marcaPanel.add(marcasMenu.getDefaultLayout(), BorderLayout.NORTH);
		
		return marcaPanel;
	}
	
	private void addFuncion(JPanel panel, JButton button, String iconName, String card) {
		marcasFuncion.add(panel, card);
		marcasMenu.addButton(button, "Marcas/"+iconName, card);
	}
}