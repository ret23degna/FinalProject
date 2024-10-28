package ui.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import helpers.utils.BaseTest;
import ui.steps.LoginUserSteps;

@Epic("UI")
public class LoginUserTests extends BaseTest {

  LoginUserSteps steps = new LoginUserSteps();

  @Severity(SeverityLevel.BLOCKER)
  @Tag("UI")
  @Feature("LoginUserTests")
  @Test
  @DisplayName("Успешная авторизация")
  void successLoginBookStore() {
    steps.openPageLoginUser();
    steps.enterDataPageLoginUser();
    steps.clickButtonPageLoginUser();
    steps.checkAuthorizedPageLoginUser();
  }

  @Severity(SeverityLevel.CRITICAL)
  @Tag("UI")
  @Feature("LoginUserTests")
  @Test
  @DisplayName("Неуспешная авторизация")
  void errorLoginBookStore() {
    steps.openPageLoginUser();
    steps.enterDataRandomPageLoginUser();
    steps.clickButtonPageLoginUser();
    steps.checkNoAuthorizedPageLoginUser();
  }

}
