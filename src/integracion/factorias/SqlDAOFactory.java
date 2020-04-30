package integracion.factorias;

import integracion.dao.IClienteDAO;
import integracion.dao.IProductoDAO;
import integracion.daoImpl.SqlClienteDAO;

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


}