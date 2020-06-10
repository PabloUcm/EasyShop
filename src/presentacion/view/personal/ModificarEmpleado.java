package presentacion.view.personal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import integracion.transfers.TPersonal;
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
    		if (idTF.getText().trim().equals("") || dniTF.getText().trim().equals("") || 
    			nombreTF.getText().trim().equals("") || tfnoTF.getText().trim().equals("") ||
    			sueldoTF.getText().trim().equals("") || horarioTF.getText().trim().equals("")) 
    		{
    			throw new Exception("Campo(s) sin rellenar.");
    		}
			
			TPersonal personal = controlador.getPersonal(Integer.parseInt(idTF.getText()));
    				    		
    		String msg = "ID: "+personal.getId()+"\n\nDNI: "+personal.getDni()+"\nNUEVO DNI: "+dniTF.getText()+"\n\nNOMBRE: "
    					  + personal.getNombre() +"\nNUEVO NOMBRE: " + nombreTF.getText() + "\n\nTELEFONO: "+personal.getTelefono()
    					  +"\nTELEFONO NUEVO: "+ tfnoTF.getText() +"\n\nSUELDO: "+personal.getSueldo()
    					  +"\nSUELDO NUEVO: "+ sueldoTF.getText() +"\n\nHORARIO: "+personal.getHorario()
    					  +"\nHORARIO NUEVO: "+ horarioTF.getText() +"\n\n ¿Quieres cambiar los datos de este empleado?";
            int input = JOptionPane.showConfirmDialog(null, msg,"Confirmar cambios en el empleado", 
            		    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, modIcon);
            
            if(input == JOptionPane.OK_OPTION) {
            	personal.setDni(dniTF.getText());
            	personal.setNombre(nombreTF.getText());
            	personal.setTelefono(tfnoTF.getText());
            	personal.setSueldo(Double.parseDouble(sueldoTF.getText()));
            	personal.setHorario(horarioTF.getText());
            	
            	controlador.modificarPersonal(personal);
            	
            	JOptionPane.showMessageDialog(null,"Empleado con ID " + idTF.getText() + " modificado con exito.",
						  					  "INFO",JOptionPane.INFORMATION_MESSAGE);
            	
            }
            limpiar();
		} 
		catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,"Los campos 'ID' y 'sueldo' deben ser un numero.", "ERROR",JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limpiar() {
		idTF.setText("");
		dniTF.setText("");
		nombreTF.setText("");
		sueldoTF.setText("");
		tfnoTF.setText("");
		horarioTF.setText("");
	}
}