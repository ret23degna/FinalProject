package ui.steps;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import api.steps.ApiSingleton;

import java.util.Map;
import org.openqa.selenium.Cookie;

public class UISinglton {
  private static boolean Auto=false;
  private static Map<String, Cookie> cookie = ApiSingleton.getCookie();
  private UISinglton() {}
  public static boolean getAuto() {
    if (Auto == false) {
      open("/images/Toolsqa.jpg");
      for (Map.Entry<String, Cookie> cookieEntry :cookie.entrySet()) {
        getWebDriver().manage().addCookie(cookieEntry.getValue());
      }
      Auto = true;
    }
    return Auto;
  }
}
