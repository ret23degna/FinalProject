package api.steps;

import static helpers.config.Endpoints.ACCOUNT_AUTHORIZED_ENDPOINT;
import static helpers.config.Endpoints.ACCOUNT_LOGIN_ENDPOINT;
import static helpers.config.Endpoints.ACCOUNT_TOKEN_ENDPOINT;
import static helpers.config.Endpoints.ACCOUNT_USER_ENDPOINT;

import api.templates.AccountTemplates;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import helpers.models.AccountNewUserRequestModel;

public class ApiUserSteps {

  private AccountNewUserRequestModel basicUser = new AccountTemplates().getBasicUser();
  private AccountNewUserRequestModel newUser;
  private String token, userId, newToken, newUserId;

  public String[] getTokenInfo(AccountNewUserRequestModel user) {
    Response response = getToken(user).getResponse();
    String token = response.path("token");
    String expires = response.path("expires");
    return new String[]{token, expires};
  }

  public String getUserId(AccountNewUserRequestModel user) {
    return getLogin(user).getResponse().path("userId");
  }

  @Step("Предварительный шаг. Создать нового пользователя")
  public RestWrapper predNewUser() {
    newUser = new AccountTemplates().getNewUser();
    return new RestWrapper()
        .post(ACCOUNT_USER_ENDPOINT, newUser, null);
  }

  @Step("Предвариетльный шаг. Генерация UserId для основного пользователя")
  public void predUserId() {
    userId = Singleton.getInstance().userId;
  }

  @Step("Предвариетльный шаг. Генерировать токен для основного пользователя")
  public void predGetToken() {
    token = Singleton.getInstance().token;
  }

  @Step("Преварительный шаг.Залогироваться для нового пользователя")
  public void newGetLogin() {
    newUserId = getLogin(newUser).getResponse().path("userId");
  }

  @Step("Преварительный шаг. Генерировать токен для нового пользователя")
  public void newGetToken() {
    newToken = getToken(newUser).getResponse().path("token");
  }

  @Step("Залогироваться")
  public RestWrapper getLogin(AccountNewUserRequestModel user) {
    return new RestWrapper()
        .post(ACCOUNT_LOGIN_ENDPOINT, user, null);
  }

  @Step("Создать нового пользователя")
  public void newUser(AccountNewUserRequestModel user) {
    new RestWrapper()
        .post(ACCOUNT_USER_ENDPOINT, user, null);
  }

  @Step("Генерировать токен")
  public RestWrapper getToken(AccountNewUserRequestModel user) {
    return new RestWrapper()
        .post(ACCOUNT_TOKEN_ENDPOINT, user, null);
  }

  @Step("Создать нового пользователя")
  public RestWrapper newUser() {
    AccountNewUserRequestModel newUser = new AccountTemplates().getNewUser();
    return new RestWrapper()
        .post(ACCOUNT_USER_ENDPOINT, newUser, null);
  }


  @Step("Авторизовать пользователя")
  public RestWrapper authorized() {
    return new RestWrapper()
        .post(ACCOUNT_AUTHORIZED_ENDPOINT, basicUser, token);
  }


  @Step("Удалить пользователя")
  public RestWrapper deleteUser() {
    return new RestWrapper()
        .delete(ACCOUNT_USER_ENDPOINT + "/" + newUserId, "", newToken);
  }

  @Step("Получить информацию о пользователе")
  public RestWrapper getUser() {
    return new RestWrapper()
        .get(ACCOUNT_USER_ENDPOINT + "/" + userId, token);
  }

  @Step("Генерировать токен")
  public RestWrapper getToken() {
    return new RestWrapper()
        .post(ACCOUNT_TOKEN_ENDPOINT, newUser, null);
  }


}
