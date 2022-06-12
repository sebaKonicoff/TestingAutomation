package pageObjects;

import java.util.List;

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
	WebElement edadMenor,valueElemento;
	
	@FindBy(css="div#searchbox-sbox-box-hotels div.sbox-places-destination--1xd0k input")
	WebElement destino;
	@FindBy(css="div.ac-wrapper.-show>div>div>ul>li")	
	WebElement primerCiudad;
	@FindBy(css="div#searchbox-sbox-box-hotels div.sbox5-box-dates-checkbox-container div.sbox5-dates-input1")
	WebElement calendarioIngreso;
	@FindBys( {
		   @FindBy(css = "div.sbox5-floating-tooltip-opened div.sbox5-monthgrid.sbox5-compact-view div.sbox5-monthgrid-dates.sbox5-monthgrid-dates-30  div.sbox5-monthgrid-datenumber-number")
		} )
		private List<WebElement> fecha;
	@FindBy(css="div#searchbox-sbox-box-hotels div.sbox5-box-dates-checkbox-container div.sbox5-dates-input2")
	WebElement calendarioSalida;
	
	@FindBy(xpath="//*[@class='sbox5-floating-tooltip sbox5-floating-tooltip-opened']//*[@class='sbox5-monthgrid-dates sbox5-monthgrid-dates-30']//*[@class='sbox5-monthgrid-datenumber']//*[@class='sbox5-monthgrid-datenumber-number'][text()='27']")
	WebElement fechaSalida;
	@FindBy(css="div.sbox5-box-content div.sbox5-3-double-input div.sbox5-3-first-input-wrapper input")
	WebElement habitacion;
	@FindBy(css="div.stepper__room div.stepper__distribution_container div.stepper__room__row:nth-child(1) button.steppers-icon-right.stepper__icon")
	WebElement botonMasAdulto;
	@FindBy(css="div.stepper__room div.stepper__distribution_container div.stepper__room__row:nth-child(2) button.steppers-icon-right.stepper__icon")
	WebElement botonMasMenor;
	@FindBy(css="div.select-container select.select")
	WebElement btnEdadMenor;
	@FindBys( {
		   @FindBy(css = "select.select option")
		} )
		private List<WebElement> edadesMenores;
	@FindBy(css="div.distribution__container.distribution__type__rooms div.stepper__room__footer a.sbox5-3-btn.-md.-primary")
	WebElement btnAplicar;
	@FindBy(css="div.sbox5-box-content button")
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
		destino.sendKeys(Keys.ENTER);
	}
	
	public void ingresarFechaIngreso() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(calendarioIngreso));
		calendarioIngreso.click();
		seleccionarDia(23);
	}
	
	public void ingresarFechaSalida() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(calendarioIngreso));
		calendarioSalida.click();
		seleccionarDia(26);
	}
	
	public void seleccionarPasajero(int edadMenor) throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(habitacion));
		habitacion.click();
		wait.until(ExpectedConditions.elementToBeClickable(botonMasAdulto));
		botonMasAdulto.click();
		Thread.sleep(100);
		//busqueda para seleccionar un menor.
		wait.until(ExpectedConditions.elementToBeClickable(botonMasMenor));
		botonMasMenor.click();
		btnEdadMenor.click();
		Thread.sleep(1000);
		ciclarLista(edadMenor);
		//Boton Aplicar
		wait.until(ExpectedConditions.elementToBeClickable(btnAplicar));
		btnAplicar.click();
	}
	
	public ResultsPage buscarReserva()
	{
		btnBuscar.click();
		return new ResultsPage(this.driver);
	}
	
	public void seleccionarDia(int dia)
	{
		wait.until(ExpectedConditions.visibilityOf(fecha.get(dia)));
		fecha.get(dia).click();
	}
	
	public void ciclarLista(int edad) {		
		wait.until(ExpectedConditions.visibilityOfAllElements(edadesMenores));
		edadesMenores.get(edad).click();
	}
}
