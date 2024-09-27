package api.tests;

import api.steps.ApiBookSteps;
import helpers.config.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApiBookTests extends BaseTest {

  ApiBookSteps steps = new ApiBookSteps();

  @Test
  @DisplayName("Добавление книги пользователю")
  void successfulPostBooks() {
    steps.successfulPostBooks();
  }

  @Test
  @DisplayName("Получить список книг")
  void successfulGetBooks() {
    steps.successfulGetBooks();
  }

  @Test
  @DisplayName("Получить книгу")
  void successfulGetBook() {
    steps.successfulGetBook();
  }

  @Test
  @DisplayName("Удаление всех книг у пользователя")
  void successfulDeleteBooks() {
    steps.successfulDeleteBooks();
  }

  @Test
  @DisplayName("Удаление одной книги у пользователя")
  void successfulDeleteBook() {
    steps.successfulDeleteBook();
  }

  @Test
  @DisplayName("Обновление данных о книге пользователя")
  void successfulPutBooks() {
    steps.successfulPutBooks();
  }
}
