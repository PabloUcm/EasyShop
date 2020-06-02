package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import integracion.daoImpl.SqlClienteDAO;
import integracion.daoImpl.SqlMarcaDAO;
import integracion.daoImpl.SqlPersonalDAO;
import integracion.factorias.DAOServiceFactory;
import integracion.factorias.IDAOServiceFactory;
import integracion.transfers.TCliente;
import integracion.transfers.TMarca;
import integracion.transfers.TPersonal;

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
    	personalObservers = new ArrayList<>();
    	marcaObservers = new ArrayList<>();
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
		personalObservers.add(o);
		
	}
	
	public void addObserver(VentaObserver o) {
		// TODO Auto-generated method stub
		
	}
	
	public void addObserver(ProductoObserver o) {
		// TODO Auto-generated method stub
		
	}
	
	public void addObserver(MarcaObserver o) {
		marcaObservers.add(o);
		
	}
	
	public void listarClientes() {
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		List<TCliente> clienteList = clienteDAO.getAllClientes();
		
		for(ClienteObserver o: clienteObservers) o.mostrarCliente(clienteList);
	}
	
	public void altaCliente(String dni, String nombre, String telefono) throws Exception{
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		
		TCliente cliente = clienteDAO.getClienteByDNI(dni);
				
		if(cliente != null && cliente.isActivo()) throw new Exception("Cliente ya existente.");
		
		int id = clienteDAO.altaCliente(new TCliente(dni,nombre,telefono));
		
		cliente = clienteDAO.getClienteByID(id);
		
		for(ClienteObserver o: clienteObservers) o.altaCliente(cliente);		
	}
	
	public void bajaCliente(int id) throws Exception {
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		TCliente cliente = clienteDAO.getClienteByID(id);
			
		if(cliente == null) throw new Exception("Cliente inexistente.");
		if (!cliente.isActivo()) throw new Exception("El cliente ya esta dado de baja.");
		
		clienteDAO.bajaCliente(id);
			
		for(ClienteObserver o: clienteObservers) o.bajaCliente(cliente);
	}
	
	public void modificarCliente(int id, String dni, String nombre, String telefono) throws Exception {
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		TCliente cliente = clienteDAO.getClienteByID(id);
		TCliente clienteDNI = clienteDAO.getClienteByDNI(dni);
			
		if (cliente == null) throw new Exception("Cliente inexistente.");
		if (!cliente.isActivo()) throw new Exception("El cliente esta inactivo.");
		if (clienteDNI != null && id != clienteDNI.getId()) throw new Exception("Ya existe otro cliente con ese DNI.");
		
		clienteDAO.modificarCliente(id, new TCliente(dni,nombre,telefono));
			
		for(ClienteObserver o: clienteObservers) o.modificarCliente();
	}
	
	public TCliente getCliente(int id) throws Exception {		
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		TCliente cliente = clienteDAO.getClienteByID(id);
			
		if(cliente == null || !cliente.isActivo()) throw new Exception("Cliente inexistente.");
			
		for(ClienteObserver o: clienteObservers) o.obtenerCliente(cliente);
		
		return cliente;
	}
	
	public void altaPersonal(String dni, String nombre, String sueldo, String telefono,String horario) throws Exception {
	
		SqlPersonalDAO empleadoDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		
		TPersonal empleado = empleadoDAO.getEmpleadoByDNI(dni);
				
		if(empleado != null && empleado.isActivo()) throw new Exception("Empleado ya existente.");
		
		int id = empleadoDAO.altaEmpleado(new TPersonal(dni,nombre,sueldo,telefono,horario));
		
		empleado = empleadoDAO.getEmpleadoByID(id);
		
		for(PersonalObserver o: personalObservers) o.altaEmpleado(empleado);
	}
	
	public void bajaPersonal(int id) throws Exception {
		
		SqlPersonalDAO empleadoDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		
		TPersonal empleado = empleadoDAO.getEmpleadoByID(id);
			
		if(empleado == null) throw new Exception("Empleado inexistente.");
		
		if (!empleado.isActivo()) throw new Exception("El empleado ya esta dado de baja.");
		
		empleadoDAO.bajaEmpleado(id);
	
		
		for(PersonalObserver o: personalObservers) o.bajaEmpleado(empleado);
	}
	
	public void modificarPersonal(int id, String dni, String nombre, String sueldo,String telefono,String horario) throws Exception{
		
		SqlPersonalDAO empleadoDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		TPersonal empleadoId = empleadoDAO.getEmpleadoByID(id);
		TPersonal empleadoDNI = empleadoDAO.getEmpleadoByDNI(dni);
			
		if (empleadoId == null) throw new Exception("Empleado inexistente.");
		if (!empleadoId.isActivo()) throw new Exception("El empleado esta inactivo.");
		if (empleadoDNI != null && id != empleadoDNI.getId()) throw new Exception("Ya existe otro empleado con ese DNI.");
		
		empleadoDAO.modificarEmpleado(id, new TPersonal(dni,nombre,telefono,sueldo,horario));
			
		for(PersonalObserver o: personalObservers) o.bajaEmpleado(null);
		
	}
	public void listarEmpleados(){
		SqlPersonalDAO empleadoDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		List<TPersonal> empleadoList = empleadoDAO.getAllEmpleados();
		
		for(PersonalObserver o: personalObservers) o.mostrarEmpleado(empleadoList);
		
		
	}
	public TPersonal getEmpleado(int id) throws Exception {		
		
		SqlPersonalDAO empleadoDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		
		TPersonal empleado = empleadoDAO.getEmpleadoByID(id);
			
		if(empleado == null || !empleado.isActivo()) throw new Exception("Empleado inexistente.");
			
		for(PersonalObserver o: personalObservers) o.obtenerEmpleado(empleado);
		
		return empleado;
	}
	
	public void altaMarca(String cif, String nombre, String pais) throws Exception {
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		
		TMarca marca = marcaDAO.getMarcaByCIF(cif);
		
		if(marca != null && marca.isActivo()) throw new Exception("Marca ya existente.");
		
		int id = marcaDAO.altaMarca(new TMarca(cif, nombre, pais));
		marca = marcaDAO.getMarcaByID(id);
		
		for(MarcaObserver o : marcaObservers) o.altaMarca(marca);
	}
	
	public void bajaMarca(int id) throws Exception {
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		
		TMarca marca = marcaDAO.getMarcaByID(id);
		
		if(marca == null) throw new Exception("Marca inexistente.");
		
		if(!marca.isActivo()) throw new Exception("La marca ya esta dada de baja.");
		
		marcaDAO.bajaMarca(id);
		
		for(MarcaObserver o : marcaObservers) o.bajaMarca(marca);
	}
	
	public void modificarMarca(int id, String cif, String nombre, String pais) throws Exception {
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		
		TMarca marca = marcaDAO.getMarcaByID(id);
		TMarca marcaCIF = marcaDAO.getMarcaByCIF(cif);
		
		if(marca == null) throw new Exception("Marca inexistente.");
		if(!marca.isActivo()) throw new Exception("La marca esta inactiva.");
		if(marcaCIF != null && id != marcaCIF.getId()) throw new Exception("Ya existe otra marca con ese CIF.");
		
		marcaDAO.modificarMarca(id, new TMarca(cif, nombre, pais));
		
		for(MarcaObserver o : marcaObservers) o.bajaMarca(null);
		
	}
	
	public void listarMarcas() {
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		List<TMarca> marcaList = marcaDAO.getAllMarcas();
		
		for(MarcaObserver o: marcaObservers) o.mostrarMarca(marcaList);
	}
	
	public TMarca getMarca(int id) throws Exception{
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		
		TMarca marca = marcaDAO.getMarcaByID(id);
		
		if(marca == null || !marca.isActivo()) throw new Exception("Marca inexistente.");
		
		for(MarcaObserver o: marcaObservers) o.obtenerMarca(marca);
		
		return marca;
	}
	
	public void altaProducto(String dni, String nombre, String telefono) {
		
		//for(ProductoObserver o: productoObservers) 
	}
	
	public void bajaProducto(String dni) {
		
		 
		//for(ProductoObserver o: productoObservers) 
	}
	
	
}
