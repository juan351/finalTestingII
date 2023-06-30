package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAccountSuccessPage extends BasePage{


	private By mensaje = By.xpath("//*[@id=\"rightPanel\"]/div/div/p[1]");
	private By accountOverview;


	public NewAccountSuccessPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public String obtenerMensaje() throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(mensaje));

		return getText(mensaje);
    }

	public void clickearAccountOverview(){
		accountOverview = By.xpath("//*[@id=\"leftPanel\"]/ul/li[2]/a");
		wait.until(ExpectedConditions.presenceOfElementLocated(accountOverview));

		clickear(accountOverview);
	}

}
