package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Base.BasePage;

public class RegisterSuccessPage extends BasePage{


	private By mensaje = By.xpath("//DIV[@id='rightPanel']//P");



	public RegisterSuccessPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public String obtenerMensaje() throws InterruptedException {

		wait.until(ExpectedConditions.presenceOfElementLocated(mensaje));

		return getText(mensaje);
    }



}
