package api.specs;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

import helpers.logfilter.Log4j2Filter;
import helpers.config.Configurations;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

public class AccountAndBookSpec {

  private static Configurations config = ConfigFactory.create(Configurations.class,
      System.getProperties());
  public static RequestSpecification requestSpec = with()
      .filter(new Log4j2Filter())
      .contentType(JSON)
      .baseUri(config.getBaseUrl());
}