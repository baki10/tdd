package ui;

import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;

public class SelenideTest {

  @Test
  public void browserWikiTest() {
    // Opening Wikipedia page
    open("http://en.wikipedia.org/wiki/Main_Page");

    // search type tdd
    $(By.id("searchInput")).setValue("Test-driven development");

    // search click
    $(By.id("searchButton")).click();

    // Checks
    assertThat(title(), startsWith("Test-driven development"));
  }
}
