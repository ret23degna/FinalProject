package ui.steps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static helpers.config.Endpoints.PAGE_REGISTER_USER;
import static helpers.config.RandomUtils.firstName;
import static helpers.config.RandomUtils.lastName;
import static helpers.config.RandomUtils.login;
import static helpers.config.RandomUtils.password;

import io.qameta.allure.Step;
import ui.pages.LocatorsRegisterUser;

public class RegisterUserSteps {

  LocatorsRegisterUser locator = new LocatorsRegisterUser();

  @Step("Открыть страницу регистрации пользователя")
  public void openPageRegisterUser() {
    open(PAGE_REGISTER_USER);
  }

  @Step("Ввести данные")
  public void enterDataPageRegisterUser() {
    locator.firstName().setValue(firstName());
    locator.lastName().setValue(lastName());
    locator.userName().setValue(login());
    locator.password().setValue(password());
  }

  @Step("Кликнуть по кнопке регистрации пользователя")
  public void clickButtonRegisterPageRegisterUser() {
    locator.buttonRegisterUser().click();
  }

  @Step("Проверить то что регистрация не удалась из-за не ввода капчи")
  public void checkErrorCaptchaResultPageRegisterUser() {
    locator.labelErrorCaptcha().shouldHave(text("Please verify reCaptcha to register!"));
  }
}
