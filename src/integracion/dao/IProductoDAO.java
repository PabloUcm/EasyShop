package integracion.dao;

import java.util.List;

import integracion.transfers.TPc;
import integracion.transfers.TPeriferico;
import integracion.transfers.TProducto;


public interface IProductoDAO {
	public TProducto getProductoByUPC(String upc);
	public TPc getPCByID(int id);
	public TPeriferico getPerifericoByID(int id);
	public int altaPC(TPc pc);
	public int altaPeriferico(TPeriferico periferico);
	public void reactivarPC(TPc pc);
	public void reactivarPeriferico(TPeriferico periferico);
	public List<TProducto> getAll();
	public TProducto getById(long id);
}
