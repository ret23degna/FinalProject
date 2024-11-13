package api.steps;

import static helpers.config.Endpoints.ACCOUNT_AUTHORIZED_ENDPOINT;
import static helpers.config.Endpoints.ACCOUNT_LOGIN_ENDPOINT;
import static helpers.config.Endpoints.ACCOUNT_TOKEN_ENDPOINT;
import static helpers.config.Endpoints.ACCOUNT_USER_ENDPOINT;

import api.templates.AccountTemplates;
import helpers.config.Authorization;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import helpers.models.AccountNewUserRequestModel;
import java.util.HashMap;
import java.util.Map;

public class ApiUserSteps {

  private AccountNewUserRequestModel basicUser = new AccountTemplates().getBasicUser();

  private AccountNewUserRequestModel newUser;

  private String token, userId, newToken, newUserId;


  public Map<String, String> getTokenInfo(AccountNewUserRequestModel user) {
    Response response = getToken(user).getResponse();
    String token = response.path("token");
    String expires = response.path("expires");
    Map<String, String> tokenInfo = new HashMap<>();
    tokenInfo.put("token", token);
    tokenInfo.put("expires", expires);
    return tokenInfo;
  }

  public String getUserId(AccountNewUserRequestModel user) {
    return getLogin(user).getResponse().path("userId");
  }

  @Step("Предварительные шаги. Подготовить данные для получения информации о пользователе")
  public void preStepSuccessfulGetUser() {
    preStepGetToken();
    preStepUserId();
  }

  @Step("Предварительные шаги. Подготовить данные для удаления пользователя")
  public void preStepSuccessfulDeleteUser() {
    preStepNewUser();
    newGetToken();
    newGetLogin();
  }

  @Step("Предварительный шаг. Создать нового пользователя")
  public void preStepNewUser() {
    newUser = new AccountTemplates().getNewUser();
    new RestWrapper()
        .post(ACCOUNT_USER_ENDPOINT, newUser, null, Authorization.UNKNOWN);
  }

  @Step("Предварительный шаг. Генерация UserId для основного пользователя")
  public void preStepUserId() {
    userId = Singleton.getInstance().userId;
  }

  @Step("Предварительный шаг. Генерировать токен для основного пользователя")
  public void preStepGetToken() {
    token = Singleton.getInstance().token;
  }

  @Step("Предварительный шаг.Залогироваться для нового пользователя")
  public void newGetLogin() {
    newUserId = getLogin(newUser).getResponse().path("userId");
  }

  @Step("Предварительный шаг. Генерировать токен для нового пользователя")
  public void newGetToken() {
    newToken = getToken(newUser).getResponse().path("token");
  }

  @Step("Залогироваться")
  public RestWrapper getLogin(AccountNewUserRequestModel user) {
    return new RestWrapper()
        .post(ACCOUNT_LOGIN_ENDPOINT, user, null, Authorization.UNKNOWN);
  }

  @Step("Создать нового пользователя")
  public void newUser(AccountNewUserRequestModel user) {
    new RestWrapper()
        .post(ACCOUNT_USER_ENDPOINT, user, null, Authorization.UNKNOWN);
  }

  @Step("Генерировать токен")
  public RestWrapper getToken(AccountNewUserRequestModel user) {
    return new RestWrapper()
        .post(ACCOUNT_TOKEN_ENDPOINT, user, null, Authorization.UNKNOWN);
  }

  @Step("Создать нового пользователя")
  public RestWrapper newUser() {
    AccountNewUserRequestModel newUser = new AccountTemplates().getNewUser();
    return new RestWrapper()
        .post(ACCOUNT_USER_ENDPOINT, newUser, null, Authorization.UNKNOWN);
  }

  @Step("Авторизовать пользователя")
  public RestWrapper authorized() {
    return new RestWrapper()
        .post(ACCOUNT_AUTHORIZED_ENDPOINT, basicUser, token, Authorization.OAUTH_2);
  }

  @Step("Удалить пользователя")
  public RestWrapper deleteUser() {
    return new RestWrapper()
        .delete(ACCOUNT_USER_ENDPOINT + "/" + newUserId, new HashMap<>(), "", newToken,
            Authorization.BEARER);
  }

  @Step("Получить информацию о пользователе")
  public RestWrapper getUser() {
    return new RestWrapper()
        .get(ACCOUNT_USER_ENDPOINT + "/" + userId, new HashMap<>(), token, Authorization.BEARER);
  }

  @Step("Генерировать токен")
  public RestWrapper getToken() {
    return new RestWrapper()
        .post(ACCOUNT_TOKEN_ENDPOINT, newUser, null, Authorization.UNKNOWN);
  }

}
