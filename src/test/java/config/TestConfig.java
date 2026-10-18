package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:local.properties"
})

public interface TestConfig extends Config{
    @Key("browserName")
    @DefaultValue("CHROME")
    String getBrowserName();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("browserSize")
    String getBrowserSize();
}