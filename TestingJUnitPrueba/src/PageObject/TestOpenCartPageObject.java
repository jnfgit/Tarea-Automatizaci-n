package PageObject;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import org.junit.Test;

public class TestOpenCartPageObject extends IniTest {
   
	public TestOpenCartPageObject() {
		super();
	}
		
	@Test
	public void buscarProducto() {
		OpenCartHomePage landingPage = new OpenCartHomePage(driver);
		assertEquals("iPhone", landingPage.buscarProducto("iPhone"));
	}
	
	@Test
	public void buscarEntrarYVerificarTituloDelProducto() {
		OpenCartHomePage landingPage = new OpenCartHomePage(driver);
		assertEquals("iPhone", landingPage.buscarEntrarYVerificarTituloDelProducto("iPhone"));
	}
	
	@Test
	public void escribirTodosLosProductos() {
		OpenCartMenuPage menuPage = new OpenCartMenuPage(driver);
		assertTrue(menuPage.escribirTodosLosProductos());
	}
	
	@Test
	public void buscarProductos() {
		OpenCartHomePage landingPage = new OpenCartHomePage(driver);
		ArrayList<String> listaProductos = getParamsFromFile();
		for (String producto : listaProductos) {
			assertEquals(producto, landingPage.buscarProducto(producto));	
		}
	}
	
    /**
     * Método encargado de obtener de un archivo los parámetros de búsqueda (productos).
     * @return
     */
    public static ArrayList<String> getParamsFromFile(){
    	String cadena;
    	ArrayList<String> productos = new ArrayList<String>();
    	try {
	    	FileReader f = new FileReader("desafio_2_parametros_productos.txt");
	        BufferedReader b = new BufferedReader(f);
	        while((cadena = b.readLine())!=null) {
	            productos.add(cadena);
	        }	                
	        b.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return productos;
    }
}