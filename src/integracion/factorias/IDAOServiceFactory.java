package integracion.factorias;

import integracion.dao.IClienteDAO;
import integracion.dao.IMarcaDAO;
import integracion.dao.IPersonalDao;
import integracion.dao.IProductoDAO;
import integracion.daoImpl.SqlMarcaDAO;

public interface IDAOServiceFactory {
	public IClienteDAO getClienteDAO();
	public IProductoDAO getProductoDAO();
	public IPersonalDao getEmpleadoDAO();
	public IMarcaDAO getMarcaDAO();
}
