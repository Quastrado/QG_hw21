package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserStackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackDriver implements WebDriverProvider {

    protected static BrowserStackConfig browserStackConfig = ConfigFactory.create(BrowserStackConfig.class,
            System.getProperties());

    @NonNull
    @Override
    public WebDriver createDriver(@NonNull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("browserstack.user", browserStackConfig.getUsername());
        caps.setCapability("browserstack.key", browserStackConfig.getKey());
        caps.setCapability("app", browserStackConfig.getApp());
        caps.setCapability("device", browserStackConfig.getDevice());
        caps.setCapability("os_version", browserStackConfig.getVersion());
        caps.setCapability("project", browserStackConfig.getProject());
        caps.setCapability("build", browserStackConfig.getBuild());
        caps.setCapability("name", browserStackConfig.getName());

        try{
            return new RemoteWebDriver(
                    new URL(browserStackConfig.getRemoteUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
