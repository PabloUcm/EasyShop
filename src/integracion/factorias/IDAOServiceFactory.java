package integracion.factorias;

import integracion.dao.IClienteDAO;
import integracion.dao.IMarcaDAO;
import integracion.dao.IPersonalDAO;
import integracion.dao.IProductoDAO;
import integracion.dao.IVentaDAO;

public interface IDAOServiceFactory {
	public IClienteDAO getClienteDAO();
	public IProductoDAO getProductoDAO();
	public IPersonalDAO getEmpleadoDAO();
	public IMarcaDAO getMarcaDAO();
	public IVentaDAO getVentaDAO();
}
