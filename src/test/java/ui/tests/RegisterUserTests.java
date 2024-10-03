package ui.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import helpers.utils.BaseTest;
import ui.steps.RegisterUserSteps;

@Epic("UI")
public class RegisterUserTests extends BaseTest {

  RegisterUserSteps steps = new RegisterUserSteps();

  @Severity(SeverityLevel.NORMAL)
  @Feature("RegisterUserTests")
  @Test
  @DisplayName("Ошибка регистрации пользователя.Не пройдена капча")
  void errorRegisterBookStore() {
    steps.openPageRegisterUser();
    steps.enterDataPageRegisterUser();
    steps.clickButtonRegisterPageRegisterUser();
    steps.checkErrorCaptchaResultPageRegisterUser();
  }
}
