package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.foundation.SimCard;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;
    private SimCard simCard;

    private final String url = "http://localhost:8444/actuate";

    @Given("Successfully activate sim card")
    public void aFunctionalSimCard() {
        simCard = new SimCard("1255789453849037777", "test1@gmail.com", false);
    }

    @Given("Fail to activate sim card")
    public void aBrokenSimCard() {
        simCard = new SimCard("8944500102198304826", "test2@yahoo.com", false);
    }

    @When("Request to activate the sim card")
    public void requestToActivateASimCard() {
        this.restTemplate.postForObject(url, simCard, String.class);
    }

    @Then("Successfully activate and record to database")
    public void activateAndRecordToDatabase() {
        var simCard = this.restTemplate.getForObject("http://localhost:8080/query?simCardId={simCardId}", SimCard.class, 1);
        assertTrue(simCard.getActiveStatus());
    }

    @Then("Fail to activate and record to the database")
    public void failToActivateAndRecordToDatabase() {
        var simCard = this.restTemplate.getForObject("http://localhost:8080/query?simCardId={simCardId}", SimCard.class, 2);
        assertFalse(simCard.getActiveStatus());
    }

}