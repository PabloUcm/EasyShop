package presentacion.view.clientes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BajaCliente extends JPanel {
	
	public BajaCliente() {
	  initGUI();
	}
	
	private JLabel idJL;
	private JTextField idTF;
	private JButton baja;
	private ImageIcon imageIcon;
	
	private void initGUI() {
		setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	   
	    idJL = new JLabel("ID CLIENTE:");
	    idJL.setFont(new Font(idJL.getFont().toString(), Font.BOLD, 50));
	    idJL.setPreferredSize(new Dimension(300,50));
	    idJL.setMaximumSize(idJL.getPreferredSize());
	   
	    idTF = new JTextField();
	    idTF.setFont(new Font(idTF.getFont().toString(), Font.PLAIN, 45));
	    idTF.setPreferredSize(new Dimension(300,50));
	    idTF.setMaximumSize(idTF.getPreferredSize());
	   
	    baja = new JButton("DAR DE BAJA AL CLIENTE");
	    baja.setContentAreaFilled(false);
	    baja.setFocusPainted(false);
	    baja.setBorder(BorderFactory.createRaisedBevelBorder());
	    baja.setOpaque(true);
	    baja.setPreferredSize(new Dimension(230,80));
	    baja.setMaximumSize(baja.getPreferredSize());
	    baja.setBackground(Color.RED);
	    baja.setAlignmentX(CENTER_ALIGNMENT);
	   
	    imageIcon = new ImageIcon("icons/baja.png"); 
	    Image image = imageIcon.getImage(); 
	    Image newimg = image.getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH); 
	    imageIcon = new ImageIcon(newimg);  
	    baja.setIcon(imageIcon);
	   
	    baja.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) { 
	    		String msg = "ID:\nDNI:\nNOMBRE:\nTELEFONO:\n\n ¿Quieres dar de baja a este cliente?";
	            int input = JOptionPane.showConfirmDialog(null, msg,"Confirmar baja de cliente", 
	            		    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, imageIcon);
	    	}
	    });
	   
	    c.gridx = 0;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    add(idJL, c);
	    c.gridx = 1;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    add(idTF, c);
	    c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    add(Box.createRigidArea(new Dimension(0, 15)), c);
	    c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.anchor = GridBagConstraints.CENTER;
	    add(baja, c);
	}
}

