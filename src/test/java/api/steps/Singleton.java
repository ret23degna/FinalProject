package api.steps;

import static api.steps.ApiBookSteps.getBooks;

import api.templates.AccountTemplates;
import api.templates.BookTemplates;
import helpers.models.IsbnModel;
import io.restassured.response.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.Cookie;

public class Singleton {

  public String token, expires, userId;
  public Map<String, Cookie> cookies;
  public List<IsbnModel> isbnArray;
  private static Singleton instance;

  private void setToken() {
    Response response = new ApiUserSteps().getToken(new AccountTemplates().getBasicUser())
        .getResponse();
    expires = response.path("expires");
    token = response.path("token");
  }

  private void getUserId() {
    userId = new ApiUserSteps().getLogin(new AccountTemplates().getBasicUser()).getResponse()
        .path("userId");
  }

  private void getCookie() {
    cookies = new HashMap<>();
    cookies.put("token", new Cookie("token", token));
    cookies.put("expires", new Cookie("expires", expires));
    cookies.put("userID", new Cookie("userID", userId));
  }

  private void getISBN() {
    Map<String, ?> bookMap = getBooks().getResponse()
        .path("books.find { it.title == '" + "Git Pocket Guide" + "' }");
    IsbnModel isbn = new BookTemplates().formIsbn(bookMap);
    isbnArray = Collections.singletonList(isbn);
  }

  private Singleton() {
    setToken();
    getUserId();
    getCookie();
    getISBN();
  }

  public static Singleton getInstance() {
    if (instance == null) {
      instance = new Singleton();
    }
    return instance;
  }
}
