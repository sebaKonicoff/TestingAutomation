package com.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
	
	public static void goToMainPage(WebDriver driver)
	{
		driver.get("https://www.despegar.com.ar/");
	}
	
	public static WebDriver LevatnarBrowser(String browserName, String url)
	{
		browserName.toLowerCase();
		WebDriver driver = null;
		switch(browserName)
		{
			case "chrome":
			{
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\sebak\\eclipse-workspace\\proyectoAutomation\\src\\resources\\Recursos\\chromedriver.exe");  
				System.out.println("Abro browser de Chrome");
				driver = new ChromeDriver();
				break;
			}
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "C:\\Users\\sebak\\eclipse-workspace\\proyectoAutomation\\src\\resources\\Recursos\\geckodriver.exe");  
				System.out.println("Abro browser de Firefox");
				driver = new FirefoxDriver();
				break;
			case "edge":
				System.out.println("Browser sin implementar");
				break;
			default:
			{
				System.out.println("No se seleccionó navegador, abrimos Chrome por defecto.");
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\sebak\\eclipse-workspace\\proyectoAutomation\\src\\resources\\Recursos\\chromedriver.exe");  
				System.out.println("Abro browser de Chrome");
				driver = new ChromeDriver();
				break;
			}
		}
		return driver;
	}

}
