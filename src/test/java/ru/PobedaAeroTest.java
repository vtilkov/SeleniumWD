package ru;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.pages.PobedaMainPage;
import ru.pages.ManageBookingPage;
import java.time.Duration;

public class PobedaAeroTest {

    private WebDriver driver;

    //сестрички странички
    //private GooglePage googlePage;
    private PobedaMainPage pobedaMainPage;
    //private PobedaEnglishPage pobedaEnglishPage;
    private ManageBookingPage manageBookingPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\_openjdk\\demo\\SeleniumWD\\SeleniumWD\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();

        //неявные ожидания
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(130));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
        driver.manage().window().maximize();

        //инициализауия страниц
        //googlePage = new GooglePage(driver);
        pobedaMainPage = new PobedaMainPage(driver);
        //pobedaEnglishPage = new PobedaEnglishPage(driver);
        manageBookingPage = new ManageBookingPage(driver);
    }

    /*//поиск сайта компании Победа
    @Test
    public void testSearchPobedaInGoogle() {
        googlePage.openGoogle();
        googlePage.searchFor("Сайт компании Победа");
        googlePage.clickFirstSearchResult();
    }*/

    /*//баннер Калининград
    @Test
    public void testKaliningradBanner() {
        googlePage.openGoogle();
        googlePage.searchFor("Сайт компании Победа");
        googlePage.clickFirstSearchResult();
        pobedaMainPage.waitForKaliningrad();
    }*/

    /*//тексты Ticket search, Online check-in, Manage my booking
    @Test
    public void testLanguageSwitch() {
        googlePage.openGoogle();
        googlePage.searchFor("Сайт компании Победа");
        googlePage.clickFirstSearchResult();
        pobedaMainPage.waitForKaliningrad();
        pobedaMainPage.switchToEnglish();
        pobedaEnglishPage.verifyEnglishTextsAreDisplayed();
    }*/

    //Задание №1. Page Object. Всплывающее окно
    @Test
    public void testInformationPopup() {
        pobedaMainPage.openPobedaAero();
        pobedaMainPage.verifyMainPageLoaded();
        pobedaMainPage.hoverOverInformationAndVerifyPopup();
    }

    //Задание №2. Page Object. Инициирование поиска
    @Test
    public void testTicketSearch() {
        pobedaMainPage.openPobedaAero();
        pobedaMainPage.verifyMainPageLoaded();
        pobedaMainPage.scrollToSearchBlockAndVerify();
        pobedaMainPage.fillSearchCriteriaAndVerifyError();
    }

    //Задание №3. Page Object. Результаты поиска
    @Test
    public void testManageBooking() {
        pobedaMainPage.openPobedaAero();
        pobedaMainPage.verifyMainPageLoaded();
        pobedaMainPage.clickManageBooking();
        manageBookingPage.verifyManageBookingPageLoaded();
        manageBookingPage.searchBookingWithInvalidData();
    }

    @AfterEach
    public void tearDown() {
        if (driver !=null) {driver.quit();}
    }
}