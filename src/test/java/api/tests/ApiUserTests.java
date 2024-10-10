package api.tests;

import static org.hamcrest.Matchers.containsString;

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

  @Severity(SeverityLevel.CRITICAL)
  @Feature("ApiUserTests")
  @Test
  @DisplayName("Получить информацию о пользователе")
  void successfulGetUser() {
    steps.getUser()
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("userId", containsString("0d2a0ab8-725e-49b7-85e1-2f4981b81d4f"))
        .shouldHaveJsonPath("username", containsString("Kakabyaka_47"));
  }

  @Severity(SeverityLevel.BLOCKER)
  @Feature("ApiUserTests")
  @Test
  @DisplayName("Авторизация пользователя")
  void successfulAuthorized() {
    steps.authorized()
        .shouldHaveStatusCode(200)
        .responseBodyIsNoJson("true");
  }

  @Severity(SeverityLevel.BLOCKER)
  @Feature("ApiUserTests")
  @Test
  @DisplayName("Генерация токена")
  void successfulGenerateToken() {
    steps.getToken()
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("status", containsString("Success"))
        .shouldHaveJsonPath("result", containsString("User authorized successfully"));
  }

  @Severity(SeverityLevel.CRITICAL)
  @Feature("ApiUserTests")
  @Test
  @DisplayName("Создание нового пользователя")
  void successfulUser() {
    steps.newUser()
        .shouldHaveStatusCode(201);
  }


  @Severity(SeverityLevel.NORMAL)
  @Feature("ApiUserTests")
  @Test
  @DisplayName("Удаление пользователя")
  void successfulDeleteUser() {
    steps.deleteUser()
        .shouldHaveStatusCode(204);
  }
}
