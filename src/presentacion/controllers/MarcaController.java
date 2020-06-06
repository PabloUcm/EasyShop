package presentacion.controllers;

import java.util.List;

import integracion.transfers.TMarca;
import negocio.Modelo;

public class MarcaController {
	private Modelo modelo;
	
	public MarcaController(Modelo m) {
		this.modelo = m;
	}
	
	public void altaMarca(String cif, String nombre, String pais) throws Exception {
		modelo.altaMarca(cif, nombre, pais);
	}
	
	public void bajaMarca(int id) throws Exception {
		 modelo.bajaMarca(id);
	}
	
	public void modificarMarca(int id, String cif, String nombre, String pais) throws Exception{
		modelo.modificarMarca(id, cif, nombre, pais);
	}
	
	public TMarca getMarca(int id) throws Exception{
		return modelo.getMarca(id);
	}
	
	public List<TMarca> listarMarcas() {
		return modelo.listarMarcas();
	}
}
