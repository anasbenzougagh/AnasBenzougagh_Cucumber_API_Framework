package com.steps;

import com.utilities.pojo.FruitShop;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.testng.asserts.SoftAssert;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class FruitShopTest {

    @When("Create a POJO utility file to store response then use TestNG soft and hard assertion")
    public void createAPOJOUtilityFileToStoreResponseThenUseTestNGSoftAndHardAssertion() {
        /***POJO + Deserialization to Collections + Soft and hard assertion*/
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 3)
                .when().get("/products/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        /**Asserting by JsonPath (While using collections)*/
        JsonPath jsonPath = response.jsonPath();
        FruitShop fruit = jsonPath.getObject("", FruitShop.class);

        //TestNG Hard assert:
        String expectedName = "Cherry";
        Assert.assertEquals(expectedName,fruit.getName());

        //TestNG Soft assertion:
        Map<String,Object> expectedVendors = jsonPath.getMap("vendors[0]");

        Map<String,Object> actualVendors = new LinkedHashMap<>();
        actualVendors.put("id",2);
        actualVendors.put("name","Max Obsthof GmbH");
        actualVendors.put("self_link","/shop/v2/vendors/2");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("Cherry",fruit.getName());
        softAssert.assertEquals(2.90,fruit.getPrice());
        softAssert.assertEquals(expectedVendors,actualVendors);
        softAssert.assertAll();

        //JUnit 5 soft Assertion
        Assertions.assertAll(
                "JUnit 5 soft assertion",
                ()-> Assertions.assertEquals("Cherry",fruit.getName()),
                ()-> Assertions.assertEquals(2.90,fruit.getPrice()),
                ()-> Assertions.assertEquals(expectedVendors,actualVendors));

    }

    @And("Assert using hamCrest")
    public void assertUsingHamCrest() {
        /**Asserting by HamCrest*/
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 3)
                .when().get("/products/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", Matchers.is("Cherry"))
                .body("id", Matchers.is(3))
                .body("vendors[0].name", Matchers.is("Max Obsthof GmbH"))
                .body("price", Matchers.is(2.90f))
                .extract().response();

    }

    /** Other ways can be used to assert such as:
     * Response.path()
     * jsonPath().getString("path") ...
     * xmlPath().getString("path") ... */

    @Then("Validate Json schema")
    public void validateJsonSchema() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 3)
                .when().get("/products/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("FruitShopSchema.json")).extract().response();
    }

}
