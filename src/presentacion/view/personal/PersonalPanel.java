package presentacion.view.personal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.view.CardSwitcher;
import presentacion.view.ModuloMenu;
import presentacion.view.ModuloPanel;
import presentacion.view.clientes.AltaCliente;
import presentacion.view.clientes.BajaCliente;

public class PersonalPanel extends JPanel {
	public PersonalPanel() {
		initGUI();
	}
	private ModuloMenu personalMenu;
	private ModuloPanel personalFuncion;
	
	private void initGUI() {
		setLayout(new BorderLayout());
		
		CardLayout cardLayout = new CardLayout();
		personalFuncion = new ModuloPanel(cardLayout);
		CardSwitcher switcher = new CardSwitcher(personalFuncion, cardLayout);
		personalMenu = new ModuloMenu(switcher, 45);
		
		addFuncion(new AltaEmpleado(), new JButton("   Alta empleado"), "altaempleado", "AltaCliente");
		addFuncion(new BajaEmpleado(), new JButton("   Baja empleado"), "bajaempleado", "BajaCliente");
		addFuncion(new ModEmpleado(), new JButton("Modificar empleado"), "modempleado", "ModificarEmpleado");
		addFuncion(new BajaCliente(), new JButton("  Mostrar empleado"), "mostrarempleado", "MostrarEmpleado");
		addFuncion(new BajaCliente(), new JButton("   Listar empleado"), "listarempleado", "ListarClientes");
		addFuncion(new BajaCliente(), new JButton("Historial empleado"), "historialempleado", "HistorialCliente");
		
		add(personalFuncion, BorderLayout.CENTER);
		add(personalMenu, BorderLayout.NORTH);
		
		addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent evt) {personalMenu.reset();}
        });
	}
	
	private void addFuncion(JPanel panel, JButton button, String iconName, String card) {
		personalFuncion.addPanel(panel, card);
		personalMenu.addButton(button, "Personal/"+iconName, card);
	}
}