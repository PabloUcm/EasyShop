package presentacion.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SwingFactory {

	public static JLabel getJLabel(Dimension dimension, String texto, int fontSize) {
		JLabel jl = new JLabel(texto);
		jl.setFont(new Font(jl.getFont().toString(), Font.BOLD, fontSize));
		jl.setPreferredSize(dimension);
		jl.setMaximumSize(jl.getPreferredSize());
		jl.setAlignmentX(Component.RIGHT_ALIGNMENT);
		return jl;
	}
	
	public static JTextField getJTextField(Dimension dimension, int fontSize) {
		JTextField tf = new JTextField();
		tf.setFont(new Font(tf.getFont().toString(), Font.PLAIN, fontSize));
		tf.setPreferredSize(dimension);
		tf.setMaximumSize(tf.getPreferredSize());
		if (dimension.width > 250) tf.setMinimumSize(new Dimension(dimension.width - 150, dimension.height));
		else tf.setMinimumSize(tf.getPreferredSize());
		return tf;
	}
	
	public static JButton getJButton(Dimension dimension, String texto, String iconDir, 
									 int iconSize, Color colorNormal, Color colorMouse) 
	{
		JButton button = new JButton(texto);
		
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		button.setOpaque(true);
		
		button.setPreferredSize(dimension);
		button.setMaximumSize(button.getPreferredSize());
		button.setMinimumSize(button.getPreferredSize());
		
		button.setBackground(colorNormal);
		
		ImageIcon imageIcon = new ImageIcon(iconDir+".png"); 
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(iconSize,iconSize,java.awt.Image.SCALE_SMOOTH); 
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
	
	
	public static JTextArea getJTextArea(int fontSize, boolean editable) {
		JTextArea ta = new JTextArea();
		
		ta.setFont(new Font(ta.getFont().toString(), Font.PLAIN, fontSize));
		
		ta.setEditable(editable);
		
		if (editable) {
			ta.setLineWrap(true);
			ta.setWrapStyleWord(true);
		}
		
		return ta;
	}
	
	public static ImageIcon getScaledIcon(String iconDir, int scaledSize) {
		ImageIcon icon= new ImageIcon(iconDir+".png"); 
		
		Image image = icon.getImage(); 
		Image newimg = image.getScaledInstance(scaledSize, scaledSize, java.awt.Image.SCALE_SMOOTH); 
		icon = new ImageIcon(newimg);
		
		return icon;
	}
}
