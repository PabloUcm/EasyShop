package presentacion.controllers;


import java.util.List;

import integracion.transfers.TPersonal;
import negocio.Modelo;

public class PersonalController {
private Modelo modelo;
	
	public PersonalController(Modelo m) {
		this.modelo = m;
	}
	
	public TPersonal altaPersonal(String dni, String nombre, String sueldo, String telefono,String horario) throws Exception {
		return modelo.altaPersonal(dni, nombre, sueldo, telefono,horario);
	}
	
	public void reactivarPersonal(TPersonal personal) {
		modelo.reactivarPersonal(personal);
	}
	
	public void bajaPersonal(int id) throws Exception {
		 modelo.bajaPersonal(id);
	}
	public void modificarPersonal(int id, String dni, String nombre, String sueldo,String telefono,String horario) throws Exception{
		modelo.modificarPersonal( id,dni,nombre,telefono,sueldo,horario);
	}
	public List<TPersonal> listarEmpleados(){
		return modelo.listarEmpleados();
	}
	public TPersonal getEmpleado(int id) throws Exception {
		return modelo.getEmpleado(id);
	}

}
