package helpers.config;

import org.aeonbits.owner.Config;

@Config.Sources("file:src/test/resources/config.properties")
public interface Configurations extends Config {

  @Key("PageLoadStrategy")
  @DefaultValue("eager")
  String getPageLoadStrategy();

  @Key("BrowserSize")
  @DefaultValue("1920x1080")
  String getBrowserSize();

  @Key("BaseUrl")
  @DefaultValue("https://demoqa.com")
  String getBaseUrl();

  @Key("Browser")
  @DefaultValue("chrome")
  String getBrowser();
}
