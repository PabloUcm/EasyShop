package integracion.transfers;

public class TPersonal {

	private int id;
	private String dni;
	private String nombre;
	private String telefono;
	private String sueldo;
	private String horario;
	private boolean activo;
public TPersonal() {};
	
	public TPersonal(String dni,String nombre, String telefono,String sueldo,String horario) {
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.sueldo = sueldo;
		this.horario = horario;
	}

	public TPersonal(int id, String dni, String nombre, String telefono,String sueldo, String horario,boolean activo) {
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
	public void setSueldo(String sueldo){
		this.sueldo = sueldo;
	}
	public String getSueldo(){
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
		// TODO Auto-generated method stub
		return horario;
	}
	public void setHorario(String horario){
		this.horario = horario;
	}
}
