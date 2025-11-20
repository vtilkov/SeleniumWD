package ru.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Set;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.junit.jupiter.api.Assertions.*;

//бронирование
public class ManageBookingPage extends BasePage {

    /*@FindBy(xpath = "//input[@placeholder='Номер бронирования или билета']")
    private WebElement orderNumberInput;*/
    private SelenideElement orderNumberInput = $(byXpath("//input[@placeholder='Номер бронирования или билета']"));

    /*@FindBy(xpath = "//input[@placeholder='Фамилия клиента']")
    private WebElement lastNameInput;*/
    private SelenideElement lastNameInput = $(byXpath("//input[@placeholder='Фамилия клиента']"));

    /*@FindBy(xpath = "//button[.//span[text()='Поиск']]")
    private WebElement searchButton;*/
    private SelenideElement searchButton = $(byXpath("//button[.//span[text()='Поиск']]"));

    /*@FindBy(xpath = "//div[contains(@class, 'message_error') and contains(text(), 'Заказ с указанными параметрами не найден')]")
    private WebElement errorMessage;*/
    private SelenideElement errorMessage = $(byXpath("//div[contains(@class, 'message_error') and contains(text(), 'Заказ с указанными параметрами не найден')]"));

    /*public ManageBookingPage(WebDriver driver) {
        super(driver);
    }*/

    public void verifyManageBookingPageLoaded() {
        /*wait.until(ExpectedConditions.visibilityOf(orderNumberInput));
        assertTrue(orderNumberInput.isDisplayed(), "Номер заказа");
        assertTrue(lastNameInput.isDisplayed(), "Фамилия клиента");
        assertTrue(searchButton.isDisplayed(), "Поиск");*/
        orderNumberInput.shouldBe(visible);
        lastNameInput.shouldBe(visible);
        searchButton.shouldBe(visible);
    }

    public void searchBookingWithInvalidData() {
        /*orderNumberInput.sendKeys("XXXXXX");
        lastNameInput.sendKeys("Qwerty");
        searchButton.click();*/
        orderNumberInput.shouldBe(visible).setValue("XXXXXX");
        lastNameInput.shouldBe(visible).setValue("Qwerty");
        searchButton.shouldBe(visible).click();

        //Ждем пока новая вкладка откроется и перейдем на нее
        /*String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));*/
        switchTo().window(1);

        //переклюимся на новую вкладку
        /*Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }*/
        errorMessage.shouldBe(visible);

        //Закрываем вкладку
        /*driver.close();
        driver.switchTo().window(originalWindow);*/
        closeWindow();
        switchTo().window(0);
    }
}