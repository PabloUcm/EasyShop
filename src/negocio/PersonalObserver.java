package negocio;

import java.util.List;

import integracion.transfers.TPersonal;

public interface PersonalObserver {

	public void altaEmpleado(TPersonal personal);
	public void bajaEmpleado(TPersonal personal);
	public void mostrarEmpleadoId();
	public void modificarEmpleado();
	public void listarEmpleados();
	public void obtenerEmpleado(TPersonal empleado);
	public void mostrarEmpleado(List<TPersonal> empleadoList);
	
	
	
}
