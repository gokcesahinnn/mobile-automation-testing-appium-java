package utilities;

import configs.LocalConfigs.ApkInstaller;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;


public class DesiredCapabilitiesUtil {
    public DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("udid", System.getProperty("udid", ConfReader.get("udid")));
        desiredCapabilities.setCapability("platformVersion", System.getProperty("platformVersion", ConfReader.get("platformVersion")));
        desiredCapabilities.setCapability("platformName", System.getProperty("platformName", ConfReader.get("platformName")));
        desiredCapabilities.setCapability("appBuildVersion", System.getProperty("appBuildVersion", ConfReader.get("appBuildVersion")));
        desiredCapabilities.setCapability("deviceCleanup", System.getProperty("deviceCleanup", ConfReader.get("deviceCleanup")));
        desiredCapabilities.setCapability("newCommandTimeout", System.getProperty("newCommandTimeout", ConfReader.get("newCommandTimeout")));
        desiredCapabilities.setCapability("testName", System.getProperty("testName", ConfReader.get("testName")));
        desiredCapabilities.setCapability("accessKey", System.getProperty("accessKey", ConfReader.get("accessKey")));

        if (ConfReader.getPlatform().equals("Android")) {
            desiredCapabilities.setCapability("appActivity", System.getProperty("appActivity", ConfReader.get("appActivity")));
            desiredCapabilities.setCapability("appPackage", System.getProperty("appPackage", ConfReader.get("appPackage")));
        } else {
            desiredCapabilities.setCapability("bundleId", System.getProperty("bundleId", ConfReader.get("bundleId")));
        }

        if (ConfReader.getEnvironment().equals("Local")) {
            String udid = desiredCapabilities.getCapability("udid").toString();
            if (!isEmulatorConnected(udid)) {
                String connectedEmulator = getFirstConnectedEmulator();
                if (connectedEmulator != null) {
                    desiredCapabilities.setCapability("udid", connectedEmulator);
                }
            }
        }
         try {
            String apkFileName = "com.example.pocandroidapp_.MainActivity_ver_16.apk";
            String apkPath = Paths.get("src/test/java/apps", apkFileName).toString();
            ApkInstaller.downloadAndInstallApkIfNeeded(apkPath);
        } catch (IOException | InterruptedException e) {
            System.err.println("An error occurred while installing the APK: " + e.getMessage());
            e.printStackTrace();
        }

        desiredCapabilities.setCapability("app", System.getProperty("app", ConfReader.get("app")));


        return desiredCapabilities;
    }

    private static boolean isEmulatorConnected(String udid) {
        try {
            Process process = Runtime.getRuntime().exec("adb devices");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(udid)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getFirstConnectedEmulator() {
        try {
            Process process = Runtime.getRuntime().exec("adb devices");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    return line.split("\\s+")[0];
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}