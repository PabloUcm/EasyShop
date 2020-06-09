package integracion.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import integracion.dao.IVentaDAO;
import integracion.dbadapters.IDBAdapter;
import integracion.factorias.DBFactory;
import integracion.transfers.TLineaVenta;
import integracion.transfers.TVenta;

public class SqlVentaDAO implements IVentaDAO{
	
	private IDBAdapter dbAdapter;

	public SqlVentaDAO() {
		dbAdapter = DBFactory.getDefaultAdapter();
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
						+ "(venta,producto,unidades) values(?,?,?)");
				
				statement2.setInt(1, id);
				statement2.setInt(2, lineaVenta.getIdProducto());
				statement2.setInt(3, lineaVenta.getUnidades());
				
				statement2.executeUpdate();
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}

}
