package ui.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class LocatorsRegisterUser {

  public SelenideElement firstName() {
    return $("#firstname").as("Firstname");
  }

  public SelenideElement lastName() {
    return $("#lastname").as("Lastname");
  }

  public SelenideElement userName() {
    return $("#userName").as("Username");
  }

  public SelenideElement password() {
    return $("#password").as("Password");
  }

  public SelenideElement labelErrorCaptcha() {
    return $("#name").as("Label error captcha");
  }

  public SelenideElement buttonRegisterUser() {
    return $("#register").as("Button register user");
  }
}
