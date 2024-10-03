package api.tests;

import api.steps.ApiBookSteps;
import helpers.utils.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("API")
public class ApiBookTests extends BaseTest {

  ApiBookSteps steps = new ApiBookSteps();

  @Severity(SeverityLevel.CRITICAL)
  @Feature("ApiBookTests")
  @Test
  @DisplayName("Добавление книги пользователю")
  void successfulPostBooks() {
    steps.postBooks();
    steps.checkPostBooks();
  }

  @Severity(SeverityLevel.CRITICAL)
  @Feature("ApiBookTests")
  @Test
  @DisplayName("Получение списка книг")
  void successfulGetBooks() {
    steps.getBooks();
    steps.checkGetBooks();
  }

  @Severity(SeverityLevel.CRITICAL)
  @Feature("ApiBookTests")
  @Test
  @DisplayName("Получение книги")
  void successfulGetBook() {
    steps.getBook();
    steps.checkGetBook();
  }

  @Severity(SeverityLevel.NORMAL)
  @Feature("ApiBookTests")
  @Test
  @DisplayName("Удаление всех книг у пользователя")
  void successfulDeleteBooks() {
    steps.deleteBooks();
    steps.checkDeleteBooks();
  }

  @Severity(SeverityLevel.NORMAL)
  @Feature("ApiBookTests")
  @Test
  @DisplayName("Удаление одной книги у пользователя")
  void successfulDeleteBook() {
    steps.deleteBook();
    steps.checkDeleteBooks();
  }

  @Severity(SeverityLevel.NORMAL)
  @Feature("ApiBookTests")
  @Test
  @DisplayName("Обновление данных о книге пользователя")
  void successfulPutBooks() {
    steps.putBooks();
    steps.checkPutBooks();
  }
}
