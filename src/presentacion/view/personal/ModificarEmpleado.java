package presentacion.view.personal;

import java.awt.BorderLayout;
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
import presentacion.view.SwingFactory;

public class ModificarEmpleado{
	
	private PersonalController controlador;
	
	private JTextField idTF;
	private JTextField dniTF;
	private JTextField nombreTF;
	private JTextField sueldoTF;
	private JTextField tfnoTF;
	private JTextField horarioTF;
	private JButton modificar;
	private JButton limpiar;
	private ImageIcon modIcon;
	
	public ModificarEmpleado(PersonalController c) {
		this.controlador = c;
		initGUI();
	}
	
	private void initGUI() {
		
		idTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		dniTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		nombreTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		sueldoTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		tfnoTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		horarioTF = SwingFactory.getJTextField(new Dimension(550,35), 25);
		
		modificar = SwingFactory.getJButton(new Dimension(230,60), "MODIFICAR EMPLEADO", 
                							"icons/modificar", 50, new Color(250,243,58), new Color(230,215,73));
		limpiar = SwingFactory.getJButton(new Dimension(230,60), "LIMPIAR CAMPOS DE TEXTO", 
				  						  "icons/limpiar", 50, new Color(205,205,205),new Color(166,166,166));
		
		modificar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { modificar(); }
	    });
		
		limpiar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e ) { limpiar(); }
	    });
		
		modIcon = SwingFactory.getScaledIcon("icons/modificar", 45);
	}
	
	public JPanel getDefaultLayout() {
		JPanel modEmpPanel = new JPanel(new BorderLayout());
		
		JPanel campos = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		campos.add(SwingFactory.getJLabel(new Dimension(230,50), "ID EMPLEADO:" ,30), c);
		c.gridx = 1;
		c.gridy = 0;
		campos.add(idTF, c);
		c.gridx = 0;
		c.gridy = 1;
		campos.add(SwingFactory.getJLabel(new Dimension(230,50), "                   DNI:" ,30), c);
		c.gridx = 1;
		c.gridy = 1;
		campos.add(dniTF, c);
		c.gridx = 0;
		c.gridy = 2;
		campos.add(SwingFactory.getJLabel(new Dimension(230,50), "         NOMBRE:" ,30), c);
		c.gridx = 1;
		c.gridy = 2;
		campos.add(nombreTF, c);
		c.gridx = 0;
		c.gridy = 3;
		campos.add(SwingFactory.getJLabel(new Dimension(230,50), "          SUELDO:" ,30), c);
		c.gridx = 1;
		c.gridy = 3;
		campos.add(sueldoTF, c);
		c.gridx = 0;
		c.gridy = 4;
		campos.add(SwingFactory.getJLabel(new Dimension(230,50), "     TELEFONO:" ,30), c);
		c.gridx = 1;
		c.gridy = 4;
		campos.add(tfnoTF, c);
		c.gridx = 0;
		c.gridy = 5;
		campos.add(SwingFactory.getJLabel(new Dimension(230,50), "        HORARIO:" ,30),c);
		c.gridx=1;
		c.gridy=5;
		campos.add(horarioTF,c);
		
		
		JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		botones.setBorder(BorderFactory.createMatteBorder(
                  1, 0, 0, 0, Color.black));
		
		botones.add(modificar);
		botones.add(limpiar);
		
		modEmpPanel.add(campos,BorderLayout.CENTER);
		modEmpPanel.add(botones, BorderLayout.SOUTH);
		
		return modEmpPanel;
	}
	
	private void modificar(){
		try {
    		if (idTF.getText().isEmpty() || dniTF.getText().isEmpty() || nombreTF.getText().isEmpty()) {
    			throw new Exception("Campo(s) sin rellenar.");
    		}
			
			TPersonal empleado = controlador.getEmpleado(Integer.parseInt(idTF.getText()));
			
			String tfno;
			if (empleado.getTelefono() == null) tfno = "[Vacio]";
			else tfno = empleado.getTelefono();
			
			String tfnoNuevo; 
			if (tfnoTF.getText().isEmpty()) tfnoNuevo = "[Vacio]";
			else tfnoNuevo = tfnoTF.getText();

			String sueldo;
			if (empleado.getSueldo() == null) sueldo = "[Vacio]";
			else sueldo = empleado.getSueldo();
			
			String sueldoNuevo; 
			if (sueldoTF.getText().isEmpty()) sueldoNuevo = "[Vacio]";
			else sueldoNuevo = sueldoTF.getText();
			
			String horario;
			
			if (empleado.getHorario() == null) horario = "[Vacio]";
			else horario = empleado.getHorario();
			
			String horarioNuevo; 
			String numCadena= String.valueOf(0);
			if (horarioTF.getText().isEmpty()) horarioNuevo = "[Vacio]";
			else horarioNuevo = horarioTF.getText();
    				    		
    		String msg = "ID: "+empleado.getId()+"\n\nDNI: "+empleado.getDni()+"\nNUEVO DNI: "+dniTF.getText()+"\n\nNOMBRE: "
    					  + empleado.getNombre() +"\nNUEVO NOMBRE: " + nombreTF.getText() + "\n\nTELEFONO: "+tfno
    					  +"\nTELEFONO NUEVO: "+ tfnoNuevo +"\n\nSUELDO: "+sueldo
    					  +"\nSUELDO NUEVO: "+ sueldoNuevo +"\n\nHORARIO: "+horario
    					  +"\nHORARIO NUEVO: "+ horarioNuevo +"\n\n ¿Quieres cambiar los datos de este empleado?";
            int input = JOptionPane.showConfirmDialog(null, msg,"Confirmar cambios en el empleado", 
            		    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, modIcon);
            
            if(input == JOptionPane.OK_OPTION) {
            	if (!tfnoTF.getText().isEmpty()&&!sueldoTF.getText().isEmpty()&&!horarioTF.getText().isEmpty()) {
            		controlador.modificarPersonal(Integer.parseInt(idTF.getText()), dniTF.getText(), 
            	
            		nombreTF.getText(), tfnoTF.getText(),sueldoTF.getText(),horarioTF.getText());
            		}
            	else if(!tfnoTF.getText().isEmpty()&&!sueldoTF.getText().isEmpty()){
            		controlador.modificarPersonal(Integer.parseInt(idTF.getText()), dniTF.getText(), 
                        	
                    		nombreTF.getText(), tfnoTF.getText(),sueldoTF.getText(),numCadena);
            	}
            	else if(!tfnoTF.getText().isEmpty()&&!horarioTF.getText().isEmpty()){
            			controlador.modificarPersonal(Integer.parseInt(idTF.getText()), dniTF.getText(), 
                        	
                    		nombreTF.getText(),tfnoTF.getText(),numCadena,horarioTF.getText());
            	}
            	else if(!sueldoTF.getText().isEmpty()&&!horarioTF.getText().isEmpty()){
            		controlador.modificarPersonal(Integer.parseInt(idTF.getText()), dniTF.getText(), 
                        	
                    		nombreTF.getText(),numCadena,sueldoTF.getText(),horarioTF.getText());
            	}
            	else if(!tfnoTF.getText().isEmpty()){
            			controlador.modificarPersonal(Integer.parseInt(idTF.getText()), dniTF.getText(), 
                        	
                    		nombreTF.getText(),tfnoTF.getText(),numCadena,numCadena);
            	}
            	else if(!sueldoTF.getText().isEmpty()){
        			controlador.modificarPersonal(Integer.parseInt(idTF.getText()), dniTF.getText(), 
                    	
                		nombreTF.getText(),numCadena,sueldoTF.getText(),numCadena);
        	}
            	else if(!horarioTF.getText().isEmpty()){
        			controlador.modificarPersonal(Integer.parseInt(idTF.getText()), dniTF.getText(), 
                    	
                		nombreTF.getText(),numCadena,numCadena,horarioTF.getText());
        	}
            	
    			else {
    				
    				controlador.modificarPersonal(Integer.parseInt(idTF.getText()), dniTF.getText(), 
    			
						 						  nombreTF.getText(),numCadena,numCadena,numCadena);
    			}
            	JOptionPane.showMessageDialog(null,"Empleado con ID " + idTF.getText() + " modificado con exito.",
						  					  "INFO",JOptionPane.INFORMATION_MESSAGE);
            	
            }
		} 
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limpiar() {
		idTF.setText(" ");
		dniTF.setText(" ");
		nombreTF.setText(" ");
		sueldoTF.setText(" ");
		tfnoTF.setText(" ");
	}
}