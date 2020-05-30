package presentacion.controllers;


import integracion.transfers.TPersonal;
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
	
	public void altaPersonal(String dni, String nombre, String sueldo, String telefono,String horario) throws Exception {
		modelo.altaPersonal(dni, nombre, sueldo, telefono,horario);
	}
	
	public void bajaPersonal(int id) throws Exception {
		 modelo.bajaPersonal(id);
	}
	public void modificarPersonal(int id, String dni, String nombre, String sueldo,String telefono,String horario) throws Exception{
		modelo.modificarPersonal( id,dni,nombre,telefono,sueldo,horario);
	}
	public void listarEmpleados(){
		modelo.listarEmpleados();
	}
	public TPersonal getEmpleado(int id) throws Exception {
		return modelo.getEmpleado(id);
	}

}
