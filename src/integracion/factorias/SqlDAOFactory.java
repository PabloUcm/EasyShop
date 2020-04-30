package integracion.factorias;

import integracion.dao.IProductoDAO;
import integracion.daoImpl.SqlProductDAO;

public class SqlDAOFactory implements IDAOServiceFactory{

	@Override
	public IProductoDAO getProductDAOService() {
		return new SqlProductDAO();
	}

}