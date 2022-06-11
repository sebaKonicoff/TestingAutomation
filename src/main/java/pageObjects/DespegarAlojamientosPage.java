package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DespegarAlojamientosPage {
	private WebDriver driver = null;
	WebDriverWait wait = null;
	List<WebElement> listaElementos;
	WebElement edadMenor,valueElemento;
	
	@FindBy(xpath="//*[@class='input-container']//*[@type='text'][@placeholder='Ingresá una ciudad, alojamiento o punto de interés']")
	WebElement destino;
	@FindBy(xpath="//*[@class='ac-group-container']//*[@class='ac-group-items']//*[@class='item -active']")
	WebElement primerCiudad;
	@FindBy(xpath="//*[@class='sbox5-box-dates-checkbox-container']//*[@class='sbox5-dates-input1-container']//*[@class='input-container']")
	WebElement calendarioIngreso;
	@FindBy(xpath="//*[@class='sbox5-floating-tooltip sbox5-floating-tooltip-opened']//*[@class='sbox5-monthgrid-dates sbox5-monthgrid-dates-30']//*[@class='sbox5-monthgrid-datenumber']//*[@class='sbox5-monthgrid-datenumber-number'][text()='24']")
	WebElement fechaIngreso;
	@FindBy(xpath="//*[@class='sbox5-box-dates-checkbox-container']//*[@class='sbox5-dates-input2-container']")
	WebElement calendarioSalida;
	@FindBy(xpath="//*[@class='sbox5-floating-tooltip sbox5-floating-tooltip-opened']//*[@class='sbox5-monthgrid-dates sbox5-monthgrid-dates-30']//*[@class='sbox5-monthgrid-datenumber']//*[@class='sbox5-monthgrid-datenumber-number'][text()='27']")
	WebElement fechaSalida;
	@FindBy(xpath="//*[@class='sbox5-3-distribution-passengers sbox5-3-validation -top-right']//*[@class='sbox5-3-first-input']")
	WebElement habitacion;
	@FindBy(xpath="//*[@class='stepper__room']//*[@class='stepper__room__row'][1]//*[@class='steppers-icon-right stepper__icon']")
	WebElement botonMasAdulto;
	@FindBy(xpath="//*[@class='stepper__room']//*[@class='stepper__room__row'][2]//*[@class='steppers-icon-right stepper__icon']")
	WebElement botonMasMenor;
	@FindBy(xpath="//*[@class='select-container']//*[@class='select']")
	WebElement btnEdadMenor;
	@FindBys( {
		   @FindBy(xpath = "//select[@class='select']//option[@class='select-option']")
		} )
		private List<WebElement> edadesMenores;
	@FindBy(xpath="//*[@class='distribution__container distribution__type__rooms']//*[@class='stepper__room__footer ']//*[@class='sbox5-3-btn -md -primary']")
	WebElement btnAplicar;
	@FindBy(xpath="//*[@class='sbox5-box-content']//*[@type='button']")
	WebElement btnBuscar;
	
	public DespegarAlojamientosPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver,5);
	}
	
	public void ingresarCiudadDestino(String ciudad) throws InterruptedException
	{
		
		wait.until(ExpectedConditions.elementToBeClickable(destino));
		destino.click();
		destino.sendKeys(ciudad);
		Thread.sleep(1000);
		destino.sendKeys(Keys.CONTROL);
		wait.until(ExpectedConditions.elementToBeClickable(primerCiudad));
		//Thread.sleep(1000);
		destino.sendKeys(Keys.ENTER);
	}
	
	public void ingresarFechaIngreso() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(calendarioIngreso));
		calendarioIngreso.click();
		//Thread.sleep(100);
		wait.until(ExpectedConditions.elementToBeClickable(fechaIngreso));
		fechaIngreso.click();
	}
	
	public void ingresarFechaSalida() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(calendarioIngreso));
		calendarioSalida.click();
		//Thread.sleep(100);
		fechaSalida.click();
	}
	
	public void seleccionarPasajero(int edadMenor) throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(habitacion));
		habitacion.click();
		//Thread.sleep(100);
		wait.until(ExpectedConditions.elementToBeClickable(botonMasAdulto));
		botonMasAdulto.click();
		Thread.sleep(100);
		//busqueda para seleccionar un menor.
		wait.until(ExpectedConditions.elementToBeClickable(botonMasMenor));
		botonMasMenor.click();
		//Thread.sleep(1000);
		btnEdadMenor.click();
		Thread.sleep(1000);
		ciclarLista(edadMenor);
		//edadMenor.click();
		//Boton Aplicar
		wait.until(ExpectedConditions.elementToBeClickable(btnAplicar));
		btnAplicar.click();
	}
	
	public ResultsPage buscarReserva()
	{
		//wait.until(ExpectedConditions.elementToBeClickable(btnBuscar));
		btnBuscar.click();
		return new ResultsPage(this.driver);
	}
	
	public void ciclarLista(int edad) {		
		wait.until(ExpectedConditions.visibilityOfAllElements(edadesMenores));
		edadesMenores.get(edad).click();
	}
}
