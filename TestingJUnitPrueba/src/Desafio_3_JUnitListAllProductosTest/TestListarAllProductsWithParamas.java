package Desafio_3_JUnitListAllProductosTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

/**
 * DESAFIO 3
 * @author Jorge Nicolás Fernández
 */
@RunWith(value = Parameterized.class)
public class TestListarAllProductsWithParamas {
    FirefoxDriver wd;
    String menuPosition;
    String menuSeeAll;
    String refineSearch;
    
    @Before
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
        
    public TestListarAllProductsWithParamas(String menuItem, String clickSeeAll, String refineSearch){
    	this.menuPosition = menuItem;
    	this.menuSeeAll = clickSeeAll;
    	this.refineSearch = refineSearch;
    }
    
	@Parameters
	public static Collection<Object[]> data() {
		//Para este caso, y para probar, decidí pasar por parámetros los xPaths.
	   return Arrays.asList(new Object[][] {
			   { "//*[@id='menu']/div[2]/ul/li[1]", "//*[@id='menu']/div[2]/ul/li[1]/div/a", ""},
			   { "//*[@id='menu']/div[2]/ul/li[2]", "//*[@id='menu']/div[2]/ul/li[2]/div/a", ""},
			   { "//*[@id='menu']/div[2]/ul/li[3]", "//*[@id='menu']/div[2]/ul/li[3]/div/a", "//*[@id='content']/div/div/ul/li[2]/a"},
			   { "//*[@id='menu']/div[2]/ul/li[4]", "", ""},
			   { "//*[@id='menu']/div[2]/ul/li[6]", "", ""},
			   { "//*[@id='menu']/div[2]/ul/li[7]", "", ""},
			   { "//*[@id='menu']/div[2]/ul/li[8]", "//*[@id='menu']/div[2]/ul/li[8]/div/a", ""}});			
	}

    @Test
    public void TestListarAllProducts() {
        wd.get("http://demo.opencart.com/");
        
        if(!menuSeeAll.equals("")){
            //Generamos un MOUSE OVER       
            Actions action = new Actions(wd);
	        WebElement we = wd.findElement(By.xpath(menuPosition));
	        action.moveToElement(we).build().perform();
	        //Abrimos los See All
	        wd.findElement(By.xpath(menuSeeAll)).click();
	        //Refinamos si es necesario
	        if(refineSearch != null && !refineSearch.equals("")){
	        	wd.findElement(By.xpath(refineSearch)).click();
	        }
	        
	        //Lista de Productos
	        wd.findElement(By.id("list-view")).click();
	        
	        //Recorremos los productos
	        List<WebElement> che = wd.findElements(By.className("caption"));
	        int i = 1;
	        for (WebElement webElement : che) {
	        	String ar = "";
	        	try {
	        		ar = webElement.getText();
	        		ar = webElement.findElement(By.tagName("a")).getText();
	        		escribir(ar);
	        	} catch (Exception e) {
	        		throw e;
				}
        		i = i+1;
			}	  
	        
        }else{
        	wd.findElement(By.xpath(menuPosition)).click();
	        //Lista de Productos
	        wd.findElement(By.id("list-view")).click();

	        //Recorremos los productos
	        List<WebElement> che = wd.findElements(By.className("caption"));
	        int i = 1;
	        for (WebElement webElement : che) {
	        	String ar = "";
	        	try {
	        		ar = webElement.findElement(By.tagName("a")).getText();
	        		escribir(ar);
	        	} catch (Exception e) {
				}
        		i = i+1;
			}
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
     * Método encargado de escribir la linea recibida cómo parámetro.
     * @param linea
     */
    public void escribir(String linea)
    {
    	File f;
    	f = new File("desafio_3_lista_todos_los_productos.txt");
    	
    	try{
    		FileWriter w = new FileWriter(f, true);
    		BufferedWriter bw = new BufferedWriter(w);
    		PrintWriter wr = new PrintWriter(bw);  
    		wr.write(linea + "\r\n");
    		wr.close();
    		bw.close();
    
    	}catch(IOException e){
    		e.printStackTrace();
    	};
    }
}
