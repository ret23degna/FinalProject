package helpers.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.config.Configurations;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

  public final static Configurations config = ConfigFactory.create(Configurations.class,
      System.getProperties());

 @BeforeEach
void BaseEach() {
   Configuration.pageLoadStrategy = config.getPageLoadStrategy();
   Configuration.browserSize = config.getBrowserSize();
   Configuration.baseUrl = config.getBaseUrl();
   Configuration.browser = config.getBrowser();
   Configuration.screenshots = false;
   SelenideLogger.addListener("allure", new AllureSelenide());
 }

}
