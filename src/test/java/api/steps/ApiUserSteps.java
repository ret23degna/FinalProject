package api.steps;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import helpers.config.Endpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;;
import java.util.HashMap;
import java.util.Map;
import helpers.models.AccountGenerateTokenModel;
import helpers.models.AccountNewUserRequestModel;
import org.openqa.selenium.Cookie;
import helpers.config.RandomUtils;

public class ApiUserSteps {

  Endpoints endpoints = new Endpoints();
  RandomUtils random = new RandomUtils();

  public Map<String, Cookie> getCookie(AccountNewUserRequestModel User) {
    String cookieTokenKey = "token";
    String cookieExpiresKey = "expires";
    String cookieUserIDKey = "userID";
    String cookieUserIDValue = getLogin(User);
    AccountGenerateTokenModel token = getToken(User);
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

  private AccountNewUserRequestModel getLoginPassword() {
    AccountNewUserRequestModel loginPassword = new AccountNewUserRequestModel();
    loginPassword.setUserName(random.login());
    loginPassword.setPassword(random.password());
    return loginPassword;
  }

  public String getLogin(AccountNewUserRequestModel user) {
    Response response = new RestWrapper()
        .post(endpoints.accountLoginEndpoint, user)
        .shouldHaveStatusCode(200)
        .shouldGiveResponce();
    return response.path("userId");
  }

  public AccountGenerateTokenModel getToken(AccountNewUserRequestModel user) {
    Response response = new RestWrapper()
        .post(endpoints.accountTokenEndpoint, user)
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("status", containsString("Success"))
        .shouldGiveResponce();
    AccountGenerateTokenModel token = response.as(AccountGenerateTokenModel.class);
    return token;
  }

  @Step("Создание нового пользователя")
  public AccountNewUserRequestModel setNewUser() {
    AccountNewUserRequestModel user = getLoginPassword();
    new RestWrapper()
        .post(endpoints.accountUserEndpoint, user)
        .shouldHaveStatusCode(201)
        .shouldHaveJsonPath("username", containsString(user.getUserName()));
    return user;
  }

  @Step("Генерация токена")
  public void getToken() {
    AccountNewUserRequestModel user = setNewUser();
    new RestWrapper()
        .post(endpoints.accountTokenEndpoint, user)
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("status", containsString("Success"));
  }

  @Step("Авторизация пользователя")
  public void setAuthorized() {
    AccountNewUserRequestModel user = setNewUser();
    AccountGenerateTokenModel token = getToken(user);
    Response response = new RestWrapper()
        .post(endpoints.accountAuthorizedEndpoint, user, token.getToken())
        .shouldHaveStatusCode(200)
        .shouldGiveResponce();
    assertEquals("true", response.getBody().asString());//подумать тут

  }

  @Step("Удаление пользователя")
  public void setDeleteUser() {
    AccountNewUserRequestModel user = setNewUser();
    AccountGenerateTokenModel token = getToken(user);
    String userId = getLogin(user);
    new RestWrapper()
        .delete(endpoints.accountUserEndpoint + "/" + userId, token.getToken())
        .shouldHaveStatusCode(204);
  }

  @Step("Получить информацию о пользователе")
  public void getUser() {
    AccountNewUserRequestModel user = setNewUser();
    AccountGenerateTokenModel token = getToken(user);
    String userId = getLogin(user);
    new RestWrapper()
        .get(endpoints.accountUserEndpoint + "/" + userId, token.getToken())
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("username", containsString(user.getUserName()));
  }
}
