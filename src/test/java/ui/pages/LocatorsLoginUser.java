package ui.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class LocatorsLoginUser {


  public SelenideElement userName() {
    return $("#userName").as("Username");
  }

  public SelenideElement password() {
    return $("#password").as("Password");
  }

  public SelenideElement buttonLoginUser() {
    return $("#login").as("Button login user");
  }

  public SelenideElement invalidLable() {
    return $("#name").as("Invalid lable");
  }

  public SelenideElement loginLable() {
    return $("#userForm").$("h5").as("Login lable");
  }

}
