package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ResultsPage {
	private WebDriver driver = null;
	WebDriverWait wait = null;
	
	@FindBy(css="div.eva-3-results-list.-banner-top div.results-cluster-container")
	WebElement resultadoBusqueda;

	public ResultsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver,5);
	}
	
	public void cerrar()
	{
		driver.close();
		driver.quit();
	}
	
	
	public void resultado()
	{
		wait.until(ExpectedConditions.elementToBeClickable(resultadoBusqueda));
		Assert.assertTrue(resultadoBusqueda.isDisplayed(), "No hay reserva");		
	}

}
