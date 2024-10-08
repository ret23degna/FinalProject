package ui.tests;


import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import helpers.utils.BaseTest;
import ui.steps.ProfileSteps;

@Epic("UI")
public class ProfileTests extends BaseTest {

  ProfileSteps steps = new ProfileSteps();

  @Severity(SeverityLevel.CRITICAL)
  @Feature("ProfileTests")
  @Test
  @DisplayName("Выход из профиля")
  void logOutBookStore() {
    steps.loginBasicProfile();
    steps.openPageProfile();
    steps.clickButtonLogOutPageProfile();
    steps.checkLogOutPageProfile();
  }

  @Severity(SeverityLevel.NORMAL)
  @Feature("ProfileTests")
  @Test
  @DisplayName("Удаление аккаунта")
  void deleteAccountBookStore() {
    steps.loginNewProfile();
    steps.openPageProfile();
    steps.deleteAccountPageProfile();
    steps.checkDeleteAccountPageProfile();
  }

  @Severity(SeverityLevel.NORMAL)
  @Feature("ProfileTests")
  @Test
  @DisplayName("Отмена удаление аккаунта")
  void cancelDeleteAccountBookStore() {
    steps.loginBasicProfile();
    steps.openPageProfile();
    steps.cancelDeleteAccountPageProfile();
    steps.checkCancelPageProfile();
  }

  @Severity(SeverityLevel.NORMAL)
  @Feature("ProfileTests")
  @Test
  @DisplayName("Удаление всех книг")
  void deleteAllBooks() {
    steps.loginBasicProfile();
    steps.openPageProfile();
    steps.deleteAllBooksPageProfile();
    steps.checkDeleteBooksPageProfile();
  }

  @Severity(SeverityLevel.NORMAL)
  @Feature("ProfileTests")
  @Test
  @DisplayName("Отмена удаления всех книг")
  void cancelDeleteAllBooks() {
    steps.loginBasicProfile();
    steps.openPageProfile();
    steps.cancelDeleteAllBooksPageProfile();
    steps.checkCancelPageProfile();
  }

  @Severity(SeverityLevel.NORMAL)
  @Feature("ProfileTests")
  @Test
  @DisplayName("Удаление одной книги")
  void deleteBook() {
    steps.loginBasicProfile();
    steps.openPageProfile();

    steps.deleteBookPageProfile();

    steps.checkDeleteBooksPageProfile();

  }

  @Severity(SeverityLevel.NORMAL)
  @Feature("ProfileTests")
  @Test
  @DisplayName("Отмена удаление одной книги")
  void cancelDeleteBook() {
    steps.loginBasicProfile();
    steps.openPageProfile();
    steps.cancelDeleteBookPageProfile();
    steps.checkCancelPageProfile();
  }
}
