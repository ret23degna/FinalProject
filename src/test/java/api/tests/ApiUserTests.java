package api.tests;

import helpers.config.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import api.steps.ApiUserSteps;

public class ApiUserTests extends BaseTest {

  ApiUserSteps steps = new ApiUserSteps();

  @Test
  @DisplayName("Авторизация пользователя")
  void successfulAuthorized() {
    steps.setAuthorized();
  }

  @Test
  @DisplayName("Генерация токена")
  void successfulGenerateToken() {
    steps.getToken();
  }

  @Test
  @DisplayName("Создание нового пользователя")
  void successfulUser() {
    steps.setNewUser();
  }

  @Test
  @DisplayName("Удаление пользователя")
  void successfulDeleteUser() {
    steps.setDeleteUser();
  }

  @Test
  @DisplayName("Получить информацию о пользователе")
  void successfulGetUser() {
    steps.getUser();
  }

}
