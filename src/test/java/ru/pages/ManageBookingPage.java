package ru.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

//бронирование
public class ManageBookingPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder='Номер бронирования или билета']")
    private WebElement orderNumberInput;

    @FindBy(xpath = "//input[@placeholder='Фамилия клиента']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//button[.//span[text()='Поиск']]")
    private WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class, 'message_error') and contains(text(), 'Заказ с указанными параметрами не найден')]")
    private WebElement errorMessage;

    public ManageBookingPage(WebDriver driver) {
        super(driver);
    }

    public void verifyManageBookingPageLoaded() {
        wait.until(ExpectedConditions.visibilityOf(orderNumberInput));
        assertTrue(orderNumberInput.isDisplayed(), "Номер заказа");
        assertTrue(lastNameInput.isDisplayed(), "Фамилия клиента");
        assertTrue(searchButton.isDisplayed(), "Поиск");
    }

    public void searchBookingWithInvalidData() {
        orderNumberInput.sendKeys("XXXXXX");
        lastNameInput.sendKeys("Qwerty");
        searchButton.click();

        //Ждем пока новая вкладка откроется
        String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        //переклюимся на новую вкладку
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        assertTrue(errorMessage.isDisplayed());

        //Закрываем вкладку
        driver.close();
        driver.switchTo().window(originalWindow);
    }
}