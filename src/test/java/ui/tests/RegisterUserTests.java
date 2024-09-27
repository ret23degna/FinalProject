package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import helpers.config.BaseTest;
import ui.steps.registerUser.RegisterUserSteps;

public class RegisterUserTests extends BaseTest {

  RegisterUserSteps steps = new RegisterUserSteps();

  @Test
  @DisplayName("Ошибка регистрации пользователя.Не пройдена капча")
  void errorRegisterBookStore() {
    steps.openPageRegisterUser();
    steps.enterDataPageRegisterUser();
    steps.clickButtonRegisterPageRegisterUser();
    steps.checkErrorCaptchaResultPageRegisterUser();
  }
}
