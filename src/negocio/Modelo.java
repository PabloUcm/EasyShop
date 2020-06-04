package negocio;

import java.util.List;


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
    private LogObserver logObserver;
    private IDAOServiceFactory factoryDAO;

    private Modelo() {
    	factoryDAO = DAOServiceFactory.getDefaultFactory();
    }

    public static Modelo getModelo() {
        if (modelo == null) modelo = new Modelo();

        return modelo;
    }
    
    public void addObserver(LogObserver logObserver) {
    	this.logObserver = logObserver;
    }
    
    
    //-------------CLIENTES-----------------//

    
	
	public TCliente altaCliente(String dni, String nombre, String telefono) throws Exception{
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		
		TCliente cliente = clienteDAO.getClienteByDNI(dni);
				
		if(cliente != null && cliente.isActivo()) throw new Exception("Cliente ya existente.");
		if(cliente != null && !cliente.isActivo()) return cliente;
		
		int id = clienteDAO.altaCliente(new TCliente(dni,nombre,telefono));
		
		cliente = clienteDAO.getClienteByID(id);
		
		logObserver.alta(Entity.CLIENTE);
		
		return null;	
	}
	
	public void reactivarCliente(TCliente cliente) {
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		
		clienteDAO.reactivarCliente(cliente);
	}
	
	public void bajaCliente(int id) throws Exception {
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		TCliente cliente = clienteDAO.getClienteByID(id);
			
		if(cliente == null) throw new Exception("Cliente inexistente.");
		if (!cliente.isActivo()) throw new Exception("El cliente ya esta dado de baja.");
		
		clienteDAO.bajaCliente(id);
		
		logObserver.baja(Entity.CLIENTE);
	}
	
	public void modificarCliente(int id, String dni, String nombre, String telefono) throws Exception {
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		TCliente cliente = clienteDAO.getClienteByID(id);
		TCliente clienteDNI = clienteDAO.getClienteByDNI(dni);
			
		if (cliente == null) throw new Exception("Cliente inexistente.");
		if (!cliente.isActivo()) throw new Exception("El cliente esta inactivo.");
		if (clienteDNI != null && id != clienteDNI.getId()) throw new Exception("Ya existe otro cliente con ese DNI.");
		
		clienteDAO.modificarCliente(id, new TCliente(dni,nombre,telefono));
			
		logObserver.modificar(Entity.CLIENTE);
	}
	
	
	public TCliente getCliente(int id) throws Exception {		
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		TCliente cliente = clienteDAO.getClienteByID(id);
			
		if(cliente == null || !cliente.isActivo()) throw new Exception("Cliente inexistente.");
			
		logObserver.mostrar(Entity.CLIENTE);
		
		return cliente;
	}
	
	  
	public List<TCliente> listarClientes() {
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		List<TCliente> clienteList = clienteDAO.getAllClientes();
			
		logObserver.listar(Entity.CLIENTE);
			
		return clienteList;
	}
	
	
	 //-------------PERSONAL-----------------//
	
	
	public void altaPersonal(String dni, String nombre, String sueldo, String telefono,String horario) throws Exception {
	
		SqlPersonalDAO empleadoDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		
		TPersonal empleado = empleadoDAO.getEmpleadoByDNI(dni);
				
		if(empleado != null && empleado.isActivo()) throw new Exception("Empleado ya existente.");
		
		int id = empleadoDAO.altaEmpleado(new TPersonal(dni,nombre,sueldo,telefono,horario));
		
		empleado = empleadoDAO.getEmpleadoByID(id);
		
	}
	
	public void bajaPersonal(int id) throws Exception {
		
		SqlPersonalDAO empleadoDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		
		TPersonal empleado = empleadoDAO.getEmpleadoByID(id);
			
		if(empleado == null) throw new Exception("Empleado inexistente.");
		
		if (!empleado.isActivo()) throw new Exception("El empleado ya esta dado de baja.");
		
		empleadoDAO.bajaEmpleado(id);
	
		
	}
	
	public void modificarPersonal(int id, String dni, String nombre, String sueldo,String telefono,String horario) throws Exception{
		
		SqlPersonalDAO empleadoDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		TPersonal empleadoId = empleadoDAO.getEmpleadoByID(id);
		TPersonal empleadoDNI = empleadoDAO.getEmpleadoByDNI(dni);
			
		if (empleadoId == null) throw new Exception("Empleado inexistente.");
		if (!empleadoId.isActivo()) throw new Exception("El empleado esta inactivo.");
		if (empleadoDNI != null && id != empleadoDNI.getId()) throw new Exception("Ya existe otro empleado con ese DNI.");
		
		empleadoDAO.modificarEmpleado(id, new TPersonal(dni,nombre,telefono,sueldo,horario));
				
	}
	
	public void listarEmpleados(){
		SqlPersonalDAO empleadoDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		List<TPersonal> empleadoList = empleadoDAO.getAllEmpleados();
		
	}
	
	public TPersonal getEmpleado(int id) throws Exception {		
		
		SqlPersonalDAO empleadoDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		
		TPersonal empleado = empleadoDAO.getEmpleadoByID(id);
			
		if(empleado == null || !empleado.isActivo()) throw new Exception("Empleado inexistente.");
		
		return empleado;
	}
	
	
	
	 //-------------MARCAS-----------------//
	
	
	
	public void altaMarca(String cif, String nombre, String pais) throws Exception {
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		
		TMarca marca = marcaDAO.getMarcaByCIF(cif);
		
		if(marca != null && marca.isActivo()) throw new Exception("Marca ya existente.");
		
		int id = marcaDAO.altaMarca(new TMarca(cif, nombre, pais));
		marca = marcaDAO.getMarcaByID(id);
		
	}
	
	public void bajaMarca(int id) throws Exception {
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		
		TMarca marca = marcaDAO.getMarcaByID(id);
		
		if(marca == null) throw new Exception("Marca inexistente.");
		
		if(!marca.isActivo()) throw new Exception("La marca ya esta dada de baja.");
		
		marcaDAO.bajaMarca(id);
		
	}
	
	public void modificarMarca(int id, String cif, String nombre, String pais) throws Exception {
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		
		TMarca marca = marcaDAO.getMarcaByID(id);
		TMarca marcaCIF = marcaDAO.getMarcaByCIF(cif);
		
		if(marca == null) throw new Exception("Marca inexistente.");
		if(!marca.isActivo()) throw new Exception("La marca esta inactiva.");
		if(marcaCIF != null && id != marcaCIF.getId()) throw new Exception("Ya existe otra marca con ese CIF.");
		
		marcaDAO.modificarMarca(id, new TMarca(cif, nombre, pais));
		
	}
	
	public void listarMarcas() {
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		List<TMarca> marcaList = marcaDAO.getAllMarcas();
		
	}
	
	public TMarca getMarca(int id) throws Exception{
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		
		TMarca marca = marcaDAO.getMarcaByID(id);
		
		if(marca == null || !marca.isActivo()) throw new Exception("Marca inexistente.");
		
		return marca;
	}	
	
}
