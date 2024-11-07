package api.steps;

import static api.specs.AccountAndBookSpec.requestSpecWithAuthorization;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import helpers.utils.BaseTest.authoriz;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.hamcrest.Matcher;

public class RestWrapper {

  private Response response;

  public RestWrapper post(String endpoint, String[] parameter, Object body, String token,
      authoriz flag) {
    this.response = given()
        .spec(requestSpecWithAuthorization(token, parameter, flag))
        .body(body)
        .post(endpoint);
    return this;
  }

  public RestWrapper get(String endpoint, String[] parameter, String token, authoriz flag) {
    this.response = given()
        .spec(requestSpecWithAuthorization(token, parameter, flag))
        .get(endpoint);
    return this;
  }

  public RestWrapper delete(String endpoint, String[] parameter, Object body, String token,
      authoriz flag) {
    this.response = given()
        .spec(requestSpecWithAuthorization(token, parameter, flag))
        .body(body)
        .delete(endpoint);
    return this;
  }

  public RestWrapper put(String endpoint, String[] parameter, Object body, String token,
      authoriz flag) {
    this.response = given()
        .spec(requestSpecWithAuthorization(token, parameter, flag))
        .body(body)
        .put(endpoint);
    return this;
  }

  @Step("Проверить код ответа")
  public RestWrapper shouldHaveStatusCode(int statusCode) {
    response.then().assertThat().statusCode(statusCode);
    return this;
  }

  @Step("Проверить ожидаемые данные")
  public RestWrapper shouldHaveJsonPath(String jsonPath, Matcher matcher) {
    response.then().assertThat().body(jsonPath, matcher);
    return this;
  }

  public Response getResponse() {
    return response;
  }

  public RestWrapper responseBodyIsNoJson(String body) {
    response.then().assertThat().body(equalTo(body));
    return this;
  }

}
