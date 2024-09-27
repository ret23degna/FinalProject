package ui.steps.registerUser;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

import ui.pages.LocatorsRegisterUser;
import helpers.config.Endpoints;
import helpers.config.RandomUtils;

public class RegisterUser {

  Endpoints page = new Endpoints();
  RandomUtils random = new RandomUtils();
  LocatorsRegisterUser locator = new LocatorsRegisterUser();

  public RegisterUser openPage() {
    open(page.pageRegisterUser);
    return this;
  }

  public RegisterUser setFirstName() {
    locator.firstName().setValue(random.firstName());
    return this;
  }

  public RegisterUser setLastName() {
    locator.lastName().setValue(random.lastName());
    return this;
  }

  public RegisterUser setUserName() {
    locator.userName().setValue(random.login());
    return this;
  }

  public RegisterUser setPassword() {
    locator.password().setValue(random.password());
    return this;
  }

  public RegisterUser clickButtonRegister() {
    locator.buttonRegisterUser().click();
    return this;
  }

  public RegisterUser checkErrorCaptchaResult() {
    locator.labelErrorCaptcha().shouldHave(text("Please verify reCaptcha to register!"));
    return this;
  }
}
