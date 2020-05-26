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
		try {
			Connection connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cliente WHERE dni=?");
			statement.setString(1, dni);
			
			ResultSet results = statement.executeQuery();
			
			if(results.next()) return new TCliente(results.getInt(1),results.getString(2),results.getString(3),
					    					       results.getString(4),results.getBoolean(5));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TCliente> getAllClientes() {
		Connection connection = dbAdapter.getConnection();
		List<TCliente> clienteList = new ArrayList<>();
		

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cliente");
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				clienteList.add(new TCliente(results.getInt(1),results.getString(2),results.getString(3),
						results.getString(4),results.getBoolean(5)));
			}
			
			
			return clienteList;
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
	public void altaCliente(TCliente cliente) {
		try {
			Connection connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO cliente (dni,nombre,telefono) VALUES(?,?,?)");
			
			statement.setString(1, cliente.getDni());
			statement.setString(2, cliente.getNombre());
			statement.setString(3, cliente.getTelefono());
			
			statement.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	public TCliente getClienteByID(int id) {
		try {
			Connection connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTE WHERE ID=?");
			statement.setInt(1, id);
			
			ResultSet results = statement.executeQuery();
			if(results.next()) return new TCliente(results.getInt(1),results.getString(2),results.getString(3),
												   results.getString(4), results.getBoolean(5));
					
		}catch(Exception e) {
			e.printStackTrace();
		}	
		return null;
	}

}
