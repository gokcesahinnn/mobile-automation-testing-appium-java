package utilities;

import java.io.FileInputStream;
import java.util.Properties;
import java.io.IOException;

public class ConfReader {
    public static String getPlatform() {
        return System.getProperty("platform", "Android");
    }

    public static String getEnvironment() {
        return System.getProperty("environment", "DigitalAI");
    }
    private static final String PATH = getEnvironment()+"Configs/" + getPlatform() + ".properties";
    private static Properties properties = new Properties();

    static {
        loadProperties(PATH);
    }

    private static void loadProperties(String path) {
        try (FileInputStream input = new FileInputStream("src/test/java/configs/" + path)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String keyName) {
        return properties.getProperty(keyName);
    }

    public static String get(String keyName, String path) {
        Properties tempProperties = new Properties();
        try (FileInputStream input = new FileInputStream("src/test/java/configs/" + path)) {
            tempProperties.load(input);
            return tempProperties.getProperty(keyName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}