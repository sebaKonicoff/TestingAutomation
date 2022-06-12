package proyectoAutomation.proyectoAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.selenium.driver.Driver;

import pageObjects.DespegarAlojamientosPage;
import pageObjects.DespegarHomePage;
import pageObjects.ResultsPage;


public class Despegar extends Driver{
	WebDriverWait wait;
	WebDriver driver;
	
	@BeforeMethod(alwaysRun=true)
	public void initTest(ITestContext context)
	{
		String navegadorSuite = context.getCurrentXmlTest().getParameter("Navegador");
		String navegador = navegadorSuite != null ? navegadorSuite : "chrome";
		
		driver = Driver.LevatnarBrowser(navegador);
	}
	
	@DataProvider(name = "ciudades")
	public Object[][] ciudadesProvider()
	{
		return new Object[][] {{"Córdoba"}};//,{"Mendoza"}};//,{"San Juan"}};
	}
	
	@AfterMethod(alwaysRun=true)
	public void cerrar()
	{
		driver.close();
	}
	
	@Test(groups = {"grupoMenor10"}, description = "Validar busqueda en pagina Despegar con menor de 10 años", dataProvider="ciudades")
	public void validarBusqueda(String ciudades) throws InterruptedException
	{
		despegar(ciudades,11);
	}
	
	@Test(groups = {"grupoMenor15"}, description = "Validar busqueda en pagina Despegar con menor de 15 años", dataProvider="ciudades")
	public void validarBusqueda2(String ciudades) throws InterruptedException
	{
		despegar(ciudades,16);		
	}	
	
	public void despegar(String ciudades, int edad) throws InterruptedException
	{
		Driver.goToMainPage(driver);
		DespegarHomePage homePage = new DespegarHomePage(driver);
		DespegarAlojamientosPage alojamientoPage = homePage.buscarAlojamiento();
		alojamientoPage.ingresarCiudadDestino(ciudades);
		alojamientoPage.ingresarFechaIngreso();
		alojamientoPage.ingresarFechaSalida();
		alojamientoPage.seleccionarPasajero(edad);
		ResultsPage resultsPage = new ResultsPage(driver);
		resultsPage = alojamientoPage.buscarReserva();
		resultsPage.resultado();
	}
}
