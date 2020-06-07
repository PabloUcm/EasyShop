package integracion.transfers;

public class TPc extends TProducto {
	
	private String so;
	private String procesador;
	private String ram;
	private String discoduro;
	private String tarjetagrafica;
	private String placabase;
	
	public TPc() {}
	
	public TPc(int id,int marcaId, String UPC,String nombre, String tipo, double precio, int cantidad, String descripcion,boolean activo,
			   String so, String procesador, String ram, String discoduro, String tarjetagrafica, String placabase) 
	{
		super(id,marcaId,UPC,nombre,tipo,precio,cantidad,descripcion,activo);
		this.so = so;
		this.procesador = procesador;
		this.ram = ram;
		this.discoduro = discoduro;
		this.tarjetagrafica = tarjetagrafica;
		this.placabase = placabase;
	}

	public String getSo() {
		return so;
	}

	public void setSo(String so) {
		this.so = so;
	}

	public String getProcesador() {
		return procesador;
	}

	public void setProcesador(String procesador) {
		this.procesador = procesador;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getDiscoduro() {
		return discoduro;
	}

	public void setDiscoduro(String discoduro) {
		this.discoduro = discoduro;
	}

	public String getTarjetagrafica() {
		return tarjetagrafica;
	}

	public void setTarjetagrafica(String tarjetagrafica) {
		this.tarjetagrafica = tarjetagrafica;
	}

	public String getPlacabase() {
		return placabase;
	}

	public void setPlacabase(String placabase) {
		this.placabase = placabase;
	}
}
