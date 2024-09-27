package ui.steps.loginUser;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

import helpers.config.Endpoints;
import helpers.models.AccountNewUserRequestModel;
import ui.pages.LocatorsLoginUser;
import ui.pages.LocatorsProfile;
import api.steps.ApiUserSteps;
import helpers.config.RandomUtils;

public class LoginUser {

  Endpoints page = new Endpoints();

  private ApiUserSteps api = new ApiUserSteps();
  private LocatorsLoginUser locatorLoginUser = new LocatorsLoginUser();
  private LocatorsProfile locatorProfile = new LocatorsProfile();
  private RandomUtils random = new RandomUtils();

  private AccountNewUserRequestModel user;

  public LoginUser openPage() {
    open(page.pageLoginUser);
    return this;
  }

  public void setUserName(String userName) {
    locatorLoginUser.userName().setValue(userName);
  }

  public void setPassword(String password) {
    locatorLoginUser.password().setValue(password);
  }

  public LoginUser setData() {
    user = api.setNewUser();
    setUserName(user.getUserName());
    setPassword(user.getPassword());
    return this;
  }

  public LoginUser setDataRandom() {
    setUserName(random.login());
    setPassword(random.password());
    return this;
  }

  public LoginUser clickButtonLogin() {
    locatorLoginUser.buttonLoginUser().click();
    return this;
  }

  public LoginUser checkAuthorized() {
    locatorProfile.userNameLable().shouldHave(text(user.getUserName()));
    return this;
  }

  public LoginUser checkNoAuthorized() {
    locatorLoginUser.invalidLable().shouldHave(text("Invalid username or password!"));
    return this;
  }

}
