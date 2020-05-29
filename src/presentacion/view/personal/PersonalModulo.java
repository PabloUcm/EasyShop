package presentacion.view.personal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import negocio.Modelo;
import presentacion.controllers.PersonalController;
import presentacion.view.CardSwitcher;
import presentacion.view.clientes.AltaCliente;
import presentacion.view.clientes.BajaCliente;
import presentacion.view.marcas.AltaMarca;
import presentacion.view.menus.ModuloMenu;

public class PersonalModulo {
	
	private PersonalController controlador;
	
	private AltaEmpleado altaEmp;
	private BajaEmpleado bajaEmp;
	private ModificarEmpleado modEmp;
	//private MostrarEmpleado mostrarEmp;
	//private ListarEmpleado listarEmp;
	//private HistorialEmpleado histEmp;
	
	public PersonalModulo() {
		controlador = new PersonalController(Modelo.getModelo());
		
		altaEmp = new AltaEmpleado(controlador);
		bajaEmp = new BajaEmpleado(controlador);
		modEmp = new ModificarEmpleado(controlador);
		//mostrarEmp = new MostrarEmpleado(controlador);
		//listarEmp = new ListarEmpleado(controlador);
		//histEmp = HistorialEmpleado(controlador);
		
		initGUI();
	}
	
	private ModuloMenu personalMenu;
	private JPanel personalFuncion;
	
	private void initGUI() {
		CardLayout cardLayout = new CardLayout();
		personalFuncion = new JPanel(cardLayout);
		CardSwitcher switcher = new CardSwitcher(personalFuncion, cardLayout);
		personalMenu = new ModuloMenu(switcher, 45);
		
		addFuncion(altaEmp.getDefaultLayout(), new JButton("   Alta empleado"), 
				   "altaempleado", "AltaCliente");
		addFuncion(bajaEmp.getDefaultLayout(), new JButton("   Baja empleado"), 
				   "bajaempleado", "BajaCliente");
		addFuncion(modEmp.getDefaultLayout(), new JButton("Modificar empleado"), 
				   "modempleado", "ModificarEmpleado");
		//addFuncion(mostrarEmp.getDefaultLayout(), new JButton("  Mostrar empleado"), "mostrarempleado", "MostrarEmpleado");
		//addFuncion(listarEmp.getDefaultLayout(), new JButton("   Listar empleado"), "listarempleado", "ListarClientes");
		//addFuncion(histEmp.getDefaultLayout(), new JButton("Historial empleado"), "historialempleado", "HistorialCliente");
	}
	
	public JPanel getDefaultLayout() {
		JPanel personalPanel = new JPanel(new BorderLayout());
		
		personalPanel.add(personalFuncion, BorderLayout.CENTER);
		personalPanel.add(personalMenu.getDefaultLayout(), BorderLayout.NORTH);
		
		return personalPanel;
	}
	
	private void addFuncion(JPanel panel, JButton button, String iconName, String card) {
		personalFuncion.add(panel, card);
		personalMenu.addButton(button, "Personal/"+iconName, card);
	}
}