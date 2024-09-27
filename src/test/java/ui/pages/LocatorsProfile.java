package ui.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class LocatorsProfile {

  public SelenideElement userNameLable() {
    return $("#userName-value").as("User name lable");
  }

  public SelenideElement buttonLogOut() {
    return $("#books-wrapper").$("#submit").as("Button logout");
  }

  public SelenideElement buttonDeleteAccount() {
    return $x("//div[@class='text-center button']").$("#submit").as("Button delete account");
  }

  public SelenideElement buttonDeleteAllBooks() {
    return $x("//div[@class='text-right button di']").$("#submit").as("Button delete all books");
  }

  public SelenideElement buttonDeleteBook() {
    return $("#delete-record-undefined").as("Button delete book");
  }

  public SelenideElement popupButtonOk() {
    return $("#closeSmallModal-ok").as("Popup button OK");
  }

  public SelenideElement popupButtonCancel() {
    return $("#closeSmallModal-cancel").as("Popup button cancel");
  }

  public SelenideElement book() {
    return $x("//div[@class='rt-tr -odd']").as("Book");
  }


}
