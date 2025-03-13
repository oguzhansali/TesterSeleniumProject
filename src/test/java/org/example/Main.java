package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;




public class Main {
    WebDriver driver;
    WebDriverWait wait;

    // Menü öğelerinin XPath seçicileri
    private By companyMenu = By.xpath(  "//a[contains(text(),'Company')]");
    private By careersMenu = By.xpath("//a[contains(text(),'Careers')]");

    // Kariyer sayfasındaki belirli bölümler
    private  By teamsBlock = By.id("career-find-our-calling");
    private By locationsBlock = By.id("location-slider");
    private  By lifeAtInsiderBlock = By.xpath("//div[@data-id='a8e7b90']");

    // Açık pozisyonlar için buton linki
    private By buttonLink = By.linkText("https://useinsider.com/careers/open-positions/?department=qualityassurance");


    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();// ChromeDriver başlatılır
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // WebDriverWait tanımlanır
    }

    @Test
    public void homePageIsAvailable() {
        driver.navigate().to("https://useinsider.com/");// Anasayfaya gidilir

        // Sayfanın doğru şekilde yüklendiğini kontrol et
        wait.until(ExpectedConditions.titleIs("#1 Leader in Individualized, Cross-Channel CX — Insider"));

        String title = driver.getTitle();
        assertEquals("#1 Leader in Individualized, Cross-Channel CX — Insider", title);
    }

    @Test
    public void careerPageIsAvailable(){
        driver.navigate().to("https://useinsider.com/"); // Anasayfaya gidilir

        driver.navigate().to("https://useinsider.com/careers/");// Kariyer sayfasına gidilir

        // Kariyer sayfasında belirli öğelerin varlığını kontrol et
        driver.findElement(By.id("location-slider"));
        driver.findElement(By.className("elementor-widget-container"));
        driver.findElement(By.className("career-load-more"));


    }

    @Test
    public  void QAJobs (){
        // QA iş ilanları sayfasına gidilir
        driver.navigate().to("https://useinsider.com/careers/quality-assurance/");

        // URL'nin doğru olup olmadığını kontrol et
        wait.until(ExpectedConditions.urlContains("quality-assurance"));

        // Açık QA pozisyonlarına gidilir
        driver.navigate().to("https://useinsider.com/careers/open-positions/?department=qualityassurance");

        // Sayfanın tamamen yüklenmesini bekle
        wait.until(ExpectedConditions.urlContains("open-positions")); // URL'nin "open-positions" içermesini bekle

        // Şehir filtresi için dropdown öğesi bulunur
        WebElement downBox =driver.findElement(By.id("select2-filter-by-location-container"));
        downBox.sendKeys("Istanbul, Turkiye");// İstanbul seçilebilir


        // Departman filtresi öğesi bulunur
        driver.findElement(By.id("select2-filter-by-department-container"));
        downBox.sendKeys("Quality Assurance");// Departman olarak Quality Assurance seçilir

    }

    @AfterEach
    public void teardown() {
        driver.quit();// Tarayıcı kapatılır
    }

}