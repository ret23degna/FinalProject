package api.steps;

import static api.templates.AccountTemplates.getGenerateToken;
import static api.templates.AccountTemplates.getNewUser;
import static helpers.config.Endpoints.ACCOUNT_AUTHORIZED_ENDPOINT;
import static helpers.config.Endpoints.ACCOUNT_LOGIN_ENDPOINT;
import static helpers.config.Endpoints.ACCOUNT_TOKEN_ENDPOINT;
import static helpers.config.Endpoints.ACCOUNT_USER_ENDPOINT;
import static org.hamcrest.Matchers.containsString;

import io.qameta.allure.Step;
import io.restassured.response.Response;;
import java.util.HashMap;
import java.util.Map;
import helpers.models.AccountGenerateTokenModel;
import helpers.models.AccountNewUserRequestModel;
import org.openqa.selenium.Cookie;

public class ApiUserSteps {

  private Response response;
  private String userId;
  private AccountNewUserRequestModel user;
  private AccountGenerateTokenModel token;

  public Map<String, Cookie> getCookie(AccountNewUserRequestModel user) {
    String cookieTokenKey = "token";
    String cookieExpiresKey = "expires";
    String cookieUserIDKey = "userID";
    String cookieUserIDValue = getLogin(user);
    token = getGenerateToken(user);
    String cookieTokenValue = token.getToken();
    String cookieExpiresValue = token.getExpires();
    Cookie cookieToken = new Cookie(cookieTokenKey, cookieTokenValue);
    Cookie cookieExpires = new Cookie(cookieExpiresKey, cookieExpiresValue);
    Cookie cookieUserID = new Cookie(cookieUserIDKey, cookieUserIDValue);
    Map<String, Cookie> cookies = new HashMap<>();
    cookies.put(cookieTokenKey, cookieToken);
    cookies.put(cookieExpiresKey, cookieExpires);
    cookies.put(cookieUserIDKey, cookieUserID);
    return cookies;
  }

  public String getLogin(AccountNewUserRequestModel user) {
    Response response = new RestWrapper(ACCOUNT_LOGIN_ENDPOINT, user)
        .post()
        .shouldGiveResponce();
    return response.path("userId");
  }

  public void newUser(AccountNewUserRequestModel user) {
    response = new RestWrapper(ACCOUNT_USER_ENDPOINT, user)
        .post()
        .shouldGiveResponce();
  }

  @Step("Создать нового пользователя")
  public AccountNewUserRequestModel newUser() {
    user = getNewUser();
    response = new RestWrapper(ACCOUNT_USER_ENDPOINT, user)
        .post()
        .shouldGiveResponce();
    return user;
  }

  @Step("Проверить cоздание нового пользователя")
  public void checkNewUser() {
    new RestWrapper()
        .setResponse(response)
        .shouldHaveStatusCode(201)
        .shouldHaveJsonPath("username", containsString(user.getUserName()));
  }

  @Step("Генерировать токен")
  public void getToken() {
    ApiBookSteps api = new ApiBookSteps();
    user = api.settingUser();
    response = new RestWrapper(ACCOUNT_TOKEN_ENDPOINT, user)
        .post()
        .shouldGiveResponce();
    ;
  }

  @Step("Проверить генерацию токена")
  public void checkGetToken() {
    new RestWrapper()
        .setResponse(response)
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("status", containsString("Success"))
        .shouldHaveJsonPath("result", containsString("User authorized successfully"));
  }

  @Step("Авторизовать пользователя")
  public void authorized() {
    ApiBookSteps api = new ApiBookSteps();
    user = api.settingUser();
    token = getGenerateToken(user);
    response = new RestWrapper(ACCOUNT_AUTHORIZED_ENDPOINT, user, token.getToken())
        .post()
        .shouldGiveResponce();
  }

  @Step("Проверить авторизацию пользователя")
  public void checkAuthorized() {
    new RestWrapper()
        .setResponse(response)
        .shouldHaveStatusCode(200)
        .responseBodyIsNoJson("true");
  }

  @Step("Удалить пользователя")
  public void deleteUser() {
    //  user = newUser();;
    ApiBookSteps api = new ApiBookSteps();
    user = api.settingUser();
    token = getGenerateToken(user);
    String userId = getLogin(user);
    response = new RestWrapper(ACCOUNT_USER_ENDPOINT + "/" + userId, token.getToken())
        .delete()
        .shouldGiveResponce();
  }

  @Step("Проверить удаление пользователя")
  public void checkDeleteUser() {
    new RestWrapper()
        .setResponse(response)
        .shouldHaveStatusCode(204);
  }

  @Step("Получить информацию о пользователе")
  public void getUser() {
    ApiBookSteps api = new ApiBookSteps();
    user = api.settingUser();
    token = getGenerateToken(user);
    userId = getLogin(user);
    response = new RestWrapper(ACCOUNT_USER_ENDPOINT + "/" + userId, token.getToken())
        .get()
        .shouldGiveResponce();
  }

  @Step("Проверить информацию о пользователе")
  public void checkGetUser() {
    new RestWrapper()
        .setResponse(response)
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("userId", containsString(userId))
        .shouldHaveJsonPath("username", containsString(user.getUserName()));
  }
}
