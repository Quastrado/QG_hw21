package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserStackConfig;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import javax.annotation.Nonnull;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BrowserStackDriver implements WebDriverProvider {

    protected static BrowserStackConfig browserStackConfig = ConfigFactory.create(BrowserStackConfig.class,
            System.getProperties());

    @NonNull
    @Override
    public WebDriver createDriver(@NonNull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        // 1. Базовые мобильные параметры (W3C стандарт)
        caps.setCapability("platformName", "android"); // или "ios", в зависимости от платформы
        caps.setCapability("appium:deviceName", browserStackConfig.getDevice());
        caps.setCapability("appium:platformVersion", browserStackConfig.getVersion());
        caps.setCapability("appium:app", browserStackConfig.getApp());

        // 2. Учетные данные и метаданные упаковываем строго внутрь bstack:options
        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("userName", browserStackConfig.getUsername());
        browserstackOptions.put("accessKey", browserStackConfig.getKey());
        browserstackOptions.put("projectName", browserStackConfig.getProject());
        browserstackOptions.put("buildName", browserStackConfig.getBuild());
        browserstackOptions.put("sessionName", browserStackConfig.getName());

        // Принудительно задаем версию Appium для хаба BrowserStack
        browserstackOptions.put("appiumVersion", "2.6.0");

        // Записываем словарь опций в capabilities
        caps.setCapability("bstack:options", browserstackOptions);

        try{
            URL remoteUrl = new URL("https://hub.browserstack.com/wd/hub");

            System.out.println("--- ПОПЫТКА ЗАПУСКА ДРАЙВЕРА НА BROWSERSTACK ---");
            AndroidDriver driver = new AndroidDriver(remoteUrl, caps);
            System.out.println("--- ДРАЙВЕР УСПЕШНО СОЗДАН ---");

            return driver;
        } catch (Throwable t) {
            // Это напечатает реальную причину (например, неверный токен, имя устройства или bstack:options)
            System.err.println("!!! КРИТИЧЕСКАЯ ОШИБКА СОЗДАНИЯ ДРАЙВЕРА BROWSERSTACK !!!");
            t.printStackTrace();
            throw new RuntimeException(t);
        }
    }
}
