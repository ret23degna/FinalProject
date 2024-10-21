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

public class ApiSingleton {

  private static String token;
  private static String  expires;
  private static String  userId = "7a854906-dfc3-4b4e-b527-1f8ba0c8070c";
  private static List<IsbnModel> isbnArray;
  private static  Map<String, Cookie> cookies;
  private ApiSingleton() {}

  public static List<IsbnModel> getISBN() {
    if (isbnArray == null) {
      Map<String, ?> bookMap = getBooks().getResponse()
          .path("books.find { it.title == '" + "Git Pocket Guide" + "' }");
      IsbnModel isbn = new BookTemplates().formIsbn(bookMap);
      isbnArray=Collections.singletonList(isbn);
    }
    return isbnArray;
  }
  public static String getToken() {
    if (token == null) {
      Response response  = new ApiUserSteps().getToken(new AccountTemplates().getBasicUser())
          .getResponse();
      expires = response.path("expires");
      token= response.path("token");
    }
    return token;
  }
  public static String getExpires() {
    return expires;
  }

  public static String getUserId() {
    return userId;
  }

  public static Map<String, Cookie> getCookie() {
    if (cookies == null) {
    cookies = new HashMap<>();
    cookies.put("token", new Cookie("token", getToken()));
    cookies.put("expires", new Cookie("expires", getExpires()));
    cookies.put("userID", new Cookie("userID", getUserId()));
    }
    return cookies;
  }
}
