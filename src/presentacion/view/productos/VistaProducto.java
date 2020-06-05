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

public class VistaProducto {
	
	private ProductoController controlador;
	
	
	private AltaProducto altaProducto;
	//private BajaProducto bajaProducto;
	//private ModificarProducto modProducto;
	//private MostrarProducto mostrarProducto;
	private ListarProducto listarProducto;
	//private BuscarProducto buscarProducto;
	
	private ModuloMenu productosMenu;
	private JPanel productosFuncion;
	
	public VistaProducto() {
		this.controlador = new ProductoController(Modelo.getModelo());
		
		altaProducto = new AltaProducto(controlador);
		//bajaProducto = new BajaProducto(controlador);
		//modProducto = new ModificarProducto(controlador);
		//mostrarProducto = new MostrarProducto(controlador);
		listarProducto = new ListarProducto(controlador);
		//buscarProducto = new BuscarProducto(controlador);
		
		initGUI();
	}
	
	private void initGUI() {
		CardLayout cardLayout = new CardLayout();
		productosFuncion = new JPanel(cardLayout);
		CardSwitcher switcher = new CardSwitcher(productosFuncion, cardLayout);
		productosMenu = new ModuloMenu(switcher, 45);
		
		addFunction(altaProducto.getPCLayout(), new JButton("   Alta producto"), "altaproducto", "AltaProducto");
		//addFunction(bajaProducto.getDefaultLayout(), new JButton("   Baja producto"), "bajaproducto", "BajaProducto");
		//addFunction(modProducto.getDefaultLayout(), new JButton("Modificar producto"), "modproducto", "ModificarProducto");
		//addFunction(mostrarProducto.getDefaultLayout(), new JButton("Mostrar producto"), "mostrarproducto", "MostrarProducto");
		addFunction(listarProducto.getDefaultLayout(), new JButton("Listar productos"), "listarproducto", "ListarProductos");
		//addFunction(buscarProducto.getDefaultLayout(), new JButton("Buscar productos"), "buscarproducto", "BuscarProductos");
	}
	
	public JPanel getDefaultLayout() {
		JPanel productoPanel = new JPanel(new BorderLayout());
		
		productoPanel.add(productosFuncion, BorderLayout.CENTER);
		productoPanel.add(productosMenu.getDefaultLayout(), BorderLayout.NORTH);
		
		return productoPanel;
	}
	
	private void addFunction(JPanel panel, JButton button, String iconName, String card) {
		productosFuncion.add(panel, card);
		productosMenu.addButton(button, "Productos/"+iconName, card);
	}
}