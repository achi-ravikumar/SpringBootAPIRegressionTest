package com.services;

import io.restassured.response.Response;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;


@Component
public class httpServices {
    private String uri = "http://localhost";


    @LocalServerPort
    private int port = 8080;
    public static Response response;

    private String endPoint() {
        return uri + ":" + port + "/";
    }


    public Response post(String service, Map<String, String> requestBody) throws IOException {
        response = given().log().all().body(requestBody).when().
                headers("Content-Type", "application/json").post(endPoint() + service).
                then().extract().response();
        return response;
    }

    public Response get(String service, String bearerToken) {
        String ibearerToken = "";
        if (bearerToken.equals("valid")) {
            ibearerToken = "Bearer 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";
        } else if (bearerToken.equals("invalid")) {
            ibearerToken = "test";
        } else if (bearerToken.equals("empty")) {
            ibearerToken = "";
        }
        response = given().log().all().when().
                headers("Authorization",  ibearerToken).
                headers("Content-Type", "application/json")
                .get(endPoint() + service).
                then().extract().response();
        return response;
    }
}
