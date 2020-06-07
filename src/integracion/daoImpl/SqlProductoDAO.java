package integracion.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import integracion.dao.IProductoDAO;
import integracion.dbadapters.IDBAdapter;
import integracion.factorias.DBFactory;
import integracion.transfers.TCliente;
import integracion.transfers.TMarca;
import integracion.transfers.TPc;
import integracion.transfers.TPeriferico;
import integracion.transfers.TProducto;


public class SqlProductoDAO implements IProductoDAO{

	private IDBAdapter dbAdapter;
	
	public SqlProductoDAO() {
		dbAdapter = DBFactory.getDefaultAdapter();
	}

	@Override
	public List<TProducto> getAll() {
		Connection connection = dbAdapter.getConnection();
		List<TProducto> productoList = new ArrayList<>();
		

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Producto WHERE activo = true");
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				productoList.add(new TProducto(results.getInt(1),results.getInt(2),results.getString(3),results.getString(4),
								  results.getString(5),results.getDouble(6),results.getInt(7),results.getString(8),results.getBoolean(9)));
			}
			
			
			return productoList;
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
	public TProducto getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TPc getPCByID(int id) {
		try {
			Connection connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Producto WHERE id=?");
			statement.setInt(1, id);
			
			ResultSet results = statement.executeQuery();
			
			PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM PC WHERE producto=?");
			statement2.setInt(1, id);
			
			ResultSet results2 = statement2.executeQuery();
			
			if (results.next() && results2.next()) return new TPc(results.getInt(1),results.getInt(2),results.getString(3),results.getString(4),
								  								  results.getString(5),results.getDouble(6),results.getInt(7),results.getString(8),
								  								  results.getBoolean(9), results2.getString(2), results2.getString(3),
								  								  results2.getString(4), results2.getString(5), results2.getString(6),
								  								  results2.getString(7));	
					  								
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public TPeriferico getPerifericoByID(int id) {
		try {
			Connection connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Producto WHERE id=?");
			statement.setInt(1, id);
			
			ResultSet results = statement.executeQuery();
			
			PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM Periferico WHERE producto=?");
			statement2.setInt(1, id);
			
			ResultSet results2 = statement2.executeQuery();
			
			if (results.next() && results2.next()) return new TPeriferico(results.getInt(1),results.getInt(2),results.getString(3),
																		  results.getString(4),results.getString(5),results.getDouble(6),
																		  results.getInt(7),results.getString(8),results.getBoolean(9),
																		  results2.getString(2), results2.getString(3));	
					  								
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public TProducto getProductoByUPC(String upc) {
		try {
			Connection connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Producto WHERE upc=?");
			statement.setString(1, upc);
			
			ResultSet results = statement.executeQuery();
			
			if(results.next()) return new TProducto(results.getInt(1),results.getInt(2),results.getString(3),results.getString(4),
					  								results.getString(5),results.getDouble(6),results.getInt(7),results.getString(8),
					  								results.getBoolean(9));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@Override
	public int altaPC(TPc pc) {
		int id = -1;
		try {
			Connection connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Producto (marca,upc,nombre,tipo,precio,cantidad,descripcion) "
																	  + "VALUES(?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, pc.getMarcaId());
			statement.setString(2, pc.getUPC());
			statement.setString(3, pc.getNombre());
			statement.setString(4, pc.getTipo());
			statement.setDouble(5, pc.getPrecio());
			statement.setInt(6, pc.getCantidad());
			statement.setString(7, pc.getDescripcion());
			
			statement.executeUpdate();
			
			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) id = result.getInt(1);
			
			PreparedStatement statement2 = connection.prepareStatement("INSERT INTO PC (producto,procesador,ram,disco_duro,tarjeta_grafica,placa_base) "
																	    + "	VALUES(?,?,?,?,?,?)");
			
			statement2.setInt(1, id);
			statement2.setString(2, pc.getProcesador());
			statement2.setString(3, pc.getRam());
			statement2.setString(4, pc.getDiscoduro());
			statement2.setString(5, pc.getTarjetagrafica());
			statement2.setString(6, pc.getPlacabase());
			
			statement2.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
		return id;
		
	}

	@Override
	public int altaPeriferico(TPeriferico periferico) {
		int id = -1;
		try {
			Connection connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Producto (marca,upc,nombre,tipo,precio,cantidad,descripcion) "
																	  + "VALUES(?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, periferico.getMarcaId());
			statement.setString(2, periferico.getUPC());
			statement.setString(3, periferico.getNombre());
			statement.setString(4, periferico.getTipo());
			statement.setDouble(5, periferico.getPrecio());
			statement.setInt(6, periferico.getCantidad());
			statement.setString(7, periferico.getDescripcion());
			
			statement.executeUpdate();
			
			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) id = result.getInt(1);
			
			PreparedStatement statement2 = connection.prepareStatement("INSERT INTO Periferico (producto,tipo_periferico,tipo_conexion) VALUES(?,?,?)");
			
			statement2.setInt(1, id);
			statement2.setString(2, periferico.getTipoPeriferico());
			statement2.setString(3, periferico.getConexion());
			
			statement2.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
		return id;
	}

	@Override
	public void reactivarPC(TPc pc) {
		try {
			Connection connection = dbAdapter.getConnection();
		
			PreparedStatement statement = connection.prepareStatement("UPDATE Producto SET marca=?, upc=?, "
																	  + "nombre=?, tipo=?, precio=?, cantidad=?, descripcion=?, activo=true  "
																	  + "WHERE id=?");

			statement.setInt(1, pc.getMarcaId());
			statement.setString(2, pc.getUPC());
			statement.setString(3, pc.getNombre());
			statement.setString(4, pc.getTipo());
			statement.setDouble(5, pc.getPrecio());
			statement.setInt(6, pc.getCantidad());
			statement.setString(7, pc.getDescripcion());
			statement.setInt(8, pc.getId());
			
			statement.executeUpdate();	
			
			PreparedStatement statement2 = connection.prepareStatement("UPDATE PC SET so=?, procesador=?, ram=?, disco_duro=?, tarjeta_grafica=?, "
																		+ "placa_base=? WHERE producto=?");
			
			statement2.setString(1, pc.getSo());
			statement2.setString(2, pc.getProcesador());
			statement2.setString(3, pc.getRam());
			statement2.setString(4, pc.getDiscoduro());
			statement2.setString(5, pc.getTarjetagrafica());
			statement2.setString(6, pc.getPlacabase());
			statement2.setInt(7, pc.getId());
			
			statement2.executeUpdate();
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void reactivarPeriferico(TPeriferico periferico) {
		try {
			Connection connection = dbAdapter.getConnection();
		
			PreparedStatement statement = connection.prepareStatement("UPDATE Producto SET marca=?, upc=?, "
																	  + "nombre=?, tipo=?, precio=?, cantidad=?, descripcion=?, activo=true  "
																	  + "WHERE id=?");

			statement.setInt(1, periferico.getMarcaId());
			statement.setString(2, periferico.getUPC());
			statement.setString(3, periferico.getNombre());
			statement.setString(4, periferico.getTipo());
			statement.setDouble(5, periferico.getPrecio());
			statement.setInt(6, periferico.getCantidad());
			statement.setString(7, periferico.getDescripcion());
			statement.setInt(8, periferico.getId());
			
			statement.executeUpdate();	
			
			PreparedStatement statement2 = connection.prepareStatement("UPDATE Periferico SET tipo_periferico=?, tipo_conexion=? WHERE producto=?");
			
			statement2.setString(1, periferico.getTipoPeriferico());
			statement2.setString(2, periferico.getConexion());
			statement2.setInt(3, periferico.getId());
			
			statement2.executeUpdate();
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}