package PageObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * PAGINA MENU/PRODUCTOS
 * @author Jorge Nicolás Fernández
 */
public class OpenCartMenuPage extends AbstractPageObject {

	private By listView = By.id("list-view");
	private By listElement = By.className("caption");
	private By findByTafA = By.tagName("a");
	private By menuDesktopOption = By.xpath("//*[@id='menu']/div[2]/ul/li[1]");
	private By menuDesktopSubOption = By.xpath("//*[@id='menu']/div[2]/ul/li[1]/div/a");
	private By menuLaptopOption  = By.xpath("//*[@id='menu']/div[2]/ul/li[2]");
	private By menuLaptopSubOption = By.xpath("//*[@id='menu']/div[2]/ul/li[2]/div/a");
	private By menuComponentsOption = By.xpath( "//*[@id='menu']/div[2]/ul/li[3]");
	private By menuComponentsSubOption = By.xpath("//*[@id='menu']/div[2]/ul/li[3]/div/a");
	private By menuComponentsSubOptionDetail = By.xpath("//*[@id='content']/div/div/ul/li[2]/a");
	private By menuTabletOption = By.xpath("//*[@id='menu']/div[2]/ul/li[4]");
	private By menuPhonesOption = By.xpath("//*[@id='menu']/div[2]/ul/li[6]");
	private By menuCamerasOption = By.xpath("//*[@id='menu']/div[2]/ul/li[7]");
	private By menuMP3PlayersOption = By.xpath("//*[@id='menu']/div[2]/ul/li[8]");
	private By menuMP3PlayersSubOption = By.xpath("//*[@id='menu']/div[2]/ul/li[8]/div/a");
	
	public OpenCartMenuPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean escribirTodosLosProductos(){
		List<WebElement> listProducts = null;
		//Desktops
		WebElement element = driver.findElement(menuDesktopOption);
		generateMouseOver(element);
		WebElement elementoOption = driver.findElement(menuDesktopSubOption);
		elementoOption.click();
		WebElement gridViewElement = driver.findElement(listView);
		gridViewElement.click();
		listProducts = driver.findElements(listElement);
		escribirListaDeProductos(listProducts);
		
		//Laptops
		element = driver.findElement(menuLaptopOption);
		generateMouseOver(element);
		elementoOption = driver.findElement(menuLaptopSubOption);
		elementoOption.click();		
		gridViewElement = driver.findElement(listView);
		gridViewElement.click();
		listProducts = driver.findElements(listElement);
		escribirListaDeProductos(listProducts);

		//Components
		element = driver.findElement(menuComponentsOption);
		generateMouseOver(element);
		elementoOption = driver.findElement(menuComponentsSubOption);
		elementoOption.click();
		WebElement elementDetail = driver.findElement(menuComponentsSubOptionDetail);
		elementDetail.click();
		gridViewElement = driver.findElement(listView);
		gridViewElement.click();
		listProducts = driver.findElements(listElement);
		escribirListaDeProductos(listProducts);
		
		//Tablets
		element = driver.findElement(menuTabletOption);
		element.click();
		gridViewElement = driver.findElement(listView);
		gridViewElement.click();
		listProducts = driver.findElements(listElement);
		escribirListaDeProductos(listProducts);
		
		//Phones
		element = driver.findElement(menuPhonesOption);
		element.click();
		gridViewElement = driver.findElement(listView);
		gridViewElement.click();
		listProducts = driver.findElements(listElement);
		escribirListaDeProductos(listProducts);
		
		//Cameras
		element = driver.findElement(menuCamerasOption);
		element.click();
		gridViewElement = driver.findElement(listView);
		gridViewElement.click();
		listProducts = driver.findElements(listElement);
		escribirListaDeProductos(listProducts);
		
		//MP3 Players
		element = driver.findElement(menuMP3PlayersOption);
		generateMouseOver(element);
		elementoOption = driver.findElement(menuMP3PlayersSubOption);
		elementoOption.click();		
		gridViewElement = driver.findElement(listView);
		gridViewElement.click();
		listProducts = driver.findElements(listElement);
		escribirListaDeProductos(listProducts);
        
		return true;
	}
	
	public void generateMouseOver(WebElement we){
        Actions action = new Actions(driver);
        action.moveToElement(we).build().perform();
	}
	
	public void escribirListaDeProductos(List<WebElement> listProducts){
        for (WebElement webElement : listProducts) {
        	try {
        		escribir(webElement.findElement(findByTafA).getText());
        	} catch (Exception e) {
        		throw e;
			}
		}
	}
	
    public void escribir(String linea)
    {
    	File f;
    	f = new File("page_object_lista_todos_los_productos.txt");
    	
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
