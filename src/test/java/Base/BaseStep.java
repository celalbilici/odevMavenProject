package Base;

import okhttp3.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.util.Random;


public class BaseStep  {

    protected String aramaBolumu = "//input[@placeholder='Keşfetmeye Bak']";
    protected String dizustuKatogorisi ="//p[text()='Dizüstü Bilgisayar (Laptop)']";
    protected String araButonu ="//button[span='BUL']";
    protected String appleMacbook = "//h2[@title='Apple Macbook Pro M1 Çip 8GB 256GB macOS 13 QHD Taşınabilir Bilgisayar Gümüş MYDA2TU/A']";
    protected String ikinciSayfa = "//li[@data-testid='pagination-list-item']//span[text()='2']";
    protected String sayfaElementi = "//li[@data-testid='pagination-list-item']//parent::a[@aria-current='true']";
    protected String sepeteEkle = "//div[@id='sp-addbasket-button']";
    protected String sepetim = "//a[text()='Sepete Git']";
    protected String urunAdeti = "//div[@class='gg-input gg-input-select ']";
    protected String ikiAdet = "//select[@class='amount']//option[@value='2']";
    protected String logo="(//a[@title='Alışveriş Sitesi'])[1]";
    protected String sepetIkon = "//div[@class='gekhq4-4 egoSnI'][text()='Sepetim']";
    protected String sepetSil = "//div[@data-cy='product-card-remove-button']";



    public WebDriver driver;
    public JavascriptExecutor jse = (JavascriptExecutor) driver;
    final FirefoxProfile firefoxProfile=new FirefoxProfile();




    public void openBrowser(String browserName) throws MalformedURLException {

        String browser = System.getProperty("BROWSER");



        switch (browserName) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                String projectPath =System.getProperty("user.dir");
                System.out.println(projectPath + "************"+"sistem yolu");
                System.setProperty("webdriver.gecko.driver","/Users/celalbilici/Desktop/geckodriver");
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);
                driver = new FirefoxDriver(capabilities);

                break;
            case "edge":
                System.setProperty("webdriver.edge.driver","/Users/celalbilici/Desktop/edgedriver_mac64/msedgedriver");
                System.setProperty("webdriver.edge.verboseLogging", "true");
                EdgeDriverService service = EdgeDriverService.createDefaultService();
                EdgeOptions options = new EdgeOptions();
               // EdgeDriver driver = new EdgeDriver(service, options);
                driver = new EdgeDriver();
                break;
//            Current releases of Opera are built on top of the Chromium engine, and WebDriver is now supported via the closed-source Opera Chromium Driver,
//            which can be added to your PATH or as a system property.
            case "opera":
                System.setProperty("webdriver.opera.driver", "/Users/celalbilici/Desktop/operadriver_mac64/operadriver");
                driver = new OperaDriver();
                break;
            case "safari":
                driver = new SafariDriver();
//                Run the following command from the terminal for the first time and type your password at the prompt to authorise WebDriver
//                /usr/bin/safaridriver -p 1337</
                break;
        }
        System.out.println("Opening Browser=>" + browserName);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    public enum Pather {

        id,

        className,

        name,

        xPath,

        cssSelector,

        linkText
    }

    public enum TimeOut {

        LOW(5),

        MIDDLE(10),

        HIGH(15),

        CUSTOM_MAX(60);

        private final int value;

        public int getValue() {

            return value;

        }
        // enum constructor - cannot be public or protected

        private TimeOut(int value) {

            this.value = value;

        }
    }

    public void geturl(String name) throws Exception {
        driver.get(name);
    }

    public void waitElement(WebElement element, TimeOut timeOut)  {

        try {

            WebDriverWait wait = new WebDriverWait(driver, timeOut.value);

            wait.until(ExpectedConditions.invisibilityOf(element));

        } catch (Exception ex) {

        }

    }



    public void findElementSendKeys(String path, Pather type, String text) {

        try {

            WebDriverWait wait = new WebDriverWait(driver, TimeOut.MIDDLE.value);

            switch (type) {

                case className:

                    wait.until(ExpectedConditions.elementToBeClickable(By.className(path))).sendKeys(text);

                    break;

                case id:

                    wait.until(ExpectedConditions.elementToBeClickable(By.id(path))).sendKeys(text);

                    break;

                case name:

                    wait.until(ExpectedConditions.elementToBeClickable(By.name(path))).sendKeys(text);

                    break;

                case xPath:

                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path))).sendKeys(text);

                    break;

                case cssSelector:

                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(path))).sendKeys(text);

                    break;

                case linkText:

                    wait.until(ExpectedConditions.elementToBeClickable(By.linkText(path))).sendKeys(text);

                    break;

                default:

                    new NotFoundException();

            }

        } catch (Exception ex) {

        }

    }

    public WebElement findElement(String path, Pather type, TimeOut timeOut) {


        try {

            WebDriverWait wait = new WebDriverWait(driver, timeOut.value);

            WebElement element = null;

            switch (type) {

                case className:

                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(path)));

                    break;

                case id:

                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(path)));

                    break;

                case name:

                    element = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.name(path)));

                    break;

                case xPath:

                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));

                    break;

                case cssSelector:

                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(path)));

                    break;

                case linkText:

                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(path)));

                    break;

                default:

                    new NotFoundException();

            }

            return element;

        } catch (Exception ex) {
            return null;

        }

    }


    public void PageScrolldown() {

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        jse.executeScript("window.scrollBy(0,300)", "");
    }

    public void PageScrollup() {

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        jse.executeScript("window.scrollBy(0,-600)", "");

    }

    public void DriverQuit() {

        driver.quit();
    }
    public void findElementClear(String path, Pather type) {

        try {

            WebDriverWait wait = new WebDriverWait(driver, TimeOut.MIDDLE.value);

            switch (type) {

                case className:

                    wait.until(ExpectedConditions.elementToBeClickable(By.id(path))).clear();
                    break;

                case id:

                    wait.until(ExpectedConditions.elementToBeClickable(By.id(path))).clear();

                    break;

                case name:

                    wait.until(ExpectedConditions.elementToBeClickable(By.name(path))).clear();

                    break;

                case xPath:

                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path))).clear();

                    break;

                case cssSelector:

                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(path))).clear();

                    break;

                case linkText:

                    wait.until(ExpectedConditions.elementToBeClickable(By.linkText(path))).clear();

                    break;

                default:

                    throw  new NotFoundException();

            }

        } catch (Exception ex) {
        }

    }


    public void DriverClose() {
        driver.close();
    }


    public void RestAPI(String link, String method, String jsonBody, Integer reponseValue) throws Throwable {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        switch (method) {
            case "GET":
                Request request = new Request.Builder()
                        .url(link)
                        .method(method, null)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response response = client.newCall(request).execute();
                Integer responseCode = response.code();
                String bodyValue = response.body().string();
                Assert.assertEquals(responseCode, reponseValue);
                System.out.println(bodyValue);
                System.out.println(responseCode);
                break;
            case "POST":
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, jsonBody);
                Request requestPost = new Request.Builder()
                        .url(link)
                        .method(method, body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response responsePost = client.newCall(requestPost).execute();
                String bodyValuePost = responsePost.body().string();
                Integer responseCodePost = responsePost.code();
                Assert.assertEquals(responseCodePost, reponseValue);
                System.out.println(bodyValuePost);
                System.out.println(responsePost);
                break;
            case "PUT":
                MediaType mediaTypePut = MediaType.parse("application/json");
                RequestBody bodyPut = RequestBody.create(mediaTypePut, jsonBody);
                Request requestPut = new Request.Builder()
                        .url(link)
                        .method(method, bodyPut)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response responsePut = client.newCall(requestPut).execute();
                String bodyValuePut = responsePut.body().string();
                Integer responseCodePut = responsePut.code();
                Assert.assertEquals(responseCodePut, reponseValue);
                System.out.println(bodyValuePut);
                System.out.println(responsePut);
                break;
        }
    }
}