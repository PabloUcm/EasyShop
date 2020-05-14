package presentacion.view.productos;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import negocio.Modelo;
import presentacion.controllers.ProductoController;
import presentacion.view.CardSwitcher;
import presentacion.view.marcas.AltaMarca;
import presentacion.view.menus.ModuloMenu;
import presentacion.view.personal.AltaEmpleado;

public class ProductoModulo {
	
	private ProductoController controlador;
	
	
	//private AltaProducto altaProducto;
	//private BajaProducto bajaProducto;
	//private ModificarProducto modProducto;
	//private MostrarProducto mostrarProducto;
	//private ListarProducto listarProducto;
	//private BuscarProducto buscarProducto;
	
	
	public ProductoModulo() {
		this.controlador = new ProductoController(Modelo.getModelo());
		
		//altaProducto = new AltaProducto(controlador);
		//bajaProducto = new BajaProducto(controlador);
		//modProducto = new ModificarProducto(controlador);
		//mostrarProducto = new MostrarProducto(controlador);
		//listarProducto = new ListarProducto(controlador);
		//buscarProducto = new BuscarProducto(controlador);
		
		initGUI();
	}
	
	private ModuloMenu productosMenu;
	private JPanel productosFuncion;
	
	private void initGUI() {
		CardLayout cardLayout = new CardLayout();
		productosFuncion = new JPanel(cardLayout);
		CardSwitcher switcher = new CardSwitcher(productosFuncion, cardLayout);
		productosMenu = new ModuloMenu(switcher, 45);
		
		//addFuncion(altaProducto.getDefaultLayout(), new JButton("   Alta producto"), "altaproducto", "AltaProducto");
		//addFuncion(bajaProducto.getDefaultLayout(), new JButton("   Baja producto"), "bajaproducto", "BajaProducto");
		//addFuncion(modProducto.getDefaultLayout(), new JButton("Modificar producto"), "modproducto", "ModificarProducto");
		//addFuncion(mostrarProducto.getDefaultLayout(), new JButton("Mostrar producto"), "mostrarproducto", "MostrarProducto");
		//addFuncion(listarProducto.getDefaultLayout(), new JButton("Listar productos"), "listarproducto", "ListarProductos");
		//addFuncion(buscarProducto.getDefaultLayout(), new JButton("Buscar productos"), "buscarproducto", "BuscarProductos");
	}
	
	public JPanel getDefaultLayout() {
		JPanel productoPanel = new JPanel(new BorderLayout());
		
		productoPanel.add(productosFuncion, BorderLayout.CENTER);
		productoPanel.add(productosMenu.getDefaultLayout(), BorderLayout.NORTH);
		
		return productoPanel;
	}
	
	private void addFuncion(JPanel panel, JButton button, String iconName, String card) {
		productosFuncion.add(panel, card);
		productosMenu.addButton(button, "Productos/"+iconName, card);
	}
}