package presentacion.view.personal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import integracion.transfers.TPersonal;
import negocio.PersonalObserver;
import presentacion.controllers.PersonalController;

public class HistorialEmpleado implements PersonalObserver{
	private PersonalController controlador;
	public HistorialEmpleado(PersonalController c){
		controlador = c;
		controlador.addObserver(this);
		initGUI();
		
	}
	private JTextField idTF;
	private JTextArea historialTA;
	private JButton buscar;
	private JButton limpiar;
	
	private void initGUI(){
		idTF = crearTextField();
		buscar = crearBoton("BUSCAR HISTORIAL", new Color(8,213,249), new Color(6,160,190), 
							"lupa", 150, 30);
		limpiar = crearBoton("LIMPIAR BUSQUEDA", new Color(205,205,205), new Color(166,166,166), 
							 "limpiar", 170, 45);
		historialTA = crearTextArea();
		JScrollPane historialSP = new JScrollPane(historialTA);
		historialSP.setPreferredSize(new Dimension(550,600));
		historialSP.setMaximumSize(historialSP.getPreferredSize());
		
		buscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		//historialTA.setText(historialToString());
	    		historialTA.setCaretPosition(0);
	    	}
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		idTF.setText("");
	    		historialTA.setText("");
	    	}
	    });
		
	}
	
	public JPanel getDefaultLayout() {
		JPanel histClientePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JScrollPane historialSP = new JScrollPane(historialTA);
		historialSP.setPreferredSize(new Dimension(550,600));
		historialSP.setMaximumSize(historialSP.getPreferredSize());
		
		JPanel barraBusqueda = new JPanel(new FlowLayout( FlowLayout.CENTER));
		barraBusqueda.add((crearJLabel(" ID EMPLEADO:")));
		barraBusqueda.add(idTF);
		barraBusqueda.add(Box.createRigidArea(new Dimension(10, 0)));
		barraBusqueda.add(buscar);
		
		c.gridx = 0;
		c.gridy = 0;
		histClientePanel.add(barraBusqueda, c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.anchor = GridBagConstraints.CENTER;
	    histClientePanel.add(historialSP, c);
	    c.weightx = 0.0;
		c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    histClientePanel.add(Box.createRigidArea(new Dimension(0,15)),c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 3;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    histClientePanel.add(limpiar,c);
		
		return histClientePanel;
	}
	
	private JLabel crearJLabel(String texto) {
		JLabel jl = new JLabel(texto);
		
		jl.setFont(new Font(jl.getFont().toString(), Font.BOLD, 25));
		
		jl.setPreferredSize(new Dimension(160,50));
		jl.setMaximumSize(jl.getPreferredSize());
		
		return jl;
	}
	
	private JTextField crearTextField() {
		JTextField tf = new JTextField();
		
		tf.setFont(new Font(tf.getFont().toString(), Font.PLAIN, 25));
		
		tf.setPreferredSize(new Dimension(290,30));
		tf.setMaximumSize(tf.getPreferredSize());
		
		return tf;
	}
	
	private JTextArea crearTextArea() {
		JTextArea ta = new JTextArea();
		
		ta.setFont(new Font(ta.getFont().toString(), Font.PLAIN, 13));
		
		ta.setEditable(false);
		
		return ta;
	}
	
	private JButton crearBoton(String texto, Color colorNormal, Color colorMouse, String icono, int ancho, int altura) {
		JButton button = new JButton(texto);
		
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		button.setOpaque(true);
		
		button.setPreferredSize(new Dimension(ancho,altura));
		button.setMaximumSize(button.getPreferredSize());
		
		button.setBackground(colorNormal);
		
		ImageIcon imageIcon = new ImageIcon("icons/"+icono+".png"); 
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(25,25,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg);  
		button.setIcon(imageIcon);
		
		button.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	button.setBackground(colorMouse);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	button.setBackground(colorNormal);
		    }
		    
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	button.setBorder(BorderFactory.createBevelBorder(1));
		    }
		    
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	button.setBorder(BorderFactory.createRaisedBevelBorder());
		    }
		});

		return button;
	}
	/*
	private String historialToString() {
		StringBuilder histstr = new StringBuilder();
		
		for (int i = 0; i < 33; i++) histstr.append(" ");
		histstr.append("====================================\n");
		for (int i = 0; i < 38; i++) histstr.append(" ");
		histstr.append("Historial de compras del cliente con ID: "+idTF.getText()+"\n");
		for (int i = 0; i < 33; i++) histstr.append(" ");
		histstr.append("====================================\n\n");
		
		
		return histstr.toString();
	}
	*/
	
	@Override
	public void altaEmpleado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bajaEmpleado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarEmpleadoId() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarEmpleado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listarEmpleados() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void obtenerEmpleado(TPersonal empleado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarEmpleado(List<TPersonal> empleadoList) {
		// TODO Auto-generated method stub
		
	}
	

}
