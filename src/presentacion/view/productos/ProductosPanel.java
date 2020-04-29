package presentacion.view.productos;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.view.CardSwitcher;
import presentacion.view.ModuloMenu;
import presentacion.view.ModuloPanel;
import presentacion.view.clientes.AltaCliente;
import presentacion.view.personal.AltaEmpleado;
import presentacion.view.ventas.ListarVentas;

public class ProductosPanel extends JPanel {
	public ProductosPanel() {
		initGUI();
	}
	private ModuloMenu productosMenu;
	private ModuloPanel productosPanel;
	
	private void initGUI() {
		setLayout(new BorderLayout());
		
		CardLayout cardLayout = new CardLayout();
		productosPanel = new ModuloPanel(cardLayout);
		CardSwitcher switcher = new CardSwitcher(productosPanel, cardLayout);
		productosMenu = new ModuloMenu(switcher, 45);
		
		addFuncion(new AltaEmpleado(), new JButton("   Alta producto"), "altaproducto", "AltaProducto");
		addFuncion(new ListarVentas(), new JButton("   Baja producto"), "bajaproducto", "BajaProducto");
		addFuncion(new AltaCliente(), new JButton("Modificar producto"), "modproducto", "ModificarProducto");
		addFuncion(new AltaCliente(), new JButton("Mostrar producto"), "mostrarproducto", "MostrarProducto");
		addFuncion(new AltaCliente(), new JButton("Listar productos"), "listarproducto", "ListarProductos");
		addFuncion(new AltaCliente(), new JButton("Buscar productos"), "buscarproducto", "BuscarProductos");
		
		
		add(productosPanel, BorderLayout.CENTER);
		add(productosMenu, BorderLayout.NORTH);
		
	}
	
	private void addFuncion(JPanel panel, JButton button, String iconName, String card) {
		productosPanel.addPanel(panel, card);
		productosMenu.addButton(button, "Productos/"+iconName, card);
	}
}