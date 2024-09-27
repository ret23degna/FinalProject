package ui.steps.profile;

import io.qameta.allure.Step;

public class ProfileSteps {

  Profile profile = new Profile();

  @Step("Открыть страницу профиля")
  public void openPageProfile() {
    profile.openPage();
  }

  @Step("Клик по кнопке выхода из профиля")
  public void clickButtonLogOutPageProfile() {
    profile.clickButtonLogOut();
  }

  @Step("Клик по кнопке удаления профиля")
  public void clickButtonDeleteAccountPageProfile() {
    profile.clickButtonDeleteAccount();
  }

  @Step("Клик по кнопке 'OK' в Popup")
  public void popupClickButtonOkPageProfile() {
    profile.popupAccountClickButtonOk();
  }

  @Step("Клик по кнопке 'Cancel' в Popup")
  public void popupClickButtonCancelPageProfile() {
    profile.popupClickButtonCancel();
  }

  @Step("Проверка выхода из профиля")
  public void checkLogOutPageProfile() {
    profile.checkLogOut();
  }

  @Step("Проверка удаления профиля")
  public void checkDeleteAccountPageProfile() {
    profile.checkDeleteAccount();
  }

  @Step("Проверка отмены")
  public void checkCancelPageProfile() {
    profile.checkCancel();
  }

  @Step("Клик по кнопке удаления всех книг")
  public void clickButtonDeleteAllBooksPageProfile() {
    profile.clickButtonDeleteAllBooks();
  }

  @Step("Проверка отмены удаления книги")
  public void checkDeleteBooksPageProfile() {
    profile.checkDeleteBooks();
  }

  @Step("Клик по кнопке удаления книги")
  public void clickButtonDeleteBookPageProfile() {
    profile.clickButtonDeleteBook();
  }
}
