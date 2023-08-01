package com.example;


 import com.demo.api.parametersPojo;
 import org.json.JSONObject;
import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
 import org.springframework.web.bind.annotation.*;
 import org.springframework.web.client.RestTemplate;


@RestController
public class gitRepos {

    //    get Repos using Bearer Token passed from Postman/Rest client.
    @GetMapping(value = "/getRepos")
    private ResponseEntity<String> getRepos(@RequestHeader("Authorization") String bearertoken) {
        HttpHeaders headers = new HttpHeaders();
        String uri = "https://api.github.com/user/repos";
        headers.set("Authorization", bearertoken);

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return result;
    }

    //    No Bearer token
    @GetMapping(value = "/getIssues")
    private ResponseEntity<String> getIssues(@RequestParam("page") String per_page) {
        HttpHeaders headers = new HttpHeaders();
        String uri = "https://api.github.com/repos/octocat/Spoon-Knife/issues?page=" + per_page;

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class, "per-page");
        return result;
    }

    //    Create Repo with Bearer token NOT passed from postman or Rest client
    @PostMapping(value = "/createRepo",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    private ResponseEntity<String> createRepo(@RequestBody parametersPojo myModel) {
        String uri = "https://api.github.com/user/repos";

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", myModel.getName());
        requestBody.put("description", myModel.getDescription());

//        set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + "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");

        HttpEntity<String> entity = new HttpEntity<String>(requestBody.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        return result;
    }


}