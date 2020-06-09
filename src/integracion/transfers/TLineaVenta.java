package integracion.transfers;

public class TLineaVenta {

	//ATRIBUTOS
	private int idProducto; 		// Identificador del producto que figura en la l�nea de la factura.
	private String nombre; 			// Nombre del producto.
	private int unidades; 			// Unidades del producto en la venta.
	private double precio_unitario;	// Precio de una unidad del producto.
	private double total; 			// Importe total de la l�nea de la factura.
	
	//CONSTRUCTORA
	
	public TLineaVenta() {}
	
	public TLineaVenta(int idProducto, String nombre, int unidades, double precio_unitario) {
		this.idProducto=idProducto;
		this.nombre=nombre;
		this.unidades=unidades;
		this.precio_unitario=precio_unitario;
		calculateTotal();
	}
		
	//M�TODOS P�BLICOS
	
	
	public int getIdProducto() {
		return idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
		calculateTotal();
	}

	public double getPrecio_unitario() {
		return precio_unitario;
	}

	public double getTotal() {
		return total;
	}

	//M�TODOS PRIVADOS
	public void calculateTotal() {
		total=unidades*precio_unitario;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
}
