package presentacion.controllers;


import java.util.List;

import integracion.transfers.TPersonal;
import negocio.Modelo;

public class PersonalController {
private Modelo modelo;
	
	public PersonalController(Modelo m) {
		this.modelo = m;
	}
	
	public TPersonal altaPersonal(TPersonal personal) throws Exception {
		return modelo.altaPersonal(personal);
	}
	
	public void reactivarPersonal(int id) throws Exception {
		modelo.reactivarPersonal(id);
	}
	
	public void bajaPersonal(int id) throws Exception {
		 modelo.bajaPersonal(id);
	}
	public void modificarPersonal(TPersonal personal) throws Exception{
		modelo.modificarPersonal(personal);
	}
	public List<TPersonal> listarPersonal(){
		return modelo.listarPersonal();
	}
	public TPersonal getPersonal(int id) throws Exception {
		return modelo.getPersonal(id);
	}

}
