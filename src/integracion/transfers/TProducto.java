package integracion.transfers;

public class TProducto {
	
	private int id;
	private int marcaId;
	private String UPC;
	private String nombre;
	private String tipo;
	private double precio;
	private int cantidad;
	private String descripcion;
	private boolean activo;
	
	public TProducto() {}

	public TProducto(int id,int marcaId, String UPC, String nombre, String tipo, double precio, int cantidad,String descripcion,boolean activo) {
		this.id = id;
		this.marcaId = marcaId;
		this.UPC = UPC;
		this.nombre = nombre;
		this.tipo = tipo;
		this.precio = precio;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		if (descripcion != null) return descripcion;
		else return "Sin descripcion.";
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(int marcaId) {
		this.marcaId = marcaId;
	}

	public String getUPC() {
		return UPC;
	}

	public void setUPC(String uPC) {
		UPC = uPC;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	};
	
	public boolean isActivo() {
		return activo;
	}
	
}
