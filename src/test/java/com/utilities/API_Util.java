package com.utilities;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class API_Util {


    /**
     * Return TOKEN as String by using provided username from /token endpoint
     * @param userType
     * @return
     */
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

    public static Map<String,Object> getRandomDataMap(){

        Faker faker = new Faker() ;
        Map<String,Object> bookMap = new LinkedHashMap<>();
        bookMap.put("address", faker.address());
        //...
        return bookMap ;
    }
}