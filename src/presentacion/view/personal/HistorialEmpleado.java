package presentacion.view.personal;

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
import presentacion.controllers.PersonalController;
import presentacion.view.SwingFactory;
import presentacion.view.tablas.HistVentasTableModel;

public class HistorialEmpleado {
	
	private PersonalController controlador;
	
	private JTextField idTF;
	private JButton buscar;
	private JButton limpiar;
	
	private JTable histVentasTable;
	private HistVentasTableModel tableModel;
	
	public HistorialEmpleado(PersonalController c){
		controlador = c;
		initGUI();
		
	}
	
	private void initGUI(){
		tableModel = new HistVentasTableModel();
		histVentasTable = new JTable(tableModel);
		
		idTF = SwingFactory.getJTextField(new Dimension(290,30), 25);
		
		buscar = SwingFactory.getJButton(new Dimension(150,30), "BUSCAR HISTORIAL", 
				 						 "icons/lupa", 20, new Color(8,213,249),  new Color(6,160,190));
		limpiar = SwingFactory.getJButton(new Dimension(170,45), "LIMPIAR BUSQUEDA", 
				                          "icons/limpiar", 25, new Color(205,205,205),  new Color(166,166,166));
		
		buscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { getHistorialVentas(); }
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { limpiar(); }
	    });
		
	}
	
	public JPanel getDefaultLayout() {
		JPanel histClientePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		histVentasTable.setPreferredScrollableViewportSize(new Dimension(600,500));
		JScrollPane tablaSP = new JScrollPane(histVentasTable);
		tablaSP.getViewport().setBackground(Color.WHITE);
		tablaSP.setMinimumSize(new Dimension(600,700));
		
		JPanel barraBusqueda = new JPanel(new FlowLayout( FlowLayout.CENTER));
		barraBusqueda.add(SwingFactory.getJLabel(new Dimension(190,50), "ID EMPLEADO:" ,25));
		barraBusqueda.add(idTF);
		barraBusqueda.add(Box.createRigidArea(new Dimension(10, 0)));
		barraBusqueda.add(buscar);
		
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
	
	private void getHistorialVentas() {
		try {
			if (idTF.getText().trim().equals("")) throw new Exception("Campo sin rellenar.");
			
			int id = Integer.parseInt(idTF.getText());
			
			List<TVenta> histVentas = controlador.getHistorialVentas(id);
			
			tableModel.setHistorialVentas(histVentas);
		} catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,"El campo 'ID' debe ser un numero.", "ERROR",JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limpiar() {
		idTF.setText("");
		tableModel.vaciar();
	}

}
