package presentacion.view.clientes;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import negocio.ClienteObserver;
import negocio.Modelo;
import presentacion.controllers.ClienteController;
import presentacion.view.CardSwitcher;
import presentacion.view.ModuloMenu;
import presentacion.view.ModuloPanel;
import presentacion.view.marcas.AltaMarca;

public class ClientesPanel extends JPanel {
	private ClienteController controlador;
	
	public ClientesPanel() {
		this.controlador = new ClienteController(Modelo.getModelo());
		initGUI();
	}
	private ModuloMenu clientesMenu;
	private ModuloPanel clientesFuncion;
	
	private void initGUI() {
		setLayout(new BorderLayout());
		
		CardLayout cardLayout = new CardLayout();
		clientesFuncion = new ModuloPanel(cardLayout);
		CardSwitcher switcher = new CardSwitcher(clientesFuncion, cardLayout);
		clientesMenu = new ModuloMenu(switcher, 35);
		
		addFuncion(new AltaCliente(controlador), new JButton("     Alta cliente"), "altacliente", "AltaCliente");
		addFuncion(new BajaCliente(controlador), new JButton("     Baja cliente"), "bajacliente", "BajaCliente");
		addFuncion(new ModificarCliente(controlador), new JButton("Modificar cliente"), "modcliente", "ModificarCliente");
		addFuncion(new MostrarCliente(controlador), new JButton("     Mostrar cliente"), "mostrarcliente", "MostrarCliente");
		addFuncion(new ListarClientes(controlador), new JButton("     Listar clientes"), "listarcliente", "ListarClientes");
		addFuncion(new HistorialCliente(controlador), new JButton("Historial cliente"), "historialcliente", "HistorialCliente");
		
		add(clientesFuncion, BorderLayout.CENTER);
		add(clientesMenu, BorderLayout.NORTH);
		
		addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent evt) {clientesMenu.reset();
            }
        });
	}
	
	private void addFuncion(JPanel panel, JButton button, String iconName, String card) {
		clientesFuncion.addPanel(panel, card);
		clientesMenu.addButton(button, "Clientes/"+iconName, card);
	}

}
