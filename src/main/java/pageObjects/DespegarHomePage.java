package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DespegarHomePage {
	
	@FindBy(css="a.shifu-3-button-circle.HOTELS.paint-circle ")
	WebElement alojamiento;
	
	private WebDriver driver = null;
	
	public DespegarHomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public DespegarAlojamientosPage buscarAlojamiento()
	{
		alojamiento.click();
		return new DespegarAlojamientosPage(this.driver);
	}
}
