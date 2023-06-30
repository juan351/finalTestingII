package com.Tests.Front;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import com.Pages.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.extent_reports.ExtentFactory;
import org.junit.jupiter.api.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestsFront {

	private static WebDriver driver;

	private static WebDriverWait wait;

	static ExtentSparkReporter sparky = new ExtentSparkReporter("target/Sparky.html");
	static ExtentReports extent;
	static ExtentTest test;

	@BeforeAll
	@Tag("front")
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofMillis(2000));
		driver.manage().window().maximize();
		driver.get("https://parabank.parasoft.com/parabank/index.htm");
		extent = ExtentFactory.getInstance();
	}


	@Test
	@Tag("front")
	@Order(1)
	void registroExitoso() throws InterruptedException {
		extent.attachReporter(sparky);
		test = extent.createTest("Registro");
		test.log(Status.INFO, "Inicio del test...");
		HomePage homePage = new HomePage(driver, wait);
		homePage.clickearRegister();


		test.log(Status.INFO, "Ingresando a página de registro...");
		RegisterPage registerPage = new RegisterPage(driver, wait);
		test.log(Status.PASS, "Se ha ingresado exitosamente a la pagina.");

		test.log(Status.INFO, "Completando input...");
		registerPage.completarInput("Jorge", "Perez", "Basso Dastugue SN", "Chivilcoy",
				"Buenos Aires", "6602", "23465454545", "0515415454154", "jorgitoperez8", "123456");

		test.log(Status.INFO, "Enviando formulario...");
		registerPage.submit();


		RegisterSuccessPage registerSuccessPage = new RegisterSuccessPage(driver, wait);
		test.log(Status.PASS, "Se ha enviado exitosamente el formulario.");
		test.log(Status.INFO, "Obteniendo mensaje de registro...");
		String mensaje = registerSuccessPage.obtenerMensaje();

		test.log(Status.INFO, "Evaluando que el mensaje sea el esperado...");
		// Resultado: Se muestra pantalla con el texto "You are now logged in"
		assertTrue(mensaje.contains("You are now logged in"));
		if (mensaje.contains("You are now logged in")) {
			test.log(Status.PASS, "El mensaje coincide con el esperado.");
		} else {
			test.log(Status.FAIL, "El mensaje no coincide con el esperado. Recibido: "+mensaje);
		}
		test.log(Status.INFO, "Redirigiendo a la página de Nueva Cuenta...");
		registerSuccessPage.clickearNewAccount();
	}

	@Test
	@Tag("front")
	@Order(2)
	void abrirNuevaCuenta() throws InterruptedException {

		test = extent.createTest("Abrir Nueva Cuenta");

		NewAccountPage newAccountPage = new NewAccountPage(driver, wait);

		test.log(Status.INFO, "Página de Nueva Cuenta abierta...");

		test.log(Status.INFO, "Seleccionando tipo de cuenta 1...");
		newAccountPage.seleccionarAccountType("1");
		test.log(Status.PASS, "Cuenta seleccionada");

		test.log(Status.INFO, "Haciendo click en Abrir Nueva Cuenta...");
		newAccountPage.clickearAbrirNuevaCuenta();

		NewAccountSuccessPage newAccountSuccessPage = new NewAccountSuccessPage(driver, wait);
		test.log(Status.PASS, "Nueva cuenta creada exitosamente");
		test.log(Status.INFO, "Obteniendo mensaje de creación exitosa...");
		String mensaje = newAccountSuccessPage.obtenerMensaje();
		test.log(Status.PASS, "Mensaje obtenido");

		test.log(Status.INFO, "Evaluando que el mensaje coincida con el esperado...");
		// Resultado: Se muestra pantalla el texto "Congratulations, your account is now open."
		assertTrue(mensaje.contains("Congratulations, your account is now open."));

		if (mensaje.contains("Congratulations, your account is now open.")) {
			test.log(Status.PASS, "El mensaje coincide con el esperado.");
		} else {
			test.log(Status.FAIL, "El mensaje no coincide con el esperado. Recibido: "+mensaje);
		}

		// Creando segunda cuenta para la transferencia
		test.log(Status.INFO, "Creando segunda cuenta para hacer una posterior transferencia...");
		newAccountSuccessPage.clickearNewAccount();

		NewAccountPage newAccountPage2 = new NewAccountPage(driver, wait);

		newAccountPage2.seleccionarAccountType("1");

		newAccountPage2.clickearAbrirNuevaCuenta();

		NewAccountSuccessPage newAccountSuccessPage2 = new NewAccountSuccessPage(driver, wait);
		String mensaje2 = newAccountSuccessPage.obtenerMensaje();

		test.log(Status.PASS, "Segunda cuenta creada exitosamente.");

	}

	@Test
	@Tag("front")
	@Order(3)
	void resumenCuenta() throws InterruptedException {

		test = extent.createTest("Resumen de cuenta");

		test.log(Status.INFO, "Ingresando a página de Resumen de Cuenta...");
		AccountsOverviewPage accountsOverviewPage = new AccountsOverviewPage(driver, wait);
		accountsOverviewPage.clickearAccountOverview();
		test.log(Status.PASS, "Ingreso exitoso.");

		test.log(Status.INFO, "Obteniendo mensaje de página de Resumen");
		String mensaje = accountsOverviewPage.obtenerMensaje();
		test.log(Status.PASS, "mensaje obtenido.");

		test.log(Status.INFO, "Evaluando que el mensaje coincida con el esperado...");
		// Resultado: Se muestra en pantalla el texto "*Balance includes deposits that may be subject to holds"
		assertTrue(mensaje.contains("*Balance includes deposits that may be subject to holds"));
		if (mensaje.contains("*Balance includes deposits that may be subject to holds")) {
			test.log(Status.PASS, "El mensaje coincide con el esperado.");
		} else {
			test.log(Status.FAIL, "El mensaje no coincide con el esperado. Recibido: "+mensaje);
		}

	}

	@Test
	@Tag("front")
	@Order(4)
	void transferirFondos() throws InterruptedException {

		test = extent.createTest("Transferir fondos");

		test.log(Status.INFO, "Ingresando a página de Transferencia de fondos...");
		TransferFundsPage transferFundsPage = new TransferFundsPage(driver, wait);
		transferFundsPage.clickearTransferirFondos();
		Thread.sleep(4000);
		test.log(Status.PASS, "Ingreso exitoso.");

		test.log(Status.INFO, "Obteniendo título de página de Transferencia de fondos...");
		// Resultado: el texto "Transfer Funds" es visible en la pantalla
		String mensajeFunds = transferFundsPage.getMensajeTransferirFondos();
		test.log(Status.PASS, "Título obtenido.");

		test.log(Status.INFO, "Evaluando que el título coincida con el esperado...");
		assertTrue(mensajeFunds.contains("Transfer Funds"));

		if (mensajeFunds.contains("Transfer Funds")) {
			test.log(Status.PASS, "El título coincide con el esperado.");
		} else {
			test.log(Status.FAIL, "El título no coincide con el esperado. Recibido: "+mensajeFunds);
		}

		test.log(Status.INFO, "Ingresando monto y seleccionando cuentas...");
		transferFundsPage.completarInput("2000");
		test.log(Status.PASS, "Monto ingresado y cuentas seleccionadas.");
		test.log(Status.INFO, "Enviando formulario...");
		transferFundsPage.submit();

		TransferFundsSuccessPage transferFundsSuccessPage = new TransferFundsSuccessPage(driver, wait);

		Thread.sleep(4000);
		test.log(Status.PASS, "Formulario enviado. Mostrando página de Success");

		test.log(Status.INFO, "Obteniendo mensaje de página de Success...");
		String mensajeSuccess = transferFundsSuccessPage.obtenerMensaje();
		test.log(Status.PASS, "Mensaje obtenido");

		test.log(Status.INFO, "Evaluando que el mensaje coincida con el esperado...");
		//Resultado: el texto "Transfer Complete!" es visible en la pantalla
		assertTrue(mensajeSuccess.contains("Transfer Complete!"));
		if (mensajeSuccess.contains("Transfer Complete!")) {
			test.log(Status.PASS, "El mensaje coincide con el esperado.");
		} else {
			test.log(Status.FAIL, "El mensaje no coincide con el esperado. Recibido: "+mensajeSuccess);
		}
	}

	@Test
	@Tag("front")
	@Order(5)
	void resumenCuentaPorMes() throws InterruptedException {

		test = extent.createTest("Resumen de Cuenta por mes");

		test.log(Status.INFO, "Ingresando a página de Resumen...");

		AccountsOverviewPage accountsOverviewPage = new AccountsOverviewPage(driver, wait);
		accountsOverviewPage.clickearAccountOverview();
		test.log(Status.PASS, "Ingreso exitoso.");

		test.log(Status.INFO, "Obteniendo mensaje de página de Resumen");
		String mensaje = accountsOverviewPage.obtenerMensaje();
		test.log(Status.PASS, "Mensaje obtenido.");

		test.log(Status.INFO, "Evaluando que el mensaje coincida con el esperado...");
		// Resultado: Se muestra en pantalla el texto "*Balance includes deposits that may be subject to holds"
		assertTrue(mensaje.contains("*Balance includes deposits that may be subject to holds"));
		if (mensaje.contains("*Balance includes deposits that may be subject to holds")) {
			test.log(Status.PASS, "El mensaje coincide con el esperado.");
		} else {
			test.log(Status.FAIL, "El mensaje no coincide con el esperado. Recibido: "+mensaje);
		}

		test.log(Status.INFO, "Ingresando a detalle mensual de cuenta...");
		accountsOverviewPage.clickearCuentaAExaminar();

		AccountsDetailsPage accountsDetailsPage = new AccountsDetailsPage(driver, wait);
		test.log(Status.PASS, "Ingresando exitoso...");

		test.log(Status.INFO, "Obteniendo mensaje de página de detalle de cuenta por mes");
		String mensajeDetail = accountsDetailsPage.obtenerMensaje();
		test.log(Status.PASS, "Mensaje obtenido.");

		test.log(Status.INFO, "Evaluando que el título de página coincida con el esperado...");
		// Resultado: Se muestra en pantalla el texto "Account Details"
		assertTrue(mensajeDetail.contains("Account Details"));
		if (mensajeDetail.contains("Account Details")) {
			test.log(Status.PASS, "El título coincide con el esperado.");
		} else {
			test.log(Status.FAIL, "El título no coincide con el esperado. Recibido: "+mensajeDetail);
		}

		test.log(Status.INFO, "Seleccionando todos los periodos de actividad...");
		accountsDetailsPage.seleccionarPeriodoActividad("All");
		accountsDetailsPage.seleccionarTipoActividad("All");

		test.log(Status.INFO, "Enviando solicitud...");
		accountsDetailsPage.clickearIr();

		Thread.sleep(4000);
		test.log(Status.PASS, "Test finalizado.");

	}

	@AfterAll
	@Tag("front")
	public static void quit(){
		extent.flush();

		driver.close();
		driver.quit();
	}

}
