package integracion.transfers;

public class TProducto {
	
	private int id;
	private int marcaId;
	private String nombre;
	private double precio;
	private String descripcion;
	private boolean activo;

	public TProducto(int id,int marcaId,String nombre, double precio, String descripcion,boolean activo) {
		this.id = id;
		this.marcaId = marcaId;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(int marcaId) {
		this.marcaId = marcaId;
	};
	
	
}
