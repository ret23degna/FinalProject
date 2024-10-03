package api.steps;

import static api.specs.AccountAndBookSpec.allureFilter;
import static api.specs.AccountAndBookSpec.requestSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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
        .filter(allureFilter)
        .spec(requestSpec)
        .body(body)
        .auth().oauth2(token)
        .when()
        .post(endpoint)
        .then()
        .extract()
        .response();
    return this;
  }

  public RestWrapper get() {
    this.response = given()
        .filter(allureFilter)
        .spec(requestSpec)
        .header("Authorization", "Bearer " + token)
        .when()
        .get(endpoint)
        .then()
        .extract()
        .response();
    return this;
  }


  public RestWrapper delete() {
    this.response = given()
        .filter(allureFilter)
        .spec(requestSpec)
        .header("Authorization", "Bearer " + token)
        .body(body)
        .when()
        .delete(endpoint)
        .then()
        .extract()
        .response();
    return this;
  }

  public RestWrapper put() {
    this.response = given()
        .filter(allureFilter)
        .spec(requestSpec)
        .header("Authorization", "Bearer " + token)
        .body(body)
        .when()
        .put(endpoint)
        .then()
        .extract()
        .response();
    return this;
  }

  public RestWrapper shouldHaveStatusCode(int statusCode) {
    response.then().assertThat().statusCode(statusCode);
    return this;
  }

  public RestWrapper shouldHaveJsonPath(String jsonPath, Matcher matcher) {
    response.then().assertThat().body(jsonPath, matcher);
    return this;
  }

  public Response shouldGiveResponce() {
    return response;
  }

  public RestWrapper responseBodyIsNoJson(String body) {
    response.then().assertThat().body(equalTo(body));
    return this;
  }

  public RestWrapper setResponse(Response response) {
    this.response = response;
    return this;
  }
}
