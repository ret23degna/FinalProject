package api.steps;

import static api.templates.AccountTemplates.getBasicUser;
import static api.templates.AccountTemplates.getGenerateToken;
import static api.templates.BookTemplates.formAddBook;
import static api.templates.BookTemplates.formChangeBook;
import static api.templates.BookTemplates.formIsbn;
import static helpers.config.Endpoints.BOOK_STOREBOOKS_ENDPOINT;
import static helpers.config.Endpoints.BOOK_STOREBOOK_ENDPOINT;
import static org.hamcrest.Matchers.containsString;

import helpers.models.AccountGenerateTokenModel;
import helpers.models.AccountNewUserRequestModel;
import helpers.models.BookAddPostRequestModel;
import helpers.models.BookDeleteRequestModel;
import helpers.models.IsbnModel;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiBookSteps {

  private Response response;
  private AccountNewUserRequestModel user;
  private AccountGenerateTokenModel token;
  private String isbn;
  private String userId;
  private BookAddPostRequestModel addBook;
  private BookDeleteRequestModel book;
  private Map<String, ?> bookMap;
  private List<IsbnModel> isbnArray;
  ApiUserSteps apiUser = new ApiUserSteps();

  private List<IsbnModel> getISBN() {
    bookMap = getBooks("Git Pocket Guide");
    IsbnModel isbn = formIsbn(bookMap);
    List<IsbnModel> isbnArray = new ArrayList<>();
    isbnArray.add(0, isbn);
    return isbnArray;
  }

  private Map<String, ?> getBooks(String title) {
    response = new RestWrapper(BOOK_STOREBOOKS_ENDPOINT)
        .get()
        .shouldGiveResponce();
    return response.path("books.find { it.title == '" + title + "' }");
  }

  public AccountNewUserRequestModel settingUser() {
    AccountNewUserRequestModel user = getBasicUser();
    token = getGenerateToken(user);
    if (token.getToken() == null) {
      apiUser.newUser(user);
    }
    deleteBooks(user);
    return user;
  }

  public String postBooks(AccountNewUserRequestModel user) {
    token = getGenerateToken(user);
    userId = apiUser.getLogin(user);
    isbnArray = getISBN();
    addBook = formAddBook(userId, isbnArray);
    new RestWrapper(BOOK_STOREBOOKS_ENDPOINT, addBook, token.getToken())
        .post();
    return isbnArray.get(0).getIsbn();
  }

  public void deleteBooks(AccountNewUserRequestModel user) {
    token = getGenerateToken(user);
    userId = apiUser.getLogin(user);
    new RestWrapper(BOOK_STOREBOOKS_ENDPOINT + "?UserId=" + userId, token.getToken())
        .delete();
  }

  @Step("Добавить книгу пользователю")
  public void postBooks() {
    user = settingUser();
    token = getGenerateToken(user);
    userId = apiUser.getLogin(user);
    isbnArray = getISBN();
    addBook = formAddBook(userId, isbnArray);
    response = new RestWrapper(BOOK_STOREBOOKS_ENDPOINT, addBook, token.getToken())
        .post()
        .shouldGiveResponce();
  }

  @Step("Проверить добавление книги пользователю")
  public void checkPostBooks() {
    new RestWrapper()
        .setResponse(response)
        .shouldHaveStatusCode(201)
        .shouldHaveJsonPath("books[0].isbn", containsString(isbnArray.get(0).getIsbn()));
  }

  @Step("Получить список книг")
  public Map<String, ?> getBooks() {
    response = new RestWrapper(BOOK_STOREBOOKS_ENDPOINT)
        .get()
        .shouldHaveStatusCode(200)
        .shouldGiveResponce();
    return response.path("books.find{it}");
  }

  @Step("Проверить получение списка книг")
  public void checkGetBooks() {
    new RestWrapper()
        .setResponse(response)
        .shouldHaveStatusCode(200);
  }

  @Step("Получить книгу")
  public void getBook() {
    bookMap = getBooks();
    isbn = bookMap.get("isbn").toString();
    response = new RestWrapper(BOOK_STOREBOOK_ENDPOINT + "?ISBN=" + isbn)
        .get()
        .shouldGiveResponce();
  }

  @Step("Проверить получение книги")
  public void checkGetBook() {
    new RestWrapper()
        .setResponse(response)
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("isbn", containsString(bookMap.get("isbn").toString()))
        .shouldHaveJsonPath("title", containsString(bookMap.get("title").toString()))
        .shouldHaveJsonPath("subTitle", containsString(bookMap.get("subTitle").toString()))
        .shouldHaveJsonPath("author", containsString(bookMap.get("author").toString()))
        .shouldHaveJsonPath("publish_date", containsString(bookMap.get("publish_date").toString()))
        .shouldHaveJsonPath("publisher", containsString(bookMap.get("publisher").toString()))
        .shouldHaveJsonPath("description", containsString(bookMap.get("description").toString()))
        .shouldHaveJsonPath("website", containsString(bookMap.get("website").toString()));
  }

  @Step("Удалить все книги у пользователя")
  public void deleteBooks() {
    user = settingUser();
    postBooks(user);
    token = getGenerateToken(user);
    userId = apiUser.getLogin(user);
    response = new RestWrapper(BOOK_STOREBOOKS_ENDPOINT + "?UserId=" + userId, token.getToken())
        .delete()
        .shouldGiveResponce();
  }

  @Step("Удалить одну книги у пользователя")
  public void deleteBook() {
    user = settingUser();
    isbn = postBooks(user);
    token = getGenerateToken(user);
    userId = apiUser.getLogin(user);
    book = formChangeBook(isbn, userId);
    response = new RestWrapper(BOOK_STOREBOOK_ENDPOINT, book, token.getToken())
        .delete()
        .shouldGiveResponce();
  }

  @Step("Проверить удаление  книг у пользователя")
  public void checkDeleteBooks() {
    new RestWrapper()
        .setResponse(response)
        .shouldHaveStatusCode(204);
  }

  @Step("Обновить данные о книге пользователя")
  public void putBooks() {
    user = settingUser();
    isbn = postBooks(user);
    token = getGenerateToken(user);
    userId = apiUser.getLogin(user);
    book = formChangeBook("9781449337711", userId);
    response = new RestWrapper(BOOK_STOREBOOKS_ENDPOINT + "/" + isbn, book, token.getToken())
        .put()
        .shouldGiveResponce();
  }

  @Step("Проверить обновленные данны о книге пользователя")
  public void checkPutBooks() {
    new RestWrapper()
        .setResponse(response)
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("userId", containsString(userId))
        .shouldHaveJsonPath("username", containsString(user.getUserName()));
  }
}
