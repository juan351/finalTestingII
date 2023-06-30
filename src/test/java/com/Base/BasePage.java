package com.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    private By abrirNuevaCuenta = By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div/input");
    private By accountOverview;
    private By newAccount;
    private By transferirFondos = By.xpath("//*[@id=\"leftPanel\"]/ul/li[3]/a");



    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void ingresar(String url) {
        driver.get(url);
    }

    public void setup(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver.manage().window().maximize();
    }

    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    public void sendText(String input, By locator){
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(input);
    }

    public void sendKey(CharSequence input, By locator){ //no sé para qué es
        driver.findElement(locator).sendKeys(input);
    }

    public void clickear(By locator){
        driver.findElement(locator).click();
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public void seleccionarEnSelect(By locator, String value){
        Select select = new Select(driver.findElement(locator));
        select.selectByValue(value);

    }

    public void clickearAbrirNuevaCuenta() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(abrirNuevaCuenta));

        clickear(abrirNuevaCuenta);
    }

    public void clickearTransferirFondos() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(transferirFondos));

        clickear(transferirFondos);
    }

    public void clickearNewAccount() throws InterruptedException {
        Thread.sleep(4000);
        newAccount = By.xpath("/html/body/div[1]/div[3]/div[1]/ul/li[1]/a");
        System.out.println(newAccount);
        wait.until(ExpectedConditions.presenceOfElementLocated(newAccount));

        clickear(newAccount);
    }

    public void clickearAccountOverview(){
        accountOverview = By.xpath("//*[@id=\"leftPanel\"]/ul/li[2]/a");
        wait.until(ExpectedConditions.presenceOfElementLocated(accountOverview));

        clickear(accountOverview);
    }



    public void cerrar() {
        driver.quit();
    }
}