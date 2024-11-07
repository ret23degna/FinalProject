package ui.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class LocatorsRegisterUser {

  public SelenideElement firstName() {
    return $("#firstname").as("Имя пользователя");
  }

  public SelenideElement lastName() {
    return $("#lastname").as("Фамилия пользователя");
  }

  public SelenideElement userName() {
    return $("#userName").as("Логин пользователя");
  }

  public SelenideElement password() {
    return $("#password").as("Пароль");
  }

  public SelenideElement labelErrorCaptcha() {
    return $("#name").as("Текст ошибки регистрации");
  }

  public SelenideElement buttonRegisterUser() {
    return $("#register").as("Кнопка Регистрации пользователя");
  }

}
