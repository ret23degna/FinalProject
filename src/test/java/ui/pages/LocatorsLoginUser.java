package ui.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class LocatorsLoginUser {

  public SelenideElement userName() {
    return $("#userName").as("Логин пользователя");
  }

  public SelenideElement password() {
    return $("#password").as("Пароль");
  }

  public SelenideElement buttonLoginUser() {
    return $("#login").as("Кнопка авторизации");
  }

  public SelenideElement invalidLabel() {
    return $("#name").as("Текст ошибки авторизации");
  }

  public SelenideElement loginLabel() {
    return $("#userForm").$("h5").as("Текст авторизации пользователя");
  }

}
