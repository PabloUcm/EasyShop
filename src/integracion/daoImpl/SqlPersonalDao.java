package integracion.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import integracion.dao.IPersonalDao;
import integracion.dbadapters.IDBAdapter;
import integracion.factorias.DBFactory;
import integracion.transfers.TPersonal;

public class SqlPersonalDAO implements IPersonalDao{
	
	private IDBAdapter dbAdapter;
	
	public SqlPersonalDAO(){
		
	dbAdapter = DBFactory.getDefaultAdapter();

	}

	@Override
	public TPersonal getEmpleadoByDNI(String dni) {
		// TODO Auto-generated method stub
	
		try {
			Connection connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Personal WHERE dni=?");
			statement.setString(1, dni);
			
			ResultSet results = statement.executeQuery();
			
			if(results.next()) return new TPersonal(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),
					    					       results.getString(5),results.getString(7),results.getBoolean(8));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TPersonal getEmpleadoByID(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Personal WHERE id=?");
			statement.setInt(1, id);
			
			ResultSet results = statement.executeQuery();
			if(results.next()) return new TPersonal(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),
												   results.getString(5), results.getString(7),results.getBoolean(8));
					
		}catch(Exception e) {
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public List<TPersonal> getAllEmpleados() {
		// TODO Auto-generated method stub
		Connection connection = dbAdapter.getConnection();
		List<TPersonal> empleadoList = new ArrayList<>();
		

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Personal WHERE activo = true");
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				empleadoList.add(new TPersonal(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),
						results.getString(5),results.getString(7),results.getBoolean(8)));
			}
			
			
			return empleadoList;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void altaEmpleado(TPersonal empleado) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Personal (dni,nombre,sueldo,telefono,horario) VALUES(?,?,?,?,?)");
			
			statement.setString(1, empleado.getDni());
			statement.setString(2, empleado.getNombre());
			statement.setString(3, empleado.getSueldo());
			statement.setString(4, empleado.getTelefono());
			statement.setString(5, empleado.getHorario());
			
			statement.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void bajaEmpleado(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE Personal SET ACTIVO = FALSE WHERE id=?");
			statement.setInt(1, id);
			statement.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void modificarEmpleado(int id, TPersonal empleado) {
		// TODO Auto-generated method stub
		try {
			
			Connection connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE Personal SET dni=?, nombre=?, sueldo = ?,telefono=?, horario = ? WHERE id=?");
			statement.setString(1, empleado.getDni());
			statement.setString(2, empleado.getNombre());
			statement.setString(3, empleado.getSueldo());
			statement.setString(4, empleado.getTelefono());
			statement.setString(5, empleado.getHorario());
			statement.setInt(6, id);
			
			statement.executeUpdate();
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	

	
}