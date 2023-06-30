package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Base.BasePage;

public class HomePage extends BasePage{


	private By register = By.xpath("//*[@id=\"loginPanel\"]/p[2]/a");

	public HomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public void clickearRegister() throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(register));
        clickear(register);
    }

}
