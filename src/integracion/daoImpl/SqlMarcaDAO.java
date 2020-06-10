package integracion.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import integracion.dao.IMarcaDAO;
import integracion.dbadapters.IDBAdapter;
import integracion.factorias.DBFactory;
import integracion.transfers.TMarca;

public class SqlMarcaDAO implements IMarcaDAO {
	
	private IDBAdapter dbAdapter;
	
	public SqlMarcaDAO() {
		dbAdapter = DBFactory.getDefaultAdapter();
	}
	
	@Override
	public TMarca getMarcaByID(int id) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Marca WHERE id=?");
			statement.setInt(1, id);
			
			ResultSet results = statement.executeQuery();
			if(results.next()) return new TMarca(results.getInt(1),results.getString(2),results.getString(3),
												   results.getString(4), results.getBoolean(5));
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public TMarca getMarcaByCIF(String cif) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Marca WHERE cif=?");
			statement.setString(1, cif);
			
			ResultSet results = statement.executeQuery();
			
			if(results.next()) return new TMarca(results.getInt(1), results.getString(2), results.getString(3),
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
	public TMarca getMarcaByName(String name) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Marca WHERE nombre=?");
			statement.setString(1, name);
			
			ResultSet results = statement.executeQuery();
			
			if(results.next()) return new TMarca(results.getInt(1), results.getString(2), results.getString(3),
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
	public List<TMarca> getAllMarcas() {
		Connection connection = dbAdapter.getConnection();
		List<TMarca> marcaList = new ArrayList<>();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Marca WHERE activo = true");
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				marcaList.add(new TMarca(results.getInt(1),results.getString(2),results.getString(3),
						results.getString(4),results.getBoolean(5)));
			}
			
			return marcaList;
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
	public List<String> getNombreMarcas() {
		Connection connection = dbAdapter.getConnection();
		List<String> nombres = new ArrayList<>();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT Nombre FROM Marca WHERE activo = true");
			
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				nombres.add(results.getString(1));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return nombres;
	}
	
	@Override
	public int altaMarca(TMarca marca) {
		Connection connection = dbAdapter.getConnection();
		int id = -1;
		
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Marca (cif,nombre,pais) VALUES(?,?,?)",
																	   PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, marca.getCIF());
			statement.setString(2, marca.getNombre());
			statement.setString(3, marca.getPais());
			
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
	public void bajaMarca(int id) {
		Connection connection = dbAdapter.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE Marca SET ACTIVO = FALSE WHERE id=?");
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
	public void reactivarMarca(int id) {
		Connection connection = dbAdapter.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE Marca SET ACTIVO = TRUE WHERE id=?");
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
	public void modificarMarca(TMarca marca) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE Marca SET cif=?, nombre=?, pais=?  WHERE id=?");
			statement.setString(1, marca.getCIF());
			statement.setString(2, marca.getNombre());
			statement.setString(3, marca.getPais());
			statement.setInt(4, marca.getId());
			
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
