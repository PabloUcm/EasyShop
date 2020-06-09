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
	
	public TProducto altaProducto(TProducto producto, String nombreMarca) throws Exception {
		return modelo.altaProducto(producto, nombreMarca);
	}

	
	public void reactivarProducto(int id) throws Exception {
		modelo.reactivarProducto(id);
	}
	
	public void bajaProducto(int id) throws Exception {
		modelo.bajaProducto(id);
	}
	
	public List<TProducto> listarProductos(){
		return modelo.listarProductos();
	}
	
	public String getNombreMarcaByID(int id) {
		return modelo.getNombreMarcaByID(id);
	}
	
	public List<String> getNombreMarcas() {
		return modelo.getNombreMarcas();
	}
	
	public TProducto getProductoById(int id,String tipo) throws Exception {
		return modelo.getProductoById(id, tipo);
	}
	
	public TPc getPcById(int id) throws Exception {
		return modelo.getPcById(id);
	}
	
	public TPeriferico getPerifericoById(int id) throws Exception {
		return modelo.getPerifericoById(id);
	}
	
	public void modificarProducto(TProducto producto) {
		modelo.modificarProducto(producto);
	}
	
	public List<TProducto> listarProductosPorMarca(String nombreMarca) throws Exception {
		return modelo.listarProductosPorMarca(nombreMarca);
	}
	
	public List<TProducto> listarProductosPorPrecio(double precioSuperior, double precioInferior){
		return modelo.listarProductosPorPrecio(precioSuperior, precioInferior);
	}
}
