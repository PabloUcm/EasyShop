package integracion.dao;

import java.util.List;


import integracion.transfers.TPersonal;

public interface IPersonalDAO{

	public TPersonal getEmpleadoByDNI(String dni);
	public TPersonal getEmpleadoByID(int id);
	public List<TPersonal> getAllEmpleados();
	public int altaEmpleado(TPersonal personal);
	public void bajaEmpleado(int id);
	public void modificarEmpleado(TPersonal personal);
	public void reactivarPersonal(int id) ;
}
