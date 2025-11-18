package ru.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

//Сайт компании Победа (EN)
public class PobedaEnglishPage {

    protected WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//span[text()='Ticket search' and position()=2]")
    private WebElement ticketSearchText;

    @FindBy(xpath = "//span[text()='Online check-in' and position()=2]")
    private WebElement onlineCheckInText;

    @FindBy(xpath = "//span[text()='Manage my booking' and position()=2]")
    private WebElement manageBookingText;

    public PobedaEnglishPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(130));
        PageFactory.initElements(driver, this);
    }

    //убедитя, что отображаются тексты на английском языке
    public void verifyEnglishTextsAreDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(ticketSearchText));
        wait.until(ExpectedConditions.visibilityOf(onlineCheckInText));
        wait.until(ExpectedConditions.visibilityOf(manageBookingText));

        System.out.println("На главной странице отображаются тексты Ticket search, Online check-in, Manage my booking");
    }
}