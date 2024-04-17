package com.steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CRUD_Flow {

    Response response;
    static int id;
    static HashMap<String,Object> apples;

    //@Post
    @When("I send Post a new {string} with the price of {int} dollars as request to {string} endpoint")
    public void iSendPostANewWithThePriceOfDollarsAsRequestToEndpoint(String item, int price, String endpoint) {
        apples = new LinkedHashMap<>();
        apples.put("name",item);
        apples.put("price",price);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(apples)
                .when()
                .post(endpoint).prettyPeek();

        JsonPath jsonPath = response.then().extract().jsonPath();
        id = jsonPath.getInt("id");
    }

    //Get
    @When("I send Get request to {string} to get added item")
    public void iSendGetRequestToToGetAddedItem(String endpoint) {
        response = RestAssured.given().
                accept(ContentType.JSON)
                .pathParam("id", id)
                .get(endpoint).prettyPeek();
    }

    //Put
    @When("I send Put request to {string} updating {string} and {int}")
    public void iSendPutRequestToUpdatingAnd(String endpoint, String item, int price) {
        apples.put("name",item);
        apples.put("price",price);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(apples)
                .when()
                .put(endpoint).prettyPeek();
    }

    //Patch
    @When("I send Patch request to {string} patching the item to {string}")
    public void iSendPatchRequestToPatchingTheItemTo(String endpoint, String item) {
        apples.put("name",item);
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(apples)
                .when()
                .put(endpoint).prettyPeek();
    }

    //Delete
    @When("I send Delete request to {string} to delete posted item")
    public void iSendDeleteRequestToToDeletePostedItem(String endpoint) {
        response = RestAssured.given().
                accept(ContentType.JSON)
                .pathParam("id", id)
                .delete(endpoint).prettyPeek();
    }

    @When("I send Get of deleted item request to {string}")
    public void iSendGetOfDeletedItemRequestTo(String endpoint) {
        response = RestAssured.given().
                accept(ContentType.JSON)
                .pathParam("id", id)
                .get(endpoint).prettyPeek();
    }

    @Then("status code should be {int}")
    public void status_code_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("Response Content type is {string}")
    public void response_content_type_is(String contentType) {
        response.then().contentType(contentType);
    }
}