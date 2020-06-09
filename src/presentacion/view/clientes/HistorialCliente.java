package presentacion.view.clientes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import integracion.transfers.TVenta;
import presentacion.controllers.ClienteController;
import presentacion.view.SwingFactory;
import presentacion.view.tablas.ComprasTableModel;

public class HistorialCliente {
	
	private ClienteController controlador;
	
	private JTextField idTF;
	private JButton buscar;
	private JButton limpiar;
	
	private JTable comprasTable;
	private ComprasTableModel tableModel;
	
	public HistorialCliente(ClienteController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
		tableModel = new ComprasTableModel();
		comprasTable = new JTable(tableModel);
		
		idTF = SwingFactory.getJTextField(new Dimension(290,30), 25);
		
		buscar = SwingFactory.getJButton(new Dimension(150,30), "BUSCAR HISTORIAL", 
										 "icons/lupa", 20, new Color(8,213,249),  new Color(6,160,190));
		limpiar = SwingFactory.getJButton(new Dimension(170,45), "LIMPIAR BUSQUEDA", 
										  "icons/limpiar", 25, new Color(205,205,205),  new Color(166,166,166));
		
		buscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		getCompras(Integer.parseInt(idTF.getText()));
	    	}
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { limpiar(); }
	    });
	}
	
	public JPanel getDefaultLayout() {
		JPanel histClientePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		comprasTable.setPreferredScrollableViewportSize(new Dimension(600,500));
		JScrollPane tablaSP = new JScrollPane(comprasTable);
		tablaSP.getViewport().setBackground(Color.WHITE);
		tablaSP.setMinimumSize(new Dimension(600,700));
		
		JPanel barraBusqueda = new JPanel(new FlowLayout( FlowLayout.CENTER));
		barraBusqueda.add(SwingFactory.getJLabel(new Dimension(160,50), " ID CLIENTE:" ,25));
		barraBusqueda.add(idTF);
		barraBusqueda.add(Box.createRigidArea(new Dimension(10, 0)));
		barraBusqueda.add(buscar);
		barraBusqueda.setMinimumSize(new Dimension(300,150));
		
		c.gridx = 0;
		c.gridy = 0;
		histClientePanel.add(barraBusqueda, c);
	    c.weightx = 0.0;
		c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    histClientePanel.add(Box.createRigidArea(new Dimension(0,15)),c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    histClientePanel.add(tablaSP,c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 4;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    histClientePanel.add(limpiar,c);
		
		return histClientePanel;
	}
	
	private void getCompras(int id) {
		try {
			List<TVenta> listaCompras = controlador.getCompras(id);
			tableModel.setCompras(listaCompras);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiar() {
		idTF.setText("");
		tableModel.vaciar();
	}
}
