package presentacion.view.ventas;

import javax.swing.JButton;
import javax.swing.JPanel;

import negocio.Modelo;
import presentacion.controllers.VentaController;
import presentacion.view.CardSwitcher;
import presentacion.view.marcas.AltaMarca;
import presentacion.view.menus.ModuloMenu;

import java.awt.BorderLayout;
import java.awt.CardLayout;

public class VentaModulo {
	
	private VentaController controlador;
	
	//private AbrirVenta abrirVenta;
	//private MostrarVenta mostrarVenta;
	//private ListarVenta listarVenta;
	
	public VentaModulo() {
		this.controlador = new VentaController(Modelo.getModelo());
		
		//abrirVenta = new AbrirVenta(controlador);
		//mostrarVenta = new MostrarVenta(controlador);
		//listarVenta = new ListarVenta(controlador);
		
		initGUI();
	}
	
	private ModuloMenu ventasMenu;
	private JPanel ventasPanel;
	
	private void initGUI() {
		CardLayout cardLayout = new CardLayout();
		ventasPanel = new JPanel(cardLayout);
		CardSwitcher switcher = new CardSwitcher(ventasPanel, cardLayout);
		ventasMenu = new ModuloMenu(switcher, 30);
		
		//addFuncion(abrirVenta.getDefaultLayout(), new JButton("   Abrir venta"), "abrirventa", "AbrirVenta");
		//addFuncion(mostrarVenta.getDefaultLayout(), new JButton("   Mostrar venta"), "mostrarventa", "MostrarVenta");
		//addFuncion(listarVenta.getDefaultLayout(), new JButton("   Listar venta"), "listarventa", "ListarVentas");
	}
	
	public JPanel getDefaultLayout() {
		JPanel ventaPanel = new JPanel(new BorderLayout());
		
		ventaPanel.add(ventasPanel, BorderLayout.CENTER);
		ventaPanel.add(ventasMenu.getDefaultLayout(), BorderLayout.NORTH);
		
		return ventaPanel;
	}
	
	private void addFuncion(JPanel panel, JButton button, String iconName, String card) {
		ventasPanel.add(panel, card);
		ventasMenu.addButton(button, "Ventas/"+iconName, card);
	}
}