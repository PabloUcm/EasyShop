package negocio;

import java.util.List;

import integracion.transfers.TMarca;

public interface MarcaObserver {
	public void altaMarca(TMarca marca);
	public void bajaMarca(TMarca marca);
	public void mostrarMarcaId();
	public void modificarMarca();
	public void listarMarcas();
	public void obtenerMarca(TMarca marca);
	void mostrarMarca(List<TMarca> marcaList);
}
