package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPageObject {
	protected WebDriver driver;
	protected WebDriverWait wait;

	public AbstractPageObject(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 30);
	}
}
