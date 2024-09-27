package ui.steps.profile;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import api.steps.ApiBookSteps;
import com.codeborne.selenide.Selenide;
import helpers.config.Endpoints;
import java.util.Map;
import helpers.models.AccountNewUserRequestModel;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import ui.pages.LocatorsLoginUser;
import ui.pages.LocatorsProfile;
import api.steps.ApiUserSteps;

public class Profile {

  Endpoints page = new Endpoints();
  private ApiUserSteps apiUser = new ApiUserSteps();
  private ApiBookSteps apiBook = new ApiBookSteps();
  private LocatorsProfile locatorProfile = new LocatorsProfile();
  private LocatorsLoginUser locatorLoginUser = new LocatorsLoginUser();
  private AccountNewUserRequestModel user;

  private void loginProfile() {
    user = apiUser.setNewUser();
    apiBook.successfulPostBooks(user);
    Map<String, Cookie> cookie = apiUser.getCookie(user);
    open("/images/Toolsqa.jpg");
    getWebDriver().manage().addCookie(cookie.get("token"));
    getWebDriver().manage().addCookie(cookie.get("expires"));
    getWebDriver().manage().addCookie(cookie.get("userID"));
  }

  public Profile openPage() {
    loginProfile();
    open(page.pageProfile);
    return this;
  }

  public Profile clickButtonLogOut() {
    locatorProfile.buttonLogOut().click();
    return this;
  }

  public Profile checkLogOut() {
    locatorLoginUser.loginLable().shouldHave(text("Login in Book Store"));
    return this;
  }

  public Profile clickButtonDeleteAccount() {
    locatorProfile.buttonDeleteAccount().click();
    return this;
  }

  public Profile popupAccountClickButtonOk() {
    locatorProfile.popupButtonOk().click();
    return this;
  }

  public Profile popupClickButtonCancel() {
    locatorProfile.popupButtonCancel().click();
    return this;
  }

  public Profile checkDeleteAccount() {
    Alert alert = Selenide.switchTo().alert();
    alert.accept();
    locatorLoginUser.loginLable().shouldHave(text("Login in Book Store"));
    return this;
  }

  public Profile checkCancel() {
    locatorProfile.userNameLable().shouldHave(text(user.getUserName()));
    return this;
  }

  public Profile clickButtonDeleteAllBooks() {
    locatorProfile.buttonDeleteAllBooks().click();
    return this;
  }

  public Profile checkDeleteBooks() {
    Alert alert = Selenide.switchTo().alert();
    alert.accept();
    locatorProfile.book().shouldBe(hidden);
    return this;
  }

  public Profile clickButtonDeleteBook() {
    locatorProfile.buttonDeleteBook().click();
    return this;
  }
}
