package com.stepDefs;

import com.example.ApiApplication;
import com.services.httpServices;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = ApiApplication.class)
@CucumberContextConfiguration
public class createRepo_sd {
    httpServices HttpServices = new httpServices();
    private final Logger log = LoggerFactory.getLogger(createRepo_sd.class);

    @When("I triggered POST {string} service endpoint with")
    public void i_triggered_service_endpoint_with(String service, DataTable dataTable) throws IOException {
        JSONObject json = null;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());


        Map<String, String> data = new HashMap<String, String>();
        for (Map<String, String> source : dataTable.asMaps(String.class, String.class)) {
            for (String field : source.keySet()) {
                if (field.equalsIgnoreCase("NAME")) {
                    data.put(field, source.get(field) + timestamp.getTime());
                } else {
                    data.put(field, source.get(field));
                }

            }
            json = new JSONObject(data);

        }
        HttpServices.post(service, data);
    }

    @Then("I should get response code {string}")
    public void iShouldGetResponseCode(String responseCode) {
        Assert.assertEquals(Integer.parseInt(responseCode), HttpServices.response.getStatusCode());

    }

    @And("I should see below details in the response:")
    public void iShouldSeeBelowDetailsInTheResponse(DataTable expectedResponse) {
        JsonPath jsonPathEvaluator = HttpServices.response.jsonPath();
        Map<String, String> data = new HashMap<String, String>();
        for (Map<String, String> source : expectedResponse.asMaps(String.class, String.class)) {
            for (String field : source.keySet()) {
                data.put(field, source.get(field));
                jsonPathEvaluator.get(field);
            }
        }

    }

    @And("I should see response error message {string} {string}")
    public void iShouldSeeResponseErrorMessage(String field, String expectedErrorMessage) {
        JsonPath jsonPathEvaluator = HttpServices.response.jsonPath();
        Assert.assertEquals(expectedErrorMessage, jsonPathEvaluator.get(field));
    }


}
