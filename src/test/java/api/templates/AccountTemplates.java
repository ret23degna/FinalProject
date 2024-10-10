package api.templates;

import static helpers.config.RandomUtils.login;
import static helpers.config.RandomUtils.password;
import static helpers.utils.BaseTest.config;

import helpers.models.AccountNewUserRequestModel;


public class AccountTemplates {

  public AccountNewUserRequestModel getNewUser() {
    AccountNewUserRequestModel loginPassword = new AccountNewUserRequestModel();
    loginPassword.setUserName(login());
    loginPassword.setPassword(password());
    return loginPassword;
  }

  public AccountNewUserRequestModel getBasicUser() {
    AccountNewUserRequestModel loginPassword = new AccountNewUserRequestModel();
    loginPassword.setUserName(config.geLogin());
    loginPassword.setPassword(config.getPassword());
    return loginPassword;
  }

}
