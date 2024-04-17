package com.steps;




import com.utilities.ConfigurationReader;
import io.cucumber.java.*;
import io.restassured.RestAssured;



public class Hooks {


    @Before("@api")
    public void setUpAPI() {
        RestAssured.baseURI=ConfigurationReader.getProperty("baseUri");
    }

    @After ("@api")
    public static void destroy(){
        RestAssured.reset();
        // It resets your BaseURI/BasePath etc. to DEFAULT values from Rest Assured
    }

}