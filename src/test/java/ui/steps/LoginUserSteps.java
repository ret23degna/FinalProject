package ui.steps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static helpers.config.Endpoints.PAGE_LOGIN_USER;

import io.qameta.allure.Step;
import ui.pages.LocatorsLoginUser;
import ui.pages.LocatorsProfile;

public class LoginUserSteps {

  private LocatorsLoginUser locatorLoginUser = new LocatorsLoginUser();

  private LocatorsProfile locatorProfile = new LocatorsProfile();

  private String loginUser;

  @Step("Открыть страницу логирования")
  public void openPageLoginUser() {
    open(PAGE_LOGIN_USER);
  }

  @Step("Ввести данные существующего пользователя")
  public void enterDataPageLoginUser(String login, String password) {
    loginUser = login;
    locatorLoginUser.userName().setValue(login);
    locatorLoginUser.password().setValue(password);
  }

  @Step("Кликнуть по кнопке логирования")
  public void clickButtonPageLoginUser() {
    locatorLoginUser.buttonLoginUser().click();
  }

  @Step("Проверить успешную авторизации")
  public void checkAuthorizedPageLoginUser() {
    locatorProfile.userNameLable().shouldHave(text(loginUser));
  }

  @Step("Проверить неуспешную авторизации")
  public void checkNoAuthorizedPageLoginUser() {
    locatorLoginUser.invalidLabel().shouldHave(text("Invalid username or password!"));
  }

}
