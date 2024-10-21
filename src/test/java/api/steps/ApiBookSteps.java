package api.steps;

import static helpers.config.Endpoints.BOOK_STOREBOOKS_ENDPOINT;
import static helpers.config.Endpoints.BOOK_STOREBOOK_ENDPOINT;

import api.templates.AccountTemplates;
import api.templates.BookTemplates;
import helpers.models.AccountNewUserRequestModel;
import helpers.models.BookAddPostRequestModel;
import helpers.models.BookDeleteRequestModel;
import io.qameta.allure.Step;


public class ApiBookSteps {
  private final AccountNewUserRequestModel basicUser= new AccountTemplates().getBasicUser();
  private final String isbn= ApiSingleton.getISBN().get(0).getIsbn();;

  @Step("Добавить книгу пользователю")
  public void postBooks(AccountNewUserRequestModel user) {
    BookAddPostRequestModel addBook = new BookTemplates().formAddBook(ApiSingleton.getUserId(), ApiSingleton.getISBN());
    new RestWrapper()
        .post(BOOK_STOREBOOKS_ENDPOINT, addBook,ApiSingleton.getToken());
  }
  @Step("Удалить все книги у пользователя")
  public void deleteBooks(AccountNewUserRequestModel user) {
    new RestWrapper()
        .delete(BOOK_STOREBOOKS_ENDPOINT + "?UserId=" + ApiSingleton.getUserId(), "",ApiSingleton.getToken());
  }

  @Step("Добавить книгу пользователю")
  public RestWrapper postBooks() {
    deleteBooks(basicUser);
    BookAddPostRequestModel addBook = new BookTemplates().formAddBook(ApiSingleton.getUserId(), ApiSingleton.getISBN());
    return new RestWrapper()
        .post(BOOK_STOREBOOKS_ENDPOINT, addBook, ApiSingleton.getToken());
  }

  @Step("Получить список книг")
  public static RestWrapper getBooks() {
    return new RestWrapper()
        .get(BOOK_STOREBOOKS_ENDPOINT,"");
  }

  @Step("Получить книгу")
  public RestWrapper getBook() {
    return new RestWrapper()
        .get(BOOK_STOREBOOK_ENDPOINT + "?ISBN=" + isbn,"");
  }


  @Step("Удалить все книги у пользователя")
  public RestWrapper deleteBooks() {
    postBooks();
    return new RestWrapper()
        .delete(BOOK_STOREBOOKS_ENDPOINT + "?UserId=" + ApiSingleton.getUserId(),"",ApiSingleton.getToken());
  }

  @Step("Удалить одну книги у пользователя")
  public RestWrapper deleteBook() {
    postBooks();
    BookDeleteRequestModel book = new BookTemplates().formChangeBook(isbn, ApiSingleton.getUserId());
    return new RestWrapper()
        .delete(BOOK_STOREBOOK_ENDPOINT, book, ApiSingleton.getToken());
  }


  @Step("Обновить данные о книге пользователя")
  public RestWrapper putBooks() {
    postBooks();
    BookDeleteRequestModel book = new BookTemplates().formChangeBook("9781449337711", ApiSingleton.getUserId());
    return new RestWrapper()
        .put(BOOK_STOREBOOKS_ENDPOINT + "/" + isbn, book, ApiSingleton.getToken());
  }

}
