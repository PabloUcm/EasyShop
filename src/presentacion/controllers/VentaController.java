package presentacion.controllers;

import negocio.ClienteObserver;
import negocio.Modelo;
import negocio.VentaObserver;

public class VentaController {
private Modelo modelo;
	
	public void addObserver(VentaObserver o) {
		modelo.addObserver(o);
	}
	
	public VentaController(Modelo m) {
		this.modelo = m;
	}
	

}
