package ru.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;

//Сайт компании Победа
public class PobedaMainPage extends BasePage {

    //Задание №1. Page Object. Всплывающее окно
    @FindBy(xpath = "//title[contains(text(), 'Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//img[contains(@alt, '«Авиакомпания «Победа», Группа «Аэрофлот»') or contains(@src, 'logo')]")
    private WebElement logo;

    //driver.findElement(By.xpath("//button[@data-idx='9']")).click();
    @FindBy(xpath = "//a[contains(text(), 'Информация')]")
    private WebElement informationLink;

    /*//div[contains(text(), 'Подготовка к полёту')]*/
    /*//div[@id='collapse-pan=:r4:']//a[text()='Подготовка к полёту']*/
    /*@class='dp-1719q95-root-root'*/
    @FindBy(xpath = "//a[@class='dp-17i9q9s-root-root' and text()='Подготовка к полёту']")
    private WebElement flightPreparationHeader;

    /*//div[contains(text(), 'Полезная информация')]*/
    /*@class='dp-1719q95-root-root'*/
    @FindBy(xpath = "//a[@class='dp-17i9q9s-root-root' and text()='Полезная информация']")
    private WebElement usefulInfoHeader;

    /*//div[contains(text(), 'О компании*/
    /*@class='dp-1719q95-root-root'*/
    @FindBy(xpath = "//a[@class='dp-17i9q9s-root-root' and text()='О компании']")
    private WebElement aboutCompanyHeader;

    //загрузка главной страницы
    public void verifyMainPageLoaded() {
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
    private WebElement departureInput;

    @FindBy(xpath = "//input[@placeholder='Куда']")
    private WebElement arrivalInput;

    /*//div[contains(@class, 'trip-dates')]//input[@placeholder='Туда']*/
    @FindBy(xpath = "//input[@placeholder='Туда']")
    private WebElement departureDateInput;

    /*//div[contains(@class, 'trip-dates')]//input[@placeholder='Обратно']*/
    @FindBy(xpath = "//input[@placeholder='Обратно']")
    private WebElement returnDateInput;

    @FindBy(xpath = "//button[.//span[text()='Поиск']]")
    private WebElement searchButton;

    /*//div[contains(@class, 'error')]//input[@placeholder='Туда']*/
    @FindBy(xpath = "//input[@placeholder='Туда' and @data-empty='true']")
    private WebElement errorDepartureDate;

    //прокрутить, чтобы найти (заблокирован / проверить)
    public void scrollToSearchBlockAndVerify() {
        //скролл к блоку поиска
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", departureInput);

        wait.until(ExpectedConditions.visibilityOf(departureInput));
        assertTrue(departureInput.isDisplayed());
        assertTrue(arrivalInput.isDisplayed());
        assertTrue(departureDateInput.isDisplayed());
        assertTrue(returnDateInput.isDisplayed());
    }

    //заполнить критерии поиска / проверьть ошибку
    public void fillSearchCriteriaAndVerifyError() {
        departureInput.clear();
        departureInput.sendKeys("Москва\n");

        arrivalInput.clear();
        arrivalInput.sendKeys("Санкт-Петербург\n");

        searchButton.click();

        assertTrue(errorDepartureDate.isDisplayed());
    }

    //Задание №3. Page Object. Результаты поиска
    @FindBy(xpath = "//button[.//span[text()='Управление бронированием']]")
    private WebElement manageBookingLink;

    public PobedaMainPage(WebDriver driver) {
        super(driver);
    }

    public void openPobedaAero() {
        driver.get("https://www.pobeda.aero");
    }

    //нажать на //button[.//span[text()='Управление бронированием']]
    public void clickManageBooking() {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", manageBookingLink);
        manageBookingLink.click();
    }
}