package api.tests;

import static org.hamcrest.Matchers.containsString;

import api.steps.ApiBookSteps;
import helpers.utils.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("API")
public class ApiBookTests extends BaseTest {

  ApiBookSteps steps = new ApiBookSteps();

  @Severity(SeverityLevel.CRITICAL)
  @Tag("API")
  @Feature("ApiBookTests")
  @Test
  @DisplayName("Добавление книги пользователю")
  void successfulPostBooks() {
    steps.preStepDeleteBooks();
    steps.postBooks()
        .shouldHaveStatusCode(201)
        .shouldHaveJsonPath("books[0].isbn", containsString("9781449325862"));
  }

  @Severity(SeverityLevel.CRITICAL)
  @Tag("API")
  @Feature("ApiBookTests")
  @Test
  @DisplayName("Получение списка книг")
  void successfulGetBooks() {
    steps.getBooks()
        .shouldHaveStatusCode(200);
  }

  @Severity(SeverityLevel.CRITICAL)
  @Tag("API")
  @Feature("ApiBookTests")
  @Test
  @DisplayName("Получение книги")
  void successfulGetBook() {
    steps.getBook()
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("isbn", containsString("9781449325862"))
        .shouldHaveJsonPath("title", containsString("Git Pocket Guide"))
        .shouldHaveJsonPath("subTitle", containsString("A Working Introduction"))
        .shouldHaveJsonPath("author", containsString("Richard E. Silverman"))
        .shouldHaveJsonPath("publish_date", containsString("2020-06-04T08:48:39.000Z"))
        .shouldHaveJsonPath("publisher", containsString("O'Reilly Media"));
  }

  @Severity(SeverityLevel.NORMAL)
  @Tag("API")
  @Feature("ApiBookTests")
  @Test
  @DisplayName("Удаление всех книг у пользователя")
  void successfulDeleteBooks() {
    steps.preStepSuccessfulDeleteBook();
    steps.deleteBooks()
        .shouldHaveStatusCode(204);
  }

  @Severity(SeverityLevel.NORMAL)
  @Tag("API")
  @Feature("ApiBookTests")
  @Test
  @DisplayName("Удаление одной книги у пользователя")
  void successfulDeleteBook() {
    steps.preStepSuccessfulDeleteBook();
    steps.deleteBook()
        .shouldHaveStatusCode(204);
  }

  @Severity(SeverityLevel.NORMAL)
  @Tag("API")
  @Feature("ApiBookTests")
  @Test
  @DisplayName("Обновление данных о книге пользователя")
  void successfulPutBooks() {
    steps.preStepSuccessfulPutBook();
    steps.putBooks()
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("userId", containsString("7a854906-dfc3-4b4e-b527-1f8ba0c8070c"))
        .shouldHaveJsonPath("username", containsString("Kakabyaka_48"));
  }

}
