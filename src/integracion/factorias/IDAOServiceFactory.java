package integracion.factorias;

import integracion.dao.IProductoDAO;

public interface IDAOServiceFactory {
	public IProductoDAO getProductDAOService();
}
