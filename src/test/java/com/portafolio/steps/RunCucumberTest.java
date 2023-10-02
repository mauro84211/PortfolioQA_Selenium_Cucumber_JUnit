package com.portafolio.steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        plugin = {"pretty",
                "html:target/site/cucumber-pretty",
                "json:target/site/cucumber.json"},
        glue = {"com.portafolio.steps"}
        )
class RunCucumberTest {
}