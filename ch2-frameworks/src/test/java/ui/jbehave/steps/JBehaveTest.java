package ui.jbehave.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class JBehaveTest {
  @Given("I go to Wikipedia homepage")
  public void goToWikiPage() {
    open("http://en.wikipedia.org/wiki/Main_Page");
  }

  @When("^I enter the value (.*) on a field named (.*)$")
  public void enterValueOnFieldByName(String value, String fieldName) {
    $(By.name(fieldName)).setValue(value);
  }

  @When("^I click the button (.*)$")
  public void clickButonByName(String buttonName) {
    $(By.name(buttonName)).click();
  }

  @Then("^the page title contains (.*)$")
  public void pageTitleIs(String title) {
    assertThat(title(), containsString(title));
  }
}
