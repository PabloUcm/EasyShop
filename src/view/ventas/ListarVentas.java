package view.ventas;


import javax.swing.JPanel;

public class ListarVentas extends JPanel {
	
	public ListarVentas() {
		initGUI();
	}
	
	
	private void initGUI() {
		add(new VentasTable());
	}
}