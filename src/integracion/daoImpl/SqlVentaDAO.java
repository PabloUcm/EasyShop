package integracion.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import integracion.dao.IVentaDAO;
import integracion.dbadapters.IDBAdapter;
import integracion.factorias.DBFactory;
import integracion.transfers.TCliente;
import integracion.transfers.TLineaVenta;
import integracion.transfers.TVenta;

public class SqlVentaDAO implements IVentaDAO{
	
	private IDBAdapter dbAdapter;

	public SqlVentaDAO() {
		dbAdapter = DBFactory.getDefaultAdapter();
	}
	
	@Override
	public TVenta getVentaByID(int id) {
		Connection connection = null;
		
		try {
			connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Venta WHERE id = ?");
			
			statement.setInt(1, id);
			
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM Venta_producto WHERE venta = ?");
				statement2.setInt(1, id);
				
				ResultSet results2 = statement2.executeQuery();
				
				ArrayList<TLineaVenta> productosList = new ArrayList<>();
				
				while(results2.next()) {
					
					productosList.add(new TLineaVenta(results2.getInt(2),results2.getString(3), 
							  						  results2.getInt(4),results2.getDouble(5)));
					
				}
				
				return new TVenta(results.getInt(1),results.getInt(2), results.getInt(3),results.getDate(4), 
										  productosList, results.getDouble(5));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return null;
	}
	
	public List<TVenta> getAllVentas() {
		Connection connection = null;
		List<TVenta> ventasList = new ArrayList<>();
		
		try {
			connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Venta");
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM Venta_producto WHERE venta = ?");
				statement2.setInt(1, results.getInt(1));
				
				ResultSet results2 = statement2.executeQuery();
				
				ArrayList<TLineaVenta> productosList = new ArrayList<>();
				
				while(results2.next()) {
					
					productosList.add(new TLineaVenta(results2.getInt(2),results2.getString(3), 
													  results2.getInt(4),results2.getDouble(5)));
					
				}
				
				ventasList.add(new TVenta(results.getInt(1),results.getInt(2), results.getInt(3),results.getDate(4), 
										  productosList, results.getDouble(5)));
			}
			
			return ventasList;
			
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
	public int altaVenta(TVenta venta) {
		int id = -1;
		try {
			Connection connection = dbAdapter.getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO VENTA "
			+ "(cliente,personal,fecha,importe) values(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, venta.getIdCliente());
			statement.setInt(2, venta.getIdPersonal());
			statement.setDate(3, new Date(venta.getFecha().getTime()));
			statement.setDouble(4, venta.getTotal());
			
			statement.executeUpdate();
			
			ResultSet result = statement.getGeneratedKeys();
			if(result.next()) id = result.getInt(1);
			
			for(int i=0; i < venta.getNumeroLineas(); i++) {
				TLineaVenta lineaVenta = venta.getLineaVenta(i);
				
				PreparedStatement statement2 = connection.prepareStatement("INSERT INTO venta_producto "
						+ "(venta,producto,nombre,unidades,precio_unitario) values(?,?,?,?,?)");
				
				statement2.setInt(1, id);
				statement2.setInt(2, lineaVenta.getIdProducto());
				statement2.setString(3, lineaVenta.getNombre());
				statement2.setInt(4, lineaVenta.getUnidades());
				statement2.setDouble(5, lineaVenta.getPrecio_unitario());
				
				statement2.executeUpdate();
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}
}
