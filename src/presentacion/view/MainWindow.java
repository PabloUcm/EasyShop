package presentacion.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;


import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.view.clientes.ClienteModulo;
import presentacion.view.marcas.MarcaModulo;
import presentacion.view.menus.MainMenu;
import presentacion.view.personal.PersonalModulo;
import presentacion.view.productos.ProductoModulo;
import presentacion.view.ventas.VentaModulo;


public class MainWindow {
	
	private ClienteModulo clienteModulo;
	private PersonalModulo personalModulo;
	private VentaModulo ventaModulo;
	private ProductoModulo productoModulo;
	private MarcaModulo marcaModulo;
	
	public MainWindow() {
		clienteModulo =  new ClienteModulo();
		personalModulo = new PersonalModulo();
		ventaModulo = new VentaModulo();
		productoModulo = new ProductoModulo();
		marcaModulo = new MarcaModulo();
		
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