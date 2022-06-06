package proyectoAutomation.proyectoAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.selenium.driver.Driver;

import pageObjects.DespegarAlojamientosPage;
import pageObjects.DespegarHomePage;
import pageObjects.ResultsPage;

public class Despegar2 extends Driver{
	WebDriverWait wait;
	@Test(description = "Validar busqueda en pagina Despegar")
	public void validarBusqueda() throws InterruptedException
	{
		WebDriver driver = Driver.LevatnarBrowser("CHROME", null);
		wait = new WebDriverWait(driver,5);
		Driver.goToMainPage(driver);
		DespegarHomePage homePage = new DespegarHomePage(driver);
		DespegarAlojamientosPage alojamientoPage = new DespegarAlojamientosPage(driver);
		alojamientoPage = homePage.buscarAlojamiento();
		alojamientoPage.ingresarCiudadDestino("Córdoba");
		alojamientoPage.ingresarFechaIngreso();
		alojamientoPage.ingresarFechaSalida();
		alojamientoPage.seleccionarPasajero();
		ResultsPage resultsPage = new ResultsPage(driver);
		resultsPage = alojamientoPage.buscarReserva();
		resultsPage.resultado();
	}
}
