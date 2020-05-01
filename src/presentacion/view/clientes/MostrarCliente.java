package presentacion.view.clientes;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import integracion.transfers.TCliente;
import negocio.ClienteObserver;
import presentacion.controllers.ClienteController;

public class MostrarCliente extends JPanel implements ClienteObserver {
	private ClienteController controlador;
	
	public MostrarCliente(ClienteController c) {
		this.controlador = c;
		controlador.addObserver(this);
		initGUI();
	}
	
	private JTextField dniTF;
	private JTextArea datosTA;
	private JButton buscar;
	private JButton limpiar;
	
	private void initGUI() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		dniTF = crearTextField();
		buscar = crearBoton("BUSCAR CLIENTE", new Color(8,213,249), new Color(6,160,190), 
							"lupa", 140, 30);
		datosTA = crearTextArea();
		limpiar = crearBoton("LIMPIAR BUSQUEDA", new Color(205,205,205), new Color(166,166,166), 
							 "limpiar", 170, 45);
		
		buscar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		mostrarCliente();
	    	}
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		dniTF.setText("");
	    		datosTA.setText("");
	    	}
	    });
		
		JPanel barraBusqueda = new JPanel();
		barraBusqueda.setLayout(new FlowLayout( FlowLayout.CENTER ));
		barraBusqueda.add((crearJLabel(" ID CLIENTE:")));
		barraBusqueda.add(dniTF);
		barraBusqueda.add(Box.createRigidArea(new Dimension(10, 0)));
		barraBusqueda.add(buscar);
		
		c.gridx = 0;
		c.gridy = 0;
		add(barraBusqueda, c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.anchor = GridBagConstraints.CENTER;
	    add(datosTA, c);
	    c.weightx = 0.0;
		c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    add(Box.createRigidArea(new Dimension(0,15)),c);
	    c.weightx = 0.5;
		c.gridx = 0;
	    c.gridy = 3;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    add(limpiar,c);
		
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
		ta.setBorder(BorderFactory.createLineBorder(Color.black));
		
		ta.setPreferredSize(new Dimension(550,250));
		ta.setMaximumSize(ta.getPreferredSize());
		
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
		});

		return button;
	}
	
	private void mostrarCliente() {
		
		datosTA.setText("                                             ");
		datosTA.append("=====================\n");
		datosTA.append("                                            ");
		datosTA.append("      DATOS DEL CLIENTE   \n");
		datosTA.append("                                             ");
		datosTA.append("=====================\n\n");
		datosTA.append("    ID: ");
		datosTA.append("3\n\n");
		datosTA.append("    DNI: ");
		datosTA.append("003\n\n");
		datosTA.append("    NOMBRE: ");
		datosTA.append("Manolo\n\n");
		datosTA.append("    TELEFONO: ");
		datosTA.append("125\n\n");
	}
	
	@Override
	public void altaCliente() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bajaCliente() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mostrarClienteId() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarCliente() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listarClientes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void obtenerCliente() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarCliente(List<TCliente> clienteList) {
		// TODO Auto-generated method stub
		
	}
	
}
