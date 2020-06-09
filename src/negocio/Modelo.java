package negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import integracion.daoImpl.SqlClienteDAO;
import integracion.daoImpl.SqlMarcaDAO;
import integracion.daoImpl.SqlPersonalDAO;
import integracion.daoImpl.SqlProductoDAO;
import integracion.daoImpl.SqlVentaDAO;
import integracion.factorias.DAOServiceFactory;
import integracion.factorias.IDAOServiceFactory;
import integracion.transfers.TCliente;
import integracion.transfers.TMarca;
import integracion.transfers.TPc;
import integracion.transfers.TPeriferico;
import integracion.transfers.TPersonal;
import integracion.transfers.TProducto;
import integracion.transfers.TVenta;

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

    
	
	public TCliente altaCliente(TCliente cliente) throws Exception{
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		
		TCliente clienteYaRegistrado = clienteDAO.getClienteByDNI(cliente.getDni());
				
		if(clienteYaRegistrado != null && clienteYaRegistrado.isActivo()) throw new Exception("Cliente ya existente.");
		if(clienteYaRegistrado != null && !clienteYaRegistrado.isActivo()) return clienteYaRegistrado;
		
		clienteDAO.altaCliente(cliente);
		
		logObserver.alta(Entity.CLIENTE);
		
		return null;	
	}
	
	public void reactivarCliente(int id) throws Exception {
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		
		TCliente cliente = clienteDAO.getClienteByID(id);
		
		if (cliente == null) throw new Exception("Cliente inexistente.");
		if (cliente != null && cliente.isActivo()) throw new Exception("El cliente ya esta activo.");
		
		clienteDAO.reactivarCliente(id);
	}
	
	public void bajaCliente(int id) throws Exception {
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		TCliente cliente = clienteDAO.getClienteByID(id);
			
		if(cliente == null) throw new Exception("Cliente inexistente.");
		if (!cliente.isActivo()) throw new Exception("El cliente ya esta dado de baja.");
		
		clienteDAO.bajaCliente(id);
		
		logObserver.baja(Entity.CLIENTE);
	}
	
	public void modificarCliente(TCliente cliente) throws Exception {
		SqlClienteDAO clienteDAO = (SqlClienteDAO) factoryDAO.getClienteDAO();
		TCliente clienteID = clienteDAO.getClienteByID(cliente.getId());
		TCliente clienteDNI = clienteDAO.getClienteByDNI(cliente.getDni());
			
		if (clienteID == null) throw new Exception("Cliente inexistente.");
		if (!clienteID.isActivo()) throw new Exception("El cliente esta inactivo.");
		if (clienteDNI != null && cliente.getId() != clienteDNI.getId()) throw new Exception("Ya existe otro cliente con ese DNI.");
		
		clienteDAO.modificarCliente(cliente);
			
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
	
	
	public TPersonal altaPersonal(TPersonal personal) throws Exception {
	
		SqlPersonalDAO personalDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		
		TPersonal personalYaRegistrado = personalDAO.getEmpleadoByDNI(personal.getDni());
				
		if(personalYaRegistrado != null && personalYaRegistrado.isActivo()) throw new Exception("Cliente ya existente.");
		if(personalYaRegistrado != null && !personalYaRegistrado.isActivo()) return personalYaRegistrado;
		
		personalDAO.altaEmpleado(personal);
		
		logObserver.alta(Entity.PERSONAL);
		
		return null;	
		
	}
	
	public void reactivarPersonal(int id) throws Exception {
		SqlPersonalDAO personalDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		
		TPersonal personal = personalDAO.getEmpleadoByID(id);
		
		if (personal == null) throw new Exception("Empleado inexistente.");
		if (personal != null && personal.isActivo()) throw new Exception("El empleado ya esta activo.");
		
		personalDAO.reactivarPersonal(id);
	}
	
	public void bajaPersonal(int id) throws Exception {
		
		SqlPersonalDAO empleadoDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		
		TPersonal empleado = empleadoDAO.getEmpleadoByID(id);
			
		if(empleado == null) throw new Exception("Empleado inexistente.");
		
		if (!empleado.isActivo()) throw new Exception("El empleado ya esta dado de baja.");
		
		empleadoDAO.bajaEmpleado(id);
	
		
	}
	
	public void modificarPersonal(TPersonal personal) throws Exception{
		
		SqlPersonalDAO empleadoDAO = (SqlPersonalDAO) factoryDAO.getEmpleadoDAO();
		TPersonal personalID = empleadoDAO.getEmpleadoByID(personal.getId());
		TPersonal personalDNI = empleadoDAO.getEmpleadoByDNI(personal.getDni());
			
		if (personalID == null) throw new Exception("Empleado inexistente.");
		if (!personalID.isActivo()) throw new Exception("El empleado esta inactivo.");
		if (personalDNI != null && personal.getId() != personalDNI.getId()) throw new Exception("Ya existe otro empleado con ese DNI.");
		
		empleadoDAO.modificarEmpleado(personal);
				
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
	
	
	
	public void altaMarca(TMarca marca) throws Exception {
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		
		TMarca marcaYaRegistrada = marcaDAO.getMarcaByCIF(marca.getCIF());
		
		if(marcaYaRegistrada != null && marcaYaRegistrada.isActivo()) throw new Exception("Marca ya existente.");
		
		marcaDAO.altaMarca(marca);
	}
	
	public void bajaMarca(int id) throws Exception {
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		
		TMarca marca = marcaDAO.getMarcaByID(id);
		
		if(marca == null) throw new Exception("Marca inexistente.");
		
		if(!marca.isActivo()) throw new Exception("La marca ya esta dada de baja.");
		
		marcaDAO.bajaMarca(id);
		
	}
	
	public void modificarMarca(TMarca marca) throws Exception {
		SqlMarcaDAO marcaDAO = (SqlMarcaDAO) factoryDAO.getMarcaDAO();
		
		TMarca marcaID = marcaDAO.getMarcaByID(marca.getId());
		TMarca marcaCIF = marcaDAO.getMarcaByCIF(marca.getCIF());
		
		if(marcaID == null) throw new Exception("Marca inexistente.");
		if(!marcaID.isActivo()) throw new Exception("La marca esta inactiva.");
		if(marcaCIF != null && marca.getId() != marcaCIF.getId()) throw new Exception("Ya existe otra marca con ese CIF.");
		
		marcaDAO.modificarMarca(marca);
		
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

	public void reactivarProducto(int id) throws Exception {
		SqlProductoDAO productoDAO = (SqlProductoDAO) factoryDAO.getProductoDAO();
		
		TProducto producto = productoDAO.getById(id);
		
		if (producto == null) throw new Exception("Producto inexistente.");
		if (producto != null && producto.isActivo()) throw new Exception("El producto ya esta activo.");
		
		productoDAO.reactivarProducto(id);
	}
	
	public void bajaProducto(int id) throws Exception {
		SqlProductoDAO productoDAO = (SqlProductoDAO) factoryDAO.getProductoDAO();

		productoDAO.bajaProducto(id);
	}
	
	public List<TProducto> listarProductos() {
		SqlProductoDAO productoDAO = (SqlProductoDAO) factoryDAO.getProductoDAO();
		List<TProducto> productoList = productoDAO.getAll();
			
		//logObserver.listar(Entity.PRODUCTO);
			
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
	
	
	//--------------VENTAS--------------------------//
	

	public void comprobarStock(String nombre, int unidades) throws Exception {
		SqlProductoDAO productoDAO = (SqlProductoDAO) factoryDAO.getProductoDAO();
		
		TProducto producto = productoDAO.getProductByName(nombre);
		
		if(producto == null || !producto.isActivo()) throw new Exception("Producto " + nombre + " inexistente");
		if(producto.getCantidad() < unidades) throw new Exception("No hay suficientes unidades del producto '" + nombre +"'");
	
	}
	
	public void cerrarVenta(TVenta venta, HashMap<String,Integer> mapaProductos) {
		SqlProductoDAO productoDAO = (SqlProductoDAO) factoryDAO.getProductoDAO();
		SqlVentaDAO ventaDAO = (SqlVentaDAO) factoryDAO.getVentaDAO();
		
		List<String> list = new ArrayList<>(mapaProductos.keySet());
		for(String nombreProducto: list) {
			TProducto producto = productoDAO.getProductByName(nombreProducto);
			
			venta.addLineaVenta(producto.getId(), nombreProducto,mapaProductos.get(nombreProducto),
								producto.getPrecio());
			
		    productoDAO.cambiarUnidades(producto.getId(),producto.getCantidad() - mapaProductos.get(nombreProducto));
			
		}
		
		ventaDAO.altaVenta(venta);
		
	}
	
}
