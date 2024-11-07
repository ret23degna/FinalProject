package api.specs;

import static helpers.utils.BaseTest.authoriz.oauth2;
import static helpers.utils.BaseTest.authoriz.unknown;
import static helpers.utils.BaseTest.config;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

import helpers.utils.BaseTest.authoriz;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import java.io.PrintStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;

public class AccountAndBookSpec {

  private static final Logger logger = LogManager.getLogger(AccountAndBookSpec.class);

  private static final PrintStream logStream = IoBuilder.forLogger(logger)
      .buildPrintStream();

  public static AllureRestAssured allureFilter = new AllureRestAssured()
      .setRequestTemplate("custom-http-request.ftl")
      .setResponseTemplate("custom-http-response.ftl");

  public static RequestSpecification requestSpec = with()
      .filter(allureFilter)
      .filter(new RequestLoggingFilter(LogDetail.ALL, false, logStream, true))
      .filter(new ResponseLoggingFilter(LogDetail.ALL, false, logStream))
      .contentType(JSON)
      .baseUri(config.getBaseUrl());

  public static RequestSpecification requestSpecWithAuthorization(String token, String[] parameter,
      authoriz flag) {
    if (parameter != null) {
      requestSpec.queryParam(parameter[0], parameter[1]);
    }
    if (!flag.equals(unknown)) {
      if (flag.equals(oauth2)) {
        requestSpec.auth().oauth2(token);
      } else {
        requestSpec.header("Authorization", "Bearer " + token);
      }
    }
    return requestSpec;
  }
}