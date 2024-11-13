package api.steps;

import static api.specs.AccountAndBookSpec.requestSpecWithAuthorization;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import org.hamcrest.Matcher;

public class RestWrapper {

  private Response response;

  public RestWrapper post(String endpoint, Map<String, String> parameter, Object body, String token,
      Authoriz authToken) {
    RequestSpecification requestSpec = given()
        .spec(requestSpecWithAuthorization(token, authToken))
        .body(body);
    if (parameter != null && !parameter.isEmpty()) {
      for (Map.Entry<String, String> value : parameter.entrySet()) {
        requestSpec.queryParam(value.getKey(), value.getValue());
      }
    }
    this.response = requestSpec.post(endpoint);
    return this;
  }

  public RestWrapper get(String endpoint, Map<String, String> parameter, String token,
      Authoriz authToken) {
    RequestSpecification requestSpec = given()
        .spec(requestSpecWithAuthorization(token, authToken));
    if (parameter != null && !parameter.isEmpty()) {
      for (Map.Entry<String, String> value : parameter.entrySet()) {
        requestSpec.queryParam(value.getKey(), value.getValue());
      }
    }
    this.response = requestSpec.get(endpoint);
    return this;
  }

  public RestWrapper delete(String endpoint, Map<String, String> parameter, Object body,
      String token,
      Authoriz authToken) {
    RequestSpecification requestSpec = given()
        .spec(requestSpecWithAuthorization(token, authToken))
        .body(body);
    if (parameter != null && !parameter.isEmpty()) {
      for (Map.Entry<String, String> value : parameter.entrySet()) {
        requestSpec.queryParam(value.getKey(), value.getValue());
      }
    }
    this.response = requestSpec.delete(endpoint);
    return this;
  }

  public RestWrapper put(String endpoint, Map<String, String> parameter, Object body, String token,
      Authoriz authToken) {
    RequestSpecification requestSpec = given()
        .spec(requestSpecWithAuthorization(token, authToken))
        .body(body);
    if (parameter != null && !parameter.isEmpty()) {
      for (Map.Entry<String, String> value : parameter.entrySet()) {
        requestSpec.queryParam(value.getKey(), value.getValue());
      }
    }
    this.response = requestSpec.put(endpoint);
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
