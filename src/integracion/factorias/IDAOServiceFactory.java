package integracion.factorias;

import integracion.dao.IClienteDAO;
import integracion.dao.IProductoDAO;

public interface IDAOServiceFactory {
	public IClienteDAO getClienteDAO();
	public IProductoDAO getProductoDAO();
}
