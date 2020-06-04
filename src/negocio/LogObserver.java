package negocio;

public interface LogObserver {

	public void alta(Entity entity);
	public void baja(Entity entity);
	public void modificar(Entity entity);
	public void mostrar(Entity entity);
	public void listar(Entity entity);
}
