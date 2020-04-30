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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TCliente> getAllClientes() {
		Connection connection = dbAdapter.getConnection();
		List<TCliente> clienteList = new ArrayList<>();
		

		try {
			PreparedStatement statement =
					connection.prepareStatement("SELECT * FROM Cliente");
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

}
