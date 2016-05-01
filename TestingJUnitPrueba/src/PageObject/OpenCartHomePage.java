package PageObject;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * PAGINA HOME
 * @author Jorge Nicolás Fernández
 */
public class OpenCartHomePage extends AbstractPageObject {
	
	private By inputSearch = By.name("search");
	private By buttonSearch = By.xpath("//span[@class='input-group-btn']/button");
	private By gridView = By.id("grid-view");
	private By lista = By.className("caption");
	private By aTagName = By.tagName("a");
	private By tittle = By.xpath("//*[@id='content']/div[1]/div[2]/h1");
	
	public OpenCartHomePage(WebDriver driver) {
		super(driver);
	}
		
	/**
	 * Busca un producto con nombre nombreProducto pasado por parámetro.
	 * @param nombreProducto
	 * @return
	 */
	public String buscarProducto(String nombreProducto){
		WebElement inputSearchElement = driver.findElement(inputSearch);
		if(inputSearchElement != null){
			inputSearchElement.clear();
			inputSearchElement.sendKeys(nombreProducto);
			WebElement buttonElement = driver.findElement(buttonSearch);
			buttonElement.click();
			WebElement gridViewElement = driver.findElement(gridView);
			gridViewElement.click();
			List<WebElement> gridViewList = driver.findElements(lista);
	        if(gridViewList.size() >= 0){
	        	WebElement firstElement = gridViewList.get(0);
	    		return firstElement.findElement(aTagName).getText();
	        }	
		}
		return "";
	}
	
	/**
	 * Busca un producto con nombre nombreProducto pasado por parámetro, presiona 
	 * Click sobre el mismo y devuelve el título.
	 * @param nombreProducto
	 * @return
	 */
	public String buscarEntrarYVerificarTituloDelProducto(String nombreProducto){
		WebElement inputSearchElement = driver.findElement(inputSearch);
		if(inputSearchElement != null){
			inputSearchElement.clear();
			inputSearchElement.sendKeys(nombreProducto);
			WebElement buttonElement = driver.findElement(buttonSearch);
			buttonElement.click();
			WebElement gridViewElement = driver.findElement(gridView);
			gridViewElement.click();
			List<WebElement> gridViewList = driver.findElements(lista);
	        if(gridViewList.size() >= 0){
	        	WebElement firstElement = gridViewList.get(0);
	        	firstElement.findElement(aTagName).click();
	        	WebElement tittleElement = driver.findElement(tittle);
	    		return tittleElement.getText();
	        }	
		}
		return "";
	}
}