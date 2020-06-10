package integracion.dao;

import java.util.List;

import integracion.transfers.TVenta;

public interface IVentaDAO {
	
	public TVenta getVentaByID(int id);
	public int altaVenta(TVenta venta);
	public List<TVenta> getAllVentas();
	public List<TVenta> getComprasCliente(int id);
	public List<TVenta> getHistorialEmpleado(int id);
}
