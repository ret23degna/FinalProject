package api.steps;

import static helpers.config.Endpoints.ACCOUNT_AUTHORIZED_ENDPOINT;
import static helpers.config.Endpoints.ACCOUNT_LOGIN_ENDPOINT;
import static helpers.config.Endpoints.ACCOUNT_TOKEN_ENDPOINT;
import static helpers.config.Endpoints.ACCOUNT_USER_ENDPOINT;
import static helpers.utils.BaseTest.config;

import api.templates.AccountTemplates;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import helpers.models.AccountNewUserRequestModel;
import org.openqa.selenium.Cookie;

public class ApiUserSteps {

  private AccountNewUserRequestModel basicUser;

  public ApiUserSteps() {
    this.basicUser = new AccountTemplates().getBasicUser();
  }

  public Map<String, Cookie> getCookie(AccountNewUserRequestModel user) {
    String cookieTokenKey = "token";
    String cookieExpiresKey = "expires";
    String cookieUserIDKey = "userID";
    String cookieUserIDValue = getLogin(user).getResponse().path("userId");
    Response response = getToken(user).getResponse();
    String cookieTokenValue = response.path("token");
    String cookieExpiresValue = response.path("expires");
    Cookie cookieToken = new Cookie(cookieTokenKey, cookieTokenValue);
    Cookie cookieExpires = new Cookie(cookieExpiresKey, cookieExpiresValue);
    Cookie cookieUserID = new Cookie(cookieUserIDKey, cookieUserIDValue);
    Map<String, Cookie> cookies = new HashMap<>();
    cookies.put(cookieTokenKey, cookieToken);
    cookies.put(cookieExpiresKey, cookieExpires);
    cookies.put(cookieUserIDKey, cookieUserID);
    return cookies;
  }

  public RestWrapper getLogin(AccountNewUserRequestModel user) {
    return new RestWrapper(ACCOUNT_LOGIN_ENDPOINT, user)
        .post();
  }

  public void newUser(AccountNewUserRequestModel user) {
    new RestWrapper(ACCOUNT_USER_ENDPOINT, user)
        .post();
  }

  public RestWrapper getToken(AccountNewUserRequestModel user) {
    return new RestWrapper(ACCOUNT_TOKEN_ENDPOINT, user)
        .post();
  }

  @Step("Создать нового пользователя")
  public RestWrapper newUser() {
    AccountNewUserRequestModel newUser = new AccountTemplates().getNewUser();
    return new RestWrapper(ACCOUNT_USER_ENDPOINT, newUser)
        .post();
  }


  @Step("Авторизовать пользователя")
  public RestWrapper authorized() {
    String token = getToken().getResponse().path("token");
    return new RestWrapper(ACCOUNT_AUTHORIZED_ENDPOINT, basicUser, token)
        .post();
  }

  @Step("Удалить пользователя")
  public RestWrapper deleteUser() {
    AccountNewUserRequestModel newUser = new AccountTemplates().getNewUser();
    newUser(newUser);
    String token = getToken(newUser).getResponse().path("token");
    String userId = getLogin(newUser).getResponse().path("userId");
    return new RestWrapper(ACCOUNT_USER_ENDPOINT + "/" + userId, token)
        .delete();
  }

  @Step("Получить информацию о пользователе")
  public RestWrapper getUser() {
    String token = getToken(basicUser).getResponse().path("token");
    String userId = config.getIdentifier();
    return new RestWrapper(ACCOUNT_USER_ENDPOINT + "/" + userId, token)
        .get();
  }

  @Step("Генерировать токен")
  public RestWrapper getToken() {
    return new RestWrapper(ACCOUNT_TOKEN_ENDPOINT, basicUser)
        .post();
  }

}
