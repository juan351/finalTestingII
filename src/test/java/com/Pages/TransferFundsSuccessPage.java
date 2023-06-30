package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferFundsSuccessPage extends BasePage{


	private By mensaje = By.xpath("//*[@id=\"rightPanel\"]/div/div/h1");



	public TransferFundsSuccessPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public String obtenerMensaje() throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(mensaje));
		Thread.sleep(2000);

		return getText(mensaje);
    }



}
