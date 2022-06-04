package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ResultsPage {
	private WebDriver driver = null;
	//public WebDriverWait wait = new WebDriverWait(driver,5);
	
	@FindBy(xpath="//*[@index='0']//*[@class='eva-3-cluster-gallery -eva-3-bc-white -eva-3-shadow-line-hover']")
	WebElement resultadoBusqueda;
	
	public ResultsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void resultado()
	{
		//wait.until(ExpectedConditions.elementToBeClickable(resultadoBusqueda));
		Assert.assertTrue(resultadoBusqueda.isDisplayed(), "No hay reserva");
		driver.close();
		driver.quit();
	}

}
