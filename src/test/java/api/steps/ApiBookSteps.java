package api.steps;

import static helpers.config.Endpoints.BOOK_STOREBOOKS_ENDPOINT;
import static helpers.config.Endpoints.BOOK_STOREBOOK_ENDPOINT;

import api.templates.BookTemplates;
import helpers.config.Authorization;
import helpers.models.BookAddPostRequestModel;
import helpers.models.BookDeleteRequestModel;
import io.qameta.allure.Step;
import java.util.HashMap;
import java.util.Map;

public class ApiBookSteps {

  private final String isbn = Singleton.getInstance().isbnArray.get(0).getIsbn();

  private String newIsbn;

  @Step("Предварительные шаги. Подготовить данные для удаления всех книг")
  public void preStepSuccessfulDeleteBook() {
    preStepDeleteBooks();
    preStepPostBooks();
  }

  @Step("Предварительные шаги. Подготовить данные для обновления данных о книге пользователя")
  public void preStepSuccessfulPutBook() {
    preStepDeleteBooks();
    preStepPostBooks();
    preStepGetBooks();
  }

  @Step("Предварительный шаг. Удалить все книги у пользователя")
  public void preStepDeleteBooks() {
    Map<String, String> parametrsMap = new HashMap<>();
    parametrsMap.put("UserId", Singleton.getInstance().userId);
    new RestWrapper()
        .delete(BOOK_STOREBOOKS_ENDPOINT, parametrsMap, "",
            Singleton.getInstance().token, Authorization.BEARER);
  }

  @Step("Предварительный шаг.Добавить книгу пользователю")
  public void preStepPostBooks() {
    BookAddPostRequestModel addBook = new BookTemplates().formAddBook(
        Singleton.getInstance().userId, Singleton.getInstance().isbnArray);
    new RestWrapper()
        .post(BOOK_STOREBOOKS_ENDPOINT, addBook, Singleton.getInstance().token,
            Authorization.OAUTH_2);
  }

  @Step("Предварительный шаг.Получить книгу для обновления")
  public void preStepGetBooks() {
    Map<String, ?> bookMap = getBooks().getResponse()
        .path("books.find { it.title == '" + "Learning JavaScript Design Patterns" + "' }");
    newIsbn = (String) bookMap.get("isbn");
  }

  @Step("Добавить книгу пользователю")
  public RestWrapper postBooks() {
    BookAddPostRequestModel addBook = new BookTemplates().formAddBook(
        Singleton.getInstance().userId, Singleton.getInstance().isbnArray);
    return new RestWrapper()
        .post(BOOK_STOREBOOKS_ENDPOINT, addBook, Singleton.getInstance().token,
            Authorization.OAUTH_2);
  }

  @Step("Получить список книг")
  public static RestWrapper getBooks() {
    return new RestWrapper()
        .get(BOOK_STOREBOOKS_ENDPOINT, new HashMap<>(), null, Authorization.UNKNOWN);
  }

  @Step("Получить книгу")
  public RestWrapper getBook() {
    Map<String, String> parametrsMap = new HashMap<>();
    parametrsMap.put("ISBN", isbn);
    return new RestWrapper()
        .get(BOOK_STOREBOOK_ENDPOINT, parametrsMap, null, Authorization.UNKNOWN);
  }

  @Step("Удалить все книги у пользователя")
  public RestWrapper deleteBooks() {
    Map<String, String> parametrsMap = new HashMap<>();
    parametrsMap.put("UserId", Singleton.getInstance().userId);
    return new RestWrapper()
        .delete(BOOK_STOREBOOKS_ENDPOINT, parametrsMap, "",
            Singleton.getInstance().token, Authorization.BEARER);
  }

  @Step("Удалить одну книги у пользователя")
  public RestWrapper deleteBook() {
    BookDeleteRequestModel book = new BookTemplates().formChangeBook(isbn,
        Singleton.getInstance().userId);
    return new RestWrapper()
        .delete(BOOK_STOREBOOK_ENDPOINT, new HashMap<>(), book, Singleton.getInstance().token,
            Authorization.BEARER);
  }

  @Step("Обновить данные о книге пользователя")
  public RestWrapper putBooks() {
    BookDeleteRequestModel book = new BookTemplates().formChangeBook(newIsbn,
        Singleton.getInstance().userId);
    return new RestWrapper()
        .put(BOOK_STOREBOOKS_ENDPOINT + "/" + isbn, book, Singleton.getInstance().token,
            Authorization.BEARER);
  }
}
