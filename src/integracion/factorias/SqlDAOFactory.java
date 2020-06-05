package integracion.factorias;

import integracion.dao.IClienteDAO;
import integracion.dao.IMarcaDAO;
import integracion.dao.IPersonalDao;
import integracion.dao.IProductoDAO;
import integracion.daoImpl.SqlClienteDAO;
import integracion.daoImpl.SqlMarcaDAO;
import integracion.daoImpl.SqlPersonalDAO;
import integracion.daoImpl.SqlProductoDAO;

public class SqlDAOFactory implements IDAOServiceFactory{

	@Override
	public IClienteDAO getClienteDAO() {
		return new SqlClienteDAO();
	}

	@Override
	public IProductoDAO getProductoDAO() {
		return new SqlProductoDAO();
	}

	@Override
	public IPersonalDao getEmpleadoDAO() {
		return new SqlPersonalDAO();
	}

	@Override
	public IMarcaDAO getMarcaDAO() {
		return new SqlMarcaDAO();
	}


}