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
	public TProducto getById(int id) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Producto WHERE id = ?");
			statement.setInt(1, id);
			
			ResultSet results = statement.executeQuery();
			
			if(results.next()) {
				return new TProducto(results.getInt(1),results.getInt(2),results.getString(3),results.getString(4),
							         results.getString(5),results.getDouble(6),results.getInt(7),results.getString(8),
							         results.getBoolean(9));
			}
			
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
		
		return null;
	}
	
	@Override
	public TPc getPCByID(int id) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Producto WHERE id=?");
			
			statement.setInt(1, id);			
			ResultSet results = statement.executeQuery();
			
			PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM PC WHERE producto=?");
			
			statement2.setInt(1, id);			
			ResultSet results2 = statement2.executeQuery();
			
			if (results.next() && results2.next()) return new TPc(results.getInt(1),results.getInt(2),results.getString(3),results.getString(4),
															       results.getString(5),results.getDouble(6),results.getInt(7),results.getString(8),
															       results.getBoolean(9), results2.getString(2), results2.getString(3),
															       results2.getString(4),results2.getString(5),results2.getString(6));	
					  								
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
	public TPeriferico getPerifericoByID(int id) {
		Connection connection = dbAdapter.getConnection();
		
		try {
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
	public TProducto getProductoByUPC(String upc) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Producto WHERE upc=?");
			statement.setString(1, upc);
			
			ResultSet results = statement.executeQuery();
			
			if(results.next()) return new TProducto(results.getInt(1),results.getInt(2),results.getString(3),results.getString(4),
					  								results.getString(5),results.getDouble(6),results.getInt(7),results.getString(8),
					  								results.getBoolean(9));
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
	
	public int altaProducto(TProducto producto) {
		Connection connection = dbAdapter.getConnection();
		int id = -1;
		
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Producto (marca,upc,nombre,tipo,precio,cantidad,descripcion) "
																	  + "VALUES(?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, producto.getMarcaId());
			statement.setString(2, producto.getUPC());
			statement.setString(3, producto.getNombre());
			statement.setString(4, producto.getTipo());
			statement.setDouble(5, producto.getPrecio());
			statement.setInt(6, producto.getCantidad());
			statement.setString(7, producto.getDescripcion());
			
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
	public void altaPC(TPc pc, int id) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO PC (producto,procesador,ram,disco_duro,tarjeta_grafica,placa_base) "
																	    + "	VALUES(?,?,?,?,?,?)");
			
			statement.setInt(1, id);
			statement.setString(2, pc.getProcesador());
			statement.setString(3, pc.getRam());
			statement.setString(4, pc.getDiscoduro());
			statement.setString(5, pc.getTarjetagrafica());
			statement.setString(6, pc.getPlacabase());
			
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
	public void altaPeriferico(TPeriferico periferico, int id) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Periferico (producto,tipo_periferico,tipo_conexion) VALUES(?,?,?)");
			
			statement.setInt(1, id);
			statement.setString(2, periferico.getTipoPeriferico());
			statement.setString(3, periferico.getConexion());
			
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
	public void reactivarProducto(int id) {
		Connection connection = dbAdapter.getConnection();
		try {
		
			PreparedStatement statement = connection.prepareStatement("UPDATE Producto SET activo=true WHERE id=?");

			statement.setInt(1, id);			
			statement.executeUpdate();	
		}
		catch(Exception e) {
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
	public void bajaProducto(int id) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE Producto SET ACTIVO = FALSE,CANTIDAD = 0 WHERE id=?");
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
	public void modificarProducto(TProducto producto) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE Producto SET UPC = ?, NOMBRE = ?, PRECIO = ?,"+
																	  "CANTIDAD = ? WHERE ID = ?");
			
			statement.setString(1, producto.getUPC());
			statement.setString(2, producto.getNombre());
			statement.setDouble(3, producto.getPrecio());
			statement.setInt(4, producto.getCantidad());
			statement.setInt(5, producto.getId());
				
			statement.executeUpdate();
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void modificarPc(TPc pc) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE PC SET PROCESADOR = ?, RAM = ?,DISCO_DURO = ?,"+
																	  "TARJETA_GRAFICA = ?,PLACA_BASE = ? WHERE PRODUCTO = ?");
			
			statement.setString(1, pc.getProcesador());
			statement.setString(2, pc.getRam());
			statement.setString(3, pc.getDiscoduro());
			statement.setString(4, pc.getTarjetagrafica());
			statement.setString(5, pc.getPlacabase());
			statement.setInt(6, pc.getId());
				
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
	public void modificarPeriferico(TPeriferico periferico) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE Periferico SET TIPO_PERIFERICO = ?, TIPO_CONEXION = ?"
																   + " WHERE PRODUCTO = ?");
			
			statement.setString(1, periferico.getTipoPeriferico());
			statement.setString(2, periferico.getConexion());
			statement.setInt(3, periferico.getId());
				
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
	public TProducto getProductByName(String name) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTO WHERE NOMBRE = ?");
			
			statement.setString(1,name);				
			ResultSet results = statement.executeQuery();
			
			if(results.next()) {
				return new TProducto(results.getInt(1),results.getInt(2),results.getString(3),results.getString(4),
							         results.getString(5),results.getDouble(6),results.getInt(7),results.getString(8),
							         results.getBoolean(9));
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
		
		return null;
	}

	@Override
	public void cambiarUnidades(int id, int unidades) {
		Connection connection = dbAdapter.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE PRODUCTO SET CANTIDAD = ? WHERE ID = ?");
			
			statement.setInt(1, unidades);
			statement.setInt(2, id);
			
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
	public List<TProducto> listarPorMarca(int id) {
		Connection connection = dbAdapter.getConnection();
		List<TProducto> productoList = new ArrayList<>();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTO WHERE ACTIVO=TRUE AND MARCA = ?");
			
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				productoList.add(new TProducto(results.getInt(1),results.getInt(2),results.getString(3),results.getString(4),
								  results.getString(5),results.getDouble(6),results.getInt(7),results.getString(8),results.getBoolean(9)));
			}		
			
			return productoList;			
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
	public List<TProducto> listarPorPrecio(double precioSuperior, double precioInferior) {
		Connection connection = dbAdapter.getConnection();
		List<TProducto> productoList = new ArrayList<>();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTO WHERE ACTIVO=TRUE AND PRECIO BETWEEN ? AND ?");
			
			statement.setDouble(1, precioSuperior);
			statement.setDouble(2, precioInferior);
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				productoList.add(new TProducto(results.getInt(1),results.getInt(2),results.getString(3),results.getString(4),
								  results.getString(5),results.getDouble(6),results.getInt(7),results.getString(8),results.getBoolean(9)));
			}		
			
			return productoList;			
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
}