package ui.steps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static helpers.config.Endpoints.PAGE_LOGIN_USER;
import static helpers.config.RandomUtils.login;
import static helpers.config.RandomUtils.password;

import api.steps.ApiBookSteps;
import helpers.models.AccountNewUserRequestModel;
import io.qameta.allure.Step;
import ui.pages.LocatorsLoginUser;
import ui.pages.LocatorsProfile;

public class LoginUserSteps {

  final private LocatorsLoginUser locatorLoginUser = new LocatorsLoginUser();
  final private LocatorsProfile locatorProfile = new LocatorsProfile();
  private ApiBookSteps apiBook = new ApiBookSteps();
  private AccountNewUserRequestModel user;

  @Step("Открыть страницу логирования")
  public void openPageLoginUser() {
    open(PAGE_LOGIN_USER);
  }

  @Step("Ввести данные существующего пользователя")
  public void enterDataPageLoginUser() {
    user = apiBook.settingUser();
    locatorLoginUser.userName().setValue(user.getUserName());
    locatorLoginUser.password().setValue(user.getPassword());
  }

  @Step("Ввести данные не существующего пользователя")
  public void enterDataRandomPageLoginUser() {
    locatorLoginUser.userName().setValue(login());
    locatorLoginUser.password().setValue(password());
  }

  @Step("Кликнуть по кнопке логирования")
  public void clickButtonPageLoginUser() {
    locatorLoginUser.buttonLoginUser().click();
  }

  @Step("Проверить успешную авторизации")
  public void checkAuthorizedPageLoginUser() {
    locatorProfile.userNameLable().shouldHave(text(user.getUserName()));
  }

  @Step("Проверить неуспешную авторизации")
  public void checkNoAuthorizedPageLoginUser() {
    locatorLoginUser.invalidLabel().shouldHave(text("Invalid username or password!"));
  }


}
