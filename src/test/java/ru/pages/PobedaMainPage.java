package ru.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;

//Сайт компании Победа
public class PobedaMainPage extends BasePage {

    //Задание №1. Page Object. Всплывающее окно
    /*@FindBy(xpath = "//title[contains(text(), 'Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//img[contains(@alt, '«Авиакомпания «Победа», Группа «Аэрофлот»') or contains(@src, 'logo')]")
    private WebElement logo;*/
    private SelenideElement logo = $(byXpath("//img[contains(@alt, '«Авиакомпания «Победа», Группа «Аэрофлот»') or contains(@src, 'logo')]"));

    //driver.findElement(By.xpath("//button[@data-idx='9']")).click();
    /*@FindBy(xpath = "//a[contains(text(), 'Информация')]")
    private WebElement informationLink;*/
    private SelenideElement informationLink = $(byXpath("//a[contains(text(), 'Информация')]"));

    /*//div[contains(text(), 'Подготовка к полёту')]*/
    /*//div[@id='collapse-pan=:r4:']//a[text()='Подготовка к полёту']*/
    /*@class='dp-1719q95-root-root'*/
    /*@FindBy(xpath = "//a[@class='dp-17i9q9s-root-root' and text()='Подготовка к полёту']")
    private WebElement flightPreparationHeader;*/
    private SelenideElement flightPreparationHeader = $(byXpath("//a[@class='dp-17i9q9s-root-root' and text()='Подготовка к полёту']"));

    /*//div[contains(text(), 'Полезная информация')]*/
    /*@class='dp-1719q95-root-root'*/
    /*@FindBy(xpath = "//a[@class='dp-17i9q9s-root-root' and text()='Полезная информация']")
    private WebElement usefulInfoHeader;*/
    private SelenideElement usefulInfoHeader = $(byXpath("//a[@class='dp-17i9q9s-root-root' and text()='Полезная информация']"));

    /*//div[contains(text(), 'О компании*/
    /*@class='dp-1719q95-root-root'*/
    /*@FindBy(xpath = "//a[@class='dp-17i9q9s-root-root' and text()='О компании']")
    private WebElement aboutCompanyHeader;*/
    private SelenideElement aboutCompanyHeader = $(byXpath("//a[@class='dp-17i9q9s-root-root' and text()='О компании']"));

    //загрузка главной страницы
    /*public void verifyMainPageLoaded() {
        wait.until(ExpectedConditions.titleContains("Авиакомпания «Победа»"));
        assertTrue(logo.isDisplayed(), "Логотип Победы отобразился");
    }

    //наведите курсор на всплывающее окно «Информация»
    public void hoverOverInformationAndVerifyPopup() {
        Actions actions = new Actions(driver);
        actions.moveToElement(informationLink).perform();

        wait.until(ExpectedConditions.visibilityOf(flightPreparationHeader));
        assertTrue(flightPreparationHeader.isDisplayed());
        assertTrue(usefulInfoHeader.isDisplayed());
        assertTrue(aboutCompanyHeader.isDisplayed());
    }

    //Задание №2. Page Object. Инициирование поиска
    @FindBy(xpath = "//input[@placeholder='Откуда']")
    private WebElement departureInput;*/
    private SelenideElement departureInput = $(byXpath("//input[@placeholder='Откуда']"));

    /*@FindBy(xpath = "//input[@placeholder='Куда']")
    private WebElement arrivalInput;*/
    private SelenideElement arrivalInput = $(byXpath("//input[@placeholder='Куда']"));

    /*//div[contains(@class, 'trip-dates')]//input[@placeholder='Туда']*/
    /*@FindBy(xpath = "//input[@placeholder='Туда']")
    private WebElement departureDateInput;*/
    private SelenideElement departureDateInput = $(byXpath("//input[@placeholder='Туда']"));

    /*//div[contains(@class, 'trip-dates')]//input[@placeholder='Обратно']*/
    /*@FindBy(xpath = "//input[@placeholder='Обратно']")
    private WebElement returnDateInput;*/
    private SelenideElement returnDateInput = $(byXpath("//input[@placeholder='Обратно']"));

    /*@FindBy(xpath = "//button[.//span[text()='Поиск']]")
    private WebElement searchButton;*/
    private SelenideElement searchButton = $(byXpath("//button[.//span[text()='Поиск']]"));

    /*//div[contains(@class, 'error')]//input[@placeholder='Туда']*/
    /*@FindBy(xpath = "//input[@placeholder='Туда' and @data-empty='true']")
    private WebElement errorDepartureDate;*/
    private SelenideElement errorDepartureDate = $(byXpath("//input[@placeholder='Туда' and @data-empty='true']"));

    public void verifyMainPageLoaded() {
        logo.shouldBe(visible);
    }

    public void hoverOverInformationAndVerifyPopup() {
        informationLink.shouldBe(visible).hover();

        flightPreparationHeader.shouldBe(visible);
        usefulInfoHeader.shouldBe(visible);
        aboutCompanyHeader.shouldBe(visible);
    }

    //прокрутить, чтобы найти (заблокирован / проверить)
    public void scrollToSearchBlockAndVerify() {
        //скролл к блоку поиска
        /*((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", departureInput);

        wait.until(ExpectedConditions.visibilityOf(departureInput));
        assertTrue(departureInput.isDisplayed());
        assertTrue(arrivalInput.isDisplayed());
        assertTrue(departureDateInput.isDisplayed());
        assertTrue(returnDateInput.isDisplayed());*/
        departureInput.scrollTo().shouldBe(visible);
        arrivalInput.shouldBe(visible);
        departureDateInput.shouldBe(visible);
        returnDateInput.shouldBe(visible);
    }

    //заполнить критерии поиска / проверьть ошибку
    public void fillSearchCriteriaAndVerifyError() {
        /*departureInput.clear();
        departureInput.sendKeys("Москва\n");

        arrivalInput.clear();
        arrivalInput.sendKeys("Санкт-Петербург\n");

        searchButton.click();

        assertTrue(errorDepartureDate.isDisplayed());*/
        departureInput.shouldBe(visible).clear();
        departureInput.setValue("Москва").pressEnter();

        arrivalInput.shouldBe(visible).clear();
        arrivalInput.setValue("Санкт-Петербург").pressEnter();

        searchButton.shouldBe(visible).click();

        errorDepartureDate.shouldBe(visible);
    }

    //Задание №3. Page Object. Результаты поиска
    /*@FindBy(xpath = "//button[.//span[text()='Управление бронированием']]")
    private WebElement manageBookingLink;*/
    private SelenideElement manageBookingLink = $(byXpath("//button[.//span[text()='Управление бронированием']]"));

    /*public PobedaMainPage(WebDriver driver) {
        super(driver);
    }*/

    public void openPobedaAero() {
        //driver.get("https://www.pobeda.aero");
        open("https://www.pobeda.aero");
    }

    //нажать на //button[.//span[text()='Управление бронированием']]
    public void clickManageBooking() {
        /*((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", manageBookingLink);
        manageBookingLink.click();*/
        manageBookingLink.scrollTo().click();
    }
}