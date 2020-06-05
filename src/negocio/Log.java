package negocio;

import java.util.Date;

public class Log {
	private int numero;
	private String tipo;
	private Entity entidad;
	private Date fecha;
	
	public Log(int numero,String tipo,Entity entidad,Date fecha) {
		this.numero = numero;
		this.tipo = tipo;
		this.entidad = entidad;
		this.fecha = fecha;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Entity getEntidad() {
		return entidad;
	}

	public void setEntidad(Entity entidad) {
		this.entidad = entidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
