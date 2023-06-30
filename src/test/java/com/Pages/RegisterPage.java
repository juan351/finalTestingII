package com.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Base.BasePage;

public class RegisterPage extends BasePage {


	private By campoFirstName = By.id("customer.firstName");
    private By campoLastName = By.id("customer.lastName");
    private By campoAddress = By.id("customer.address.street");
    private By campoCity = By.id("customer.address.city");
    private By campoState = By.id("customer.address.state");
    private By campoZipCode = By.id("customer.address.zipCode");
    private By campoTelephone = By.id("customer.phoneNumber");
    private By campoSsn = By.id("customer.ssn");
    private By campoUsername = By.id("customer.username");
    private By campoPassword = By.id("customer.password");
    private By campoConfirmPassword = By.id("repeatedPassword");

    private By botonSubmit = By.xpath("(//INPUT[@type='submit'])[2]");



    public RegisterPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

    public void completarInput(String nombre, String apellido, String address, String city, String state, String zipCode,
                                  String tel, String ssn, String username, String password) {
    	wait.until(ExpectedConditions.presenceOfElementLocated(campoFirstName));
    	wait.until(ExpectedConditions.presenceOfElementLocated(campoLastName));
        wait.until(ExpectedConditions.presenceOfElementLocated(campoAddress));
        wait.until(ExpectedConditions.presenceOfElementLocated(campoCity));
        wait.until(ExpectedConditions.presenceOfElementLocated(campoState));
        wait.until(ExpectedConditions.presenceOfElementLocated(campoZipCode));
        wait.until(ExpectedConditions.presenceOfElementLocated(campoTelephone));
        wait.until(ExpectedConditions.presenceOfElementLocated(campoSsn));
        wait.until(ExpectedConditions.presenceOfElementLocated(campoUsername));
        wait.until(ExpectedConditions.presenceOfElementLocated(campoPassword));
        wait.until(ExpectedConditions.presenceOfElementLocated(campoConfirmPassword));

        sendText(nombre, campoFirstName);
        sendText(apellido, campoLastName);
        sendText(address, campoAddress);
        sendText(city, campoCity);
        sendText(state, campoState);
        sendText(zipCode, campoZipCode);
        sendText(tel, campoTelephone);
        sendText(ssn, campoSsn);
        sendText(username, campoUsername);
        sendText(password, campoPassword);
        sendText(password, campoConfirmPassword);


    }


    public void submit() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(botonSubmit));
        clickear(botonSubmit);
    }

}
