package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:browserstack.properties"})

public interface BrowserStackConfig extends Config {
    @Key("username")
    String getUsername();

    @Key("key")
    String getKey();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("app")
    String getApp();

    @Key("device")
    String getDevice();

    @Key("os_version")
    String getVersion();

    @Key("project")
    String getProject();

    @Key("build")
    String getBuild();

    @Key("name")
    String getName();
}