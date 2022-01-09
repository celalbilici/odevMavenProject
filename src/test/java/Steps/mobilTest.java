package Steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import cucumber.api.java.tr.Diyelimki;
import cucumber.api.java.tr.Ve;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Scanner;

public class mobilTest {




        public AppiumDriver<MobileElement> driver;
        public WebDriverWait wait;


        @BeforeMethod
        @When("uygulamamizi cihazdan acalim")
        public void uygulamamiziCihazdanAcalim () throws MalformedURLException {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("deviceName", "Redmi");
            caps.setCapability("udid", "1198f2cb");
            caps.setCapability("platformName", "Android");
            caps.setCapability("platformVersion", "9.0");
            caps.setCapability("app", "/Users/celalbilici/Desktop/Bankkart_base.apk");
            caps.setCapability("appPackage", "com.bankkart.mobil");
            caps.setCapability("appWaitActivity", "com.veripark.ziraatwallet.screens.splash.activities.IntroActivity");
            caps.setCapability("appWaitDuration","20000");
            driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
            wait = new WebDriverWait(driver, 10);


        }
        @Test
    @Diyelimki("sahibinden com testi yapalim")
    public void sahibindenComTestiYapalim() throws Throwable {
        Thread.sleep(5000);
            MobileElement el1 = (MobileElement) driver.findElementById("com.bankkart.mobil:id/action_close");
            el1.click();
            MobileElement el2 = (MobileElement) driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Kampanyalar\"]/android.view.ViewGroup/android.widget.TextView");
            el2.click();
            MobileElement el3 = (MobileElement) driver.findElementById("com.bankkart.mobil:id/button_login");
            el3.click();
            MobileElement el4 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText");
            el4.sendKeys("44311003534");
            MobileElement el5 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText");
            el5.click();
            MobileElement el6 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText");
            el6.sendKeys("321682");
            MobileElement el7 = (MobileElement) driver.findElementById("com.bankkart.mobil:id/button_sign_in");
            el7.click();

        }

    @Ve("uygulama kaydirilir")
    public void uygulamaKaydirilir() throws Throwable {
        Thread.sleep(5000);
        new TouchAction(driver)
                .press(point(448,1581))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(448, 660))
                .release().perform();
        Thread.sleep(5000);

    }
}

