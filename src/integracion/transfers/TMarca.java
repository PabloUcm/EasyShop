package integracion.transfers;

public class TMarca {
	private int id;
	private String cif;
	private String nombre;
	private String pais;
	private boolean activo;
	
	public TMarca() {}
	
	public TMarca(String cif, String nombre, String pais) {
		this.cif = cif;
		this.nombre = nombre;
		this.pais = pais;
	}
	
	public TMarca(int id, String cif, String nombre, String pais, boolean activo) {
		this.id = id;
		this.cif = cif;
		this.nombre = nombre;
		this.pais = pais;
		this.activo = activo;
	}
	
	public int getId() {
		return id;
	}
	
	public String getCIF() {
		return cif;
	}
	
	public void setCIF(String cif) {
		this.cif = cif;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPais() {
		return pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}