package presentacion.controllers;

import java.util.List;

import integracion.transfers.TPc;
import integracion.transfers.TPeriferico;
import integracion.transfers.TProducto;
import negocio.Modelo;

public class ProductoController {
private Modelo modelo;
	
	public ProductoController(Modelo m) {
		this.modelo = m;
	}
	
	public TPc altaPC(TPc pc, String nombreMarca) throws Exception {
		return modelo.altaPC(pc, nombreMarca);
	}
	
	public TPeriferico altaPeriferico(TPeriferico periferico, String nombreMarca) throws Exception {
		return modelo.altaPeriferico(periferico, nombreMarca);
	}
	
	public void reactivarPC(TPc pc) {
		modelo.reactivarPC(pc);
	}
	
	public void reactivarPeriferico(TPeriferico periferico) {
		modelo.reactivarPeriferico(periferico);
	}
	
	public void bajaProducto(int id) throws Exception {
		modelo.bajaProducto(id);
	}
	
	public List<TProducto> listarProductos(){
		return modelo.listarProductos();
	}
	
	public List<String> getNombreMarcas() {
		return modelo.getNombreMarcas();
	}
	
	public TProducto getProductoById(int id,String tipo) throws Exception {
		return modelo.getProductoById(id, tipo);
	}
	
	public void modificarProducto(TProducto producto) {
		modelo.modificarProducto(producto);
	}
}
