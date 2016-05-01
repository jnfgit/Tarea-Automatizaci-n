package PageObject;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class TestOpenCartPageObjectWithParams extends IniTest {

    private String producto;
    
    public TestOpenCartPageObjectWithParams(String producto){
		super();
    	this.producto = producto;
    }
    
	@Test
	public void buscarProductos() {
		OpenCartHomePage landingPage = new OpenCartHomePage(driver);
		//Usamos el mismo método para este Test.
		assertEquals(producto, landingPage.buscarProducto(producto));
	}
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(getParamsFromFile());
	}
	
    /**
     * Método encargado de obtener de un archivo los parámetros de búsqueda (productos).
     * @return
     */
    public static Object[][] getParamsFromFile(){
    	String cadena;
    	Object [][] parametros = null;
    	try {
	    	FileReader f = new FileReader("desafio_2_parametros_productos.txt");
	        BufferedReader b = new BufferedReader(f);
	        ArrayList<String> productos = new ArrayList<String>();
	        while((cadena = b.readLine())!=null) {
	            productos.add(cadena);
	        }
	        	        
	        parametros = new Object[productos.size()][1];
	        
	        int pos = 0;
	        for (String producto : productos) {
				parametros[pos][0] = producto;
				pos++;
			}
	                
	        b.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return parametros;
    }
}
