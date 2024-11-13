package api.steps;

import static api.specs.AccountAndBookSpec.requestSpecWithAuthorization;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import helpers.config.Authorization;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Map;
import org.hamcrest.Matcher;

public class RestWrapper {

  private Response response;

  public RestWrapper post(String endpoint, Map<String, String> parameter, Object body, String token,
      Authorization authToken) {
    this.response  = given()
        .spec(requestSpecWithAuthorization(token, authToken))
        .body(body)
        .queryParams(parameter)
        .post(endpoint);
    return this;
  }

  public RestWrapper get(String endpoint, Map<String, String> parameter, String token, Authorization authToken) {
    this.response  = given()
        .spec(requestSpecWithAuthorization(token, authToken))
        .queryParams(parameter)
        .get(endpoint);
    return this;
  }

  public RestWrapper delete(String endpoint, Map<String, String> parameter, Object body, String token,
      Authorization authToken) {
    this.response  = given()
        .spec(requestSpecWithAuthorization(token, authToken))
        .body(body)
        .queryParams(parameter)
        .delete(endpoint);
    return this;
  }

  public RestWrapper put(String endpoint, Map<String, String> parameter, Object body, String token,
      Authorization authToken) {
    this.response  = given()
        .spec(requestSpecWithAuthorization(token, authToken))
        .body(body)
        .queryParams(parameter)
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
