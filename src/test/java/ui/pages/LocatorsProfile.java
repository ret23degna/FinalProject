package ui.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class LocatorsProfile {

  public SelenideElement userNameLable() {
    return $("#userName-value").as("Наименование профиля");
  }

  public SelenideElement buttonLogOut() {
    return $("#books-wrapper").$("#submit").as("Кнопка выхода из профиля");
  }

  public SelenideElement buttonDeleteAccount() {
    return $x("//div[@class='text-center button']").$("#submit").as("Кнопка удаления аккаунта");
  }

  public SelenideElement buttonDeleteAllBooks() {
    return $x("//div[@class='text-right button di']").$("#submit").as("Кнопка удаления всех книг");
  }

  public SelenideElement buttonDeleteBook() {
    return $("#delete-record-undefined").as("Кнопка удаления  книги");
  }

  public SelenideElement popupButtonOk() {
    return $("#closeSmallModal-ok").as("Кнопка OK");
  }

  public SelenideElement popupButtonCancel() {
    return $("#closeSmallModal-cancel").as("Кнопка cancel");
  }

  public SelenideElement book() {
    return $x("//div[@class='rt-tr -odd']").as("Книга");
  }

}
