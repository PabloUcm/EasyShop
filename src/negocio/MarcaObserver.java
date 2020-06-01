package negocio;

import java.util.List;

import integracion.transfers.TMarca;

public interface MarcaObserver {
	public void altaMarca();
	public void bajaMarca();
	public void mostrarMarcaId();
	public void modificarMarca();
	public void listarMarcas();
	public void obtenerMarca(TMarca marca);
	void mostrarMarca(List<TMarca> marcaList);
}
