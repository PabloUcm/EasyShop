package presentacion.view.personal;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextField;

import integracion.transfers.TCliente;
import integracion.transfers.TPersonal;
import negocio.PersonalObserver;
import presentacion.controllers.PersonalController;

public class BajaEmpleado implements PersonalObserver{
	
	private PersonalController controlador;
	
	public BajaEmpleado(PersonalController c) {
		this.controlador = c;
		controlador.addObserver(this);
		initGUI();
	}
	
	private JLabel idJL;
	private JTextField idTF;
	private JButton baja;
	private ImageIcon imageIcon;
	//private ImageIcon bajaIcon;
	
	private void initGUI() {
	    idJL = new JLabel("ID EMPLEADO:");
	    idJL.setFont(new Font(idJL.getFont().toString(), Font.BOLD, 50));
	    idJL.setPreferredSize(new Dimension(365,50));
	    idJL.setMaximumSize(idJL.getPreferredSize());
	   
	    idTF = new JTextField();
	    idTF.setFont(new Font(idTF.getFont().toString(), Font.PLAIN, 45));
	    idTF.setPreferredSize(new Dimension(300,50));
	    idTF.setMaximumSize(idTF.getPreferredSize());
	   
	    baja = new JButton("DAR DE BAJA AL EMPLEADO");
	    baja.setContentAreaFilled(false);
	    baja.setFocusPainted(false);
	    baja.setBorder(BorderFactory.createRaisedBevelBorder());
	    baja.setOpaque(true);
	    baja.setPreferredSize(new Dimension(230,80));
	    baja.setMaximumSize(baja.getPreferredSize());
	    baja.setBackground(Color.RED);
	    baja.setAlignmentX(JButton.CENTER_ALIGNMENT);
	   
	    imageIcon = new ImageIcon("icons/baja.png"); 
	    Image image = imageIcon.getImage(); 
	    Image newimg = image.getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH); 
	    imageIcon = new ImageIcon(newimg);  
	    baja.setIcon(imageIcon);
	   
	    baja.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) { 
	    		baja();
	    	}
	    });
	}
	
	public JPanel getDefaultLayout() {
		JPanel bajaEmpPanel = new JPanel(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    c.gridx = 0;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    bajaEmpPanel.add(idJL, c);
	    c.gridx = 1;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    bajaEmpPanel.add(idTF, c);
	    c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    bajaEmpPanel.add(Box.createRigidArea(new Dimension(0, 15)), c);
	    c.gridx = 0;
	    c.gridy = 2;
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.anchor = GridBagConstraints.CENTER;
	    bajaEmpPanel.add(baja, c);
	    
	    return bajaEmpPanel;
	}
	public void baja(){
		
		try {
    		if (idTF.getText().isEmpty()) throw new Exception("Campo sin rellenar.");
			
			TPersonal empleado = controlador.getEmpleado(Integer.parseInt(idTF.getText()));
    				    		
    		String msg = "ID: "+empleado.getId()+"\nDNI: "+empleado.getDni()+"\nNOMBRE: "+ empleado.getNombre() +
    				      "\nTELEFONO: "+empleado.getTelefono()+"\nSUELDO: "+empleado.getSueldo()+"\nHORARIO: "+empleado.getHorario()+"\n\n �Quieres dar de baja a este cliente?";
            int input = JOptionPane.showConfirmDialog(null, msg,"Confirmar baja de cliente", 
            		    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, imageIcon);
            
            if(input == JOptionPane.OK_OPTION) {
            	controlador.bajaPersonal(Integer.parseInt(idTF.getText()));
            	
            	JOptionPane.showMessageDialog(null,"Cliente con ID " + idTF.getText() + " dado de baja con �xito.",
						  					  "INFO",JOptionPane.INFORMATION_MESSAGE);
            }
		} 
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}

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
