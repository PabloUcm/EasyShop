package view.marcas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AltaMarca extends JPanel {
	
	public AltaMarca() {
		initGUI();
	}
	
	private JTextField cifTF;
	private JTextField nombreTF;
	private JTextField paisTF;
	private JButton confirmar;
	private JButton limpiar;
	
	private void initGUI() {
		setLayout(new BorderLayout());
		setPreferredSize((new Dimension(1090,700)));
		
		cifTF = crearTextField();
		nombreTF = crearTextField();
		paisTF = crearTextField();
		
		confirmar = crearBoton("CONFIRMAR ALTA DE MARCA", Color.GREEN, "confirmar");
		limpiar = crearBoton("LIMPIAR CAMPOS DE TEXTO", Color.GRAY.brighter(), "limpiar");
		
		confirmar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		
	    	}
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) {
	    		cifTF.setText(" ");
	    		nombreTF.setText(" ");
	    		paisTF.setText(" ");
	    	}
	    });
		
		JPanel campos = new JPanel();
		campos.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		campos.add(crearJLabel("                    CIF:"), c);
		c.gridx = 1;
		c.gridy = 0;
		campos.add(cifTF, c);
		c.gridx = 0;
		c.gridy = 1;
		campos.add(crearJLabel("         NOMBRE:"), c);
		c.gridx = 1;
		c.gridy = 1;
		campos.add(nombreTF, c);
		c.gridx = 0;
		c.gridy = 2;
		campos.add(crearJLabel("                 PAIS:"), c);
		c.gridx = 1;
		c.gridy = 2;
		campos.add(paisTF, c);
		c.gridx = 0;
		c.gridy = 3;
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout( FlowLayout.CENTER ));
		botones.setBorder(BorderFactory.createMatteBorder(
                  1, 0, 0, 0, Color.black));
		
		botones.add(confirmar);
		botones.add(limpiar);
		
		add(campos,BorderLayout.CENTER);
		add(botones, BorderLayout.SOUTH);
		
		addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent evt) {
	    		cifTF.setText(" ");
	    		nombreTF.setText(" ");
	    		paisTF.setText(" ");
            }
        });
		
	}
	
	private JLabel crearJLabel(String texto) {
		JLabel jl = new JLabel(texto);
		jl.setFont(new Font(jl.getFont().toString(), Font.BOLD, 30));
		jl.setAlignmentX(Component.CENTER_ALIGNMENT);
		jl.setPreferredSize(new Dimension(230,50));
		jl.setMaximumSize(jl.getPreferredSize());
		return jl;
	}
	
	private JTextField crearTextField() {
		JTextField tf = new JTextField();
		tf.setFont(new Font(tf.getFont().toString(), Font.PLAIN, 25));
		tf.setAlignmentX(Component.CENTER_ALIGNMENT);
		tf.setPreferredSize(new Dimension(550,35));
		tf.setMaximumSize(tf.getPreferredSize());
		return tf;
	}
	
	private JButton crearBoton(String texto, Color color, String icono) {
		JButton button = new JButton(texto);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		button.setOpaque(true);
		button.setPreferredSize(new Dimension(250,60));
		button.setMaximumSize(button.getPreferredSize());
		button.setBackground(color);
		
		ImageIcon imageIcon = new ImageIcon("icons/"+icono+".png"); 
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg);  
		button.setIcon(imageIcon);

		return button;
	}
}