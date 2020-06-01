package presentacion.view.marcas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentacion.controllers.MarcaController;
import presentacion.view.tablas.MarcaTableModel;


public class ListarMarcas {
	private MarcaController controlador;
	
	public ListarMarcas(MarcaController c) {
		this.controlador = c;
		initGUI();
	}
	
	private JTable marcasTable;
	
	private void initGUI() {
		MarcaTableModel tableModel = new MarcaTableModel(controlador);
		marcasTable = new JTable(tableModel);
	}
	
	public JPanel getDefaultLayout() {
		JPanel listarMarcasPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		marcasTable.setPreferredScrollableViewportSize(new Dimension(600,700));
		
		JScrollPane tablaSP = new JScrollPane(marcasTable);
		tablaSP.getViewport().setBackground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 0;
		listarMarcasPanel.add(tablaSP, c);
		
		return listarMarcasPanel;
	}
}
