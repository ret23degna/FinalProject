package api.tests;

import helpers.utils.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import api.steps.ApiUserSteps;

@Epic("API")
public class ApiUserTests extends BaseTest {

  ApiUserSteps steps = new ApiUserSteps();

  @Severity(SeverityLevel.BLOCKER)
  @Feature("ApiUserTests")
  @Test
  @DisplayName("Авторизация пользователя")
  void successfulAuthorized() {
    steps.authorized();
    steps.checkAuthorized();
  }

  @Severity(SeverityLevel.BLOCKER)
  @Feature("ApiUserTests")
  @Test
  @DisplayName("Генерация токена")
  void successfulGenerateToken() {
    steps.getToken();
    steps.checkGetToken();
  }

  @Severity(SeverityLevel.CRITICAL)
  @Feature("ApiUserTests")
  @Test
  @DisplayName("Создание нового пользователя")
  void successfulUser() {
    steps.newUser();
    steps.checkNewUser();
  }

  @Severity(SeverityLevel.CRITICAL)
  @Feature("ApiUserTests")
  @Test
  @DisplayName("Получить информацию о пользователе")
  void successfulGetUser() {
    steps.getUser();
    steps.checkGetUser();
  }

  @Severity(SeverityLevel.NORMAL)
  @Feature("ApiUserTests")
  @Test
  @DisplayName("Удаление пользователя")
  void successfulDeleteUser() {
    steps.deleteUser();
    steps.checkDeleteUser();
  }
}
