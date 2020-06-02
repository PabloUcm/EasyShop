package presentacion.view.marcas;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import integracion.transfers.TMarca;
import negocio.MarcaObserver;
import presentacion.controllers.MarcaController;


public class MostrarMarca{
	private MarcaController controlador;
	
	public MostrarMarca(MarcaController c) {
		this.controlador = c;
		initGUI();
	}
	
	private JTextField idTF;
	private JTextArea datosTA;
	private JButton buscar;
	private JButton limpiar;
	
	private void initGUI() {
		idTF = crearTextField();
		buscar = crearBoton("BUSCAR MARCA", new Color(8,213,249), new Color(6,160,190), 
							"lupa", 140, 30);
		limpiar = crearBoton("LIMPIAR BUSQUEDA", new Color(205,205,205), new Color(166,166,166), 
							 "limpiar", 170, 45);
		datosTA = crearTextArea();
		
		buscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { mostrar(); }
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		idTF.setText("");
	    		datosTA.setText("");
	    	}
	    });	
	}
	
	public JPanel getDefaultLayout() {
		JPanel mostrarClientePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JScrollPane datosSP = new JScrollPane(datosTA);
		datosSP.setPreferredSize(new Dimension(550,250));
		datosSP.setMaximumSize(datosSP.getPreferredSize());
		
		JPanel barraBusqueda = new JPanel(new FlowLayout( FlowLayout.CENTER));
		barraBusqueda.add((crearJLabel(" ID MARCA:")));
		barraBusqueda.add(idTF);
		barraBusqueda.add(Box.createRigidArea(new Dimension(10, 0)));
		barraBusqueda.add(buscar);
		
		c.gridx = 0;
		c.gridy = 0;
		mostrarClientePanel.add(barraBusqueda, c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.anchor = GridBagConstraints.CENTER;
	    mostrarClientePanel.add(datosSP, c);
	    c.weightx = 0.0;
		c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    mostrarClientePanel.add(Box.createRigidArea(new Dimension(0,15)),c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 3;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    mostrarClientePanel.add(limpiar,c);
		
		return mostrarClientePanel;
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
		
		ta.setFont(new Font(ta.getFont().toString(), Font.PLAIN, 15));
		
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
	
	private String busquedaToString(TMarca marca) {
		StringBuilder busqstr = new StringBuilder();
		for (int i = 0; i < 45; i++) busqstr.append(" ");
		busqstr.append("=====================\n");
		for (int i = 0; i < 45; i++) busqstr.append(" ");
		busqstr.append("      DATOS DE LA MARCA   \n");
		for (int i = 0; i < 45; i++) busqstr.append(" ");
		busqstr.append("=====================\n\n");
		busqstr.append("    ID: "+marca.getId()+"\n\n");
		busqstr.append("    CIF: "+marca.getCIF()+"\n\n");
		busqstr.append("    NOMBRE: "+marca.getNombre()+"\n\n");
		if(marca.getPais() != null) busqstr.append("    PAIS: "+marca.getPais());
		else busqstr.append("    PAIS: [Vacio]");
		
		return busqstr.toString();
	}
	
	private void mostrar() {
		try {
			if (idTF.getText().isEmpty()) throw new Exception("Campo sin rellenar.");
			
			TMarca marca = controlador.getMarca(Integer.parseInt(idTF.getText()));
			
			datosTA.setText(busquedaToString(marca));
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}
