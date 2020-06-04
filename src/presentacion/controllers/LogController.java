package presentacion.controllers;

import negocio.LogObserver;
import negocio.Modelo;

public class LogController {
	private Modelo modelo;
	
	public LogController(Modelo m) {
		this.modelo = m;
	}
	
	public void addObserver(LogObserver logObserver) {
		this.modelo.addObserver(logObserver);
	}
}
