package presentacion.view.clientes;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


import negocio.Modelo;
import presentacion.controllers.ClienteController;
import presentacion.view.CardSwitcher;
import presentacion.view.menus.ModuloMenu;

public class VistaCliente {
	
	private ClienteController controlador;
	
	private AltaCliente altaCliente;
	private BajaCliente bajaCliente;
	private ModificarCliente modCliente;
	private MostrarCliente mostrarCliente;
	private ListarClientes listarClientes;
	private HistorialCliente histCliente;

	private ModuloMenu clientesMenu;
	private JPanel clientesFuncion;
	
	public VistaCliente() {
		this.controlador = new ClienteController(Modelo.getModelo());
		
		altaCliente = new AltaCliente(controlador);
		bajaCliente = new BajaCliente(controlador);
		modCliente = new ModificarCliente(controlador);
		mostrarCliente = new MostrarCliente(controlador);
		listarClientes = new ListarClientes(controlador);
		histCliente = new HistorialCliente(controlador);
		
		initGUI();
	}
	
	private void initGUI() {
		CardLayout cardLayout = new CardLayout();
		clientesFuncion = new JPanel(cardLayout);
		CardSwitcher switcher = new CardSwitcher(clientesFuncion, cardLayout);
		clientesMenu = new ModuloMenu(switcher, 35);
		
		addFunction(altaCliente.getDefaultLayout(), new JButton("     Alta cliente"), 
				   "altacliente", "AltaCliente");
		addFunction(bajaCliente.getDefaultLayout(), new JButton("     Baja cliente"), 
				   "bajacliente", "BajaCliente");
		addFunction(modCliente.getDefaultLayout(), new JButton("Modificar cliente"), 
				   "modcliente", "ModificarCliente");
		addFunction(mostrarCliente.getDefaultLayout(), new JButton("     Mostrar cliente"), 
				   "mostrarcliente", "MostrarCliente");
		addFunction(listarClientes.getDefaultLayout(), new JButton("     Listar clientes"), 
				   "listarcliente", "ListarClientes");
		addFunction(histCliente.getDefaultLayout(), new JButton("Historial cliente"), 
				   "historialcliente", "HistorialCliente");
	}
	
	public JPanel getDefaultLayout() {
		JPanel clientePanel = new JPanel(new BorderLayout());
		
		clientePanel.add(clientesFuncion, BorderLayout.CENTER);
		clientePanel.add(clientesMenu.getDefaultLayout(), BorderLayout.NORTH);
		
		return clientePanel;
	}
	
	private void addFunction(JPanel panel, JButton button, String iconName, String card) {
		clientesFuncion.add(panel, card);
		clientesMenu.addButton(button, "Clientes/"+iconName, card);
	}

}
