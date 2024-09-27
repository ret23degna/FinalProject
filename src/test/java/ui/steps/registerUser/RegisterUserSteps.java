package ui.steps.registerUser;

import io.qameta.allure.Step;

public class RegisterUserSteps {

  RegisterUser registerUser = new RegisterUser();

  @Step("Открытие страницы регистрации пользователя")
  public void openPageRegisterUser() {
    registerUser.openPage();
  }

  @Step("Ввод данных")
  public void enterDataPageRegisterUser() {
    registerUser
        .setFirstName()
        .setLastName()
        .setUserName()
        .setPassword();
  }

  @Step("клик по кнопке регистрации пользователя")
  public void clickButtonRegisterPageRegisterUser() {
    registerUser.clickButtonRegister();
  }

  @Step("проверка того, что геитрация не удалась из-за не ввода капчи")
  public void checkErrorCaptchaResultPageRegisterUser() {
    registerUser.checkErrorCaptchaResult();
  }
}
