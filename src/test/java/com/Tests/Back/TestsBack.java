package com.Tests.Back;

import com.google.gson.JsonObject;
import com.model.Customer;
import org.junit.jupiter.api.*;


import static io.restassured.RestAssured.given;
import io.restassured.response.Response;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestsBack {


    public String usuario = "john";
    public String password = "demo";
    public Integer customerId;
    public Integer accountId = 24999;
    public Integer toAccountId = 25554;


    @Test
    @Tag("get")
    @Tag("back")
    @Order(0)
    public void registro() {
        given()
                .get("https://parabank.parasoft.com/parabank/register.htm")
                .then()
                .statusCode(200)
                .log().status();
    }

    @Test
    @Tag("get")
    @Tag("back")
    @Order(1)
    public void login() throws JAXBException {

        Response response = given()
                .get("https://parabank.parasoft.com/parabank/services/bank/login/"+usuario+"/"+password)
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();

        String body = response.getBody().asString();

        JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(body);
        Customer customer = (Customer) unmarshaller.unmarshal(reader);
        customerId = customer.getId();
        System.out.println("ID: " + customerId);
        System.out.println("https://parabank.parasoft.com/parabank/services/bank/createAccount?customerId="+ customerId
        +"&newAccountType=1&fromAccountId="+ accountId);

    }


    @Test
    @Tag("post")
    @Tag("back")
    @Order(2)
    public void abrirNuevaCuenta() {
        JsonObject request = new JsonObject();

        given()
                .contentType("application/json")
                .body(request.toString())
                .auth()
                .basic(usuario, password)
                .when()
                .post("https://parabank.parasoft.com/parabank/services/bank/createAccount?customerId="+ customerId
                        +"&newAccountType=1&fromAccountId="+ accountId)
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    @Tag("get")
    @Tag("back")
    @Order(3)
    public void resumenCuenta() {
        given()
                .when()
                .get("https://parabank.parasoft.com/parabank/services/bank/customers/"+ customerId)
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    @Tag("post")
    @Tag("back")
    @Order(4)
    public void transferirFondos() {
        given()
                .auth()
                .basic(usuario, password)
                .post("https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId="+ accountId +"&toAccountId="+ toAccountId +"&amount=10")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();
    }
    @Test
    @Tag("get")
    @Tag("back")
    @Order(5)
    public void resumenCuentaPorMes() {
        given()
                .when()
                .get("https://parabank.parasoft.com/parabank/services/bank/accounts/"+ accountId +"/transactions/month/All/type/All")
                .then()
                .statusCode(200)
                .log().body();
    }
}
