package integracion.transfers;

import java.util.ArrayList;
import java.util.Date;

public class TVenta {

	//ATRIBUTOS
	private int id; 							// Identificador de la venta.
	private int idCliente;						// Identificador del cliente a quien se le imputa la venta.
	private int idPersonal;						// Identificador del personal que efect�a la venta.
	private Date fecha;							// Fecha de cierre de la venta.
	private ArrayList<TLineaVenta> lineasVenta; // L�neas de la venta correspondientes a los productos.
	private double importe;						// Importe total de la venta.
	
	//CONSTRUCTORA
	public TVenta() { // Constructora por defecto;
		lineasVenta = new ArrayList<TLineaVenta>();
		importe = 0;
	}
	
	public TVenta(int id,int idCliente, int idPersonal,Date fecha, double importe) {
		this.id = id;
		this.idCliente=idCliente;
		this.idPersonal=idPersonal;
		this.fecha = fecha;
		this.importe = importe;
	}



	//M�TODOS P�BLICOS
	public int getId() {
		return id;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdPersonal() {
		return idPersonal;
	}

	public void setIdPersonal(int idPersonal) {
		this.idPersonal = idPersonal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void addLineaVenta(int idProducto, String nombreProducto, int unidades, double precio_unitario) {
		if(this.existeProducto(idProducto)) {
			this.changeUnits(idProducto, unidades);
		}else {
			TLineaVenta lineaVenta= new TLineaVenta(idProducto, nombreProducto, unidades, precio_unitario);
			this.lineasVenta.add(lineaVenta);
			changeTotal('s', unidades*precio_unitario);
		}
	}
		
	
	public TLineaVenta getLineaVenta(int i) {
		return lineasVenta.get(i);
	}
	
	public int getNumeroLineas() {
		return lineasVenta.size(); 
	}

	public void changeUnits(int idProducto, int unidades) {
		boolean exit=false;
		double importe;
		for(int i=0; i<lineasVenta.size() && !exit; i++) {
			if(lineasVenta.get(i).getIdProducto()==idProducto) {
				TLineaVenta lv=lineasVenta.get(i);
				if(unidades<=0) {											// Quitamos la l�nea entera.
					importe=lv.getTotal();
					lineasVenta.remove(i);
					changeTotal('r', importe);
				}else if ((unidades > 0) && (unidades < lv.getUnidades())){ // Restamos unidades.
					int diferencia=lv.getUnidades()-unidades;
					lv.setUnidades(unidades);
					changeTotal('r', diferencia*lv.getPrecio_unitario());
				}else if(unidades>lv.getUnidades()) {						// Sumamos unidades.
					lv.setUnidades(unidades);
					changeTotal('s', unidades*lv.getPrecio_unitario());
				}
				exit=true;	
			}
		}
	}
	
	public boolean existeProducto(int idProducto) {
		for(TLineaVenta lv:lineasVenta) if(lv.getIdProducto()==idProducto) return true;
		return false;
	}

	public double getTotal() {
		return importe;
	}

	//M�TODOS PRIVADOS
	private void changeTotal(char operacion, double importe) {
		if(operacion=='s') this.importe += importe;
		else if (operacion=='r') this.importe -= importe;
	}
	
	public int lineasVentaSize() {
		return lineasVenta.size();
	}
	
	
}
