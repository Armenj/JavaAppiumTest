package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import sun.security.krb5.internal.crypto.Des;

import java.net.URL;

public class Platform {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/";

    private static Platform instance;
    private Platform(){}
    public static Platform getInstance(){
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public AppiumDriver getDriver() throws Exception{
        URL URL = new URL(APPIUM_URL);
        if(this.isAndroid()){
            return new AppiumDriver(URL, this.getAndroidDesireCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(URL, this.getIOSDesireCapabilities());
        }else {
            throw new Exception("Cannot detect type of the Driver. Platform value: " + this.getPlatformVar());
        }
    }

    public boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS(){
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getAndroidDesireCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
        capabilities.setCapability("appium:platformVersion", "8.0");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesireCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("appium:platformName", "IOS");
        capabilities.setCapability("appium:deviceName", "iPhone 13 Pro Max");
        capabilities.setCapability("appium:platformVersion", "17.4");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:app", "/Users/armenmelix/Desktop/JavaAppiumTest/apks/Wikipedia.app");
        capabilities.setCapability("language", "en");
        capabilities.setCapability("locale", "en_US");
        return capabilities;
    }

    private boolean isPlatform(String my_platfform){
        String platform = this.getPlatformVar();
        return my_platfform.equals(platform);
    }

    private String getPlatformVar(){
        return System.getenv("PLATFORM");
    }
}