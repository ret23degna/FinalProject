package ui.steps;

import static api.steps.ApiSingleton.getCookie;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.config.Endpoints.PAGE_PROFILE;

import api.steps.ApiBookSteps;
import api.steps.ApiUserSteps;
import api.templates.AccountTemplates;
import com.codeborne.selenide.Selenide;
import helpers.models.AccountNewUserRequestModel;
import io.qameta.allure.Step;
import java.util.Map;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import ui.pages.LocatorsLoginUser;
import ui.pages.LocatorsProfile;

public class ProfileSteps {
  private AccountNewUserRequestModel user= new AccountTemplates().getBasicUser(), newUser;
  private ApiUserSteps apiUser;
  private ApiBookSteps apiBook;
  private LocatorsProfile locatorProfile;
  private LocatorsLoginUser locatorLoginUser;
  private Map<String, Cookie> cookie;

  public ProfileSteps() {
    this.apiUser = new ApiUserSteps();
    this.apiBook = new ApiBookSteps();
    this.locatorProfile = new LocatorsProfile();
    this.locatorLoginUser = new LocatorsLoginUser();
  }


  private void acceptAlert() {
    Alert alert = Selenide.switchTo().alert();
    alert.accept();
  }

  @Step("Предварительный шаг. Добавить книгу пользователю")
  public void addBook() {
    apiBook.postBooks(user);
  }
  @Step("Предварительный шаг. Создать нового пользователя")
  public void addNewUser() {
    newUser = new AccountTemplates().getNewUser();
    apiUser.newUser(newUser);
  }
  @Step("Предварительный шаг. Подготовить основного пользователя")
  public void prepareBasicUser() {
    apiBook.deleteBooks(user);
  }
  @Step("Предварительный шаг. Подготовить куки основного пользователя")
  public void prepareCookieBasicUser() {
   cookie = getCookie();
  }
  @Step("Предварительный шаг. Подготовить куки основного пользователя")
  public void prepareCookieNewUser() {
  cookie = apiUser.getCookie(newUser);
  }
  @Step("Авторизоваться")
  public void loginBasicProfile() {
    //UISinglton.getAuto();
    open("/images/Toolsqa.jpg");
    for (Map.Entry<String, Cookie> cookieEntry : cookie.entrySet()) {
      getWebDriver().manage().addCookie(cookieEntry.getValue());
    }
  }

  @Step("Авторизоваться")
  public void loginNewProfile() {
    open("/images/Toolsqa.jpg");
    for (Map.Entry<String, Cookie> cookieEntry : cookie.entrySet()) {
      getWebDriver().manage().addCookie(cookieEntry.getValue());
    }
  }

  @Step("Открыть страницу профиля")
  public void openPageProfile() {
    open(PAGE_PROFILE);
  }

  @Step("Кликнуть по кнопке выхода из профиля")
  public void clickButtonLogOutPageProfile() {
    locatorProfile.buttonLogOut().click();
  }

  @Step("Удалить профиль")
  public void deleteAccountPageProfile() {
    locatorProfile.buttonDeleteAccount().click();
    locatorProfile.popupButtonOk().click();
  }

  @Step("Отменить удаление профиля")
  public void cancelDeleteAccountPageProfile() {
    locatorProfile.buttonDeleteAccount().click();
    locatorProfile.popupButtonCancel().click();
  }


  @Step("Проверить выход из профиля")
  public void checkLogOutPageProfile() {
    locatorLoginUser.loginLabel().shouldHave(text("Login in Book Store"));
  }

  @Step("Проверить удаления профиля")
  public void checkDeleteAccountPageProfile() {
    acceptAlert();
    locatorLoginUser.loginLabel().shouldHave(text("Login in Book Store"));
  }

  @Step("Проверить отмену")
  public void checkCancelPageProfile() {
    locatorProfile.userNameLable().shouldHave(text(user.getUserName()));
  }

  @Step("Удалить все книги у пользователя")
  public void deleteAllBooksPageProfile() {
    locatorProfile.buttonDeleteAllBooks().click();
    locatorProfile.popupButtonOk().click();
  }

  @Step("Отменить удаление всех книги у пользователя")
  public void cancelDeleteAllBooksPageProfile() {
    locatorProfile.buttonDeleteAllBooks().click();
    locatorProfile.popupButtonCancel().click();
  }

  @Step("Проверить отмену удаления книги")
  public void checkDeleteBooksPageProfile() {
    acceptAlert();
    locatorProfile.book().shouldBe(hidden);
  }

  @Step("Удалить книгу у пользователя")
  public void deleteBookPageProfile() {
    locatorProfile.buttonDeleteBook().click();
    locatorProfile.popupButtonOk().click();
  }

  @Step("Отменить удалени книг у пользователя")
  public void cancelDeleteBookPageProfile() {
    locatorProfile.buttonDeleteBook().click();
    locatorProfile.popupButtonCancel().click();
  }
}
