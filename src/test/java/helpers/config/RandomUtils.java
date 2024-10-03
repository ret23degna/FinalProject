package helpers.config;

import com.github.javafaker.Faker;

public class RandomUtils {

  private static Faker faker = new Faker();

  public static String login() {
    return faker.name().firstName() + "_" + faker.name().lastName() + "_" +
        faker.number().numberBetween(0, 10);
  }

  public static String password() {
    return faker.food().ingredient() + "_" + faker.name().lastName() + "_" +
        faker.number().numberBetween(0, 10) + "!";
  }

  public static String lastName() {
    return faker.name().firstName();
  }

  public static String firstName() {
    return faker.name().lastName();
  }
}
