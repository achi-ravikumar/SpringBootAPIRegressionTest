package com.stepDefs;

import com.example.ApiApplication;
import com.services.httpServices;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class getRepos_sd {
    httpServices HttpServices = new httpServices();
    private final Logger log = LoggerFactory.getLogger(getRepos_sd.class);

    @When("I triggered GET {string} service endpoint with {string} token")
    public void iTriggeredGETServiceEndpointWith(String service, String token) {
        HttpServices.get(service, token);
    }

    @And("I should see below details in the response for get Repo service:")
    public void iShouldSeeBelowDetailsInTheResponseForGetRepoService(DataTable expectedResponse) {
        JsonPath jsonPathEvaluator = HttpServices.response.jsonPath();
        Map<String, String> data = new HashMap<String, String>();
        for (Map<String, String> source : expectedResponse.asMaps(String.class, String.class)) {
            for (String field : source.keySet()) {
                data.put(field, source.get(field));

                ArrayList<String> value = jsonPathEvaluator.get(field.replace("-", "."));
                Assert.assertTrue(value.contains(source.get(field)));
            }
        }


    }


}
