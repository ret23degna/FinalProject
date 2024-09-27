package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import helpers.config.BaseTest;
import ui.steps.profile.ProfileSteps;

public class ProfileTests extends BaseTest {

  ProfileSteps steps = new ProfileSteps();

  @Test
  @DisplayName("Выход из профиля")
  void logOutBookStore() {
    steps.openPageProfile();
    steps.clickButtonLogOutPageProfile();
    steps.checkLogOutPageProfile();
  }

  @Test
  @DisplayName("Удаление аккаунта")
  void deleteAccountBookStore() {
    steps.openPageProfile();
    steps.clickButtonDeleteAccountPageProfile();
    steps.popupClickButtonOkPageProfile();
    steps.checkDeleteAccountPageProfile();
  }

  @Test
  @DisplayName("Отмена удаление аккаунта")
  void cancelDeleteAccountBookStore() {
    steps.openPageProfile();
    steps.clickButtonDeleteAccountPageProfile();
    steps.popupClickButtonCancelPageProfile();
    steps.checkCancelPageProfile();
  }

  @Test
  @DisplayName("удаление всех книг")
  void deleteAllBooks() {
    steps.openPageProfile();
    steps.clickButtonDeleteAllBooksPageProfile();
    steps.popupClickButtonOkPageProfile();
    steps.checkDeleteBooksPageProfile();
  }

  @Test
  @DisplayName("Отмена удаления всех книг")
  void cancelDeleteAllBooks() {
    steps.openPageProfile();
    steps.clickButtonDeleteAllBooksPageProfile();
    steps.popupClickButtonCancelPageProfile();
    steps.checkCancelPageProfile();
  }

  @Test
  @DisplayName("удаление одной книги")
  void deleteBook() {
    steps.openPageProfile();
    steps.clickButtonDeleteBookPageProfile();
    steps.popupClickButtonOkPageProfile();
    steps.checkDeleteBooksPageProfile();
  }

  @Test
  @DisplayName("Отмена удаление одной книги")
  void cancelDeleteBook() {
    steps.openPageProfile();
    steps.clickButtonDeleteBookPageProfile();
    steps.popupClickButtonOkPageProfile();
    steps.checkCancelPageProfile();
  }
}
