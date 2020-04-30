package integracion.dao;

import java.util.List;

import integracion.transfers.TProducto;


public interface IProductoDAO {
	
	public List<TProducto> getAll();
	public TProducto getById(long id);
}
