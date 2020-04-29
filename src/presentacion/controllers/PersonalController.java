package presentacion.controllers;

import negocio.ClienteObserver;
import negocio.Modelo;
import negocio.PersonalObserver;

public class PersonalController {
private Modelo modelo;
	
	public void addObserver(PersonalObserver o) {
		modelo.addObserver(o);
	}
	
	public PersonalController(Modelo m) {
		this.modelo = m;
	}
	
	public void altaPersonal(String dni, String nombre, int sueldo, String telefono) {
		modelo.altaPersonal(dni, nombre, sueldo, telefono);
	}
	
	public void bajaPersonal(String dni) {
		 modelo.bajaPersonal(dni);
	}
}
