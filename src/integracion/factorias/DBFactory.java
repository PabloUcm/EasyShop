package integracion.factorias;

import java.util.Properties;

import integracion.dbadapters.DBType;
import integracion.dbadapters.IDBAdapter;
import integracion.dbadapters.MySqlAdapter;
import integracion.dbadapters.OracleAdapter;
import integracion.util.PropertiesUtil;


public class DBFactory {
	
	private final static String DEFAULT_DB ="metaInf/DBFactory.properties";
	private final static String DEFAULT_DB_PROPERTY="defaultDB";
	
	public static IDBAdapter getAdapter(DBType db) {
		switch(db) {
			case MySql: return new MySqlAdapter();
			case Oracle: return new OracleAdapter();
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public static IDBAdapter getDefaultAdapter(){
		try {
			Properties p = PropertiesUtil.loadProperty(DEFAULT_DB);
			String dbName = p.getProperty(DEFAULT_DB_PROPERTY);
			return (IDBAdapter) Class.forName(dbName).newInstance();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}