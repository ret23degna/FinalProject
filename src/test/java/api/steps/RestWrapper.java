package api.steps;

import static io.restassured.RestAssured.given;
import static api.specs.AccountAndBookSpec.requestSpec;

import io.restassured.response.Response;
import org.hamcrest.Matcher;

public class RestWrapper {

  private Response response;

  public RestWrapper post(String endpoint, Object body) {
    this.response = given()
        .spec(requestSpec)
        .body(body)
        .when()
        .post(endpoint)
        .then()
        .extract()
        .response();
    return this;
  }

  public RestWrapper post(String endpoint, Object body, String token) {
    this.response = given()
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

  public RestWrapper get(String endpoint, String token) {
    this.response = given()
        .spec(requestSpec)
        .header("Authorization", "Bearer " + token)
        .when()
        .get(endpoint)
        .then()
        .extract()
        .response();
    return this;
  }

  public RestWrapper get(String endpoint) {
    this.response = given()
        .spec(requestSpec)
        .when()
        .get(endpoint)
        .then()
        .extract()
        .response();
    return this;
  }

  public RestWrapper delete(String endpoint, String token) {
    this.response = given()
        .spec(requestSpec)
        .header("Authorization", "Bearer " + token)
        .when()
        .delete(endpoint)
        .then()
        .extract()
        .response();
    return this;
  }

  public RestWrapper delete(String endpoint, Object body, String token) {
    this.response = given()
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

  public RestWrapper put(String endpoint, Object body, String token) {
    this.response = given()
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
}
