package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import integracion.daoImpl.SqlClienteDAO;
import integracion.factorias.DAOServiceFactory;
import integracion.factorias.IDAOServiceFactory;
import integracion.transfers.TCliente;

public class Modelo {
    private static Modelo modelo;
    private List<ClienteObserver> clienteObservers;
    private List<PersonalObserver> personalObservers;
    private List<VentaObserver> ventaObservers;
    private List<ProductoObserver> productoObservers;
    private List<MarcaObserver> marcaObservers;    
    private IDAOServiceFactory factoryDAO;

    private Modelo() {
    	clienteObservers = new ArrayList<>();
    	factoryDAO = DAOServiceFactory.getDefaultFactory();
    }

    public static Modelo getModelo() {
        if (modelo == null) modelo = new Modelo();

        return modelo;
    }

	public void addObserver(ClienteObserver o) {
		clienteObservers.add(o);
		
	}
	
	public void addObserver(PersonalObserver o) {
		// TODO Auto-generated method stub
		
	}
	
	public void addObserver(VentaObserver o) {
		// TODO Auto-generated method stub
		
	}
	
	public void addObserver(ProductoObserver o) {
		// TODO Auto-generated method stub
		
	}
	
	public void addObserver(MarcaObserver o) {
		// TODO Auto-generated method stub
		
	}
	
	public void listarClientes() {
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		List<TCliente> clienteList = clienteDAO.getAllClientes();
		
		for(ClienteObserver o: clienteObservers) o.mostrarCliente(clienteList);
	}
	
	public void altaCliente(String dni, String nombre, String telefono){
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		
		try {
			
		if(clienteDAO.getClienteByDNI(dni) != null) throw new Exception("Cliente ya existente");
		else clienteDAO.altaCliente(new TCliente(dni,nombre,telefono));
		
		for(ClienteObserver o: clienteObservers) o.altaCliente();
		
		JOptionPane.showMessageDialog(null,"Cliente " + nombre + " registrado con �xito","Error icon",JOptionPane.INFORMATION_MESSAGE);
		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void bajaCliente(String dni) {
		System.out.println("Cliente dado de baja");
	}
	
	public void getCliente(String dni) {
		//obtengo cliente
		for(ClienteObserver o: clienteObservers) o.obtenerCliente();
	}
	
	public void altaPersonal(String dni, String nombre, int sueldo, String telefono) {
		
		//for(PersonalObserver o: personalObservers)
	}
	
	public void bajaPersonal(String dni) {
		
		//for(PersonalObserver o: personalObservers) 
	}
	
	public void altaProducto(String dni, String nombre, String telefono) {
		
		//for(ProductoObserver o: productoObservers) 
	}
	
	public void bajaProducto(String dni) {
		
		 
		//for(ProductoObserver o: productoObservers) 
	}
	
	
}
