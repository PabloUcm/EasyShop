package negocio;

import java.util.ArrayList;
import java.util.List;

public class Modelo {
    private static Modelo modelo;
    private List<ClienteObserver> clienteObservers;
    private List<PersonalObserver> personalObservers;
    private List<VentaObserver> ventaObservers;
    private List<ProductoObserver> productoObservers;
    private List<MarcaObserver> marcaObservers;

    private Modelo() {
    	clienteObservers = new ArrayList<>();
    }

    public static Modelo getModelo() {
        if (modelo == null) modelo = new Modelo();

        return modelo;
    }

	public void addObserver(ClienteObserver o) {
		// TODO Auto-generated method stub
		
	}
	
	public void addObserver(PersonalObserver o) {
		// TODO Auto-generated method stub
		
	}
	
	public void addObserver(VentaObserver o) {
		// TODO Auto-generated method stub
		
	}
	
	public void addObserver(ProductoObserver o) {
		// TODO Auto-generated method stub
		
	}
	
	public void addObserver(MarcaObserver o) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void altaCliente(String dni, String nombre, String telefono) {
		System.out.println("Cliente dado de alta");
		//conseguir transfer
		for(ClienteObserver o: clienteObservers) o.altaCliente();
	}
	
	public void bajaCliente(String dni) {
		System.out.println("Cliente dado de baja");
	}
	
	public void getCliente(String dni) {
		//obtengo cliente
		for(ClienteObserver o: clienteObservers) o.obtenerCliente();
	}
	
	public void altaPersonal(String dni, String nombre, int sueldo, String telefono) {
		
		//for(PersonalObserver o: personalObservers)
	}
	
	public void bajaPersonal(String dni) {
		
		//for(PersonalObserver o: personalObservers) 
	}
	
	public void altaProducto(String dni, String nombre, String telefono) {
		
		//for(ProductoObserver o: productoObservers) 
	}
	
	public void bajaProducto(String dni) {
		
		 
		//for(ProductoObserver o: productoObservers) 
	}
	
	
}
