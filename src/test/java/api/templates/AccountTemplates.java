package api.templates;

import static helpers.config.Endpoints.ACCOUNT_TOKEN_ENDPOINT;
import static helpers.config.RandomUtils.login;
import static helpers.config.RandomUtils.password;
import static helpers.config.User.login;
import static helpers.config.User.password;

import api.steps.RestWrapper;
import helpers.models.AccountGenerateTokenModel;
import helpers.models.AccountNewUserRequestModel;
import io.restassured.response.Response;

public class AccountTemplates {

  public static AccountNewUserRequestModel getNewUser() {
    AccountNewUserRequestModel loginPassword = new AccountNewUserRequestModel();
    loginPassword.setUserName(login());
    loginPassword.setPassword(password());
    return loginPassword;
  }

  public static AccountNewUserRequestModel getBasicUser() {
    AccountNewUserRequestModel loginPassword = new AccountNewUserRequestModel();
    loginPassword.setUserName(login);
    loginPassword.setPassword(password);
    return loginPassword;
  }

  public static AccountGenerateTokenModel getGenerateToken(AccountNewUserRequestModel user) {
    Response response = new RestWrapper(ACCOUNT_TOKEN_ENDPOINT, user)
        .post()
        .shouldGiveResponce();
    return response.as(AccountGenerateTokenModel.class);
  }
}
