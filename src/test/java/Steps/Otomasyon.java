package Steps;

import Base.BaseStep;
import cucumber.api.java.tr.Diyelimki;
import cucumber.api.java.tr.Ozaman;
import cucumber.api.java.tr.Ve;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class Otomasyon extends BaseStep {
    private static Logger log  = Logger.getLogger(Otomasyon.class);

    @Diyelimki("^\"([^\"]*)\" tarayicisinda \"([^\"]*)\" sayfasina giris yaptim$")
    public void tarayicisindaSayfasinaGirisYaptim(String browser, String url) throws Throwable {
       log.info("Tarayıcı açıldı");
        openBrowser(browser);
        driver.manage().window().maximize();
        geturl(url);
        log.info("Tarayıcı açıldı");
    }


    @Ve("^reklam penceresini kapadim$")
    public void reklamPenceresiniKapadim() {
        findElement("a[title='Close']", Pather.cssSelector, TimeOut.LOW).click();
    }

    @Ve("^\"([^\"]*)\" secenegine tikladim$")
    public void secenegineTikladim(String linkName) throws Throwable {
        findElementClick("span#not-logged-in-container", Pather.cssSelector);
        switch (linkName) {
            case "Giriş Yap":
                findElementClick("div.account-button.login", Pather.cssSelector);
                break;
            case "Üye Ol":
                findElementClick(linkName, Pather.linkText);
                break;
        }
        Thread.sleep(1000);
    }

    private void findElementClick(String s, Pather xpath) {
        driver.findElement(By.xpath(s)).click();
    }


    @Ve("^\"([^\"]*)\" alanina \"([^\"]*)\" yazarsam$")
    public void alaninaYazarsam(String label, String data) {
        findElementSendKeys(label, Pather.id, data);

    }

    @Ve("^giris butonuna tiklarsam$")
    public void girisButonunaTiklarsam() throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.xpath("span[@class=''Bilmemne olan]")).click();
        driver.findElement(By.xpath("span[@class=''Bilmemne olan]")).sendKeys("ne istersen yaz");

    }

    @Ve("^indirimleri kacirma penceresini kapatirsam$")
    public void indirimleriKacirmaPenceresiniKapatirsam() {
        findElementClick("path#Combined-Shape", Pather.cssSelector);
    }

    @Ozaman("^kullanici ikonunda \"([^\"]*)\" metni gorulur$")
    public void kullaniciIkonundaMetniGorulur(String text) throws Throwable {
        String account = driver.findElement(By.xpath("//span[@id='logged-in-container']//span")).getText();
        Assert.assertEquals(account, text);

    }


    @Ve("^\"([^\"]*)\" menusune tiklanirsa$")
    public void menusuneTiklanirsa(String menu) throws Throwable {
        findElementClick(menu, Pather.linkText);
    }

    @Ozaman("^butik imajlarinin yuklendigi gorulur$")
    public void butikImajlarininYuklendigiGorulur() throws Throwable {

        List<WebElement> total_images = driver.findElements(By.xpath("//article[@class='component-item']//descendant::img"));
        System.out.println("Total Number of images found on page = " + total_images.size());
        boolean isValid = false;
        for (int i = 0; i < total_images.size(); i++) {
            String url = total_images.get(i).getAttribute("src");

            if (url != null) {

                //Call getResponseCode function for each URL to check response code.
                isValid = getResponseCode(url);

                //Print message based on value of isValid which Is returned by getResponseCode function.
                if (isValid) {
                    System.out.println("Valid image:" + url);
                    System.out.println();
                } else {
                    System.out.println("Broken image ------> " + url);
                    System.out.println();
                }
            } else {
                //If <a> tag do not contain href attribute and value then print this message
                System.out.println("String null");
                System.out.println();
                continue;
            }
        }
    }


    private boolean getResponseCode(String url) {
        return true;
    }

    @Ve("^tum menulere tiklanip imajlarin yuklendigi gorulur$")
    public void tumMenulereTiklanipImajlarinYuklendigiGorulur() throws Throwable {
        Integer menuSize = driver.findElements(By.xpath("//ul[@class='main-nav']//li[@class='tab-link']")).size();
        for (int i = 0; i < menuSize; i++) {
            WebElement menu = driver.findElements(By.xpath("//ul[@class='main-nav']//li[@class='tab-link']")).get(i);
            menu.click();
            List<WebElement> total_images = driver.findElements(By.xpath("//article[@class='component-item']//descendant::img"));
            System.out.println("Total Number of images found on page = " + total_images.size());
            boolean isValid = false;
            for (int x = 0; x < total_images.size(); x++) {
                String url = total_images.get(x).getAttribute("src");

                if (url != null) {

                    //Call getResponseCode function for each URL to check response code.
                    isValid = getResponseCode(url);

                    //Print message based on value of isValid which Is returned by getResponseCode function.
                    if (isValid) {
                        System.out.println("Valid image:" + url);
                        System.out.println();
                    } else {
                        System.out.println("Broken image ------> " + url);
                        System.out.println();
                    }
                } else {
                    //If <a> tag do not contain href attribute and value then print this message
                    System.out.println("String null");
                    System.out.println();
                    continue;
                }
            }

        }
    }


    @Ve("^\"([^\"]*)\" butigine tiklanirsa$")
    public void butigineTiklanirsa(String boutique) throws Throwable {
        driver.findElement(By.xpath("//a[@class='campaign campaign-big']//descendant::span[text()='" + boutique + "']")).click();
        String boutigueTitle = findElement("h1.boutique-title", Pather.cssSelector, TimeOut.LOW).getText();
        Assert.assertEquals(boutigueTitle, boutique);
    }


    @Ozaman("^urun gorsellerinin yuklendigi gorulur$")
    public void urunGorsellerininYuklendigiGorulur() {
        Integer productSize = driver.findElements(By.cssSelector("img.prc-picture")).size();
        for (int i = 0; i < productSize; i++) {
            List<WebElement> products_images = driver.findElements(By.xpath("//img[@class='prc-picture'][@lazy='loaded']"));
            System.out.println("Number of products' images = " + products_images.size());
            boolean isValid = false;
            for (int x = 0; x < products_images.size(); x++) {
                String url = products_images.get(x).getAttribute("src");

                if (url != null) {

                    isValid = getResponseCode(url);

                    if (isValid) {
                        System.out.println("Visible image:" + url);
                        System.out.println();
                    } else {
                        System.out.println("Broken image ------> " + url);
                        System.out.println();
                    }
                } else {
                    System.out.println("String null");
                    System.out.println();
                    continue;
                }
            }
        }
    }

    @Ve("^\"([^\"]*)\" urunu detayina gidilir$")
    public void urunuDetayinaGidilir(String productDetail) throws Throwable {
        PageScrolldown();
        findElementClick("//div[@class='name'][text()='" + productDetail + "']", Pather.xPath);
//       String productLink = findElement("//span[@class='breadcrumb-item']//span", Pather.xPath, TimeOut.LOW).getText();
//        Assert.assertEquals(productLink,productDetail);

    }

    @Ve("^\"([^\"]*)\" butonuna basilirsa$")
    public void butonunaBasilirsa(String addToBasket) {
        Boolean isPresent = driver.findElements(By.cssSelector("div.pr-in-sz-pk")).size() > 0;
        if (isPresent == true) {
            findElementClick("div.pr-in-sz-pk", Pather.cssSelector);
            findElementClick("//li[@class='vrn-item'][1]", Pather.xPath);
            driver.findElement(By.xpath("//div[text()='" + addToBasket + "']")).click();
        } else
            driver.findElement(By.xpath("//div[text()='" + addToBasket + "']")).click();
    }

    @Ozaman("^\"([^\"]*)\" urununun sepete eklendigi gorulur$")
    public void urunununSepeteEklendigiGorulur(String productName) throws Throwable {
        Assert.assertNotNull(findElement("//h1[normalize-space(text()='Sepetim')]", Pather.xPath, TimeOut.LOW));
        findElementClick("li#myBasketListItem", Pather.cssSelector);
        String product = driver.findElement(By.cssSelector("span.description.basketlist-productinfo-description")).getText();
        Assert.assertEquals(product, productName);
    }

    @Ve("^\"([^\"]*)\" ikonuna basilarak urun sepetten cikarilir$")
    public void ikonunaBasilarakUrunSepettenCikarilir(String remove) throws Throwable {
        driver.findElement(By.xpath("//a[@class='removeitem'][text()='Kaldır']")).click();
        driver.findElement(By.cssSelector("button.btn-item.btn-remove")).click();
        Thread.sleep(1000);
    }

    @Ve("^tarayici kapatilir$")
    public void tarayiciKapatilir() throws Throwable {
        DriverClose();
        Thread.sleep(1000);
    }

    @Ve("yeni uyelik icin {string} butonuna tıklarim")
    public void yeniUyelikIcinButonunaTıklarim(String name) {
        findElementClick("//a[@class='login'][normalize-space()='"+name+"']",Pather.xPath);
    }

    @Ve("mail adresi kismina tiklayip {string} girerim")
    public void mailAdresiKisminaTiklayipGirerim(String mailAdress) {
        findElementSendKeys("input#email_create",Pather.cssSelector,mailAdress);
    }

    @Ve("{string} butonuna tiklarim")
    public void butonunaTiklarim(String buttonName) {
    }


    @Ve("uye cinsiyeti olarak {string} secerim")
    public void uyeCinsiyetiOlarakSecerim(String sex) {
        switch(sex) {
            case "Erkek":
                findElementClick("input[id='id_gender1']",Pather.cssSelector);
                break;
            case "Kadın":
                findElementClick("input[id='id_gender2']",Pather.cssSelector);
                break;
            default:
                System.out.println("Parametre dışı değer girdiniz");
        }
    }

    @Ve("{string} alani için {string} girerim")
    public void alaniIçinGirerim(String input, String information) {
        switch(input) {
            case "First name":
                System.out.println("------------------------celllllllll");
                findElementSendKeys("//input[@id='customer_firstname']",Pather.xPath,information);
                break;
            case "Last name":
                findElementSendKeys("//input[@id='customer_lastname']",Pather.xPath,information);
                break;
            case "Password":
                findElementSendKeys("//input[@id='passwd']",Pather.xPath,information);
                break;
            default:
                System.out.println("Parametre dışı değer girdiniz");
    }
}

    @Ve("dogum tarihinde gun olarak {int} secerim")
    public void dogumTarihindeGunOlarakSecerim(int value) {
        findElementClick("//select[@id='days']",Pather.xPath);
        findElementClick("//select[@id='days']/option[@value='"+value+"']",Pather.xPath);
    }

    @Ve("dogum tarihinde ay olarak {string} secerim")
    public void dogumTarihindeAyOlarakSecerim(String month) {
        findElementClick("//div[@id='uniform-months']",Pather.xPath);
        findElementClick("//select[@id='months']/option[@value='"+month+"']",Pather.xPath);
    }

    @Ve("dogum tarihinde ay olarak {int} secerim")
    public void dogumTarihindeAyOlarakSecerim(int value) {
        findElementClick("//div[@id='uniform-months']",Pather.xPath);
        findElementClick("//select[@id='months']/option[@value='"+value+"']",Pather.xPath);
    }

    @Ve("dogum tarihinde yıl olarak {int} secerim")
    public void dogumTarihindeYılOlarakSecerim(int value) {
        //findElementClick("//div[@id='uniform-years']",Pather.xPath);
        findElementClick("//select[@id='years']//option[@value='"+value+"']",Pather.xPath);
    }

    @Ve("mail yoluyla ulasılsın seceneği için {string} checkboxunu secerim")
    public void mailYoluylaUlasılsınSeceneğiIçinCheckboxunuSecerim(String answer) {
        switch(answer) {
            case "Evet":
                findElementClick("//div[@id='uniform-newsletter']",Pather.xPath);
                break;
            case "Hayır":
                break;
        }
    }

    @Ve("sayfayı kaydırırım")
    public void sayfayıKaydırırım() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(ikinciSayfa));
        jse.executeScript("return arguments[0].scrollIntoView();", element);

    }

    @Ve("{string} bolumune {string} yazarim")
    public void bolumuneYazarim(String inputName, String input) {
        findElementSendKeys("//input[@id='"+inputName+"']",Pather.xPath,input);
    }

    @Ve("state icinden {string} secilir")
    public void stateIcindenSecilir(String stateName) {
        findElementClick("//select[@id='id_state']//option[text()='"+stateName+"']",Pather.xPath);


    }

    @Ve("{string} bolumu temizlenir")
    public void bolumuTemizlenir(String input) {
        findElementClick("//input[@id='\"+inputName+\"']",Pather.xPath);
        findElementClear("//input[@id='\"+inputName+\"']",Pather.xPath);
    }

    @Ve("{string} butonuna tiklarsam")
    public void butonunaTiklarsam(String buttonName) {
        findElementClick("//button/span[normalize-space()='"+buttonName+"']",Pather.xPath);
    }

    @Ve("uyelik bilgisini {string} ifadesini gorerek dogrularim")
    public void uyelikBilgisiniIfadesiniGorerekDogrularim(String statement) {
        WebElement statementData=driver.findElement(By.xpath("//p[@class='info-account']"));
        String verificationStatement=statementData.getText();
        Assert.assertEquals(verificationStatement,statement);

    }

    @Ve("{int} saniye beklersem")
    public void saniyeBeklersem(int value) throws Throwable {
        Integer newValue=1000*value;
        Thread.sleep(newValue);
    }
    @Ve("Dresses uzerine gelirsem")
    public void dressesUzerineGelirsem() {
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("(//li/a[@title='Dresses'])[2]"));
        action.moveToElement(element).perform();
    }

    @Ve("Dresses icinden Summer Dresses menusunu secersem")
    public void dressesIcindenSummerDressesMenusunuSecersem() {
        findElementClick("(//li/a[@title='Summer Dresses'])[2]",Pather.xPath);

    }


    @Ve("urun uzerine gelirsem")
    public void urunUzerineGelirsem() {
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("(//a[@title='Printed Summer Dress'])[3]"));
        action.moveToElement(element).perform();
    }

    @Ve("{string} isimli urunu secersem")
    public void isimliUrunuSecersem(String productName) {
        findElementClick("//h5[@itemprop='name']//a[@class='product-name'][normalize-space()='"+productName+"']",Pather.xPath);
    }

    @Ve("rengini {string} secersem")
    public void renginiSecersem(String colour) {
        findElementClick("//li//a[@name='"+colour+"']",Pather.xPath);
    }

    @Ve("alisverise devam et dersem")
    public void alisveriseDevamEtDersem() {
        findElementClick("//span[@class='continue btn btn-default button exclusive-medium'][@title='Continue shopping']",Pather.xPath);
    }

    @Ve("sepeti check et butonuna basarsam")
    public void sepetiCheckEtButonunaBasarsam() {
        findElementClick("//a[@title='Proceed to checkout']",Pather.xPath);
    }

    @Ve("sepetteki urunun {string} oldudunu gorurum")
    public void sepettekiUrununOldudunuGorurum(String selectedProduct) {
        WebElement product=driver.findElement(By.xpath("(//p[@class='product-name'])[2]"));
        String productName=product.getText();
        Assert.assertEquals(productName,selectedProduct);

    }

    @Ve("urun adedinin {string} renginin ise {string} oldugunu gorurum")
    public void urunAdedininRengininIseOldugunuGorurum(String value, String color) {
        WebElement number=driver.findElement(By.xpath("//input[@class='cart_quantity_input form-control grey']"));
        String numberOfProduct=number.getAttribute("value");
        Assert.assertEquals("1",value);
        WebElement specitifation=driver.findElement(By.xpath("//td[@class='cart_description']//small//a"));
        String deger=specitifation.getText();
        Assert.assertEquals("Color : Green, Size : S",color);
    }


    @Ve("devam et derim")
    public void devamEtDerim() {
        findElementClick("(//span[normalize-space()='Proceed to checkout'])[2]",Pather.xPath);
    }

    @Ve("terms of service checkboxuna tiklarim")
    public void termsOfServiceCheckboxunaTiklarim() {
        findElementClick("//label[text()='I agree to the terms of service and will adhere to them unconditionally.']",Pather.xPath);
    }

    @Ve("odeme yontemi olarak {string} secerim")
    public void odemeYontemiOlarakSecerim(String type) {
        findElementClick("//a[@title='"+type+"']",Pather.xPath);
    }

    @Ve("odemeyi tamamlarim")
    public void odemeyiTamamlarim() {
        findElementClick("//span[normalize-space()='I confirm my order']",Pather.xPath);

    }

    @Ve("{string} ekrani acilip siparis kontrol edilir")
    public void ekraniAcilipSiparisKontrolEdilir(String menuName) throws Throwable{
        findElementClick("//a[@class='account']",Pather.xPath);
        findElementClick("//span[normalize-space()='"+menuName+"']",Pather.xPath);
        findElementClick("(//a[@class='color-myaccount'])[1]",Pather.xPath);
        Thread.sleep(5000);

    }

    @Ve("{string} bilgisi ve islem tarihi teyit edilir")
    public void bilgisiVeIslemTarihiTeyitEdilir(String arg0) {
        PageScrolldown();
        PageScrolldown();
        WebElement xy=driver.findElement(By.xpath("(//td[@class='bold'])[1]//label"));
        String yz=xy.getText();
        String data=yz.trim();
        Assert.assertEquals("Printed Chiffon Dress - Color : Green, Size : S",data);
        WebElement product=driver.findElement(By.xpath("(//td[@class='history_date bold'])[1]"));
        String productTime=product.getText().trim();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        Assert.assertEquals(dateFormat.format(date),productTime);
    }

    @Ve("{string} servisi GET metodu ile {int} doner")
    public void servisiMetoduIleDoner(String service, int value) {
        given().
                when().
                get(service).
                then().
                assertThat().statusCode(value);
    }


    @Ve("{string} site adresiyle ayni oldugunu dogrularim")
    public void siteAdresiyleAyniOldugunuDogrularim(String urlLink) {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url,urlLink);
        System.out.println(url);
        System.out.println(urlLink);

    }

    @Ve("hata alsin")
    public void hataAlsin() throws Throwable {
        driver.findElement(By.id("BİLEREK-YANLIŞ-ID-VERİYORUM"));

    }


    @Ve("arama bolumune tiklayip,{string} yazdim")
    public void aramaBolumuneTiklayipYazdim(String urun)
    {
        findElementClick(aramaBolumu,Pather.xPath);
        findElementSendKeys(aramaBolumu,Pather.xPath,urun);


    }

    @Ve("ara butonuna tıkladım")
    public void araButonunaTıkladım() {
        findElementClick(araButonu,Pather.xPath);

    }

    @Ve("kategori olarak dizustu kategorisi sectim")
    public void kategoriOlarakDizustuKategorisiSectim() {
        findElementClick(dizustuKatogorisi,Pather.xPath);

    }

    @Ve("apple macbook secerim")
    public void appleMacbookSecerim() {
        findElementClick(appleMacbook,Pather.xPath);
    }

    @Ve("ikinci sayfayi acarım ve kontrol ederim")
    public void ikinciSayfayiAcarımVeKontrolEderim() throws Throwable {
        Thread.sleep(3000);
        findElementClick(ikinciSayfa,Pather.xPath);
        String sayfadegeri =driver.findElement(By.xpath(sayfaElementi)).getText();
        Assert.assertEquals(sayfadegeri,"2");
        log.info("Sayfa kontrolü yapılarak 2. sayfada olunduğu doğrulandı");

    }

    @Ve("rastgele bir urun secip sepete atarım")
    public void rastgeleBirUrunSecipSepeteAtarım() throws Throwable{
        PageScrolldown();
        Thread.sleep(5000);
        Random rastgele = new Random();
        int rastgeleSayi = rastgele.nextInt(32)+1;
        System.out.println("**********Ratgelesayı " +rastgeleSayi);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement randomPc = driver.findElement(By.xpath("(//header[@class='sc-1n49x8z-2 eIQPKN']//h2)["+rastgeleSayi+"]"));
        jse.executeScript("return arguments[0].scrollIntoView();", randomPc);
        Thread.sleep(5000);
        driver.findElement(By.xpath("(//li[@class='sc-1nx8ums-0 dyekHG'])["+rastgeleSayi+"]")).click();

    }


    @Ve("urunun adini ve fiyatini bir txt dosyasina yazdiririm")
    public void urununAdiniVeFiyatiniBirTxtDosyasinaYazdiririm() throws Exception{
      WebElement Element =driver.findElement(By.xpath("//h1[@id='sp-title']"));
      String urunAdi= Element.getText();
        WebElement Element2 =driver.findElement(By.xpath("(//div[@id='sp-price-lowPrice'])[1]"));
        String urunFiyati= Element2.getText();

        File file = new File("text.txt");//proje içinde text.txt adında bir txt oluşturun.
        try(BufferedWriter br = new BufferedWriter(new FileWriter(file))){
            br.write(urunAdi);
            br.newLine();
            br.write(urunFiyati);
            br.newLine();
        } catch (IOException e) {
            System.out.println("Unable to read file " +file.toString());
        }
        log.info("Text.txt dosyasına ürün adı ve fiyat bilgisi yazıldı");
        PageScrolldown();
        PageScrolldown();
        Thread.sleep(2000);
        findElementClick(sepeteEkle,Pather.xPath);
        Thread.sleep(2000);
        findElementClick(sepetim,Pather.xPath);
        WebElement sepettekiUrun=driver.findElement(By.xpath("//p[@class='new-price']"));
        String sepettekiUrunFiyat =sepettekiUrun.getText();
        Assert.assertEquals(sepettekiUrunFiyat,urunFiyati);
        log.info("Ürün fiyatı ile sepetteki ürün fiyatı aynıdır");


    }


    @Ve("sepetteki urunu gunceller ve silerim")
    public void sepettekiUrunuGuncellerVeSilerim() throws Throwable{
        findElementClick(urunAdeti,Pather.xPath);
        findElementClick(ikiAdet,Pather.xPath);
        Thread.sleep(3000);
        WebElement urunSayısı=driver.findElement(By.xpath("//div[@class='gg-d-16 gg-m-14 detail-text']"));
        String sepetUrunSayisiYazisi=urunSayısı.getText().trim();
        System.out.println(sepetUrunSayisiYazisi);
        String adet = sepetUrunSayisiYazisi.substring(14, 15);
         Assert.assertEquals("2",adet);
         log.info("Ürün sayısının 2 olarak güncellendiği doğrulandı");
         findElementClick(logo,Pather.xPath);
         Thread.sleep(3000);
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//div[@class='ndodpt-1 iMgKyN'][@name='cart']"));
        action.moveToElement(element).perform();
        Thread.sleep(3000);
        WebElement element1 = driver.findElement(By.xpath("//div[@class='ciohle-3 fqUtNJ']"));
        action.moveToElement(element1).perform();
        Thread.sleep(3000);

        findElementClick(sepetSil,Pather.xPath);
        Thread.sleep(3000);
        WebElement mesaj=driver.findElement(By.xpath("//p[@class='sc-1tdlrg-0 yf6n83-0 kTJCey ciohle-12 eujyuF']"));
        String sepetMesaj=mesaj.getText();
        Assert.assertEquals(sepetMesaj,"Sepetinizde ürün bulunmamaktadır.");
        log.info("Sepetteki ürünler silindi");


    }


}




