package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import helpers.config.BaseTest;
import ui.steps.loginUser.LoginUserSteps;

public class LoginUserTests extends BaseTest {

  LoginUserSteps steps = new LoginUserSteps();

  @Test
  @DisplayName("Успешная авторизация")
  void successLoginBookStore() {
    steps.openPageLoginUser();
    steps.enterDataPageLoginUser();
    steps.clickButtonPageLoginUser();
    steps.checkAuthorizedPageLoginUser();
  }

  @Test
  @DisplayName("Неуспешная авторизация")
  void errorLoginBookStore() {
    steps.openPageLoginUser();
    steps.enterDataRandomPageLoginUser();
    steps.clickButtonPageLoginUser();
    steps.checkNoAuthorizedPageLoginUser();
  }

}
