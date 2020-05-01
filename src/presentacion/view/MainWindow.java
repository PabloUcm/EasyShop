package presentacion.view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import presentacion.view.clientes.ClientesPanel;
import presentacion.view.marcas.MarcasPanel;
import presentacion.view.personal.PersonalPanel;
import presentacion.view.productos.ProductosPanel;
import presentacion.view.ventas.VentasPanel;


public class MainWindow extends JFrame {
	private JPanel centerPanel;
	private MenuPanel menuPanel;
	
	public MainWindow() {
		super("EasyShop");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		mainPanel.setPreferredSize((new Dimension(1350,800)));
		
		CardLayout cardLayout = new CardLayout();
		
		centerPanel = new JPanel(cardLayout);
		centerPanel.setPreferredSize((new Dimension(740,800)));
		
		CardSwitcher switcher = new CardSwitcher(centerPanel, cardLayout);
		
		menuPanel = new MenuPanel(switcher);
		
		addModulo(new ClientesPanel(),new JButton("Gestion de clientes"), "cliente", 45, "Clientes");
		addModulo(new PersonalPanel(),new JButton("Gestion de personal"), "empleado", 45, "Personal");
		addModulo(new VentasPanel(),new JButton("Gestion de ventas"), "venta", 45, "Ventas");
		addModulo(new ProductosPanel(),new JButton("Gestion de productos"), "producto", 45, "Productos");
		addModulo(new MarcasPanel(),new JButton("Gestion de marcas"), "marca", 45, "Marcas");
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(menuPanel, BorderLayout.LINE_START);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void addModulo(JPanel panel, JButton button, String iconName, int iconSize, String card) {
		centerPanel.add(panel, card);
		menuPanel.addBoton(button,iconName,iconSize,card);
	}
}