package integracion.transfers;

public class TCliente {
	
	private int id;
	private String dni;
	private String nombre;
	private String telefono;
	private boolean activo;
	
	public TCliente() {};
	
	public TCliente(String dni,String nombre, String telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public TCliente(int id, String dni, String nombre, String telefono, boolean activo) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.activo = activo;
	}

	public int getId() {
		return id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TCliente other = (TCliente) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
