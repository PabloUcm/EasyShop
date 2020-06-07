package integracion.transfers;

public class TPeriferico extends TProducto{
	
	private String tipoPeriferico;
	private String conexion;
	
	public TPeriferico() {}
	
	public TPeriferico(int id,int marcaId, String UPC,String nombre, String tipo, double precio, 
					   int cantidad, String descripcion,boolean activo, String categoria, String conexion) 
	{
		super(id,marcaId,UPC,nombre,tipo,precio,cantidad,descripcion,activo);
		this.tipoPeriferico = categoria;
		this.conexion = conexion;
	}
	
	public String getTipoPeriferico() {
		return tipoPeriferico;
	}
	
	public void setTipoPeriferico(String tipoPeriferico) {
		this.tipoPeriferico = tipoPeriferico;
	}
	
	public String getConexion() {
		return conexion;
	}
	
	public void setConexion(String conexion) {
		this.conexion = conexion;
	}
	
}
