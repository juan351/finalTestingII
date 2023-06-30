package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Base.BasePage;

public class NewAccountPage extends BasePage{

	private By tipoCuenta = By.id("type");

	public NewAccountPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public void seleccionarAccountType(String value) throws InterruptedException {
		Thread.sleep(4000);
		wait.until(ExpectedConditions.presenceOfElementLocated(tipoCuenta));

		seleccionarEnSelect(tipoCuenta, value);
    }



}
