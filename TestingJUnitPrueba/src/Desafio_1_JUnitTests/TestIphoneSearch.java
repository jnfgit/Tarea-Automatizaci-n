package Desafio_1_JUnitTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

/**
 * DESAFIO 1
 * @author Jorge Nicolás Fernández
 */
public class TestIphoneSearch {
    FirefoxDriver wd;
    
    @Before
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void TestIphone() {
        wd.get("http://demo.opencart.com/");
        wd.findElement(By.name("search")).click();
        wd.findElement(By.name("search")).clear();
        wd.findElement(By.name("search")).sendKeys("iPhone");
        wd.findElement(By.xpath("//span[@class='input-group-btn']/button")).click();
        wd.findElement(By.id("grid-view")).click();
        
        List<WebElement> che = wd.findElements(By.className("caption"));
        if(che.size() >= 0){
        	WebElement element = che.get(0);
        	String ar = "";
    		ar = element.getText();
    		ar = element.findElement(By.tagName("a")).getText();
    		assertEquals("iPhone", ar);
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
}
