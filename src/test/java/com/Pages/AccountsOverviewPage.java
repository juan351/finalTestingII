package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountsOverviewPage extends BasePage{

	private By mensaje = By.xpath("//*[@id=\"accountTable\"]/tfoot/tr/td");



	private By cuentaAExaminar = By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a");

	public AccountsOverviewPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public String obtenerMensaje() throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(mensaje));

		return getText(mensaje);
    }



	public void clickearCuentaAExaminar() throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(cuentaAExaminar));

		clickear(cuentaAExaminar);
	}

}
