package presentacion.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;


import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.view.clientes.VistaCliente;
import presentacion.view.marcas.VistaMarca;
import presentacion.view.menus.MainMenu;
import presentacion.view.personal.VistaPersonal;
import presentacion.view.productos.VistaProducto;
import presentacion.view.ventas.VistaVenta;


public class MainWindow {
	
	private VistaCliente clienteModulo;
	private VistaPersonal personalModulo;
	private VistaVenta ventaModulo;
	private VistaProducto productoModulo;
	private VistaMarca marcaModulo;
	
	public MainWindow() {
		clienteModulo =  new VistaCliente();
		personalModulo = new VistaPersonal();
		ventaModulo = new VistaVenta();
		productoModulo = new VistaProducto();
		marcaModulo = new VistaMarca();
		
		initGUI();
	}
	
	private JPanel centerPanel;
	private MainMenu menuPanel;
	
	private void initGUI() {
		CardLayout cardLayout = new CardLayout();
		
		centerPanel = new JPanel(cardLayout);
		centerPanel.setPreferredSize((new Dimension(740,800)));
		
		CardSwitcher switcher = new CardSwitcher(centerPanel, cardLayout);
		
		menuPanel = new MainMenu(switcher);
		
		addModulo(clienteModulo.getDefaultLayout(), new JButton("    Gestion de clientes"), 
				  "cliente", 50, "Clientes");
		addModulo(personalModulo.getDefaultLayout(), new JButton("    Gestion de personal"), 
				  "empleado", 60, "Personal");
		addModulo(ventaModulo.getDefaultLayout(), new JButton("          Gestion de ventas"), 
				  "venta", 45, "Ventas");
		addModulo(productoModulo.getDefaultLayout(), new JButton("   Gestion de productos"), 
				  "producto", 45, "Productos");
		addModulo(marcaModulo.getDefaultLayout(), new JButton("       Gestion de marcas"), 
				  "marca", 45, "Marcas");
	}
	
	public JPanel getDefaultLayout() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize((new Dimension(1350,800)));
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(menuPanel.getDefaultLayout(), BorderLayout.LINE_START);
		
		return mainPanel;
	}
	
	private void addModulo(JPanel panel, JButton button, String iconName, int iconSize, String card) {
		centerPanel.add(panel, card);
		menuPanel.addBoton(button,iconName,iconSize,card);
	}
}