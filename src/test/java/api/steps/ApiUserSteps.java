package api.steps;

import static helpers.config.Endpoints.ACCOUNT_AUTHORIZED_ENDPOINT;
import static helpers.config.Endpoints.ACCOUNT_LOGIN_ENDPOINT;
import static helpers.config.Endpoints.ACCOUNT_TOKEN_ENDPOINT;
import static helpers.config.Endpoints.ACCOUNT_USER_ENDPOINT;

import api.templates.AccountTemplates;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import helpers.models.AccountNewUserRequestModel;
import org.openqa.selenium.Cookie;

public class ApiUserSteps {

  private AccountNewUserRequestModel basicUser= new AccountTemplates().getBasicUser();;

  public Map<String, Cookie> getCookie(AccountNewUserRequestModel user) {
    Response responseGetToken  = getToken(user).getResponse();
    Response responseGetLogin  = getLogin(user).getResponse();
    String cookieTokenValue = responseGetToken.path("token");
    String cookieExpiresValue = responseGetToken.path("expires");
    String cookieUserIDValue = responseGetLogin.path("userId");
    Map<String, Cookie> cookies = new HashMap<>();
    cookies.put("token", new Cookie("token", cookieTokenValue));
    cookies.put("expires", new Cookie("expires", cookieExpiresValue));
    cookies.put("userID",  new Cookie("userID", cookieUserIDValue));
    return cookies;
  }
  @Step("Залогироваться")
  public RestWrapper getLogin(AccountNewUserRequestModel user) {
    return new RestWrapper()
        .post(ACCOUNT_LOGIN_ENDPOINT, user,"");
  }
  @Step("Создать нового пользователя")
  public void newUser(AccountNewUserRequestModel user) {
    new RestWrapper()
        .post(ACCOUNT_USER_ENDPOINT, user,"");
  }
  @Step("Генерировать токен")
  public RestWrapper getToken(AccountNewUserRequestModel user) {
    return new RestWrapper()
        .post(ACCOUNT_TOKEN_ENDPOINT, user,"");
  }

  @Step("Создать нового пользователя")
  public RestWrapper newUser() {
    AccountNewUserRequestModel newUser = new AccountTemplates().getNewUser();
    return new RestWrapper()
        .post(ACCOUNT_USER_ENDPOINT, newUser,"");
  }


  @Step("Авторизовать пользователя")
  public RestWrapper authorized() {
    return new RestWrapper()
        .post(ACCOUNT_AUTHORIZED_ENDPOINT, basicUser, ApiSingleton.getToken());
  }

  @Step("Удалить пользователя")
  public RestWrapper deleteUser() {
    AccountNewUserRequestModel newUser = new AccountTemplates().getNewUser();
    newUser(newUser);
    String userId = getLogin(newUser).getResponse().path("userId");
    String token = getToken(newUser).getResponse().path("token");
    return new RestWrapper()
        .delete(ACCOUNT_USER_ENDPOINT + "/" + userId, "", token);
  }

  @Step("Получить информацию о пользователе")
  public RestWrapper getUser() {
    return new RestWrapper()
        .get(ACCOUNT_USER_ENDPOINT + "/" + ApiSingleton.getUserId(),ApiSingleton.getToken());
  }

  @Step("Генерировать токен")
  public RestWrapper getToken() {
    AccountNewUserRequestModel newUser = new AccountTemplates().getNewUser();
    newUser(newUser);
    return new RestWrapper()
        .post(ACCOUNT_TOKEN_ENDPOINT, newUser,"");
  }

}
