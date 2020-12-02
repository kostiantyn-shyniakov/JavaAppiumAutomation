package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Platform {
    private static final String AppiumURL = "http://127.0.0.1:4723/wd/hub";
    String platform = System.getenv("PLATFORM");

    private static Platform instance;

    private Platform(){}

    public static Platform getInstance(){
        if (instance==null) instance = new Platform();
        return instance;
    }

    private DesiredCapabilities getAndroidDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidDeviceName");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("orientation","PORTRAIT");
        capabilities.setCapability("app","C:\\Users\\kshyniakov\\Documents\\GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("deviceName","iPhone SE");
        capabilities.setCapability("platformVersion","13.4");
        capabilities.setCapability("app","/Users/webteam/Desktop/Wikipedia.app");
        return capabilities;
    }

    public boolean isAndroid(){
        return platform.equals("android");
    }

    public boolean isIOS(){
        return platform.equals("iOS");
    }

    public boolean isMobileWeb(){
        return platform.equals("mobile_web");
    }

    public RemoteWebDriver getDriver() throws Exception{
        URL URL = new URL(AppiumURL);
        if(Platform.getInstance().isIOS()){
            return new IOSDriver(URL, getIOSDesiredCapabilities());
        } else if (Platform.getInstance().isAndroid()){
            return new AndroidDriver(URL, getAndroidDesiredCapabilities());
        } else if (Platform.getInstance().isMobileWeb()){
            return new ChromeDriver();
        } else {
            throw new Exception("Cannot get run platform from env veriable");
        }
    }
}
