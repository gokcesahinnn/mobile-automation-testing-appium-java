package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class ThreadLocalDriver {
    private static final ThreadLocal<AppiumDriver<MobileElement>> tlDriver = new ThreadLocal<>();

    public static synchronized void setTLDriver(DesiredCapabilities capabilities) throws MalformedURLException {
        String platform = ConfReader.getPlatform();
        String appiumAddress = System.getProperty("appiumURL", ConfReader.get("appiumURL"));
        URL appiumURL = new URL(appiumAddress);
        if (platform.equalsIgnoreCase("Android")) {
            tlDriver.set(new AndroidDriver<>(appiumURL, capabilities));
        } else if (platform.equalsIgnoreCase("iOS")) {
            tlDriver.set(new IOSDriver<>(appiumURL, capabilities));
        } else {
            throw new IllegalArgumentException("Platform " + platform + " is not supported.");
        }
    }

    public static synchronized AppiumDriver<MobileElement> getTLDriver() {
        return tlDriver.get();
    }
}
