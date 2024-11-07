package api.steps;

import static helpers.config.Endpoints.BOOK_STOREBOOKS_ENDPOINT;
import static helpers.config.Endpoints.BOOK_STOREBOOK_ENDPOINT;

import api.templates.BookTemplates;
import helpers.models.BookAddPostRequestModel;
import helpers.models.BookDeleteRequestModel;
import helpers.utils.BaseTest.authoriz;
import io.qameta.allure.Step;
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
    String[] isbnArray = {"UserId", Singleton.getInstance().userId};
    new RestWrapper()
        .delete(BOOK_STOREBOOKS_ENDPOINT, isbnArray, "",
            Singleton.getInstance().token, authoriz.bearer);
  }

  @Step("Предварительный шаг.Добавить книгу пользователю")
  public void preStepPostBooks() {
    BookAddPostRequestModel addBook = new BookTemplates().formAddBook(
        Singleton.getInstance().userId, Singleton.getInstance().isbnArray);
    new RestWrapper()
        .post(BOOK_STOREBOOKS_ENDPOINT, null, addBook, Singleton.getInstance().token,
            authoriz.oauth2);
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
        .post(BOOK_STOREBOOKS_ENDPOINT, null, addBook, Singleton.getInstance().token,
            authoriz.oauth2);
  }

  @Step("Получить список книг")
  public static RestWrapper getBooks() {
    return new RestWrapper()
        .get(BOOK_STOREBOOKS_ENDPOINT, null, null, authoriz.unknown);
  }

  @Step("Получить книгу")
  public RestWrapper getBook() {
    String[] isbnArray = {"ISBN", isbn};
    return new RestWrapper()
        .get(BOOK_STOREBOOK_ENDPOINT, isbnArray, null, authoriz.unknown);
  }

  @Step("Удалить все книги у пользователя")
  public RestWrapper deleteBooks() {
    String[] isbnArray = {"UserId", Singleton.getInstance().userId};
    return new RestWrapper()
        .delete(BOOK_STOREBOOKS_ENDPOINT, isbnArray, "",
            Singleton.getInstance().token, authoriz.bearer);
  }

  @Step("Удалить одну книги у пользователя")
  public RestWrapper deleteBook() {
    BookDeleteRequestModel book = new BookTemplates().formChangeBook(isbn,
        Singleton.getInstance().userId);
    return new RestWrapper()
        .delete(BOOK_STOREBOOK_ENDPOINT, null, book, Singleton.getInstance().token,
            authoriz.bearer);
  }

  @Step("Обновить данные о книге пользователя")
  public RestWrapper putBooks() {
    BookDeleteRequestModel book = new BookTemplates().formChangeBook(newIsbn,
        Singleton.getInstance().userId);
    return new RestWrapper()
        .put(BOOK_STOREBOOKS_ENDPOINT + "/" + isbn, null, book, Singleton.getInstance().token,
            authoriz.bearer);
  }
}
