package helpers.config;

import org.aeonbits.owner.Config;

@Config.Sources("file:src/test/resources/config.properties")
public interface Configurations extends Config {

  @Key("page.load.strategy")
  @DefaultValue("eager")
  String getPageLoadStrategy();

  @Key("browser.size")
  @DefaultValue("1920x1080")
  String getBrowserSize();

  @Key("base.url")
  @DefaultValue("https://demoqa.com")
  String getBaseUrl();

  @Key("browser")
  @DefaultValue("chrome")
  String getBrowser();

  @Key("login")
  @DefaultValue("Kakabyaka_47")
  String geLogin();

  @Key("password")
  @DefaultValue("22!!QQww")
  String getPassword();

}
