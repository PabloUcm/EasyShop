package integracion.transfers;

public class TPersonal {

	private int id;
	private String dni;
	private String nombre;
	private String telefono;
	private double sueldo;
	private String horario;
	private boolean activo;
	
	public TPersonal() {};
	
	public TPersonal(int id, String dni, String nombre, String telefono, double sueldo, String horario,boolean activo) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.horario = horario;
		this.sueldo = sueldo;
		this.activo = activo;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	public void setSueldo(double sueldo){
		this.sueldo = sueldo;
	}
	public double getSueldo(){
		return sueldo;
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

	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario){
		this.horario = horario;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TPersonal other = (TPersonal) obj;
		if (id != other.id)
			return false;
		return true;
	}
		
}
