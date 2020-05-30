package negocio;

import java.util.List;

import integracion.transfers.TPersonal;

public interface PersonalObserver {

	public void altaEmpleado();
	public void bajaEmpleado();
	public void mostrarEmpleadoId();
	public void modificarEmpleado();
	public void listarEmpleados();
	public void obtenerEmpleado(TPersonal empleado);
	public void mostrarEmpleado(List<TPersonal> empleadoList);
	
	
	
}
