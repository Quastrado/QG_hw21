package tests;

import com.codeborne.selenide.Configuration;
import config.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {

    private static final TestConfig testConfig = ConfigFactory.create(TestConfig.class, System.getProperties());

    @BeforeAll
    static void setupSelenideEnv(){
        Configuration.baseUrl = "https://5e14.ttg.club/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = testConfig.getBrowserName();
        Configuration.browserVersion = testConfig.getBrowserVersion();
        Configuration.browserSize = testConfig.getBrowserSize();
        Configuration.remote = testConfig.getRemoteUrl();
    }

}
