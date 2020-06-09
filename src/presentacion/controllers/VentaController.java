package presentacion.controllers;

import java.util.HashMap;
import java.util.List;

import integracion.transfers.TCliente;
import integracion.transfers.TPersonal;
import integracion.transfers.TProducto;
import integracion.transfers.TVenta;
import negocio.Modelo;

public class VentaController {
private Modelo modelo;
	
	public VentaController(Modelo m) {
		this.modelo = m;
	}
	
	
	public TVenta getVenta(int id) throws Exception {
		return modelo.getVenta(id);
	}
	
	public TCliente getCliente(int id) throws Exception {
		return modelo.getCliente(id);
	}
	
	public TPersonal getPersonal(int id) throws Exception {
		return modelo.getPersonal(id);
	}
	
	public TProducto getProducto(int id) throws Exception {
		return modelo.getProductoById(id, "NONE");
	}
	
	public void comprobarStock(String producto,int unidades) throws Exception {
		modelo.comprobarStock(producto,unidades);
	}
	
	public void cerrarVenta(TVenta venta, HashMap<String,Integer> mapaProductos) {
		modelo.cerrarVenta(venta,mapaProductos);
	}
	
	public List<TVenta> listarVentas() {
		return modelo.listarVentas();
	}
	
	public List<TProducto> listarProductos(){
		return modelo.listarProductos();
	}

}
