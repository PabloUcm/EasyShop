package integracion.factorias;

import java.util.Properties;

import integracion.util.PropertiesUtil;

public class DAOServiceFactory {
	private final static String DAO_FACTORY="metaInfAbstractFactory/DAOFactory.properties";
	private final static String DAO_FACTORY_PROPERTY="defaultFactory";

	public static IDAOServiceFactory getDefaultFactory() {
		Properties p = PropertiesUtil.loadProperty(DAO_FACTORY);
		String factory = p.getProperty(DAO_FACTORY_PROPERTY);
		try {
			System.out.println(factory);
			return (IDAOServiceFactory) Class.forName(factory).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
