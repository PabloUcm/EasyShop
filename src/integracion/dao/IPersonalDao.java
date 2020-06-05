package integracion.dao;

import java.util.List;


import integracion.transfers.TPersonal;

public interface IPersonalDao{

	public TPersonal getEmpleadoByDNI(String dni);
	public TPersonal getEmpleadoByID(int id);
	public List<TPersonal> getAllEmpleados();
	public int altaEmpleado(TPersonal empleado);
	public void bajaEmpleado(int id);
	public void modificarEmpleado(int id, TPersonal empleado);
	public void reactivarPersonal(TPersonal personal) ;
}
