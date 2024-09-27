package helpers.logfilter;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Filter implements Filter {

  private static final Logger logger = LogManager.getLogger(Log4j2Filter.class);

  @Override
  public Response filter(FilterableRequestSpecification requestSpec,
      FilterableResponseSpecification responseSpec,
      FilterContext ctx) {

    logger.info(String.format("Request: %s", requestSpec.getMethod(), requestSpec.getURI()));
    logger.info(String.format("Request Headers: %s", requestSpec.getHeaders()));
    logger.info(String.format("Request Cookies: %s", requestSpec.getCookies()));
    try {
      logger.info(String.format("Request Body: %s", requestSpec.getBody().toString()));
    } catch (NullPointerException e) {
      logger.info(String.format("Request Body: %s", "NULL"));
    }

    Response response = ctx.next(requestSpec, responseSpec);
    logger.info(String.format("Response Status Code: %s", response.getStatusCode()));
    logger.info(String.format("Response Body: %s", response.getBody().asString()));

    return response;
  }
}