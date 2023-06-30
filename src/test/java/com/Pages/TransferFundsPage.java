package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TransferFundsPage extends BasePage {


	private By mensaje = By.xpath("//*[@id=\"rightPanel\"]/div/div/h1");
    private By campoAmount = By.id("amount");

    private By desdeAccount = By.id("fromAccountId");

    private By aAccount = By.id("toAccountId");


    private By botonTransfer = By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div[2]/input");



    public TransferFundsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

    public void completarInput(String amount)throws InterruptedException{
    	wait.until(ExpectedConditions.presenceOfElementLocated(campoAmount));
        wait.until(ExpectedConditions.presenceOfElementLocated(desdeAccount));
        wait.until(ExpectedConditions.presenceOfElementLocated(aAccount));

        sendText(amount, campoAmount);
        Thread.sleep(5000);
        Select select = new Select(driver.findElement(desdeAccount));
        seleccionarEnSelect(desdeAccount, select.getFirstSelectedOption().getText());
        select = new Select(driver.findElement(aAccount));
        List<WebElement> allOptions = select.getOptions();
        WebElement lastOptionElement = allOptions.get(allOptions.size() - 1);
        seleccionarEnSelect(aAccount, lastOptionElement.getText());

    }


    public void submit() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(botonTransfer));
        clickear(botonTransfer);
    }

    public String getMensajeTransferirFondos(){
        wait.until(ExpectedConditions.presenceOfElementLocated(mensaje));
        return getText(mensaje);
    }

}
