package integracion.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import integracion.dao.IPersonalDAO;
import integracion.dbadapters.IDBAdapter;
import integracion.factorias.DBFactory;
import integracion.transfers.TCliente;
import integracion.transfers.TPersonal;

public class SqlPersonalDAO implements IPersonalDAO{
	
	private IDBAdapter dbAdapter;
	
	public SqlPersonalDAO(){	
		dbAdapter = DBFactory.getDefaultAdapter();
	}

	@Override
	public TPersonal getEmpleadoByDNI(String dni) {
		Connection connection = dbAdapter.getConnection();
	
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Personal WHERE dni=?");
			statement.setString(1, dni);
			
			ResultSet results = statement.executeQuery();
			
			if(results.next()) return new TPersonal(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),
					    					       results.getDouble(5),results.getString(7),results.getBoolean(8));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public TPersonal getEmpleadoByID(int id) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Personal WHERE id=?");
			statement.setInt(1, id);
			
			ResultSet results = statement.executeQuery();
			if(results.next()) return new TPersonal(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),
												   results.getDouble(5), results.getString(7),results.getBoolean(8));
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<TPersonal> getAllEmpleados() {
		Connection connection = dbAdapter.getConnection();
		List<TPersonal> empleadoList = new ArrayList<>();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Personal WHERE activo = true");
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				empleadoList.add(new TPersonal(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),
						results.getDouble(5),results.getString(7),results.getBoolean(8)));
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
	public int altaEmpleado(TPersonal empleado) {
		Connection connection = dbAdapter.getConnection();
		int id = -1;
		
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Personal (dni,nombre,sueldo,telefono,horario) VALUES(?,?,?,?,?)",
																	   PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, empleado.getDni());
			statement.setString(2, empleado.getNombre());
			statement.setDouble(3, empleado.getSueldo());
			statement.setString(4, empleado.getTelefono());
			statement.setString(5, empleado.getHorario());
			
			statement.executeUpdate();
			
			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) id = result.getInt(1);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return id;
	}

	@Override
	public void bajaEmpleado(int id) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE Personal SET ACTIVO = FALSE WHERE id=?");
			statement.setInt(1, id);
			statement.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void modificarEmpleado(TPersonal personal) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE Personal SET dni=?, nombre=?, sueldo = ?,telefono=?, horario = ? WHERE id=?");
			statement.setString(1, personal.getDni());
			statement.setString(2, personal.getNombre());
			statement.setDouble(3, personal.getSueldo());
			statement.setString(4, personal.getTelefono());
			statement.setString(5, personal.getHorario());
			statement.setInt(6, personal.getId());
			
			statement.executeUpdate();
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
	@Override
	public void reactivarPersonal(int id) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE Personal SET activo=true  WHERE id=?");

			statement.setInt(1, id);	
			statement.executeUpdate();	
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
