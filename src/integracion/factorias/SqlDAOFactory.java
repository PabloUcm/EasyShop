package integracion.factorias;

import integracion.dao.IClienteDAO;
import integracion.dao.IMarcaDAO;
import integracion.dao.IPersonalDao;
import integracion.dao.IProductoDAO;
import integracion.daoImpl.SqlClienteDAO;
import integracion.daoImpl.SqlMarcaDAO;
import integracion.daoImpl.SqlPersonalDAO;

public class SqlDAOFactory implements IDAOServiceFactory{

	@Override
	public IClienteDAO getClienteDAO() {
		return new SqlClienteDAO();
	}

	@Override
	public IProductoDAO getProductoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPersonalDao getEmpleadoDAO() {
		// TODO Auto-generated method stub
		return new SqlPersonalDAO();
	}

	@Override
	public IMarcaDAO getMarcaDAO() {
		return new SqlMarcaDAO();
	}


}