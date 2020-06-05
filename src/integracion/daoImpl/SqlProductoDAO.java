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
				productoList.add(new TProducto(results.getInt(1),results.getInt(2),results.getString(3),
						results.getDouble(4),results.getString(5),results.getBoolean(6)));
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


}