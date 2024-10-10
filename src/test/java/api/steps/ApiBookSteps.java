package api.steps;

import static helpers.config.Endpoints.BOOK_STOREBOOKS_ENDPOINT;
import static helpers.config.Endpoints.BOOK_STOREBOOK_ENDPOINT;
import static helpers.utils.BaseTest.config;

import api.templates.AccountTemplates;
import api.templates.BookTemplates;
import helpers.models.AccountNewUserRequestModel;
import helpers.models.BookAddPostRequestModel;
import helpers.models.BookDeleteRequestModel;
import helpers.models.IsbnModel;
import io.qameta.allure.Step;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ApiBookSteps {

  private final AccountNewUserRequestModel basicUser;
  private String token;
  private final List<IsbnModel> isbnArray;
  private final String isbn;
  private String userId;

  public ApiBookSteps() {
    this.basicUser = new AccountTemplates().getBasicUser();
    this.token = new ApiUserSteps().getToken().getResponse().path("token");
    this.isbnArray = getISBN();
    this.isbn = isbnArray.get(0).getIsbn();
    this.userId = config.getIdentifier();
  }

  private static List<IsbnModel> getISBN() {
    Map<String, ?> bookMap = getBooks().getResponse()
        .path("books.find { it.title == '" + "Git Pocket Guide" + "' }");
    IsbnModel isbn = new BookTemplates().formIsbn(bookMap);
    return Collections.singletonList(isbn);
  }


  public AccountNewUserRequestModel settingUser() {
    AccountNewUserRequestModel user = new AccountTemplates().getBasicUser();
    deleteBooks(user);
    return user;
  }

  public void postBooks(AccountNewUserRequestModel user) {
    userId = new ApiUserSteps().getLogin(user).getResponse().path("userId");
    token = new ApiUserSteps().getToken(user).getResponse().path("token");
    BookAddPostRequestModel addBook = new BookTemplates().formAddBook(userId, isbnArray);
    new RestWrapper(BOOK_STOREBOOKS_ENDPOINT, addBook, token)
        .post();
  }

  public void deleteBooks(AccountNewUserRequestModel user) {
    new RestWrapper(BOOK_STOREBOOKS_ENDPOINT + "?UserId=" + userId, token)
        .delete();
  }

  @Step("Добавить книгу пользователю")
  public RestWrapper postBooks() {
    deleteBooks(basicUser);
    BookAddPostRequestModel addBook = new BookTemplates().formAddBook(userId, isbnArray);
    return new RestWrapper(BOOK_STOREBOOKS_ENDPOINT, addBook, token)
        .post();
  }

  @Step("Получить список книг")
  public static RestWrapper getBooks() {
    return new RestWrapper(BOOK_STOREBOOKS_ENDPOINT)
        .get();
  }

  @Step("Получить книгу")
  public RestWrapper getBook() {
    return new RestWrapper(BOOK_STOREBOOK_ENDPOINT + "?ISBN=" + isbn)
        .get();
  }


  @Step("Удалить все книги у пользователя")
  public RestWrapper deleteBooks() {
    postBooks();
    return new RestWrapper(BOOK_STOREBOOKS_ENDPOINT + "?UserId=" + userId, token)
        .delete();
  }

  @Step("Удалить одну книги у пользователя")
  public RestWrapper deleteBook() {
    postBooks();
    BookDeleteRequestModel book = new BookTemplates().formChangeBook(isbn, userId);
    return new RestWrapper(BOOK_STOREBOOK_ENDPOINT, book, token)
        .delete();
  }


  @Step("Обновить данные о книге пользователя")
  public RestWrapper putBooks() {
    postBooks();
    BookDeleteRequestModel book = new BookTemplates().formChangeBook("9781449337711", userId);
    return new RestWrapper(BOOK_STOREBOOKS_ENDPOINT + "/" + isbn, book, token)
        .put();
  }

}
