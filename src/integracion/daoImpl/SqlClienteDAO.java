package integracion.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import integracion.dao.IClienteDAO;
import integracion.dbadapters.IDBAdapter;
import integracion.factorias.DBFactory;
import integracion.transfers.TCliente;

public class SqlClienteDAO implements IClienteDAO {
	private IDBAdapter dbAdapter;
	
	public SqlClienteDAO() {
		dbAdapter = DBFactory.getDefaultAdapter();
	}
	
	@Override
	public TCliente getClienteByDNI(String dni) {		
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cliente WHERE dni=?");
			statement.setString(1, dni);
			
			ResultSet results = statement.executeQuery();
			
			if(results.next()) return new TCliente(results.getInt(1),results.getString(2),results.getString(3),
					    					       results.getString(4),results.getBoolean(5));
			
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
	public List<TCliente> getAllClientes() {
		Connection connection = dbAdapter.getConnection();

		try {
			List<TCliente> clienteList = new ArrayList<>();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cliente WHERE activo = true");
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				clienteList.add(new TCliente(results.getInt(1),results.getString(2),results.getString(3),
						results.getString(4),results.getBoolean(5)));
			}
			
			return clienteList;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int altaCliente(TCliente cliente) {
		Connection connection = dbAdapter.getConnection();
		int id = -1;
		
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO cliente (dni,nombre,telefono) VALUES(?,?,?)",
																	   PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, cliente.getDni());
			statement.setString(2, cliente.getNombre());
			statement.setString(3, cliente.getTelefono());
			
			statement.executeUpdate();
			
			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) id = result.getInt(1);
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		return id;
	}

	@Override
	public TCliente getClienteByID(int id) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cliente WHERE id=?");
			statement.setInt(1, id);
			
			ResultSet results = statement.executeQuery();
			if(results.next()) return new TCliente(results.getInt(1),results.getString(2),results.getString(3),
												   results.getString(4), results.getBoolean(5));
					
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
	public void bajaCliente(int id) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE Cliente SET ACTIVO = FALSE WHERE id=?");
			statement.setInt(1, id);
			
			statement.executeUpdate();
					
			connection.close();
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
	public void modificarCliente(TCliente cliente) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE Cliente SET dni=?, nombre=?, telefono=?  WHERE id=?");
			statement.setString(1, cliente.getDni());
			statement.setString(2, cliente.getNombre());
			statement.setString(3, cliente.getTelefono());
			statement.setInt(4, cliente.getId());
			
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
	public void reactivarCliente(int id) {
		Connection connection = dbAdapter.getConnection();	
		
		try {		
			PreparedStatement statement = connection.prepareStatement("UPDATE Cliente SET activo=true WHERE id=?");

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
