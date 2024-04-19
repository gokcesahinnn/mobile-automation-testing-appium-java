package test.stepDefinations;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import utilities.DesiredCapabilitiesUtil;
import utilities.ThreadLocalDriver;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Hooks {
    private final DesiredCapabilitiesUtil desiredCapabilitiesUtil = new DesiredCapabilitiesUtil();

    @Before
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = desiredCapabilitiesUtil.getDesiredCapabilities();
        ThreadLocalDriver.setTLDriver(caps);
    }

    @After
    public synchronized void teardown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                takeScreenshot();
            }
        } finally {
            if (ThreadLocalDriver.getTLDriver() != null) {
                ThreadLocalDriver.getTLDriver().quit();
            }
        }
    }

    public static void takeScreenshot() {
        try {
            File scrFile = ((TakesScreenshot) ThreadLocalDriver.getTLDriver()).getScreenshotAs(OutputType.FILE);
            String currentDir = System.getProperty("user.dir") + "/build/screenshots/";
            FileUtils.copyFile(scrFile, new File(currentDir + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

