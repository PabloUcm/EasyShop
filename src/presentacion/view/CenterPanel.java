package presentacion.view;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import presentacion.view.clientes.ClientesPanel;
import presentacion.view.marcas.MarcasPanel;
import presentacion.view.personal.PersonalPanel;
import presentacion.view.productos.ProductosPanel;
import presentacion.view.ventas.VentasPanel;

public class CenterPanel extends JPanel {
	public CenterPanel(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
		initGUI();
	}
	
	private CardLayout cardLayout;

	private void initGUI() {		
		setLayout(cardLayout);
		setPreferredSize((new Dimension(740,800)));
		
		//add(new ClientesPanel(new ControllerCliente(Modelo.getInstancia())),"Clientes");
		add(new ClientesPanel(),"Clientes");
		add(new PersonalPanel(),"Personal");
		add(new VentasPanel(),"Ventas");
		add(new ProductosPanel(),"Productos");
		add(new MarcasPanel(), "Marcas");
		
	}
}
