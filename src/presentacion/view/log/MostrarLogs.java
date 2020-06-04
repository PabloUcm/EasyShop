package presentacion.view.log;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentacion.controllers.LogController;
import presentacion.view.tablas.LogTableModel;

public class MostrarLogs {
	private LogController controller;
	private JTable logsTable;
	private LogTableModel tableModel;
	
	public MostrarLogs(LogController controller) {
		this.controller = controller;
		initGUI();
	}
		
	private void initGUI() {
		tableModel = new LogTableModel(controller);
		logsTable = new JTable(tableModel);
	}
	
	public JPanel getDefaultLayout() {
		JPanel listarLogsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		logsTable.setPreferredScrollableViewportSize(new Dimension(600,700));
		
		JScrollPane tablaSP = new JScrollPane(logsTable);
		tablaSP.getViewport().setBackground(Color.WHITE);
		
		c.gridx = 0;
		c.gridy = 0;
		listarLogsPanel.add(tablaSP, c);
		
		return listarLogsPanel;
	}
}
