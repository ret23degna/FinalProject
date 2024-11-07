package helpers.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.config.Configurations;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

  public final static Configurations config = ConfigFactory.create(Configurations.class,
      System.getProperties());

  public enum authoriz {
    unknown, oauth2, bearer

  }

  @BeforeAll
  public static void BaseEach() {
    Configuration.pageLoadStrategy = config.getPageLoadStrategy();
    Configuration.browserSize = config.getBrowserSize();
    Configuration.baseUrl = config.getBaseUrl();
    Configuration.browser = config.getBrowser();
    Configuration.screenshots = false;
    Configuration.savePageSource = false;
    SelenideLogger.addListener("allure", new AllureSelenide());
  }

}
