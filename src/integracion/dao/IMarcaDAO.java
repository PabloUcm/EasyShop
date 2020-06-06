package integracion.dao;

import java.util.List;

import integracion.transfers.TMarca;

public interface IMarcaDAO {
	public TMarca getMarcaByID(int id);
	public TMarca getMarcaByCIF(String cif);
	public List<TMarca> getAllMarcas();
	public int altaMarca(TMarca marca);
	public void bajaMarca(int id);
	public void modificarMarca(int id, TMarca marca);
	public List<String> getNombreMarcas();
}
