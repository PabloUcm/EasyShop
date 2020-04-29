package presentacion.view.ventas;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentasTable extends JPanel {
	public VentasTable() {
		VentasTableModel tableModel = new VentasTableModel();
		JTable ventasTable = new JTable(tableModel);
		
		ventasTable.setPreferredScrollableViewportSize(new Dimension(600,700));
		
		JScrollPane scrollPane = new JScrollPane(ventasTable);
		scrollPane.getViewport().setBackground(Color.WHITE);
		this.add(scrollPane);
	}
}
