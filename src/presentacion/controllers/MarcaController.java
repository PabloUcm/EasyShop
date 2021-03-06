package presentacion.controllers;

import java.util.List;

import integracion.transfers.TMarca;
import negocio.Modelo;

public class MarcaController {
	private Modelo modelo;
	
	public MarcaController(Modelo m) {
		this.modelo = m;
	}
	
	public TMarca altaMarca(TMarca marca) throws Exception {
		return modelo.altaMarca(marca);
	}
	
	public void bajaMarca(int id) throws Exception {
		 modelo.bajaMarca(id);
	}
	
	public void reactivarMarca(int id) throws Exception {
		modelo.reactivarMarca(id);
	}
	
	public void modificarMarca(TMarca marca) throws Exception{
		modelo.modificarMarca(marca);
	}
	
	public TMarca getMarca(int id) throws Exception{
		return modelo.getMarca(id);
	}
	
	public List<TMarca> listarMarcas() {
		return modelo.listarMarcas();
	}
}
