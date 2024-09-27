package ui.steps.loginUser;

import io.qameta.allure.Step;

public class LoginUserSteps {

  LoginUser loginUser = new LoginUser();

  @Step("Открытие страницы логирования")
  public void openPageLoginUser() {
    loginUser.openPage();
  }

  @Step("Ввод данных существующего пользователя")
  public void enterDataPageLoginUser() {
    loginUser.setData();
  }

  @Step("Ввод данных не существующего пользователя")
  public void enterDataRandomPageLoginUser() {
    loginUser.setDataRandom();
  }

  @Step("Клик по кнопке логирования")
  public void clickButtonPageLoginUser() {
    loginUser.clickButtonLogin();
  }

  @Step("Проверка успешной авторизации")
  public void checkAuthorizedPageLoginUser() {
    loginUser.checkAuthorized();
  }

  @Step("Проверка не успешной авторизации")
  public void checkNoAuthorizedPageLoginUser() {
    loginUser.checkNoAuthorized();
  }


}
