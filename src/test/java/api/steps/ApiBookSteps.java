package api.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import helpers.config.Endpoints;
import helpers.models.AccountGenerateTokenModel;
import helpers.models.AccountNewUserRequestModel;
import helpers.models.BookAddPostRequestModel;
import helpers.models.BookAddPostResponceModel;
import helpers.models.BookDeleteRequestModel;
import helpers.models.BookGetResponceModel;
import helpers.models.IsbnModel;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiBookSteps {

  Endpoints endpoints = new Endpoints();
  ApiUserSteps apiUser = new ApiUserSteps();

  private List<IsbnModel> getISBN() {
    IsbnModel isbn = new IsbnModel();
    isbn.setIsbn("9781593275846");
    List<IsbnModel> isbnArray = new ArrayList<>();
    isbnArray.add(0, isbn);
    return isbnArray;
  }

  public String successfulPostBooks(AccountNewUserRequestModel user) {
    AccountGenerateTokenModel token = apiUser.getToken(user);
    String userId = apiUser.getLogin(user);
    List<IsbnModel> isbnArray = getISBN();
    BookAddPostRequestModel addBook = new BookAddPostRequestModel();
    addBook.setUserId(userId);
    addBook.setCollectionOfIsbns(isbnArray);
    Response responce = new RestWrapper()
        .post(endpoints.bookStoreBooksEndpoint, addBook, token.getToken())
        .shouldHaveStatusCode(201)
        .shouldGiveResponce();
    assertEquals(isbnArray, responce.as(BookAddPostResponceModel.class).getBooks());
    return isbnArray.get(0).getIsbn().toString();
  }

  @Step("Добавление книги пользователю")
  public void successfulPostBooks() {
    AccountNewUserRequestModel user = apiUser.setNewUser();
    AccountGenerateTokenModel token = apiUser.getToken(user);
    String userId = apiUser.getLogin(user);
    List<IsbnModel> isbnArray = getISBN();
    BookAddPostRequestModel addBook = new BookAddPostRequestModel();
    addBook.setUserId(userId);
    addBook.setCollectionOfIsbns(isbnArray);
    Response responce = new RestWrapper()
        .post(endpoints.bookStoreBooksEndpoint, addBook, token.getToken())
        .shouldHaveStatusCode(201)
        .shouldGiveResponce();
    assertEquals(isbnArray, responce.as(BookAddPostResponceModel.class).getBooks());
  }

  @Step("Получить список книг")
  public Map<String, ?> successfulGetBooks() {
    Response responce = new RestWrapper()
        .get(endpoints.bookStoreBooksEndpoint)
        .shouldHaveStatusCode(200)
        .shouldGiveResponce();
    Map<String, ?> book = responce.path("books.find{it}");
    return book;
  }

  @Step("Получить книгу")
  public void successfulGetBook() {
    Map<String, ?> book = successfulGetBooks();
    String isbn = book.get("isbn").toString();
    Response responce = new RestWrapper()
        .get(endpoints.bookStoreBookEndpoint + "?ISBN=" + isbn)
        .shouldHaveStatusCode(200)
        .shouldGiveResponce();
    assertEquals(book.get("title"), responce.as(BookGetResponceModel.class).getTitle());
    assertEquals(book.get("author"), responce.as(BookGetResponceModel.class).getAuthor());
  }

  @Step("Удаление всех книг у пользователя")
  public void successfulDeleteBooks() {
    AccountNewUserRequestModel user = apiUser.setNewUser();
    successfulPostBooks(user);
    AccountGenerateTokenModel token = apiUser.getToken(user);
    String userId = apiUser.getLogin(user);
    new RestWrapper()
        .delete(endpoints.bookStoreBooksEndpoint + "?UserId=" + userId, token.getToken())
        .shouldHaveStatusCode(204);
  }

  @Step("Удаление одной книги у пользователя")
  public void successfulDeleteBook() {
    AccountNewUserRequestModel user = apiUser.setNewUser();
    String isbn = successfulPostBooks(user);
    AccountGenerateTokenModel token = apiUser.getToken(user);
    String userId = apiUser.getLogin(user);
    BookDeleteRequestModel book = new BookDeleteRequestModel();
    book.setIsbn(isbn);
    book.setUserId(userId);
    new RestWrapper()
        .delete(endpoints.bookStoreBookEndpoint, book, token.getToken())
        .shouldHaveStatusCode(204);
  }

  @Step("Обновление данных о книге пользователя")
  public void successfulPutBooks() {
    AccountNewUserRequestModel user = apiUser.setNewUser();
    String isbn = successfulPostBooks(user);
    AccountGenerateTokenModel token = apiUser.getToken(user);
    String userId = apiUser.getLogin(user);
    BookDeleteRequestModel book = new BookDeleteRequestModel();
    book.setIsbn("9781449337711");
    book.setUserId(userId);
    new RestWrapper()
        .put(endpoints.bookStoreBooksEndpoint + "/" + isbn, book, token.getToken())
        .shouldHaveStatusCode(200);
  }
}
