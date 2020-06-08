package negocio;

import java.util.List;

import integracion.daoImpl.SqlClienteDAO;
import integracion.daoImpl.SqlMarcaDAO;
import integracion.daoImpl.SqlPersonalDAO;
import integracion.daoImpl.SqlProductoDAO;
import integracion.factorias.DAOServiceFactory;
import integracion.factorias.IDAOServiceFactory;
import integracion.transfers.TCliente;
import integracion.transfers.TMarca;
import integracion.transfers.TPc;
import integracion.transfers.TPeriferico;
import integracion.transfers.TPersonal;
import integracion.transfers.TProducto;

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
	
	
	public TPersonal altaPersonal(String dni, String nombre, String sueldo, String telefono,String horario) throws Exception {
	
		SqlPersonalDAO personalDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		
		TPersonal personal = personalDAO.getEmpleadoByDNI(dni);
				
		if(personal != null && personal.isActivo()) throw new Exception("Cliente ya existente.");
		if(personal != null && !personal.isActivo()) return personal;
		
		int id = personalDAO.altaEmpleado(new TPersonal(dni,nombre,telefono,sueldo,horario));
		
		personal = personalDAO.getEmpleadoByID(id);
		
		logObserver.alta(Entity.PERSONAL);
		
		return personal;	
		
	}
	
	public void reactivarPersonal(TPersonal personal) {
		SqlPersonalDAO personalDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		
		personalDAO.reactivarPersonal(personal);
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
	
	public List<TPersonal> listarPersonal(){
		SqlPersonalDAO empleadoDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		List<TPersonal> empleadoList = empleadoDAO.getAllEmpleados();
		
		logObserver.listar(Entity.PERSONAL);
		
		return empleadoList;	
	}
	
	public TPersonal getPersonal(int id) throws Exception {		
		
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
	
	public List<TMarca> listarMarcas() {
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		List<TMarca> marcaList = marcaDAO.getAllMarcas();
		
		logObserver.listar(Entity.MARCA);
		
		return marcaList;		
	}
	
	public TMarca getMarca(int id) throws Exception{
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		
		TMarca marca = marcaDAO.getMarcaByID(id);
		
		if (marca == null || !marca.isActivo()) throw new Exception("Marca inexistente.");
		
		return marca;
	}	
	
	//-------PRODUCTOS-----------//
	
	public TProducto altaProducto(TProducto producto, String nombreMarca) throws Exception {
		SqlProductoDAO productoDAO = (SqlProductoDAO) factoryDAO.getProductoDAO();
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		
		TMarca marca = marcaDAO.getMarcaByName(nombreMarca);
		
		if (marca == null ||!marca.isActivo()) throw new Exception("Marca inexistente.");
		
		producto.setMarcaId(marca.getId());
		
		TProducto productoRegistrado = productoDAO.getProductoByUPC(producto.getUPC());
		
		if (productoRegistrado != null && productoRegistrado.isActivo()) throw new Exception("Producto ya existente.");
		if (productoRegistrado != null && !productoRegistrado.isActivo()) {
			if (!producto.getTipo().equals(productoRegistrado.getTipo())) {
				throw new Exception("El producto esta registrado e inactivo, pero no es del tipo "+producto.getTipo());
			}
			
			TMarca marcaProdReg = marcaDAO.getMarcaByID(productoRegistrado.getMarcaId());
			
			if (!marcaProdReg.isActivo()) {
				throw new Exception("El producto esta registrado e inactivo. No se puede reactivar porque su marca "+marca.getNombre()+
									" (ID: "+marca.getId()+") esta dada de baja.");
			}
			
			if (productoRegistrado.getTipo().equals("PC")) return productoDAO.getPCByID(productoRegistrado.getId());
			else if (productoRegistrado.getTipo().equals("Periferico")) return productoDAO.getPerifericoByID(productoRegistrado.getId());
			else return productoRegistrado;
		}
		
		int id = productoDAO.altaProducto(producto);
		
		if (producto.getTipo().equals("PC")) productoDAO.altaPC( (TPc) producto, id);
		else if (producto.getTipo().equals("Periferico")) productoDAO.altaPeriferico( (TPeriferico) producto, id);
		
		return null;
		
	}

	public void reactivarProducto(TProducto producto) {
		SqlProductoDAO productoDAO = (SqlProductoDAO) factoryDAO.getProductoDAO();
		
		productoDAO.reactivarProducto(producto);
		if (producto.getTipo().equals("PC")) productoDAO.modificarPc((TPc) producto);
		else if (producto.getTipo().equals("Periferico")) productoDAO.modificarPeriferico((TPeriferico) producto);
	}
	
	public void bajaProducto(int id) throws Exception {
		SqlProductoDAO productoDAO = (SqlProductoDAO) factoryDAO.getProductoDAO();

		productoDAO.bajaProducto(id);
	}
	
	public List<TProducto> listarProductos() {
		SqlProductoDAO productoDAO = (SqlProductoDAO) factoryDAO.getProductoDAO();
		List<TProducto> productoList = productoDAO.getAll();
			
		logObserver.listar(Entity.PRODUCTO);
			
		return productoList;
	}

	public TPc getPcById(int id) throws Exception {
		SqlProductoDAO productoDAO = (SqlProductoDAO) factoryDAO.getProductoDAO();
		
		TPc pc = productoDAO.getPCByID(id);
		
		if (pc == null || !pc.isActivo()) throw new Exception("PC inexistente.");
		
		return pc;
	}
	
	public TPeriferico getPerifericoById(int id) throws Exception {
		SqlProductoDAO productoDAO = (SqlProductoDAO) factoryDAO.getProductoDAO();
		
		TPeriferico periferico = productoDAO.getPerifericoByID(id);
		
		if (periferico == null || !periferico.isActivo()) throw new Exception("Periferico inexistente.");
		
		return periferico;
	}
	
	public TProducto getProductoById(int id,String tipo) throws Exception {
		SqlProductoDAO productoDAO = (SqlProductoDAO) factoryDAO.getProductoDAO();
		
		TProducto producto = productoDAO.getById(id);
		
		if(producto == null || !producto.isActivo()) throw new Exception("Producto inexistente.");
		if(producto != null && !tipo.equals("NONE") && !producto.getTipo().equals(tipo)) throw new Exception("Tipo de producto erroneo.");
		
		if (tipo.equals("PC")) producto = productoDAO.getPCByID(id);
		else if (tipo.equals("Periferico")) producto = productoDAO.getPerifericoByID(id);
		
		return producto;
	}
	
	public void modificarProducto(TProducto producto) {
		SqlProductoDAO productoDAO = (SqlProductoDAO) factoryDAO.getProductoDAO();
		
		productoDAO.modificarProducto(producto);
		if(producto.getTipo().equals("PC")) productoDAO.modificarPc((TPc) producto);
		else if(producto.getTipo().equals("Periferico")) productoDAO.modificarPeriferico((TPeriferico) producto);
		
	}
	
	public List<String> getNombreMarcas() {
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		
		List<String> nombres = marcaDAO.getNombreMarcas();
		
		return nombres;
	}
	
	public String getNombreMarcaByID(int id) {
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		
		TMarca marca = marcaDAO.getMarcaByID(id);
		
		return marca.getNombre();
	}
	
}
