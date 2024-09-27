package helpers.config;

import static com.codeborne.selenide.Selenide.closeWebDriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.config.Configurations;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
  Configurations config = ConfigFactory.create(Configurations.class,
      System.getProperties());

 @BeforeEach
void BaseEach() {
   ChromeOptions options = new ChromeOptions();
   options.addArguments("--incognito");
   Configuration.browserCapabilities  = options;
   Configuration.pageLoadStrategy = config.getPageLoadStrategy();
   Configuration.browserSize = config.getBrowserSize();
   Configuration.baseUrl = config.getBaseUrl();
   Configuration.browser = config.getBrowser();
   SelenideLogger.addListener("allure", new AllureSelenide());
 }
  @AfterEach
  void BaseAfter() {
   closeWebDriver();
  }

}
