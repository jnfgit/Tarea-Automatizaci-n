package Desafio_4_JUnitSearchAllProductsFromFileList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

/**
 * DESAFIO 4
 * @author Jorge Nicolás Fernández
 */
@RunWith(value = Parameterized.class)
public class TestOpenCartSearchAllProductosWithParams {
    FirefoxDriver wd;
    String producto;
    
    @Before
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    public TestOpenCartSearchAllProductosWithParams(String producto){
    	this.producto = producto;
    }
    
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(getParamsFromFile());
	}
    
    @Test
    public void TestOpenCart() {
        wd.get("http://demo.opencart.com/");
        wd.findElement(By.name("search")).click();
        wd.findElement(By.name("search")).clear();
        wd.findElement(By.name("search")).sendKeys(producto);
        wd.findElement(By.xpath("//span[@class='input-group-btn']/button")).click();
        wd.findElement(By.id("grid-view")).click();
        
        List<WebElement> che = wd.findElements(By.className("caption"));
        if(che.size() >= 0){
        	WebElement element = che.get(0);
        	String ar = "";
    		ar = element.getText();
    		ar = element.findElement(By.tagName("a")).getText();
    		assertEquals(producto, ar);
    		element.findElement(By.tagName("a")).click();
    		
    		assertEquals(producto, wd.findElement(By.xpath("//*[@id='content']/div[1]/div[2]/h1")).getText());
        }
    }
    
    @After
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    
    /**
     * Método encargado de obtener de un archivo los parámetros de búsqueda (productos).
     * @return
     */
    public static Object[][] getParamsFromFile(){
    	String cadena;
    	Object [][] parametros = null;
    	try {
	    	FileReader f = new FileReader("desafio_3_lista_todos_los_productos.txt");
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
