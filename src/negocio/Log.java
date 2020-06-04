package negocio;

import java.util.Date;

public class Log {
	private String tipo;
	private Entity entidad;
	private Date fecha;
	
	public Log(String tipo,Entity entidad,Date fecha) {
		this.tipo = tipo;
		this.entidad = entidad;
		this.fecha = fecha;
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
