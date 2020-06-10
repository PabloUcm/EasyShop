package negocio;

public interface LogObserver {

	public void alta(Entity entity);
	public void baja(Entity entity);
	public void modificar(Entity entity);
}
