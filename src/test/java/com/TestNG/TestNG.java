package com.TestNG;

import com.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class TestNG {
    Response response;
    static int id;
    static HashMap<String, Object> product;


    @Test(priority = 1)
    public void post() {
        product = new LinkedHashMap<>();
        product.put("name", "apple");
        product.put("price", 8);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .baseUri(ConfigurationReader.getProperty("baseUri"))
                .post("/products").prettyPeek();

        JsonPath jsonPath = response.then().extract().jsonPath();
        id = jsonPath.getInt("id");
    }


    @Test(priority = 2)
    public void get() {
        response = RestAssured.given().
                accept(ContentType.JSON)
                .pathParam("id", id)
                .baseUri(ConfigurationReader.getProperty("baseUri"))
                .get("/products/{id}").prettyPeek();
    }


    @Test(priority = 3)
    public void put() {
        product.put("name", "Update apple");
        product.put("price", 10);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(product)
                .when()
                .baseUri(ConfigurationReader.getProperty("baseUri"))
                .put("/products/{id}").prettyPeek();
    }


    @Test(priority = 4)
    public void patch() {
        product.put("name", "Patch apple");
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(product)
                .when()
                .baseUri(ConfigurationReader.getProperty("baseUri"))
                .put("/products/{id}").prettyPeek();
    }


    @Test(priority = 5)
    public void delete() {
        response = RestAssured.given().
                accept(ContentType.JSON)
                .pathParam("id", id)
                .baseUri(ConfigurationReader.getProperty("baseUri"))
                .delete("/products/{id}").prettyPeek();
    }


    @Test(priority = 6)
    public void getDeleted() {
        response = RestAssured.given().
                accept(ContentType.JSON)
                .pathParam("id", id)
                .baseUri(ConfigurationReader.getProperty("baseUri"))
                .get("/products/{id}").prettyPeek();
    }
}
