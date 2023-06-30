package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountsDetailsPage extends BasePage{

	private By mensaje = By.xpath("//*[@id=\"rightPanel\"]/div/div[1]/h1");

	private By periodoActividad = By.id("month");

	private By tipoActividad = By.id("transactionType");

	private By botonIr = By.xpath("//*[@id=\"rightPanel\"]/div/div[2]/form/table/tbody/tr[3]/td[2]/input");

	public AccountsDetailsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public String obtenerMensaje() throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(mensaje));

		return getText(mensaje);
    }

	public void seleccionarPeriodoActividad(String value) throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(periodoActividad));

		seleccionarEnSelect(periodoActividad, value);
	}

	public void seleccionarTipoActividad(String value) throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(tipoActividad));

		seleccionarEnSelect(tipoActividad, value);
	}

	public void clickearIr() throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(botonIr));

		clickear(botonIr);
	}


}
