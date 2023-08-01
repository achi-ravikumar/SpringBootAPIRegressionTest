package com.demo.api;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources" ,tags = "@createRepo or @getRepos",
        glue = {"com.stepDefs","com.common"},
        plugin = { "pretty", "html:target/report/cucumber-reports.html" },
        monochrome = true
)
public class runCukes {
}