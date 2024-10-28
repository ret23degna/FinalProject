package api.steps;

import static helpers.config.Endpoints.BOOK_STOREBOOKS_ENDPOINT;
import static helpers.config.Endpoints.BOOK_STOREBOOK_ENDPOINT;

import api.templates.BookTemplates;
import helpers.models.BookAddPostRequestModel;
import helpers.models.BookDeleteRequestModel;
import io.qameta.allure.Step;

public class ApiBookSteps {

  private final String isbn = Singleton.getInstance().isbnArray.get(0).getIsbn();

  @Step("Предварительный шаг. Удалить все книги у пользователя")
  public void predDeleteBooks() {
    new RestWrapper()
        .delete(BOOK_STOREBOOKS_ENDPOINT + "?UserId=" + Singleton.getInstance().userId, "",
            Singleton.getInstance().token);
  }

  @Step("Предварительный шаг.Добавить книгу пользователю")
  public void predPostBooks() {
    BookAddPostRequestModel addBook = new BookTemplates().formAddBook(
        Singleton.getInstance().userId, Singleton.getInstance().isbnArray);
    new RestWrapper()
        .post(BOOK_STOREBOOKS_ENDPOINT, addBook, Singleton.getInstance().token);
  }



  @Step("Добавить книгу пользователю")
  public RestWrapper postBooks() {
    BookAddPostRequestModel addBook = new BookTemplates().formAddBook(
        Singleton.getInstance().userId, Singleton.getInstance().isbnArray);
    return new RestWrapper()
        .post(BOOK_STOREBOOKS_ENDPOINT, addBook, Singleton.getInstance().token);
  }

  @Step("Получить список книг")
  public static RestWrapper getBooks() {
    return new RestWrapper()
        .get(BOOK_STOREBOOKS_ENDPOINT, null);
  }

  @Step("Получить книгу")
  public RestWrapper getBook() {
    return new RestWrapper()
        .get(BOOK_STOREBOOK_ENDPOINT + "?ISBN=" + isbn, null);
  }


  @Step("Удалить все книги у пользователя")
  public RestWrapper deleteBooks() {
    return new RestWrapper()
        .delete(BOOK_STOREBOOKS_ENDPOINT + "?UserId=" + Singleton.getInstance().userId, "",
            Singleton.getInstance().token);
  }

  @Step("Удалить одну книги у пользователя")
  public RestWrapper deleteBook() {
    BookDeleteRequestModel book = new BookTemplates().formChangeBook(isbn,
        Singleton.getInstance().userId);
    return new RestWrapper()
        .delete(BOOK_STOREBOOK_ENDPOINT, book, Singleton.getInstance().token);
  }


  @Step("Обновить данные о книге пользователя")
  public RestWrapper putBooks() {
    BookDeleteRequestModel book = new BookTemplates().formChangeBook("9781449337711",
        Singleton.getInstance().userId);
    return new RestWrapper()
        .put(BOOK_STOREBOOKS_ENDPOINT + "/" + isbn, book, Singleton.getInstance().token);
  }

}
