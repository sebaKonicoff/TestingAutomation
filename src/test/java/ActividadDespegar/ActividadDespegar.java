package ActividadDespegar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;  import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActividadDespegar {

	WebDriver driver;
	WebElement alojamiento, calendarioIngreso, calendarioSalida, fechaSalida, habitacion;
	WebDriverWait wait;
public void definirWebElement()
{
	System.setProperty("webdriver.chrome.driver", "D:\\Chromedriver\\chromedriver_win32\\chromedriver.exe");  driver = new ChromeDriver();
	wait = new WebDriverWait(driver,5);
	driver.get("https://www.despegar.com.ar/");
	alojamiento = driver.findElement(By.xpath("//*[@class='shifu-3-button-circle HOTELS paint-circle ']"));

}

public void ingresarFechaIngreso() throws InterruptedException
{
	calendarioIngreso = driver.findElement(By.xpath("//*[@class='sbox5-box-dates-checkbox-container']//*[@class='sbox5-dates-input1-container']//*[@class='input-container']"));
	wait.until(ExpectedConditions.elementToBeClickable(calendarioIngreso));
	calendarioIngreso.click();
	Thread.sleep(100);
	WebElement fechaIngreso = driver.findElement(By.xpath("//*[@class='sbox5-floating-tooltip sbox5-floating-tooltip-opened']//*[@class='sbox5-monthgrid-dates sbox5-monthgrid-dates-31']//*[@class='sbox5-monthgrid-datenumber']//*[@class='sbox5-monthgrid-datenumber-number'][text()='27']"));
	wait.until(ExpectedConditions.elementToBeClickable(fechaIngreso));
	fechaIngreso.click();
}

public void ingresarFechaSalida() throws InterruptedException
{
	calendarioSalida = driver.findElement(By.xpath("//*[@class='sbox5-box-dates-checkbox-container']//*[@class='sbox5-dates-input2-container']"));
	wait.until(ExpectedConditions.elementToBeClickable(calendarioIngreso));
	calendarioSalida.click();
	Thread.sleep(100);
	fechaSalida = driver.findElement(By.xpath("//*[@class='sbox5-floating-tooltip sbox5-floating-tooltip-opened']//*[@class='sbox5-monthgrid sbox5-compact-view']//*[@class='sbox5-monthgrid-dates sbox5-monthgrid-dates-31']//*[@class='sbox5-monthgrid-datenumber-number'][text()='31']"));
	fechaSalida.click();
}

public void seleccionarPasajero() throws InterruptedException
{
	habitacion = driver.findElement(By.xpath("//*[@class='sbox5-3-distribution-passengers sbox5-3-validation -top-right']//*[@class='sbox5-3-first-input']"));
	wait.until(ExpectedConditions.elementToBeClickable(habitacion));
	habitacion.click();
	Thread.sleep(100);
	WebElement botonMasAdulto = driver.findElement(By.xpath("//*[@class='stepper__room']//*[@class='stepper__room__row'][1]//*[@class='steppers-icon-right stepper__icon']"));
	wait.until(ExpectedConditions.elementToBeClickable(botonMasAdulto));
	botonMasAdulto.click();
	Thread.sleep(100);
	//busqueda para seleccionar un menor.
	habitacion.click();
	Thread.sleep(100);
	WebElement botonMasMenor = driver.findElement(By.xpath("//*[@class='stepper__room']//*[@class='stepper__room__row'][2]//*[@class='steppers-icon-right stepper__icon']"));
	wait.until(ExpectedConditions.elementToBeClickable(botonMasMenor));
	botonMasMenor.click();
	Thread.sleep(1000);
	WebElement edadMenor = driver.findElement(By.xpath("//*[@class='select-container']//*[@class='select']"));
	edadMenor.sendKeys(Keys.ARROW_DOWN);
	Thread.sleep(100);
	edadMenor.sendKeys(Keys.ENTER);
	//Boton Aplicar
	WebElement btnAplicar = driver.findElement(By.xpath("//*[@class='distribution__container distribution__type__rooms']//*[@class='stepper__room__footer ']//*[@class='sbox5-3-btn -md -primary']"));
	wait.until(ExpectedConditions.elementToBeClickable(btnAplicar));
	btnAplicar.click();
}

public void buscarReserva()
{
	WebElement btnBuscar = driver.findElement(By.xpath("//*[@class='sbox5-box-content']//*[@type='button']"));
	wait.until(ExpectedConditions.elementToBeClickable(btnBuscar));
	btnBuscar.click();
}

public void resultado()
{
	WebElement resultadoBusqueda = driver.findElement(By.xpath("//*[@index='0']//*[@class='eva-3-cluster-gallery -eva-3-bc-white -eva-3-shadow-line-hover']"));
	wait.until(ExpectedConditions.elementToBeClickable(resultadoBusqueda));
	Assert.assertTrue(resultadoBusqueda.isDisplayed(), "No hay reserva");
	driver.close();
}

@Test(description = "Buscar Actividad de Despegar")
public void TestDespegar() throws Exception {
	definirWebElement();
	alojamiento.click();
	WebElement destino = driver.findElement(By.xpath("//*[@class='input-container']//*[@type='text'][@placeholder='Ingresá una ciudad, alojamiento o punto de interés']"));
	//driver.manage().window().maximize();
	//sirve para maximizar el chrome
	wait.until(ExpectedConditions.elementToBeClickable(destino));
	destino.click();
	destino.sendKeys("Córdoba");
	Thread.sleep(1000);
	destino.sendKeys(Keys.CONTROL);
	Thread.sleep(1000);
	WebElement primerCiudad = driver.findElement(By.xpath("//*[@class='ac-group-container']//*[@class='ac-group-items']//*[@class='item -active']"));
	wait.until(ExpectedConditions.elementToBeClickable(primerCiudad));
	Thread.sleep(1000);
	destino.sendKeys(Keys.ENTER);
	ingresarFechaIngreso();
	ingresarFechaSalida();
	seleccionarPasajero();
	buscarReserva();
	resultado();
	}
}

