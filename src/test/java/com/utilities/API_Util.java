package com.utilities;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class API_Util {


    public static String getToken(String userType){

        String email=ConfigurationReader.getProperty(userType+"_username");
        String password="";
        
        return getToken(email,password);
    }

    public static String getToken(String email,String password){

        return given()
                .contentType(ContentType.URLENC)
                .formParam("email" , email)
                .formParam("password" , password).
                when()
                .post(ConfigurationReader.getProperty("baseUri")+"/login")
                .prettyPeek()
                .path("token") ;
    }
}