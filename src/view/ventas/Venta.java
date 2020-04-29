package view.ventas;

import java.util.Date;

public class Venta {
	public Venta(int ID, int importe, Date fecha) {
		this.ID = ID;
		this.importe = importe;
		this.fecha = fecha;
	}
	
	public int getID() {
		return ID;
	}

	public Date getFecha() {
		return fecha;
	}

	public int getImporte() {
		return importe;
	}

	private int ID;
	private int importe;
	private Date fecha;
}
