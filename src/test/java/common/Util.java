package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Util {

    static WebDriver webDriver;

    public static WebDriver getWebDriver() {
        if (webDriver != null) {
            System.out.println("Returning web driver " + webDriver);
            return webDriver;
        }
        ChromeOptions chromeOptions = new ChromeOptions();
        //     chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
        chromeOptions.addArguments("--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
     //   WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return webDriver;

    }

    public static void closeWebDriver() {
        System.out.println("Closing web driver");
        if (webDriver == null) {
            return;
        }
        webDriver.close();
        webDriver = null;
    }

}


