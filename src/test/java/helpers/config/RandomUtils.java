package helpers.config;

import com.github.javafaker.Faker;

public class RandomUtils {

  Faker faker = new Faker();

  public String login() {
    return faker.name().firstName() + "_" + faker.name().lastName() + "_" + String.valueOf(
        faker.number().numberBetween(0, 10));
  }

  public String password() {
    return faker.food().ingredient() + "_" + faker.name().lastName() + "_" + String.valueOf(
        faker.number().numberBetween(0, 10)) + "!";
  }

  public String lastName() {
    return faker.name().firstName();
  }

  public String firstName() {
    return faker.name().lastName();
  }
}
