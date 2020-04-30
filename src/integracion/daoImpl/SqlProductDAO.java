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
import integracion.transfers.TProducto;


public class SqlProductDAO implements IProductoDAO{

	private IDBAdapter dbAdapter;
	
	public SqlProductDAO() {
		dbAdapter = DBFactory.getDefaultAdapter();
	}
	
	@Override
	public List<TProducto> getAll() {
		Connection connection = dbAdapter.getConnection();
		List<TProducto> productList = new ArrayList<>();
		
		try {
			PreparedStatement statement =
					connection.prepareStatement("SELECT idProductos,productName" + ",productPrice FROM Productos");
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				productList.add(new TProducto(results.getInt(1),results.getString(2),results.getDouble(3)));
			}
			return productList;
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