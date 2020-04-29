package presentacion.view.clientes;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.view.CardSwitcher;
import presentacion.view.ModuloMenu;
import presentacion.view.ModuloPanel;

public class ClientesPanel extends JPanel {
	public ClientesPanel() {
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
		
		addFuncion(new AltaCliente(), new JButton("     Alta cliente"), "altacliente", "AltaCliente");
		addFuncion(new BajaCliente(), new JButton("     Baja cliente"), "bajacliente", "BajaCliente");
		addFuncion(new AltaCliente(), new JButton("Modificar cliente"), "modcliente", "ModificarCliente");
		addFuncion(new AltaCliente(), new JButton("     Mostrar cliente"), "mostrarcliente", "MostrarCliente");
		addFuncion(new AltaCliente(), new JButton("     Listar clientes"), "listarcliente", "ListarClientes");
		addFuncion(new AltaCliente(), new JButton("Historial cliente"), "historialcliente", "HistorialCliente");
		
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
