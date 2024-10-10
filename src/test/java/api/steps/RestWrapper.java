package api.steps;

import static api.specs.AccountAndBookSpec.requestSpec;
import static api.specs.AccountAndBookSpec.requestSpecWithAuth;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.hamcrest.Matcher;

public class RestWrapper {

  private Response response;
  private String endpoint;
  private Object body;
  private String token;

  public RestWrapper(String endpoint, Object body) {
    this.endpoint = endpoint;
    this.body = body;
    this.token = "";
  }

  public RestWrapper(String endpoint, Object body, String token) {
    this.endpoint = endpoint;
    this.body = body;
    this.token = token;
  }

  public RestWrapper(String endpoint, String token) {
    this.endpoint = endpoint;
    this.body = "";
    this.token = token;

  }

  public RestWrapper(String endpoint) {
    this.endpoint = endpoint;
    this.body = "";
    this.token = "";
  }

  public RestWrapper() {
    this.endpoint = "";
    this.body = "";
    this.token = "";
  }

  public RestWrapper post() {
    this.response = given()
        .spec(requestSpecWithAuth(token))
        .body(body)
        .post(endpoint);
    return this;
  }

  public RestWrapper get() {
    this.response = given()
        .spec(requestSpec)
        .header("Authorization", "Bearer " + token)
        .get(endpoint);
    return this;
  }


  public RestWrapper delete() {
    this.response = given()
        .spec(requestSpec)
        .header("Authorization", "Bearer " + token)
        .body(body)
        .delete(endpoint);
    return this;
  }

  public RestWrapper put() {
    this.response = given()
        .spec(requestSpec)
        .header("Authorization", "Bearer " + token)
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
