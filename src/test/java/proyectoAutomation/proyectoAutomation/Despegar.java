package proyectoAutomation.proyectoAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.selenium.driver.Driver;

import pageObjects.DespegarAlojamientosPage;
import pageObjects.DespegarHomePage;
import pageObjects.ResultsPage;


public class Despegar extends Driver{
	WebDriverWait wait;
	WebDriver driver;
	
	@BeforeMethod
	public void initTest(ITestContext context)
	{
		String navegadorSuite = context.getCurrentXmlTest().getParameter("Navegador");
		String navegador = navegadorSuite != null ? navegadorSuite : "chrome";
		
		//String url = "https://www.despegar.com.ar/";
		
		driver = Driver.LevatnarBrowser(navegador);
	}
	
	@AfterMethod
	public void cerrar()
	{
		driver.close();
	}
	
	@Test(description = "Validar busqueda en pagina Despegar")
	public void validarBusqueda() throws InterruptedException
	{
		//wait = new WebDriverWait(driver,5);
		Driver.goToMainPage(driver);
		DespegarHomePage homePage = new DespegarHomePage(driver);
		DespegarAlojamientosPage alojamientoPage = homePage.buscarAlojamiento();
		//alojamientoPage = homePage.buscarAlojamiento();
		alojamientoPage.ingresarCiudadDestino("Córdoba");
		alojamientoPage.ingresarFechaIngreso();
		alojamientoPage.ingresarFechaSalida();
		alojamientoPage.seleccionarPasajero();
		ResultsPage resultsPage = new ResultsPage(driver);
		resultsPage = alojamientoPage.buscarReserva();
		resultsPage.resultado();
	}
	
}
